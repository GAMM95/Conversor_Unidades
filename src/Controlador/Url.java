package Controlador;

import Vista.FrmConvertor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Url {

    // Metodo para abrir enlaces de navegador
    public void direccionWeb(String direccion) {
        try {
            URL url = new URL(direccion);
            URI uri = url.toURI();
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException ex) {
            Logger.getLogger(FrmConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
