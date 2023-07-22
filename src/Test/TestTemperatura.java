package Test;

import Clases.Conversor;
import Clases.ConversorTemperatura;

public class TestTemperatura {
    public static void main(String[] args) {
        double dato = 10.5;
        String unidadBase = "fahrenheit";
        String unidadCambio = "kelvin";

        Conversor conversor = new ConversorTemperatura(dato, unidadBase, unidadCambio);
        double resultado = conversor.convertir(dato, unidadBase, unidadCambio);

        // Mostrar resultado
        System.out.println(dato + " " + unidadBase + " equivalen " + String.format("%.2f",resultado) + " " + unidadCambio);
    }
}