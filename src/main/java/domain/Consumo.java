package domain;

public class Consumo {
    Float valor;
    Periodicidad periodicidad;

    public Consumo(Float valor, Periodicidad periodicidad) {
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public Float getValor() {
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
