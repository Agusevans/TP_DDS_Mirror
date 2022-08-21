package domain.Actividad;

public class Medicion {

    TipoConsumo tipoConsumo;
    Float valor;
    Periodicidad periodicidad;

    public Medicion(TipoConsumo tipoConsumo, Float valor, Periodicidad periodicidad) {
        this.tipoConsumo = tipoConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;
    }

    public Float calcularHUMedicion()
    {
        FactorEmision fe = tipoConsumo.getFactorEmision();
        return  valor * fe.getValor();
    }
}
