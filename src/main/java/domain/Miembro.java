package domain;

import java.util.ArrayList;
import java.util.List;

public class Miembro {

    String nombre;
    String apellido;
    String tipoDocumento;
    int nroDocumento;
    List<Organizacion> organizacionlist;

    public Miembro(){};

    public Miembro(String nombre, String apellido, String tipoDocumento, int nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.organizacionlist = new ArrayList<>();
    }

    public List<Sector> obtenerSectores(){

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

    public void agregarOrganizacion(Organizacion organizacion){
            this.organizacionlist.add(organizacion);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public List<Organizacion> getOrganizacionList() {
        return organizacionlist;
    }

    public void setOrganizacionList(List<Organizacion> organizacionList) {
        this.organizacionlist = organizacionList;
    }
}
