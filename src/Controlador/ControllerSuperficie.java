package Controlador;

import Clases.Conversor;
import Clases.ConversorSuperficie;
import Vista.FrmConvertor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;

public class ControllerSuperficie implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private ConversorSuperficie ca;
    private FrmConvertor frmConvertor;

    // Arrays de uniaddes de superficie
    private final String[] areas = {"seleccionar", "m² - Metro cuadrado", "mm² - Milímetro cuadrado", "cm² - Centímetro cuadrado", "dm² - Decímetro cuadrado", "ha - Hectárea", "km² - Kilómetro cuadrado", "in²- Pulgada cuadrada", "ft² - Pie cuadrado", "yd² - Yarda cuadrada", "pc - Perca", "ac - Acre"};

    public ControllerSuperficie(ConversorSuperficie ca, FrmConvertor frmConvertor) {
        this.ca = ca;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarAreas();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboAreaBase.addActionListener(this);
        frmConvertor.cboAreaCambio.addActionListener(this);
        frmConvertor.btnConvertirArea.addActionListener(this);
        frmConvertor.btnLimpiarArea.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtAreaBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboAreaBase.addMouseListener(this);
        frmConvertor.cboAreaCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarAreas() {
        for (String area : areas) {
            frmConvertor.cboAreaBase.addItem(area);
            frmConvertor.cboAreaCambio.addItem(area);
        }
        frmConvertor.cboAreaBase.setModel(new DefaultComboBoxModel(areas));
        frmConvertor.cboAreaCambio.setModel(new DefaultComboBoxModel(areas));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboAreaBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoArea.setText("Seleccione una unidad de superficie base");
            frmConvertor.txtResultadoArea.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoArea.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        } else if (frmConvertor.txtAreaBase.getText().trim().equals("")) {
            frmConvertor.txtResultadoArea.setText("Ingrese valor a convertir");
            frmConvertor.txtResultadoArea.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoArea.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtAreaBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboAreaCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoArea.setText("Seleccione una unidad de superficie a convertir");
            frmConvertor.txtResultadoArea.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoArea.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
    }

    // Metodo para realizar la conversion de unidades de superficie
    private void convertirArea() {
        try {
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
            frmConvertor.txtResultadoArea.setForeground(Color.decode("#023e8a"));
            frmConvertor.txtResultadoArea.setFont(new Font("Dialog", Font.BOLD, 16));
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboAreaBase)) {
            if (!frmConvertor.cboAreaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtAreaBase.requestFocus();
            } else {
                frmConvertor.txtAreaBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboAreaCambio)) {
            if (frmConvertor.cboAreaCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtAreaCambio.setText("");
            }else{
                convertirArea();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirArea)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirArea();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarArea)) {
            limpiarInputs();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtAreaBase)) {
            frmConvertor.txtResultadoArea.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboAreaBase) || e.getSource().equals(frmConvertor.cboAreaCambio)) {
            frmConvertor.txtResultadoArea.setText("");
        }
    }

// Eventos no utilizados
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
