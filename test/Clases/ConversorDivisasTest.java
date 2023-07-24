package Clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversorDivisasTest {

    public ConversorDivisasTest() {
    }

    @Test
    public void testConvertir() {
        System.out.println("Convertir de Soles a Dolares");
        double valor = 1.0;
        String unidadInicial = "PEN";
        String unidadCambio = "USD";
        Conversor instance = new ConversorDivisas(valor, unidadCambio, unidadCambio);
        double expResult = 0;
        double result = instance.convertir(valor, unidadInicial, unidadCambio);
        assertEquals(expResult, result, 0);
    }

}
