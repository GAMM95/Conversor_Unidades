package Controlador;

import Clases.ConversorDivisas;
import Vista.FrmConvertor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import org.json.JSONObject;

public class ControllerDivisas implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private ConversorDivisas cd;
    private FrmConvertor frmConvertor;

    // Arrays de monedas
    private final String[] divisas = {"seleccionar", "PEN - Sol peruano", "USD - Dólar estadounidense", "EUR - Euro", "BRL - Real brasilero", "ARS - Peso argentino", "CLP - Peso chileno", "COP - Peso colombiano", "BOB - Bolíviano", "PYG - Guaraní paraguayo", "UYU - Peso uruguayo", "VND - Bolívar venezolano", "MXN - Peso mexicano", "JPY - Yen japonés", "GBP - Libra esterlina", "KRW - Won surcoreano"};

    public ControllerDivisas(ConversorDivisas cd, FrmConvertor frmConvertor) {
        this.cd = cd;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarDivisas();
        inhabilitar();
    }

    // Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboDivisaBase.addActionListener(this);
        frmConvertor.cboDivisaCambio.addActionListener(this);
        frmConvertor.btnConvertirDivisa.addActionListener(this);
        frmConvertor.btnLimpiarDivisas.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtDivisaBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboDivisaBase.addMouseListener(this);
        frmConvertor.cboDivisaCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarDivisas() {
        for (String divisa : divisas) {
            frmConvertor.cboDivisaBase.addItem(divisa);
            frmConvertor.cboDivisaCambio.addItem(divisa);
        }
        frmConvertor.cboDivisaBase.setModel(new DefaultComboBoxModel(divisas));
        frmConvertor.cboDivisaCambio.setModel(new DefaultComboBoxModel(divisas));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboDivisaBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultado.setText("Seleccione una moneda base");
            frmConvertor.txtResultado.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultado.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        } else if (frmConvertor.txtDivisaBase.getText().trim().equals("")) {
            frmConvertor.txtResultado.setText("Ingrese valor a convertir");
            frmConvertor.txtResultado.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultado.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtDivisaBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboDivisaCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultado.setText("Seleccione una moneda de cambio");
            frmConvertor.txtResultado.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultado.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
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
            frmConvertor.txtResultado.setForeground(Color.decode("#023e8a"));
            frmConvertor.txtResultado.setFont(new Font("Dialog", Font.BOLD, 16));
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboDivisaBase)) {
            if (!frmConvertor.cboDivisaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtDivisaBase.requestFocus();
            } else {
                frmConvertor.txtDivisaBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboDivisaCambio)) {
            if (frmConvertor.cboDivisaCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtDivisaCambio.setText("");
            } else {
                convertirDivisa();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirDivisa)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirDivisa();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarDivisas)) {
            limpiarInputs();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtDivisaBase)) {
            frmConvertor.txtResultado.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboDivisaBase) || e.getSource().equals(frmConvertor.cboDivisaCambio)) {
            frmConvertor.txtResultado.setText("");
        }
    }

    // Eventos no utilizados
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
