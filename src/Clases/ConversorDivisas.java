package Clases;

public class ConversorDivisas extends Unidad {

    private static final double TASA_DOLAR = 0.27916491;
    private static final double TASA_EURO = 0.25081659;
    private static final double TASA_LIBRAS = 0.21712144;
    private static final double TASA_YEN = 39.577486;

    public ConversorDivisas(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0; // dato inicial en soles
        switch (unidadInicial.toLowerCase()) {
            case "sol":
                dato = valor;
                break;
            case "dolar":
                dato = valor / TASA_DOLAR;
                break;
            case "euro":
                dato = valor / TASA_EURO;
                break;
            case "libras":
                dato = valor / TASA_LIBRAS;
                break;
            case "yen":
                dato = valor / TASA_YEN;
                break;
            default:
                throw new IllegalArgumentException("Moneda base no válida");

        }
        switch (unidadCambio.toLowerCase()) {
            case "sol":
                return dato;
            case "dolar":
                return dato * TASA_DOLAR;
            case "euro":
                return dato * TASA_EURO;
            case "libras":
                return dato * TASA_LIBRAS;
            case "yen":
                return dato * TASA_YEN;
            default:
                throw new IllegalArgumentException("Moneda de cambio no válida");
        }
    }
}
