package domain.Actividad;

public class Medicion {

    TipoConsumo tipoConsumo;
    Float valor;
    Periodicidad periodicidad;

    public Float calcularHUMedicion()
    {
        FactorEmision fe = tipoConsumo.getFactorEmision();
        return  valor * fe.getValor();
    }
}
