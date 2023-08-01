package Controlador;

import Clases.Conversor;
import Clases.ConversorPeso;
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

public class ControllerPeso implements ActionListener, KeyListener, MouseListener {

    // Instancias de las clases
    private final ConversorPeso cp;
    private final FrmConvertor frmConvertor;

    // Arrays de pesos
    private final String[] pesos = {"seleccionar", "g - Gramo", "kt - Quilate", "kg - Kilogramo", "Tn - Tonelada", "oz - Onza", "lb - Libra"};

    public ControllerPeso(ConversorPeso cp, FrmConvertor frmConvertor) {
        this.cp = cp;
        this.frmConvertor = frmConvertor;
        interfaces();
        cargarPesos();
        inhabilitar();
    }

//    Metodo para implementar las interfaces
    private void interfaces() {
        // Eventos ActionListener
        frmConvertor.cboPesoBase.addActionListener(this);
        frmConvertor.cboPesoCambio.addActionListener(this);
        frmConvertor.btnConvertirPeso.addActionListener(this);
        frmConvertor.btnLimpiarPeso.addActionListener(this);
        // Eventos KeyListener
        frmConvertor.txtPesoBase.addKeyListener(this);
        // Eventos MouseListener
        frmConvertor.cboPesoBase.addMouseListener(this);
        frmConvertor.cboPesoCambio.addMouseListener(this);
    }

    //  Metodo para llenar comboBox 
    private void cargarPesos() {
        for (String peso : pesos) {
            frmConvertor.cboPesoBase.addItem(peso);
            frmConvertor.cboPesoCambio.addItem(peso);
        }
        frmConvertor.cboPesoBase.setModel(new DefaultComboBoxModel(pesos));
        frmConvertor.cboPesoCambio.setModel(new DefaultComboBoxModel(pesos));
    }

    //   Metodo para validar campos vacios
    private boolean validarCamposVacios() {
        boolean action = true;
        if (frmConvertor.cboPesoBase.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoPeso.setText("Seleccione una unidad de masa base");
            frmConvertor.txtResultadoPeso.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoPeso.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        } else if (frmConvertor.txtPesoBase.getText().trim().equals("")) {
            frmConvertor.txtResultadoPeso.setText("Ingrese valor a convertir");
            frmConvertor.txtResultadoPeso.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoPeso.setFont(new Font("Dialog", Font.BOLD, 16));
            frmConvertor.txtPesoBase.requestFocus();
            action = false;
        } else if (frmConvertor.cboPesoCambio.getSelectedItem().toString().equals("seleccionar")) {
            frmConvertor.txtResultadoPeso.setText("Seleccione una unidad de masa a convertir");
            frmConvertor.txtResultadoPeso.setForeground(Color.decode("#E94560"));
            frmConvertor.txtResultadoPeso.setFont(new Font("Dialog", Font.BOLD, 16));
            action = false;
        }
        return action;
    }

    // formato mensaje de error
    private void mensajeError() {
        frmConvertor.txtResultadoPeso.setForeground(Color.decode("#E94560"));
        frmConvertor.txtResultadoPeso.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    private void mensajeCorrecto() {
        frmConvertor.txtResultadoPeso.setForeground(Color.decode("#023e8a"));
        frmConvertor.txtResultadoPeso.setFont(new Font("Dialog", Font.BOLD, 16));
    }

    // Metodo para realizar la conversion del peso
    private void convertirPeso() {
        try {
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
            mensajeCorrecto();
        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir el valor a double
            frmConvertor.txtResultadoPeso.setText("Error en el formato del valor ingresado");
            mensajeError();
        } catch (IllegalArgumentException e) {
            // Manejar la excepción si se ingresó una unidad no válida
            frmConvertor.txtResultadoPeso.setText("Error en el formato del valor ingresado");
            mensajeError();
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
        // Evento para el comboBox de unidad Base
        if (e.getSource().equals(frmConvertor.cboPesoBase)) {
            if (!frmConvertor.cboPesoBase.getSelectedItem().equals("seleccionar")) {
                habilitar();
                frmConvertor.txtPesoBase.requestFocus();
            } else {
                frmConvertor.txtPesoBase.setText("");
                inhabilitar();
            }
        }

        // Evento para el comboBox de unidad cambio
        if (e.getSource().equals(frmConvertor.cboPesoCambio)) {
            if (frmConvertor.cboPesoCambio.getSelectedItem().equals("seleccionar")) {
                frmConvertor.txtPesoCambio.setText("");
            } else {
                convertirPeso();
            }
        }

        // Evento para el boton Convertir
        if (e.getSource().equals(frmConvertor.btnConvertirPeso)) {
            boolean validarVacios = validarCamposVacios(); // boolean: TRUE
            if (validarVacios == false) {
                validarCamposVacios();
            } else {
                convertirPeso();
            }
        }

        // Evento para el boton limpiar
        if (e.getSource().equals(frmConvertor.btnLimpiarPeso)) {
            limpiarInputs();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(frmConvertor.txtPesoBase)) {
            frmConvertor.txtResultadoPeso.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(frmConvertor.cboPesoBase) || e.getSource().equals(frmConvertor.cboPesoCambio)) {
            frmConvertor.txtResultadoPeso.setText("");
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
        if (e.getSource().equals(frmConvertor.txtPesoBase)) {
            ValidarNumerosException.soloDigitos(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
