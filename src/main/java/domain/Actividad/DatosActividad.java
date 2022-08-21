package domain.Actividad;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;

import java.util.ArrayList;
import java.util.List;

public class DatosActividad implements Medible {

    Actividad actividad;
    List<Medicion> mediciones;
    String periodoDeImputacion;

    public DatosActividad(){};

    public DatosActividad(Actividad actividad, List<Medicion> mediciones, String periodoDeImputacion) {
        this.actividad = actividad;
        this.periodoDeImputacion = periodoDeImputacion;
        this.mediciones = mediciones;
    }

    @Override //TODO: Checkear estos overrides del medible
    public String getUnidad() {
        return String.valueOf(TipoConsumo.getUnidad());
    }

    @Override
    public Float getValor() {
        return null; //TipoConsumo.getValor();
    }

    @Override
    public String getCategoria() {
        return null;
    }

    public Float calcularHU()
    {
        Float hu = 0f;
        for(Medicion medicion : mediciones) {
            hu += medicion.calcularHUMedicion();
        }

        return hu;
    }

    public String getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public void setPeriodoDeImputacion(String periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }


}