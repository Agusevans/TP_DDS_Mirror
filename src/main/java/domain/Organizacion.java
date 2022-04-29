package domain;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {

    String razonSocial;
    Tipo tipo;
    ClasificacionOrg clasificacion;
    String ubicacion;
    List<Sector> sectorlist;

    public Organizacion(String razonSocial, Tipo tipo, String ubicacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.sectorlist = new ArrayList<>();
    }

    public List<Miembro> obtenerMiembros(){
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

        Miembro miembro;
        Sector sector ;

    //Va a hacer la aprobacion de la postulacion cargada en el archivo de postulaciones
    // asumo que el archivo el la combinacion "Miembro-Sector"


    //Y en algun momento va a llamar al metodo del sector que agrega el miembro
        sector.miembrosList.add(miembro.nroDocumento);
        //Podría tambien agregarse en la lista de organizaciones del miembro
        miembro.organizacionlist.add(this.getRazonSocial());




    }

    public void agregarSector(Sector sector){
        this.sectorlist.add(sector);
    }



    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ClasificacionOrg getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionOrg clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Sector> getSectorlist() {
        return sectorlist;
    }

    public void setSectorlist(List<Sector> sectorlist) {
        this.sectorlist = sectorlist;
    }
}
