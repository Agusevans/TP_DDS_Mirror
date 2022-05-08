package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;

import java.util.ArrayList;
import java.util.List;

public class DatosActividad implements Medible {

    String actividad;
    List<TipoConsumo> tiposConsumo;
    String periodoDeImputacion;
    Alcance alcance;

    public DatosActividad(){};

    public DatosActividad(String actividad, String periodoDeImputacion, Alcance alcance) {
        this.actividad = actividad;
        this.tiposConsumo = new ArrayList<>();
        this.periodoDeImputacion = periodoDeImputacion;
        this.alcance = alcance;
    }

    public void agregarTipoConsumo(TipoConsumo tipoConsumo){
        tiposConsumo.add(tipoConsumo);
    };

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