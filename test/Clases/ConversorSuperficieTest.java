package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorSuperficieTest {

    public ConversorSuperficieTest() {
    }

    @Test
    public void testConvertir_1() {
        System.out.println("Convertir Metro cuadrado a Hectárea");
        double valor = 1250.6;
        String unidadInicial = "m² - Metro cuadrado";
        String unidadCambio = "ha - Hectárea";
        Conversor instance = new ConversorSuperficie(valor, unidadCambio, unidadCambio);
        double expResult = 0.12506;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir Perca a Metro cuadrado");
        double valor = 1.5;
        String unidadInicial = "pc - Perca";
        String unidadCambio = "ha - Hectárea";
        Conversor instance = new ConversorSuperficie(valor, unidadCambio, unidadCambio);
        double expResult = 7.543799999997985E-4;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
