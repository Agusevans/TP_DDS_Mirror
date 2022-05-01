package domain;

public class TipoConsumo {

    String tipo;
    Unidad unidad;

    public TipoConsumo(){};
    public TipoConsumo(String tipo, Unidad unidad) {
        this.tipo = tipo;
        this.unidad = unidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
