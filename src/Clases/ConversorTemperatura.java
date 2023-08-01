package Clases;

public class ConversorTemperatura extends Unidad {

    public ConversorTemperatura() {
    }

    public ConversorTemperatura(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;    
        switch (unidadInicial) {
            case "°C - Celsius" -> dato = valor;
            case "°F - Fahrenheit" -> dato = (valor - 32) * 5 / 9;
            case "K - Kelvin" -> dato = valor - 273.15;
            case "°R - Rankine" -> dato = (valor - 491.67) * 5 / 9;
            default -> throw new IllegalArgumentException("Unidad de temperatura inicial no válida");

        }
        switch (unidadCambio) {
            case "°C - Celsius" -> {return dato;}
            case "°F - Fahrenheit" -> {return (dato * 9 / 5) + 32;}
            case "K - Kelvin" -> {return dato + 273.15;}
            case "°R - Rankine" -> {return (dato * 9 / 5) + 491.67;}
            default -> throw new IllegalArgumentException("Unidad de temperatura a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "°C - Celsius" -> "°C";
            case "°F - Fahrenheit" -> "°F";
            case "K - Kelvin" -> "K";
            case "°R - Rankine" -> "°R";
            default -> null;
        };
    }
}
