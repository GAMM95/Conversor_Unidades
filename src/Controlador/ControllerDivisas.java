package Controlador;

import Clases.Conversor;
import Clases.ConversorDivisas;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class ControllerDivisas implements ActionListener {

    // Instancias de las clases
    private ConversorDivisas cd;
    private FrmConvertor frmConvertor;

    // Arrays de divisas
    private String[] divisas = {"seleccionar", "PEN - Sol peruano", "USD - Dólar estadounidense", "EUR - Euro", "GPB - Libra Esterlina", "JPY - Yen Japonés"};  //  Array de categorias de cargos

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
            if (frmConvertor.txtDivisaBase.getText().isEmpty() || frmConvertor.cboDivisaCambio.equals("seleccionar")) {
                frmConvertor.txtResultado.setText("Seleccione una moneda");
            } else {
                double moneda = Double.parseDouble(frmConvertor.txtDivisaBase.getText());
                String monedaBase = frmConvertor.cboDivisaBase.getSelectedItem().toString();
                String monedaCambio = frmConvertor.cboDivisaCambio.getSelectedItem().toString();
                Conversor cd = new ConversorDivisas(moneda, monedaBase, monedaCambio);
                double resultado = cd.convertir(moneda, monedaBase, monedaCambio);
                frmConvertor.txtDivisaCambio.setText(String.valueOf(String.format("%.2f", resultado)));

                // Mostrar resultado con codigos ISO de cada moneda
                String monBase = cd.codigoISO(frmConvertor.cboDivisaBase.getSelectedItem().toString());
                String monCambio = cd.codigoISO(frmConvertor.cboDivisaCambio.getSelectedItem().toString());
                frmConvertor.txtResultado.setText(moneda + " " + monBase + " = " + String.format("%.2f", resultado) + " " + monCambio);
            }
        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir el valor a double
            System.out.println("Error en el formato del valor ingresado.");
        } catch (IllegalArgumentException e) {
            // Manejar la excepción si se ingresó una unidad no válida
            System.out.println("Error");
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
