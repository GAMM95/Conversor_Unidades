package Test;

import Clases.Conversor;
import Clases.ConversorPeso;

public class TestPeso {
    public static void main(String[] args) {
        double dato = 1;
        String unidadBase = "g - Gramo";
        String unidadCambio = "oz - Onza";

        Conversor conversor = new ConversorPeso(dato, unidadBase, unidadCambio);
        double resultado = conversor.convertir(dato, unidadBase, unidadCambio);

        // Mostrar resultado
        System.out.println(dato + " " + unidadBase + " equivalen " + String.format("%.4f",resultado) + " " + unidadCambio);
    }
}
