package Clases;

public class ConversorPeso extends Unidad {
    public ConversorPeso(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial.toLowerCase()) {
            case "gramo":
                dato = valor;
                break;
            case "quilate":
                dato = valor / 5;
                break;
            case "kilogramo":
                dato = valor / 0.001;
                break;
            case "tonelada":
                dato = valor / 0.000001;
                break;
            case "onza":
                dato = valor / 0.035273965838;
                break;
            case "libra":
                dato = valor / 0.002204622476;
                break;
            default:
                throw new IllegalArgumentException("Unidad de peso inicial no válida");

        }
        switch (unidadCambio.toLowerCase()) {
            case "gramo":
                return dato;
            case "quilate":
                return dato * 5;
            case "kilogramo":
                return dato * 0.001;
            case "tonelada":
                return dato * 0.000001;
            case "onza":
                return dato * 0.035273965838;
            case "libra":
                return dato * 0.002204622476;
            default:
                throw new IllegalArgumentException("Unidad de peso a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
