package domain.Actividad;

public class FactorEmision {

    Float valor;
    Unidad unidad;

    public FactorEmision(){};
    public FactorEmision(Float valor, Unidad unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
