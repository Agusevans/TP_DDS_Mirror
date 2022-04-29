package domain;

public class Medicion {

    String actividad;
    String tipoConsumo;
    String unidad;
    Consumo consumo;
    String periodoDeImputacion;
    TipoAlcance alcance;

    public Medicion(String actividad, String tipoConsumo, String unidad, Consumo consumo, String periodoDeImputacion, TipoAlcance alcance) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.unidad = unidad;
        this.consumo = consumo;
        this.periodoDeImputacion = periodoDeImputacion;
        this.alcance = alcance;
    }
}
