package Controlador;

import Clases.Conversor;
import Clases.ConversorLongitud;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class ControllerLongitud implements ActionListener {

    // Instancias de las clases
    private ConversorLongitud cl;
    private FrmConvertor frmConvertor;

    // Arrays de unidades de longitud
    private String[] longitud = {"seleccionar", "m - Metro", "mm - Milímetro", "cm - Centímetro", "dm - Decímetro", "dam - Decámetro", "hm - Hectómetro", "km - Kilómetro", "in - Pulgada", "ft - Pie", "yd - Yarda", "mi - Milla"};

    public ControllerLongitud(ConversorLongitud cl, FrmConvertor frmConvertor) {
        this.cl = cl;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarLongitudes();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboLongitudBase.addActionListener(this);
        frmConvertor.cboLongitudCambio.addActionListener(this);
        frmConvertor.btnConvertirLongitud.addActionListener(this);
        frmConvertor.btnLimpiarLongitud.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarLongitudes() {
        for (int i = 0; i < longitud.length; i++) {
            frmConvertor.cboLongitudBase.addItem(longitud[i]);
            frmConvertor.cboLongitudCambio.addItem(longitud[i]);
        }
        frmConvertor.cboLongitudBase.setModel(new DefaultComboBoxModel(longitud));
        frmConvertor.cboLongitudCambio.setModel(new DefaultComboBoxModel(longitud));
    }

    // Metodo para realizar la conversion de longitud
    private void convertirLongitud() {
        try {
            if (frmConvertor.txtLongitudBase.getText().isEmpty()) {
                frmConvertor.txtResultadoLongitud.setText("Ingrese valor a convertir");
            } else if (frmConvertor.cboLongitudCambio.equals("seleccionar")) {
                frmConvertor.txtResultadoLongitud.setText("Seleccione una unidad de longitud de cambio");
            } else {
                double longitud = Double.parseDouble(frmConvertor.txtLongitudBase.getText());
                String longitudBase = frmConvertor.cboLongitudBase.getSelectedItem().toString();
                String longitudCambio = frmConvertor.cboLongitudCambio.getSelectedItem().toString();
                Conversor cd = new ConversorLongitud(longitud, longitudBase, longitudCambio);
                double resultado = cd.convertir(longitud, longitudBase, longitudCambio);
                frmConvertor.txtLongitudCambio.setText(String.valueOf(String.format("%.6f", resultado)));

                // Mostrar resultado con codigos ISO de cada unidad de longitud
                String lonBase = cd.codigoISO(frmConvertor.cboLongitudBase.getSelectedItem().toString());
                String lonCambio = cd.codigoISO(frmConvertor.cboLongitudCambio.getSelectedItem().toString());
                frmConvertor.txtResultadoLongitud.setText(longitud + " " + lonBase + " = " + String.format("%.4f", resultado) + " " + lonCambio);
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
        frmConvertor.txtLongitudBase.setEnabled(true);
        frmConvertor.cboLongitudCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtLongitudBase.setEnabled(false);
        frmConvertor.cboLongitudCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboLongitudBase.setSelectedItem("seleccionar");
        frmConvertor.cboLongitudCambio.setSelectedItem("seleccionar");
        frmConvertor.txtLongitudBase.setText("");
        frmConvertor.txtLongitudCambio.setText("");
        frmConvertor.txtResultadoLongitud.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboLongitudBase)) {
            if (!frmConvertor.cboLongitudBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtLongitudBase.requestFocus();
            } else {
                frmConvertor.txtLongitudBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirLongitud)) {
            if (!frmConvertor.cboLongitudCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirLongitud();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarLongitud)) {
            limpiarInputs();
        }

    }

}
