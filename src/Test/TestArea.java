package Test;

import Clases.ConversorSuperficie;

public class TestArea {
    public static void main(String[] args) {
        double monto = 1;
        String unidadBase = "metro cuadrado";
        String unidadCambio = "pie cuadrado";

        ConversorSuperficie cd = new ConversorSuperficie(monto, unidadBase, unidadCambio);
        double resultado = cd.convertir(monto, unidadBase, unidadCambio);

        // Mostrar resultado
        System.out.println(monto + " " + unidadBase + " = " + resultado + " " + unidadCambio);
    }
}
