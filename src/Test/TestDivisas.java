package Test;

import Clases.Conversor;
import Clases.ConversorDivisas;

public class TestDivisas {
    public static void main(String[] args) {
        double moneda = 1.0;
        String monedaBase = "USD - Dólar estadounidense";
        String monedaCambio = "PEN - Sol peruano";
   
        ConversorDivisas cd = new ConversorDivisas(moneda, monedaBase, monedaCambio);
        double resultado = cd.convertir(moneda, monedaBase, monedaCambio);

      
        // Mostrar resultado
        System.out.println(moneda + " " + monedaBase + " = " + String.format("%.2f",resultado) + " " + monedaCambio);
    }
}
