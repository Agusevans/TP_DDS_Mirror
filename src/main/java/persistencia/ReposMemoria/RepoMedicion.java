package persistencia.ReposMemoria;

import domain.Actividad.Alcance;
import domain.Actividad.DatosActividad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepoMedicion {


    //String actividad;
    public List<DatosActividad> actividadFilter(ArrayList<DatosActividad> datos, String filter){

        return datos.stream()
                .filter(datosActividad -> {return datosActividad.getActividad().compareTo(filter) == 0;})
                .collect(Collectors.toList());
    }
    //List<TipoConsumo> tiposConsumo;
    public List<DatosActividad> tipoConsumoFilter(ArrayList<DatosActividad> datos,String filter){
        return datos.stream()
                .filter(datosActividad -> {return datosActividad.getTiposConsumo().contains(filter);})
                .collect(Collectors.toList());
    }
    //String periodoDeImputacion;
    public List<DatosActividad> periodoDeImputacionFilter(ArrayList<DatosActividad> datos,String filter){
        return datos.stream().
                filter(datosActividad -> {return datosActividad.getPeriodoDeImputacion().compareTo(filter)== 0;})
                .collect(Collectors.toList());
    }
    //Alcance alcance;
    public List<DatosActividad> alcanceFilter(ArrayList<DatosActividad> datos, Alcance filter){
        return datos.stream()
                .filter(datosActividad -> {return datosActividad.getAlcance().compareTo(filter) == 0;})
                .collect(Collectors.toList());
    }
}
