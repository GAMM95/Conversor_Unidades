package Controlador;

import Clases.Conversor;
import Clases.ConversorLongitud;
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

public class ControllerLongitud implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private ConversorLongitud cl;
    private FrmConvertor frmConvertor;

    // Arrays de unidades de longitud
    private final String[] longitud = {"seleccionar", "m - Metro", "mm - Milímetro", "cm - Centímetro", "dm - Decímetro", "dam - Decámetro", "hm - Hectómetro", "km - Kilómetro", "in - Pulgada", "ft - Pie", "yd - Yarda", "mi - Milla"};

    public ControllerLongitud(ConversorLongitud cl, FrmConvertor frmConvertor) {
        this.cl = cl;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarLongitudes();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboLongitudBase.addActionListener(this);
        frmConvertor.cboLongitudCambio.addActionListener(this);
        frmConvertor.btnConvertirLongitud.addActionListener(this);
        frmConvertor.btnLimpiarLongitud.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtLongitudBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboLongitudBase.addMouseListener(this);
        frmConvertor.cboLongitudCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarLongitudes() {
        for (String longitud1 : longitud) {
            frmConvertor.cboLongitudBase.addItem(longitud1);
            frmConvertor.cboLongitudCambio.addItem(longitud1);
        }
        frmConvertor.cboLongitudBase.setModel(new DefaultComboBoxModel(longitud));
        frmConvertor.cboLongitudCambio.setModel(new DefaultComboBoxModel(longitud));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboLongitudBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoLongitud.setText("Seleccione una unidad de longitud base");
            frmConvertor.txtResultadoLongitud.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoLongitud.setFont(new Font("Dialog", Font.BOLD, 16));

            action = false;
        } else if (frmConvertor.txtLongitudBase.getText().trim().equals("")) {
            frmConvertor.txtResultadoLongitud.setText("Ingrese valor a convertir");
            frmConvertor.txtResultadoLongitud.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoLongitud.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtLongitudBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboLongitudCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoLongitud.setText("Seleccione una unidad de longitud a convertir");
            frmConvertor.txtResultadoLongitud.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoLongitud.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
    }

    // Metodo para realizar la conversion de longitud
    private void convertirLongitud() {
        try {
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
            frmConvertor.txtResultadoLongitud.setForeground(Color.decode("#023e8a"));
            frmConvertor.txtResultadoLongitud.setFont(new Font("Dialog", Font.BOLD, 16));
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboLongitudBase)) {
            if (!frmConvertor.cboLongitudBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtLongitudBase.requestFocus();
            } else {
                frmConvertor.txtLongitudBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboLongitudCambio)) {
            if (frmConvertor.cboLongitudCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtLongitudCambio.setText("");
            } else {
                convertirLongitud();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirLongitud)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirLongitud();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarLongitud)) {
            limpiarInputs();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtLongitudBase)) {
            frmConvertor.txtResultadoLongitud.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboLongitudBase) || e.getSource().equals(frmConvertor.cboLongitudCambio)) {
            frmConvertor.txtResultadoLongitud.setText("");
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
