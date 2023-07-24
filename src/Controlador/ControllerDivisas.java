package Controlador;

import Clases.Conversor;
import Clases.ConversorDivisas;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import org.json.JSONObject;

public class ControllerDivisas implements ActionListener {

    // Instancias de las clases
    private ConversorDivisas cd;
    private FrmConvertor frmConvertor;

    // Arrays de monedas
    private String[] divisas = {"seleccionar", "PEN - Sol peruano", "USD - Dólar estadounidense", "EUR - Euro", "GPB - Libra Esterlina", "JPY - Yen Japonés"};

    public ControllerDivisas(ConversorDivisas cd, FrmConvertor frmConvertor) {
        this.cd = cd;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarDivisas();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboDivisaBase.addActionListener(this);
        frmConvertor.cboDivisaCambio.addActionListener(this);
        frmConvertor.btnConvertirDivisa.addActionListener(this);
        frmConvertor.btnLimpiarDivisas.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarDivisas() {
        for (int i = 0; i < divisas.length; i++) {
            frmConvertor.cboDivisaBase.addItem(divisas[i]);
            frmConvertor.cboDivisaCambio.addItem(divisas[i]);
        }
        frmConvertor.cboDivisaBase.setModel(new DefaultComboBoxModel(divisas));
        frmConvertor.cboDivisaCambio.setModel(new DefaultComboBoxModel(divisas));
    }

    // Metodo para realizar la conversion de la divisa
    private void convertirDivisa() {
        try {
            double moneda = Double.parseDouble(frmConvertor.txtDivisaBase.getText());
            String monedaBase = cd.codigoISO(frmConvertor.cboDivisaBase.getSelectedItem().toString());
            String monedaCambio = cd.codigoISO(frmConvertor.cboDivisaCambio.getSelectedItem().toString());

            //  dirección web de la API de Open Exchange Rates que proporciona los datos de tasa de cambio en formato JSON
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=f9f7038adb5642d89ea7123623b822ac");
            
            // openStream(): abrir una conexión a la URL 
            // Scanner:leer los datos de esa conexión.
            Scanner urlScanner = new Scanner(url.openStream());
            String json = urlScanner.useDelimiter("\\Z").next();
            urlScanner.close();
            //  Acceso a los valores dek JSON
            JSONObject obj = new JSONObject(json);
            double tasaOrigen = obj.getJSONObject("rates").getDouble(monedaBase);
            double tasaDestino = obj.getJSONObject("rates").getDouble(monedaCambio);
            // Calculo de la conversion
            double resultado = moneda * tasaDestino / tasaOrigen;
            frmConvertor.txtDivisaCambio.setText(String.valueOf(String.format("%.4f", resultado)));
            frmConvertor.txtResultado.setText(moneda + " " + monedaBase + " = " + String.format("%.2f", resultado) + " " + monedaCambio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo para deshabilitar inputs
    private void habilitar() {
        frmConvertor.txtDivisaBase.setEnabled(true);
        frmConvertor.cboDivisaCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtDivisaBase.setEnabled(false);
        frmConvertor.cboDivisaCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboDivisaBase.setSelectedItem("seleccionar");
        frmConvertor.cboDivisaCambio.setSelectedItem("seleccionar");
        frmConvertor.txtDivisaBase.setText("");
        frmConvertor.txtDivisaCambio.setText("");
        frmConvertor.txtResultado.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboDivisaBase)) {
            if (!frmConvertor.cboDivisaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtDivisaBase.requestFocus();
            } else {
                frmConvertor.txtDivisaBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirDivisa)) {
            if (!frmConvertor.cboDivisaCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirDivisa();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarDivisas)) {
            limpiarInputs();
        }

    }

}
