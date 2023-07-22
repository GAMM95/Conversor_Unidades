package Clases;

public class ConversorVolumen extends Unidad{
    public ConversorVolumen(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial.toLowerCase()) {
            case "litro":
                dato = valor;
                break;
            case "mililitro":
                dato = valor / 1000;
                break;
            case "centimetro cubico":
                dato = valor / 1000;
                break;
            case "metro cubico":
                dato = valor / 0.001;
                break;
            case "onza":
                dato = valor / 33.8140222016;
                break;
            case "galon":
                dato = valor / 0.264172037284;
                break;
            case "barril":
                dato = valor / 0.006289810965;
                break;
            default:
                throw new IllegalArgumentException("Unidad de volumen inicial no válida");

        }
        switch (unidadCambio.toLowerCase()) {
            case "litro":
                return dato;
            case "mililitro":
                return dato * 1000;
            case "centimetro cubico":
                return dato * 1000;
            case "metro cubico":
                return dato * 0.001;
            case "onza":
                return dato * 33.8140222016;
            case "galon":
                return dato * 0.264172037284;
            case "barril":
                return dato * 0.006289810965;
            default:
                throw new IllegalArgumentException("Unidad de volumen a cambiar no válida");
        }
    }
}
