package domain.Actividad;

import java.util.List;

public class Actividad {

    String nombre;
    List<TipoConsumo> tiposConsumo;
    Alcance alcance;

    public Actividad(String nombre, List<TipoConsumo> tiposConsumo, Alcance alcance){
        this.nombre = nombre;
        this.tiposConsumo = tiposConsumo;
        this.alcance = alcance;
    }

    public String getNombre(){
        return nombre;
    }

}
