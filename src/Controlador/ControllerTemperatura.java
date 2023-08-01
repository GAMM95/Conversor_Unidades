package Controlador;

import Clases.Conversor;
import Clases.ConversorTemperatura;
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

public class ControllerTemperatura implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private ConversorTemperatura ct;
    private FrmConvertor frmConvertor;

    // Arrays de temperaturas
    private final String[] temperaturas = {"seleccionar", "°C - Celsius", "°F - Fahrenheit", "K - Kelvin", "°R - Rankine"};

    public ControllerTemperatura(ConversorTemperatura ct, FrmConvertor frmConvertor) {
        this.ct = ct;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarTemperaturas();
        inhabilitar();
    }

    // Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboTemperaturaBase.addActionListener(this);
        frmConvertor.cboTemperaturaCambio.addActionListener(this);
        frmConvertor.btnConvertirTemperatura.addActionListener(this);
        frmConvertor.btnLimpiarTemperatura.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtTemperaturaBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboTemperaturaBase.addMouseListener(this);
        frmConvertor.cboTemperaturaCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarTemperaturas() {
        for (String temperatura : temperaturas) {
            frmConvertor.cboTemperaturaBase.addItem(temperatura);
            frmConvertor.cboTemperaturaCambio.addItem(temperatura);
        }
        frmConvertor.cboTemperaturaBase.setModel(new DefaultComboBoxModel(temperaturas));
        frmConvertor.cboTemperaturaCambio.setModel(new DefaultComboBoxModel(temperaturas));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboTemperaturaBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoTemperatura.setText("Seleccione una unidad de temperatura base");
            frmConvertor.txtResultadoTemperatura.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoTemperatura.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        } else if (frmConvertor.txtTemperaturaBase.getText().trim().equals("")) {
            frmConvertor.txtResultadoTemperatura.setText("Ingrese valor a convertir");
            frmConvertor.txtResultadoTemperatura.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoTemperatura.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtTemperaturaBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboTemperaturaCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoTemperatura.setText("Seleccione una unidad de temperatura a convertir");
            frmConvertor.txtResultadoTemperatura.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoTemperatura.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
    }

    // formato mensaje de error
    private void mensajeError() {
        frmConvertor.txtResultadoTemperatura.setForeground(Color.decode("#E94560"));
        frmConvertor.txtResultadoTemperatura.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    private void mensajeCorrecto() {
        frmConvertor.txtResultadoTemperatura.setForeground(Color.decode("#023e8a"));
        frmConvertor.txtResultadoTemperatura.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    // Metodo para realizar la conversion de la temperatura
    private void convertirTemperatura() {
        try {
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
            mensajeCorrecto();

        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir el valor a double
            frmConvertor.txtResultadoTemperatura.setText("Error en el formato del valor ingresado");
            mensajeError();
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboTemperaturaBase)) {
            if (!frmConvertor.cboTemperaturaBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtTemperaturaBase.requestFocus();
            } else {
                frmConvertor.txtTemperaturaBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboTemperaturaCambio)) {
            if (frmConvertor.cboTemperaturaCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtTemperaturaCambio.setText("");
            } else {
                convertirTemperatura();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirTemperatura)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirTemperatura();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarTemperatura)) {
            limpiarInputs();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtTemperaturaBase)) {
            frmConvertor.txtResultadoTemperatura.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboTemperaturaBase) || e.getSource().equals(frmConvertor.cboTemperaturaCambio)) {
            frmConvertor.txtResultadoTemperatura.setText("");
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
        if (e.getSource().equals(frmConvertor.txtTemperaturaBase)) {
            ValidarNumerosException.soloDigitos(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
