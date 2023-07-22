package Clases;

public abstract class Unidad implements Conversor {

    protected double valor;
    protected String unidadBase;
    protected String unidadCambio;

    public Unidad(double valor, String unidadBase, String unidadCambio) {
        this.valor = valor;
        this.unidadBase = unidadBase;
        this.unidadCambio = unidadCambio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(String unidadBase) {
        this.unidadBase = unidadBase;
    }

    public String getUnidadCambio() {
        return unidadCambio;
    }

    public void setUnidadCambio(String unidadCambio) {
        this.unidadCambio = unidadCambio;
    }
}
