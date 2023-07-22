package Test;

import Clases.Conversor;
import Clases.ConversorDivisas;


public class TestDivisas {
    public static void main(String[] args) {
        double moneda = 1;
        String monedaBase = "euro";
        String monedaCambio = "yen";

        Conversor cd = new ConversorDivisas(moneda, monedaBase, monedaCambio);
        double resultado = cd.convertir(moneda, monedaBase, monedaCambio);

        // Mostrar resultado
        System.out.println(moneda + " " + monedaBase + " = " + String.format("%.2f",resultado) + " " + monedaCambio);
    }
}