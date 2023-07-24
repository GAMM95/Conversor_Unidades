package Controlador;

import Clases.Conversor;
import Clases.ConversorSuperficie;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class ControllerSuperficie implements ActionListener {

    // Instancias de las clases
    private ConversorSuperficie ca;
    private FrmConvertor frmConvertor;

    // Arrays de uniaddes de superficie
    private String[] areas = {"seleccionar", "m² - Metro cuadrado", "mm² - Milímetro cuadrado", "cm² - Centímetro cuadrado", "dm² - Decímetro cuadrado", "ha - Hectárea", "km² - Kilómetro cuadrado", "in²- Pulgada cuadrada", "ft² - Pie cuadrado", "yd² - Yarda cuadrada", "pc - Perca", "ac - Acre"};

    public ControllerSuperficie(ConversorSuperficie ca, FrmConvertor frmConvertor) {
        this.ca = ca;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarAreas();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.cboAreaBase.addActionListener(this);
        frmConvertor.cboAreaCambio.addActionListener(this);
        frmConvertor.btnConvertirArea.addActionListener(this);
        frmConvertor.btnLimpiarArea.addActionListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarAreas() {
        for (int i = 0; i < areas.length; i++) {
            frmConvertor.cboAreaBase.addItem(areas[i]);
            frmConvertor.cboAreaCambio.addItem(areas[i]);
        }
        frmConvertor.cboAreaBase.setModel(new DefaultComboBoxModel(areas));
        frmConvertor.cboAreaCambio.setModel(new DefaultComboBoxModel(areas));
    }

    // Metodo para realizar la conversion de unidades de superficie
    private void convertirArea() {
        try {
            if (frmConvertor.txtAreaBase.getText().isEmpty()) {
                frmConvertor.txtResultadoArea.setText("Ingrese valor a convertir");
            } else if (frmConvertor.cboAreaCambio.equals("seleccionar")) {
                frmConvertor.txtResultadoArea.setText("Seleccione una moneda de cambio");
            } else {
                double area = Double.parseDouble(frmConvertor.txtAreaBase.getText());
                String areaBase = frmConvertor.cboAreaBase.getSelectedItem().toString();
                String areaCambio = frmConvertor.cboAreaCambio.getSelectedItem().toString();
                Conversor cd = new ConversorSuperficie(area, areaBase, areaCambio);
                double resultado = cd.convertir(area, areaBase, areaCambio);
                frmConvertor.txtAreaCambio.setText(String.valueOf(String.format("%.6f", resultado)));

                // Mostrar resultado con codigos ISO de cada unidad de superficie
                String arBase = cd.codigoISO(frmConvertor.cboAreaBase.getSelectedItem().toString());
                String arCambio = cd.codigoISO(frmConvertor.cboAreaCambio.getSelectedItem().toString());
                frmConvertor.txtResultadoArea.setText(area + " " + arBase + " = " + String.format("%.4f", resultado) + " " + arCambio);
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
        frmConvertor.txtAreaBase.setEnabled(true);
        frmConvertor.cboAreaCambio.setEnabled(true);
    }

    // Metodo para deshabilitar inputs
    private void inhabilitar() {
        frmConvertor.txtAreaBase.setEnabled(false);
        frmConvertor.cboAreaCambio.setEnabled(false);
    }

    // Metodo para limpiar entradas
    private void limpiarInputs() {
        frmConvertor.cboAreaBase.setSelectedItem("seleccionar");
        frmConvertor.cboAreaCambio.setSelectedItem("seleccionar");
        frmConvertor.txtAreaBase.setText("");
        frmConvertor.txtAreaCambio.setText("");
        frmConvertor.txtResultadoArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmConvertor.cboAreaBase)) {
            if (!frmConvertor.cboAreaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtAreaBase.requestFocus();
            } else {
                frmConvertor.txtAreaBase.setText("");
                inhabilitar();
            }
        }
        if (e.getSource().equals(frmConvertor.btnConvertirArea)) {
            if (!frmConvertor.cboAreaCambio.getSelectedItem().toString().equals("seleccionar")) {
                convertirArea();
            }
        }
        if (e.getSource().equals(frmConvertor.btnLimpiarArea)) {
            limpiarInputs();
        }

    }

}
