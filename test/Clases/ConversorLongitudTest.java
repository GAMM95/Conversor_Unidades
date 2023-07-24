package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorLongitudTest {

    public ConversorLongitudTest() {
    }

    @Test
    public void testConvertir_1() {
        System.out.println("Convertir Metros a Pies");
        double valor = 1.6;
        String unidadInicial = "m - Metro";
        String unidadCambio = "ft - Pie";
        Conversor instance = new ConversorLongitud(valor, unidadCambio, unidadCambio);
        double expResult = 5.249343832016001;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir Pies a Pugadas");
        double valor = 4.8;
        String unidadInicial = "ft - Pie";
        String unidadCambio = "in - Pulgada";
        Conversor instance = new ConversorLongitud(valor, unidadCambio, unidadCambio);
        double expResult = 57.60000000011704;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
