package Clases;

public class ConversorSuperficie extends Unidad {

    public ConversorSuperficie() {
    }

    public ConversorSuperficie(double valor, String unidadBase, String unidadCambio) {
        super(valor, unidadBase, unidadCambio);
    }

    @Override
    public double convertir(double valor, String unidadInicial, String unidadCambio) {
double dato = 0;
        switch (unidadInicial) {
            case "m² - Metro cuadrado":
                dato = valor;
                break;
            case "mm² - Milímetro cuadrado":
                dato = valor / 1000000;
                break;
            case "cm² - Centímetro cuadrado":
                dato = valor / 10000;
                break;
            case "dm² - Decímetro cuadrado":
                dato = valor / 100;
                break;
            case "ha - Hectárea":
                dato = valor / 0.0001;
                break;
            case "km² - Kilómetro cuadrado":
                dato = valor / 0.000001;
                break;
            case "in²- Pulgada cuadrada":
                dato = valor / 1549.90700558;
                break;
            case "ft² - Pie cuadrado":
                dato = valor / 10.7639104167;
                break;
            case "yd² - Yarda cuadrada":
                dato = valor / 1.1959900463;
                break;
            case "pc - Perca":
                dato = valor / 0.198838781516;
                break;
            case "ac - Acre":
                dato = valor / 0.000247105381;
                break;
            default:
                throw new IllegalArgumentException("Unidad de volumen inicial no válida");
        }
        switch (unidadCambio) {
            case "m² - Metro cuadrado" -> {return dato;}
            case "mm² - Milímetro cuadrado" -> {return dato * 1000000;}
            case "cm² - Centímetro cuadrado" -> {return dato * 10000;}
            case "dm² - Decímetro cuadrado" -> {return dato * 100;}
            case "ha - Hectárea" -> {return dato * 0.0001;}
            case "km² - Kilómetro cuadrado" -> {return dato * 0.000001;}
            case "in²- Pulgada cuadrada" -> {return dato * 1549.90700558;}
            case "ft² - Pie cuadrado" -> {return dato * 10.7639104167;}
            case "yd² - Yarda cuadrada" -> {return dato * 1.1959900463;}
            case "pc - Perca" -> {return dato * 0.198838781516;}
            case "ac - Acre" -> {return dato * 0.000247105381;}
            default -> throw new IllegalArgumentException("Unidad de volumen a cambiar no válida");
        }
    }

    @Override
    public String codigoISO(String unidad) {
       return switch (unidad) {
            case "m² - Metro cuadrado" -> "m²";
            case "mm² - Milímetro cuadrado" -> "mm²";
            case "cm² - Centímetro cuadrado" -> "cm²";
            case "dm² - Decímetro cuadrado" -> "dm²";
            case "ha - Hectárea" -> "ha";
            case "km² - Kilómetro cuadrado" -> "km²";
            case "in²- Pulgada cuadrada" -> "in²";
            case "ft² - Pie cuadrado" -> "ft²";
            case "yd² - Yarda cuadrada" -> "yd²";
            case "pc - Perca" -> "pc";
            case "ac - Acre" -> "ac";
            default -> null;
        };
    }

}
