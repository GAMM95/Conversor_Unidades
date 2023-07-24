package Clases;

public class ConversorPeso extends Unidad {

    public ConversorPeso() {
    }

    public ConversorPeso(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial) {
            case "g - Gramo" -> dato = valor;
            case "kt - Quilate" -> dato = valor / 5;
            case "kg - Kilogramo" -> dato = valor / 0.001;
            case "Tn - Tonelada" -> dato = valor / 0.000001;
            case "oz - Onza" -> dato = valor / 0.035273965838;
            case "lb - Libra" -> dato = valor / 0.002204622476;
            default -> throw new IllegalArgumentException("Unidad de peso inicial no válida");

        }
        switch (unidadCambio) {
            case "g - Gramo" -> {return dato;}
            case "kt - Quilate" -> {return dato * 5;}
            case "kg - Kilogramo" -> {return dato * 0.001;}
            case "Tn - Tonelada" -> {return dato * 0.000001;}
            case "oz - Onza" -> {return dato * 0.035273965838;}
            case "lb - Libra" -> {return dato * 0.002204622476;}
            default -> throw new IllegalArgumentException("Unidad de peso a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "g - Gramo" -> "g";
            case "kt - Quilate" -> "kt";
            case "kg - Kilogramo" -> "kg";
            case "Tn - Tonelada" -> "Tn";
            case "oz - Onza" -> "oz";
            case "lb - Libra" -> "lb";
            default -> null;
        };
    }
}
