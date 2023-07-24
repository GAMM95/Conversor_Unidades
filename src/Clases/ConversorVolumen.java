package Clases;

public class ConversorVolumen extends Unidad {

    public ConversorVolumen() {
    }

    public ConversorVolumen(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
        double dato = 0;
        switch (unidadInicial) {
            case "l - Litro":
                dato = valor;
                break;
            case "ml - Mililitro":
                dato = valor / 1000;
                break;
            case "cm³ - Centímetro cúbico":
                dato = valor / 1000;
                break;
            case "m³ - Metro cúbico":
                dato = valor / 0.001;
                break;
            case "ft³ - Pie cúbico":
                dato = valor / 0.035314662471;
                break;
            case "yd³ - Yarda cúbica":
                dato = valor / 0.001307950547;
                break;
            case "oz- Onza":
                dato = valor / 33.8140222016;
                break;
            case "gal - Galón":
                dato = valor / 0.264172037284;
                break;
            case "B - Barril":
                dato = valor / 0.006289810965;
                break;
            default:
                throw new IllegalArgumentException("Unidad de volumen inicial no válida");
        }
        switch (unidadCambio) {
            case "l - Litro" -> {return dato;}
            case "ml - Mililitro" -> {return dato * 1000;}
            case "cm³ - Centímetro cúbico" -> {return dato * 1000;}
            case "m³ - Metro cúbico" -> {return dato * 0.001;}
            case "ft³ - Pie cúbico" -> {return dato * 0.035314662471;}
            case "yd³ - Yarda cúbica" -> {return dato * 0.001307950547;}
            case "oz- Onza" -> {return dato * 33.8140222016;}
            case "gal - Galón" -> {return dato * 0.264172037284;}
            case "B - Barril" -> {return dato * 0.006289810965;}
            default -> throw new IllegalArgumentException("Unidad de volumen a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
        return switch (unidad) {
            case "l - Litro" -> "l";
            case "ml - Mililitro" -> "ml";
            case "cm³ - Centímetro cúbico" -> "cm³";
            case "m³ - Metro cúbico" -> "m³";
            case "ft³ - Pie cúbico" -> "ft³";
            case "yd³ - Yarda cúbica" -> "yd³";
            case "oz- Onza" -> "oz";
            case "gal - Galón" -> "gal";
            case "B - Barril" -> "B";
            default ->
                null;
        };
    }
}
