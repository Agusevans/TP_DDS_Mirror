package domain;

public class TipoConsumo {

    String tipo;
    static Unidad unidad;
    static Float valor;
    Periodicidad periodicidad;

    public TipoConsumo(){};
    public TipoConsumo(String tipo, Unidad unidad, Float valor, Periodicidad periodicidad) {
        this.tipo = tipo;
        this.unidad = unidad;
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public static Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

}