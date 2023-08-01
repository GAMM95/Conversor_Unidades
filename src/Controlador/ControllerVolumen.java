package Controlador;

import Clases.Conversor;
import Clases.ConversorVolumen;
import Clases.ValidarNumerosException;
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

public class ControllerVolumen implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private ConversorVolumen cv;
    private FrmConvertor frmConvertor;

    // Arrays de unidades de volumen
    private final String[] volumenes = {"seleccionar", "l - Litro", "ml - Mililitro", "cm³ - Centímetro cúbico", "m³ - Metro cúbico", "ft³ - Pie cúbico", "yd³ - Yarda cúbica", "oz- Onza", "gal - Galón", "B - Barril"};

    public ControllerVolumen(ConversorVolumen cv, FrmConvertor frmConvertor) {
        this.cv = cv;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarVolumenes();
        inhabilitar();
    }

    // Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboVolumenBase.addActionListener(this);
        frmConvertor.cboVolumenCambio.addActionListener(this);
        frmConvertor.btnConvertirVolumen.addActionListener(this);
        frmConvertor.btnLimpiarVolumen.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtVolumenBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboVolumenBase.addMouseListener(this);
        frmConvertor.cboVolumenCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarVolumenes() {
        for (String volumene : volumenes) {
            frmConvertor.cboVolumenBase.addItem(volumene);
            frmConvertor.cboVolumenCambio.addItem(volumene);
        }
        frmConvertor.cboVolumenBase.setModel(new DefaultComboBoxModel(volumenes));
        frmConvertor.cboVolumenCambio.setModel(new DefaultComboBoxModel(volumenes));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboVolumenBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoVolumen.setText("Seleccione una unidad de volúmen base");
            frmConvertor.txtResultadoVolumen.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoVolumen.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        } else if (frmConvertor.txtVolumenBase.getText().trim().equals("")) {
            frmConvertor.txtResultadoVolumen.setText("Ingrese valor a convertir");
            frmConvertor.txtResultadoVolumen.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoVolumen.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtVolumenBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboVolumenCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoVolumen.setText("Seleccione una unidad de volúmen a convertir");
            frmConvertor.txtResultadoVolumen.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoVolumen.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
    }

    // formato mensaje de error
    private void mensajeError() {
        frmConvertor.txtResultadoVolumen.setForeground(Color.decode("#E94560"));
        frmConvertor.txtResultadoVolumen.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    private void mensajeCorrecto() {
        frmConvertor.txtResultadoVolumen.setForeground(Color.decode("#023e8a"));
        frmConvertor.txtResultadoVolumen.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    // Metodo para realizar la conversion del volumen
    private void convertirVolumen() {
        try {
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
            mensajeCorrecto();
        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir el valor a double
            frmConvertor.txtResultadoVolumen.setText("Error en el formato del valor ingresado");
            mensajeError();
        } catch (IllegalArgumentException e) {
            // Manejar la excepción si se ingresó una unidad no válida
            frmConvertor.txtResultadoVolumen.setText("Error en el formato del valor ingresado");
            mensajeError();
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboVolumenBase)) {
            if (!frmConvertor.cboVolumenBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtVolumenBase.requestFocus();
            } else {
                frmConvertor.txtVolumenBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboVolumenCambio)) {
            if (frmConvertor.cboVolumenCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtVolumenCambio.setText("");
            } else {
                convertirVolumen();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirVolumen)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirVolumen();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarVolumen)) {
            limpiarInputs();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtVolumenBase)) {
            frmConvertor.txtResultadoVolumen.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboVolumenBase) || e.getSource().equals(frmConvertor.cboVolumenCambio)) {
            frmConvertor.txtResultadoVolumen.setText("");
        }
    }

    // Eventos no utilizados
    @Override
    public void keyTyped(KeyEvent e) {
                if (e.getSource().equals(frmConvertor.txtVolumenBase)) {
            ValidarNumerosException.soloDigitos(e);
        }
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
