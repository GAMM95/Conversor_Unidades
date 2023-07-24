package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorPesoTest {

    public ConversorPesoTest() {
    }

    @Test
    public void testConvertir_1() {
        System.out.println("Convertir Gramos a Onzas");
        double valor = 13.5;
        String unidadInicial = "g - Gramo";
        String unidadCambio = "oz - Onza";
        Conversor instance = new ConversorPeso(valor, unidadCambio, unidadCambio);
        double expResult = 0.476198538813;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_2() {
        System.out.println("Convertir Kilogramo a Libra");
        double valor = 21.3;
        String unidadInicial = "kg - Kilogramo";
        String unidadCambio = "lb - Libra";
        Conversor instance = new ConversorPeso(valor, unidadCambio, unidadCambio);
        double expResult = 46.958458738800005;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_3() {
        System.out.println("Convertir Onza a Quilate");
        double valor = 5.5;
        String unidadInicial = "oz - Onza";
        String unidadCambio = "kt - Quilate";
        Conversor instance = new ConversorPeso(valor, unidadCambio, unidadCambio);
        double expResult = 779.6117999971171;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_4() {
        System.out.println("Convertir Tonelada a Libra");
        double valor = 10.5;
        String unidadInicial = "Tn - Tonelada";
        String unidadCambio = "lb - Libra";
        Conversor instance = new ConversorPeso(valor, unidadCambio, unidadCambio);
        double expResult = 23148.535998;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConvertir_5() {
        System.out.println("Convertir Libra a Gramo");
        double valor = 7.7;
        String unidadInicial = "lb - Libra";
        String unidadCambio = "g - Gramo";
        Conversor instance = new ConversorPeso(valor, unidadCambio, unidadCambio);
        double expResult = 3492.661480060135;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
