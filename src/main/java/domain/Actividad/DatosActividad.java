package domain.Actividad;

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
        return String.valueOf(TipoConsumo.getUnidad());
    }

    @Override
    public Float getValor() {
        return TipoConsumo.getValor();
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

    public List<TipoConsumo> getTiposConsumo() {
        return tiposConsumo;
    }

    public void setTiposConsumo(List<TipoConsumo> tiposConsumo) {
        this.tiposConsumo = tiposConsumo;
    }

    public String getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public void setPeriodoDeImputacion(String periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }

    public Alcance getAlcance() {
        return alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }
}