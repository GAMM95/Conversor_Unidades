package Clases;

public class ConversorDivisas extends Unidad {

    public ConversorDivisas() {
    }

    public ConversorDivisas(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        return 0;
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "PEN - Sol peruano" -> "PEN";
            case "USD - Dólar estadounidense" -> "USD";
            case "EUR - Euro" -> "EUR";
            case "BRL - Real brasilero" -> "BRL";
            case "ARS - Peso argentino" -> "ARS";
            case "CLP - Peso chileno" -> "CLP";
            case "COP - Peso colombiano" -> "COP";
            case "BOB - Bolíviano" -> "BOB";
            case "PYG - Guaraní paraguayo" -> "PYG";
            case "UYU - Peso uruguayo" -> "UYU";
            case "VND - Bolívar venezolano" -> "VND";
            case "MXN - Peso mexicano" -> "MXN";
            case "JPY - Yen japonés" -> "JPY";
            case "GBP - Libra esterlina" -> "GBP";
            case "KRW - Won surcoreano" -> "KRW";
            default -> null;
        };
    }

}
