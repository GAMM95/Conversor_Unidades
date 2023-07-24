package Clases;

public class ConversorDivisas extends Unidad {

    private static final double TASA_DOLAR = 0.27916491;
    private static final double TASA_EURO = 0.25081659;
    private static final double TASA_LIBRAS = 0.21712144;
    private static final double TASA_YEN = 39.577486;

    public ConversorDivisas() {
    }

    public ConversorDivisas(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
//        double dato = 0; // dato inicial en soles
//        switch (unidadInicial) {
//            case "PEN - Sol peruano" -> dato = valor;
//            case "USD - Dólar estadounidense" -> dato = valor / TASA_DOLAR;
//            case "EUR - Euro" -> dato = valor / TASA_EURO;
//            case "GPB - Libra Esterlina" -> dato = valor / TASA_LIBRAS;
//            case "JPY - Yen Japonés" -> dato = valor / TASA_YEN;
//            default -> throw new IllegalArgumentException("Moneda base no válida");
//        }
//        switch (unidadCambio) {
//            case "PEN - Sol peruano" -> {return dato;}
//            case "USD - Dólar estadounidense" -> {return dato * TASA_DOLAR;}
//            case "EUR - Euro" -> {return dato * TASA_EURO; }
//            case "GPB - Libra Esterlina" -> {return dato * TASA_LIBRAS;}
//            case "JPY - Yen Japonés" -> {return dato * TASA_YEN;}
//            default -> throw new IllegalArgumentException("Moneda de cambio no válida");
//        }
        return 0;
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "PEN - Sol peruano" ->
                "PEN";
            case "USD - Dólar estadounidense" ->
                "USD";
            case "EUR - Euro" ->
                "EUR";
            case "COP - Peso colombiano" ->
                "COP";
            case "JPY - Yen Japonés" ->
                "JPY";
            case "GPB - Libra Esterlina" ->
                "GBP";
            default ->
                null;
        };
    }

}
