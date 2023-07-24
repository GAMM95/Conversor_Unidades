package Controlador;

import Clases.Conversor;
import Clases.ConversorTemperatura;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class ControllerTemperatura implements ActionListener {

    // Instancias de las clases
    private ConversorTemperatura ct;
    private FrmConvertor frmConvertor;

    // Arrays de temperaturas
    private String[] temperaturas = {"seleccionar", "°C - Celsius", "°F - Fahrenheit", "K - Kelvin", "°R - Rankine"};

    public ControllerTemperatura(ConversorTemperatura ct, FrmConvertor frmConvertor) {
        this.ct = ct;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarTemperaturas();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboTemperaturaBase.addActionListener(this);
        frmConvertor.cboTemperaturaCambio.addActionListener(this);
        frmConvertor.btnConvertirTemperatura.addActionListener(this);
        frmConvertor.btnLimpiarTemperatura.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarTemperaturas() {
        for (int i = 0; i < temperaturas.length; i++) {
            frmConvertor.cboTemperaturaBase.addItem(temperaturas[i]);
            frmConvertor.cboTemperaturaCambio.addItem(temperaturas[i]);
        }
        frmConvertor.cboTemperaturaBase.setModel(new DefaultComboBoxModel(temperaturas));
        frmConvertor.cboTemperaturaCambio.setModel(new DefaultComboBoxModel(temperaturas));
    }

    // Metodo para realizar la conversion de la temperatura
    private void convertirTemperatura() {
        try {
            if (frmConvertor.txtTemperaturaBase.getText().isEmpty()) {
                frmConvertor.txtResultadoTemperatura.setText("Ingrese valor a convertir");
            } else if (frmConvertor.cboTemperaturaCambio.equals("seleccionar")) {
                frmConvertor.txtResultadoTemperatura.setText("Seleccione una moneda de cambio");
            } else {
                double temperatura = Double.parseDouble(frmConvertor.txtTemperaturaBase.getText());
                String temperaturaBase = frmConvertor.cboTemperaturaBase.getSelectedItem().toString();
                String temperaturaCambio = frmConvertor.cboTemperaturaCambio.getSelectedItem().toString();
                Conversor cd = new ConversorTemperatura(temperatura, temperaturaBase, temperaturaCambio);
                double resultado = cd.convertir(temperatura, temperaturaBase, temperaturaCambio);
                frmConvertor.txtTemperaturaCambio.setText(String.valueOf(String.format("%.4f", resultado)));

                // Mostrar resultado con codigos ISO de cada moneda
                String temBase = cd.codigoISO(frmConvertor.cboTemperaturaBase.getSelectedItem().toString());
                String temCambio = cd.codigoISO(frmConvertor.cboTemperaturaCambio.getSelectedItem().toString());
                frmConvertor.txtResultadoTemperatura.setText(temperatura + " " + temBase + " = " + String.format("%.2f", resultado) + " " + temCambio);
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
        frmConvertor.txtTemperaturaBase.setEnabled(true);
        frmConvertor.cboTemperaturaCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtTemperaturaBase.setEnabled(false);
        frmConvertor.cboTemperaturaCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboTemperaturaBase.setSelectedItem("seleccionar");
        frmConvertor.cboTemperaturaCambio.setSelectedItem("seleccionar");
        frmConvertor.txtTemperaturaBase.setText("");
        frmConvertor.txtTemperaturaCambio.setText("");
        frmConvertor.txtResultadoTemperatura.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboTemperaturaBase)) {
            if (!frmConvertor.cboTemperaturaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtTemperaturaBase.requestFocus();
            } else {
                frmConvertor.txtTemperaturaBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirTemperatura)) {
            if (!frmConvertor.cboTemperaturaCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirTemperatura();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarTemperatura)) {
            limpiarInputs();
        }

    }

}
