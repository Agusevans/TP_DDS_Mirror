package domain;

import java.util.List;

public class Sector {

    String nombre;
    String actividad;
    List<Miembro> miembrosList;

    public void agregarMiembro(Miembro miembro){

        this.miembrosList.add(miembro);

    }


}
