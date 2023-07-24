package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorTemperaturaTest {

    public ConversorTemperaturaTest() {
    }

    @Test
    public void testConvertir_1() {
        System.out.println("Convertir °C - Celsius a °F - Fahrenheit");
        double valor = 10.0;
        String unidadInicial = "°C - Celsius";
        String unidadCambio = "°F - Fahrenheit";
        ConversorTemperatura instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);
        double expResult = 50.0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir °F - Fahrenheit a K - Kelvin");
        double valor = 10.0;
        String unidadInicial = "°F - Fahrenheit";
        String unidadCambio = "K - Kelvin";
        ConversorTemperatura instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);

        double expResult = 260.93;
        double resultado = Math.round(expResult*100.0) / 100.0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(resultado, result, 0);
    }

    @Test
    public void testConvertir_3() {
        System.out.println("convertir");
        double valor = 10.0;
        String unidadInicial = "K - Kelvin";
        String unidadCambio = "°R - Rankinen";
        ConversorTemperatura instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);
        double expResult = 18.0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
