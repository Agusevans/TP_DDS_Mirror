package domain;

public class Medicion {

    String actividad;
    String tipoConsumo;
    String unidad;
    Consumo consumo;
    String periodoDeImputacion;
    TipoAlcance alcance;

    public Medicion(){};

    public Medicion(String actividad, String tipoConsumo, String unidad, Consumo consumo, String periodoDeImputacion, TipoAlcance alcance) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.unidad = unidad;
        this.consumo = consumo;
        this.periodoDeImputacion = periodoDeImputacion;
        this.alcance = alcance;
    }


    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public String getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public void setPeriodoDeImputacion(String periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }

    public TipoAlcance getAlcance() {
        return alcance;
    }

    public void setAlcance(TipoAlcance alcance) {
        this.alcance = alcance;
    }
}
