package Controlador;

import Vista.FrmConvertor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerFrm implements ActionListener {

    // Instancias de clases
    Url u = new Url();
    private FrmConvertor frmConvertor;
    
    public ControllerFrm(FrmConvertor frmConvertor) {
        this.frmConvertor = frmConvertor;
        this.frmConvertor.setTitle("Conversor de unidades");
        this.frmConvertor.setResizable(false);
        interfaces();
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
