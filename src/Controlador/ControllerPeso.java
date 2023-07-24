package Controlador;

import Clases.Conversor;
import Clases.ConversorPeso;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class ControllerPeso implements ActionListener {

    // Instancias de las clases
    private ConversorPeso cp;
    private FrmConvertor frmConvertor;

    // Arrays de pesos
    private String[] pesos = {"seleccionar", "g - Gramo", "kt - Quilate", "kg - Kilogramo", "Tn - Tonelada", "oz - Onza", "lb - Libra"};

    public ControllerPeso(ConversorPeso cp, FrmConvertor frmConvertor) {
        this.cp = cp;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarPesos();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboPesoBase.addActionListener(this);
        frmConvertor.cboPesoCambio.addActionListener(this);
        frmConvertor.btnConvertirPeso.addActionListener(this);
        frmConvertor.btnLimpiarPeso.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarPesos() {
        for (int i = 0; i < pesos.length; i++) {
            frmConvertor.cboPesoBase.addItem(pesos[i]);
            frmConvertor.cboPesoCambio.addItem(pesos[i]);
        }
        frmConvertor.cboPesoBase.setModel(new DefaultComboBoxModel(pesos));
        frmConvertor.cboPesoCambio.setModel(new DefaultComboBoxModel(pesos));
    }

    // Metodo para realizar la conversion del peso
    private void convertirPeso() {
        try {
            if (frmConvertor.txtPesoBase.getText().isEmpty()) {
                frmConvertor.txtResultadoPeso.setText("Ingrese valor a convertir");
            } else if (frmConvertor.cboPesoCambio.equals("seleccionar")) {
                frmConvertor.txtResultadoPeso.setText("Seleccione una moneda de cambio");
            } else {
                double peso = Double.parseDouble(frmConvertor.txtPesoBase.getText());
                String pesoBase = frmConvertor.cboPesoBase.getSelectedItem().toString();
                String pesoCambio = frmConvertor.cboPesoCambio.getSelectedItem().toString();
                Conversor cd = new ConversorPeso(peso, pesoBase, pesoCambio);
                double resultado = cd.convertir(peso, pesoBase, pesoCambio);
                frmConvertor.txtPesoCambio.setText(String.valueOf(String.format("%.6f", resultado)));

                // Mostrar resultado con codigos ISO de cada unidad de peso
                String peBase = cd.codigoISO(frmConvertor.cboPesoBase.getSelectedItem().toString());
                String peCambio = cd.codigoISO(frmConvertor.cboPesoCambio.getSelectedItem().toString());
                frmConvertor.txtResultadoPeso.setText(peso + " " + peBase + " = " + String.format("%.4f", resultado) + " " + peCambio);
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
        frmConvertor.txtPesoBase.setEnabled(true);
        frmConvertor.cboPesoCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtPesoBase.setEnabled(false);
        frmConvertor.cboPesoCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboPesoBase.setSelectedItem("seleccionar");
        frmConvertor.cboPesoCambio.setSelectedItem("seleccionar");
        frmConvertor.txtPesoBase.setText("");
        frmConvertor.txtPesoCambio.setText("");
        frmConvertor.txtResultadoPeso.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboPesoBase)) {
            if (!frmConvertor.cboPesoBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtPesoBase.requestFocus();
            } else {
                frmConvertor.txtPesoBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirPeso)) {
            if (!frmConvertor.cboPesoCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirPeso();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarPeso)) {
            limpiarInputs();
        }

    }

}
