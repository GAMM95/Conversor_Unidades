package Test;

import Clases.ConversorLongitud;

public class TestLongitud {
    public static void main(String[] args) {
        double monto = 1;
        String unidadBase = "pulgada";
        String unidadCambio = "pie";

        ConversorLongitud cd = new ConversorLongitud(monto, unidadBase, unidadCambio);
        double resultado = cd.convertir(monto, unidadBase, unidadCambio);

        // Mostrar resultado
        System.out.println(monto + " " + unidadBase + " = " + String.format("%.2f", resultado) + " " + unidadCambio);
    }
}
