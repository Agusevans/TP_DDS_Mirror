package domain.Actividad;

public class TipoConsumo {

    String tipo;
    static Unidad unidad;
    FactorEmision factorEmision;

    public TipoConsumo(){};
    public TipoConsumo(String tipo, Unidad unidad, FactorEmision fe) {
        this.tipo = tipo;
        this.unidad = unidad;
        this.factorEmision = fe;
    }

    public FactorEmision getFactorEmision(){
        return this.factorEmision;
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

}