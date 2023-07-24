package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorVolumenTest {

    public ConversorVolumenTest() {
    }

    @Test
    public void testConvertir_1() {
        System.out.println("Convertir de Litros a Centimetros Cubicos");
        double valor = 1.0;
        String unidadInicial = "l - Litro";
        String unidadCambio = "cm³ - Centímetro cúbico";
        Conversor instance = new ConversorVolumen(valor, unidadCambio, unidadCambio);
        double expResult = 1000.0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir de Litros a Centimetros Cubicos");
        double valor = 1.0;
        String unidadInicial = "m³ - Metro cúbico";
        String unidadCambio = "ft³ - Pie cúbico";
        Conversor instance = new ConversorVolumen(valor, unidadCambio, unidadCambio);
        double expResult = 35.314662471;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_3() {
        System.out.println("Convertir de Galones a Litros");
        double valor = 5.2;
        String unidadInicial = "gal - Galón";
        String unidadCambio = "l - Litro";
        Conversor instance = new ConversorVolumen(valor, unidadCambio, unidadCambio);
        double expResult = 19.68414240001376;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
