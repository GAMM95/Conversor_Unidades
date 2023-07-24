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
        Conversor instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);
        double expResult = 50.0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir °C - Celsius a K - Kelvin");
        double valor = 10.5;
        String unidadInicial = "°C - Celsius";
        String unidadCambio = "K - Kelvin";
        Conversor instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);
        double expResult = 283.65;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_3() {
        System.out.println("Convertir °F - Fahrenheita °R - Rankine");
        double valor = 24.5;
        String unidadInicial = "°F - Fahrenheit";
        String unidadCambio = "°R - Rankine";
        Conversor instance = new ConversorTemperatura(valor, unidadCambio, unidadCambio);
        double expResult = 484.17;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }
}
