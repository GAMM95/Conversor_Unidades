package Clases;

public class ConversorLongitud extends Unidad {

    public ConversorLongitud(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial.toLowerCase()) {
            case "metro":
                dato = valor;
                break;
            case "milimetro":
                dato = valor / 1000;
                break;
            case "centimetro":
                dato = valor / 100;
                break;
            case "decimetro":
                dato = valor / 10;
                break;
            case "decametro":
                dato = valor * 10;
                break;
            case "hectometro":
                dato = valor * 100;
                break;
            case "kilometro":
                dato = valor * 1000;
                break;
            case "pulgada":
                dato = valor / 39.3700787402;
                break;
            case "pie":
                dato = valor / 3.28083989501;
                break;
            case "yarda":
                dato = valor / 1.09361329834;
                break;
            case "milla":
                dato = valor / 0.00062137;
                break;
            default:
                throw new IllegalArgumentException("Unidad de longitud inicial no válida");

        }
        switch (unidadCambio.toLowerCase()) {
            case "metro":
                return dato;
            case "milimetro":
                return dato * 1000;
            case "centimetro":
                return dato * 100;
            case "decimetro":
                return dato * 10;
            case "decametro":
                return dato / 10;
            case "hectometro":
                return dato / 100;
            case "kilometro":
                return dato / 1000;
            case "pulgada":
                return dato * 39.3700787402;
            case "pie":
                return dato * 3.28083989501;
            case "yarda":
                return dato * 1.09361329834;
            case "milla":
                return dato * 0.00062137;
            default:
                throw new IllegalArgumentException("Unidad de longitud a cambiar no válida");
        }
    }
}
