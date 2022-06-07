package domain.Organizacion;

import domain.Actividad.DatosActividad;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;

import java.util.ArrayList;
import java.util.List;

public class Miembro {

    String nombre;
    String apellido;
    String tipoDocumento;
    int nroDocumento;
    Punto domicilio;
    List<Organizacion> organizacionlist;
    List<DatosActividad> datosActividadList;
    ArrayList<Trayecto> trayectos;

    public Miembro(){};

    public Miembro(String nombre, String apellido, String tipoDocumento, int nroDocumento, Punto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.organizacionlist = new ArrayList<>();
        this.trayectos = new ArrayList<Trayecto>();
        this.domicilio = domicilio;
    }

    public List<Sector> obtenerSectores(){

        List<Sector> sectoresList = new ArrayList<>();
        for (Organizacion organizacion : organizacionlist) {
            for (Sector sector : organizacion.getSectorlist()){
                if (sector.esMiembro(this)){
                    sectoresList.add(sector);
                }
            }
        }

        return sectoresList;
    }

    public void agregarOrganizacion(Organizacion organizacion){
            this.organizacionlist.add(organizacion);
    }

    public void agregarTrayecto(Trayecto trayecto){
        trayectos.add(trayecto);
    }

    public void cargarMedicion(List<DatosActividad> mediciones, String organizacion) {
        Organizacion org = new Organizacion(); //Cuando haya persistencia, el parametro "organizacion" sera la ID de la organizacion
        org.cargarMedicion(mediciones);
    }

    public Float calcularHU(float factorEmision){
        float total = 0;
        for(Trayecto trayecto:trayectos){
            total +=trayecto.calcularRecorrido()*factorEmision;
        }
        return total;
    }
    public void mostrarHUMiembro(float factorEmision){
        float huTotal = 0;
        huTotal = this.calcularHU(factorEmision);
        System.out.println("Huella Carbono total:"+ String.valueOf(huTotal));
    }
    /*Calculo de HU en relacion a la ORG*/
    Tramo detectarTramo(Trayecto trayecto, Organizacion organizacion){
        Tramo tramoEncontrado = null;
        for(Tramo tramo: trayecto.getTramos()){
            if(tramo.getPuntoFin().getLatitud() == organizacion.getUbicacion().getLatitud()){
                if (tramo.getPuntoFin().getLongitud() == organizacion.getUbicacion().getLongitud())
                    tramoEncontrado = tramo;
            }
        }
        return tramoEncontrado;
    }
    //getters & setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() { return apellido; }
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
    public List<Organizacion> getOrganizacionlist() {
        return organizacionlist;
    }
    public void setOrganizacionlist(List<Organizacion> organizacionlist) {
        this.organizacionlist = organizacionlist;
    }
    public List<DatosActividad> getDatosActividadList() {
        return datosActividadList;
    }
    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }
    public void setDomicilio(Punto domicilio) {
        this.domicilio = domicilio;
    }
    public Punto getDomicilio() {
        return domicilio;
    }
    public ArrayList<Trayecto> getTrayectos(){ return trayectos; }
}
