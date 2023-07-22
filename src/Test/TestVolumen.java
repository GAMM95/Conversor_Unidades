package Test;

import Clases.Conversor;
import Clases.ConversorVolumen;

public class TestVolumen {
    public static void main(String[] args) {
        double dato = 1;
        String unidadBase = "galon";
        String unidadCambio = "barril";

        Conversor conversor = new ConversorVolumen(dato, unidadBase, unidadCambio);
        double resultado = conversor.convertir(dato, unidadBase, unidadCambio);

        // Mostrar resultado
        System.out.println(dato + " " + unidadBase + " equivalen " + String.format("%.4f",resultado) + " " + unidadCambio);
    }
}
