package Clases;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class ValidarNumerosException {

    //  Metodo para validar el ingreso de solo digitos
    public static void soloDigitos(KeyEvent evt) {

        char car = evt.getKeyChar();
        if (Character.isLetter(car)) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }

    }
}
