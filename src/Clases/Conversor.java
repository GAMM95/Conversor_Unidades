package Clases;

public interface Conversor {

    double convertir(double valor, String unidadInicial, String unidadCambio);

    String codigoISO(String unidad);
}
