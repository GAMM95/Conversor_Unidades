package Controlador;

import Clases.Conversor;
import Clases.ConversorVolumen;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class ControllerVolumen implements ActionListener {

    // Instancias de las clases
    private ConversorVolumen cv;
    private FrmConvertor frmConvertor;

    // Arrays de unidades de volumen
    private String[] volumenes = {"seleccionar", "l - Litro", "ml - Mililitro", "cm³ - Centímetro cúbico", "m³ - Metro cúbico", "ft³ - Pie cúbico", "yd³ - Yarda cúbica", "oz- Onza", "gal - Galón", "B - Barril"};

    public ControllerVolumen(ConversorVolumen cv, FrmConvertor frmConvertor) {
        this.cv = cv;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarVolumenes();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboVolumenBase.addActionListener(this);
        frmConvertor.cboVolumenCambio.addActionListener(this);
        frmConvertor.btnConvertirVolumen.addActionListener(this);
        frmConvertor.btnLimpiarVolumen.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarVolumenes() {
        for (int i = 0; i < volumenes.length; i++) {
            frmConvertor.cboVolumenBase.addItem(volumenes[i]);
            frmConvertor.cboVolumenCambio.addItem(volumenes[i]);
        }
        frmConvertor.cboVolumenBase.setModel(new DefaultComboBoxModel(volumenes));
        frmConvertor.cboVolumenCambio.setModel(new DefaultComboBoxModel(volumenes));
    }

    // Metodo para realizar la conversion del volumen
    private void convertirVolumen() {
        try {
            if (frmConvertor.txtVolumenBase.getText().isEmpty()) {
                frmConvertor.txtResultadoVolumen.setText("Ingrese valor a convertir");
            } else if (frmConvertor.cboVolumenCambio.equals("seleccionar")) {
                frmConvertor.txtResultadoVolumen.setText("Seleccione una moneda de cambio");
            } else {
                double volumen = Double.parseDouble(frmConvertor.txtVolumenBase.getText());
                String volumenBase = frmConvertor.cboVolumenBase.getSelectedItem().toString();
                String volumenCambio = frmConvertor.cboVolumenCambio.getSelectedItem().toString();
                Conversor cd = new ConversorVolumen(volumen, volumenBase, volumenCambio);
                double resultado = cd.convertir(volumen, volumenBase, volumenCambio);
                frmConvertor.txtVolumenCambio.setText(String.valueOf(String.format("%.6f", resultado)));

                // Mostrar resultado con codigos ISO de cada unidad de volumen
                String volBase = cd.codigoISO(frmConvertor.cboVolumenBase.getSelectedItem().toString());
                String volCambio = cd.codigoISO(frmConvertor.cboVolumenCambio.getSelectedItem().toString());
                frmConvertor.txtResultadoVolumen.setText(volumen + " " + volBase + " = " + String.format("%.4f", resultado) + " " + volCambio);
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
        frmConvertor.txtVolumenBase.setEnabled(true);
        frmConvertor.cboVolumenCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtVolumenBase.setEnabled(false);
        frmConvertor.cboVolumenCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboVolumenBase.setSelectedItem("seleccionar");
        frmConvertor.cboVolumenCambio.setSelectedItem("seleccionar");
        frmConvertor.txtVolumenBase.setText("");
        frmConvertor.txtVolumenCambio.setText("");
        frmConvertor.txtResultadoVolumen.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboVolumenBase)) {
            if (!frmConvertor.cboVolumenBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtVolumenBase.requestFocus();
            } else {
                frmConvertor.txtVolumenBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirVolumen)) {
            if (!frmConvertor.cboVolumenCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirVolumen();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarVolumen)) {
            limpiarInputs();
        }

    }

}
