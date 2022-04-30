package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;

public class DatosActividad implements Medible {

    String actividad;
    String tipoConsumo;
    Consumo consumo;
    String periodoDeImputacion;
    TipoAlcance alcance;

    public DatosActividad(){};

    public DatosActividad(String actividad, String tipoConsumo, Consumo consumo, String periodoDeImputacion, TipoAlcance alcance) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.consumo = consumo;
        this.periodoDeImputacion = periodoDeImputacion;
        this.alcance = alcance;
    }


    @Override
    public String getUnidad() {

        this.tipoConsumo.getunidad();

        return null;
    }

    @Override
    public Float getValor() {
        return consumo.getValor();
    }

    @Override
    public String getCategoria() { //TODO: definir que es categoria
        return null;
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
