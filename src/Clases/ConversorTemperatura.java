package Clases;

public class ConversorTemperatura extends Unidad {

    public ConversorTemperatura(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial.toLowerCase()) {
            case "celsius":
                dato = valor;
                break;
            case "fahrenheit":
                dato = (valor - 32) * 5 / 9;
                break;
            case "kelvin":
                dato = valor * 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unidad de temperatura inicial no válida");

        }
        switch (unidadCambio.toLowerCase()) {
            case "celsius":
                return dato;
            case "fahrenheit":
                return (dato * 9 / 5) + 32;
            case "kelvin":
                return dato + 273.15;
            default:
                throw new IllegalArgumentException("Unidad de temperatura a cambiar no válida");
        }
    }
}
