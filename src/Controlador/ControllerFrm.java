package Controlador;

import Clases.ConversorDivisas;
import Clases.ConversorLongitud;
import Clases.ConversorPeso;
import Clases.ConversorSuperficie;
import Clases.ConversorTemperatura;
import Clases.ConversorVolumen;
import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerFrm implements ActionListener {

    // Instancias de clases
    Url u = new Url();
    private FrmConvertor frmConvertor;

    ConversorDivisas cd = new ConversorDivisas();
    ConversorTemperatura ct = new ConversorTemperatura();
    ConversorPeso cp = new ConversorPeso();
    ConversorLongitud cl = new ConversorLongitud();
    ConversorSuperficie cs = new ConversorSuperficie();
    ConversorVolumen cv = new ConversorVolumen();

    public ControllerFrm(FrmConvertor frmConvertor) {
        this.frmConvertor = frmConvertor;
        this.frmConvertor.setTitle("Conversor de unidades");
        this.frmConvertor.setResizable(false);
        interfaces();
        importarControllers();
    }

    // Metodo para importar controladores
    private void importarControllers() {
        ControllerDivisas divisas = new ControllerDivisas(cd, this.frmConvertor);
        ControllerTemperatura temperatura = new ControllerTemperatura(ct, this.frmConvertor);
        ControllerPeso peso = new ControllerPeso(cp, this.frmConvertor);
        ControllerLongitud longitud = new ControllerLongitud(cl, this.frmConvertor);
        ControllerSuperficie superficie = new ControllerSuperficie(cs, frmConvertor);
        ControllerVolumen volumen = new ControllerVolumen(cv, this.frmConvertor);
    }

    // Metodo para implementar las interfaces
    private void interfaces() {
        frmConvertor.btnGithub.addActionListener(this);
        frmConvertor.btnLinkedIn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Eventos para los botones de enlace GitHub y LinkedIn
        if (e.getSource().equals(frmConvertor.btnGithub)) {
            u.direccionWeb("https://github.com/GAMM95");
        } else if (e.getSource().equals(frmConvertor.btnLinkedIn)) {
            u.direccionWeb("https://www.linkedin.com/in/jhonatan-mantilla-jmm190395/");
        }
    }

}
