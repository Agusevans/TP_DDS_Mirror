package domain.Actividad;

import java.util.List;

public class Actividad {

    String nombre;
    List<TipoConsumo> tiposConsumo;
    Alcance alcance;

    public String getNombre(){
        return nombre;
    }

}
