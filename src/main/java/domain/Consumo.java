package domain;

public class Consumo {
    int valor;
    TipoPeriodicidad periodicidad;

    public Consumo(int valor, TipoPeriodicidad periodicidad) {
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public TipoPeriodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(TipoPeriodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
}
