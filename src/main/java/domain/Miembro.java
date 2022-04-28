package domain;


import java.util.ArrayList;
import java.util.List;

public class Miembro {

    String nombre;
    String apellido;
    String tipoDocumento;
    int nroDocumento;
    List<Organizacion> organizacionList = new ArrayList<>();


    public <Sectores> obtenerSectores(List<Organizacion> organizacionlist){
        organizacionList = organizacionlist;

        List<Sectores> sectoresList = new ArrayList<>();

        for (int i=0; i<organizacionlist.size();i++) {

            sectoresList.addAll(organizacionlist.sectores);
            
        }


    }
}
