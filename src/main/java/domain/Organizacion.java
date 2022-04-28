package domain;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {

    String razonSocial;
    Tipo tipo;
    ClasificacionOrg clasificacion;
    String ubicacion;
    List<Sector> sectorlist = new ArrayList<>();

    public List<Miembro> obtenerMiembros(List<Sector> sectorlist){
        List<Miembro> miembrosList = new ArrayList<>();
        //De cada sector de la organizacion guardamos sus miembros
        for (Sector sector : sectorlist){
            //Si lo tiene, agregamos el sector a la lista
            miembrosList.addAll(sector.miembrosList);

        }

        return miembrosList;

    }

    public static void cargarMedicion(List<Medicion> mediciones){



    }

    public static void aceptarMiembros(){
    //Va a hacer la aprobacion de la postulacion cargada en el archivo de postulaciones

    //Y en algun momento va a llamar al metodo del sector que agrega el miembro

    }
}
