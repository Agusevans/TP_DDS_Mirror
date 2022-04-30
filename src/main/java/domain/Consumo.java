package domain;

public class Consumo {
    Float valor;
    TipoPeriodicidad periodicidad;

    public Consumo(Float valor, TipoPeriodicidad periodicidad) {
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public TipoPeriodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(TipoPeriodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
}
