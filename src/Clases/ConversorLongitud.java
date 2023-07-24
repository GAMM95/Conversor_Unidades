package Clases;

public class ConversorLongitud extends Unidad {

    public ConversorLongitud() {
    }

    public ConversorLongitud(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial) {
            case "m - Metro":
                dato = valor;
                break;
            case "mm - Milímetro":
                dato = valor / 1000;
                break;
            case "cm - Centímetro":
                dato = valor / 100;
                break;
            case "dm - Decímetro":
                dato = valor / 10;
                break;
            case "dam - Decámetro":
                dato = valor * 10;
                break;
            case "hm - Hectómetro":
                dato = valor * 100;
                break;
            case "km - Kilómetro":
                dato = valor * 1000;
                break;
            case "in - Pulgada":
                dato = valor / 39.3700787402;
                break;
            case "ft - Pie":
                dato = valor / 3.28083989501;
                break;
            case "yd - Yarda":
                dato = valor / 1.09361329834;
                break;
            case "mi - Milla":
                dato = valor / 0.00062137;
                break;
            default:
                throw new IllegalArgumentException("Unidad de longitud inicial no válida");

        }
        switch (unidadCambio) {
            case "m - Metro":
                return dato;
            case "mm - Milímetro":
                return dato * 1000;
            case "cm - Centímetro":
                return dato * 100;
            case "dm - Decímetro":
                return dato * 10;
            case "dam - Decámetro":
                return dato / 10;
            case "hm - Hectómetro":
                return dato / 100;
            case "km - Kilómetro":
                return dato / 1000;
            case "in - Pulgada":
                return dato * 39.3700787402;
            case "ft - Pie":
                return dato * 3.28083989501;
            case "yd - Yarda":
                return dato * 1.09361329834;
            case "mi - Milla":
                return dato * 0.00062137;
            default:
                throw new IllegalArgumentException("Unidad de longitud a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "m - Metro" ->
                "m";
            case "mm - Milímetro" ->
                "mm";
            case "cm - Centímetro" ->
                "cm";
            case "dm - Decímetro" ->
                "dm";
            case "dam - Decámetro" ->
                "dam";
            case "hm - Hectómetro" ->
                "hm";
            case "km - Kilómetro" ->
                "km";
            case "in - Pulgada" ->
                "in";
            case "ft - Pie" -> "ft";
            case "yd - Yarda" -> "yd";
            case "mi - Milla" -> "mi";
            default ->  null;
        };
    }
}
