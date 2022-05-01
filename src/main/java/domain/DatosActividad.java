package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;

public class DatosActividad implements Medible {

    String actividad;
    TipoConsumo tipoConsumo;
    Consumo consumo;
    String periodoDeImputacion;
    Alcance alcance;

    public DatosActividad(){};

    public DatosActividad(String actividad, TipoConsumo tipoConsumo, Consumo consumo, String periodoDeImputacion, Alcance alcance) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.consumo = consumo;
        this.periodoDeImputacion = periodoDeImputacion;
        this.alcance = alcance;
    }


    @Override
    public String getUnidad() {
        return String.valueOf(tipoConsumo.getUnidad());
    }

    @Override
    public Float getValor() {
        return consumo.getValor();
    }

    @Override
    public String getCategoria() { //TODO: definir que es categoria
        return null;
    }


}
