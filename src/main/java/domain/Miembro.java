package domain;

import java.util.ArrayList;
import java.util.List;

public class Miembro {

    String nombre;
    String apellido;
    String tipoDocumento;
    int nroDocumento;
    List<Organizacion> organizacionList = new ArrayList<>();


    public List<Sector> obtenerSectores(List<Organizacion> organizacionlist){

        List<Sector> sectoresList = new ArrayList<>();
        //De cada organizacion revisamos los sectores
        for (Organizacion organizacion : organizacionlist) {
            //En cada sectores nos fijamos si tiene al miembro
            for (Sector sector : organizacion.sectorlist){
                //Si lo tiene, agregamos el sector a la lista
                if (sector.miembrosList.contains(this)){

                    sectoresList.add(sector);

                }

            }

        }

        return sectoresList;

    }
}
