package domain;

import java.util.ArrayList;
import java.util.List;

public class Miembro {

    String nombre;
    String apellido;
    String tipoDocumento;
    int nroDocumento;
    List<Organizacion> organizacionlist;
    List<DatosActividad> datosActividadList;
    ArrayList<Trayecto> trayectos;
    public Miembro(){};

    public Miembro(String nombre, String apellido, String tipoDocumento, int nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.organizacionlist = new ArrayList<>();
        this.trayectos = new ArrayList<Trayecto>();
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

    public void cargarMedicion(List<DatosActividad> mediciones, String organizacion) {

        Organizacion org = new Organizacion(); //Cuando haya persistencia, el parametro "organizacion" sera la ID de la organizacion
        org.cargarMedicion(mediciones);
    }
    public void agregarTramo(Trayecto trayecto){
        trayectos.add(trayecto);
    }
    public ArrayList<float> calcularHUPorTramo(Trayecto trayecto, float factorEmision){
        ArrayList<float> huellaPorTramo = new ArrayList<float>();
        trayecto.tramos.forEach(tramo ->{
            huellaPorTramo.add(tramo.calcularTramo()*factorEmision);
        });
        return huellaPorTramo;
    }
    public ArrayList<float> calcularHUPorTrayecto(float factorEmision){
        ArrayList<float> totalPorTrayecto = new ArrayList<float>();
        ArrayList<float> totalPorTramo = new ArrayList<float>();
        float total= 0;
        for (int i = 0; i < trayectos.size() ; i++) {
            totalPorTramo = calcularHUPorTramo(trayectos.get(i),factorEmision );
            for (int j = 0; j < totalPorTramo.size(); j++) {
                total +=totalPorTramo.get(j);
            }
            totalPorTrayecto.add(total);
            total = 0;
        }
        return totalPorTrayecto;
    }
    public void calcularHU(float factorEmision){
        float total = 0;
        List<float> huellaPorTrayecto = calcularHUPorTrayecto(factorEmision);
        for(int i = 0; i < huellaPorTrayecto.size();i++){
            total += huellaPorTrayecto.get(i);
        }
        System.out.println("Huella Carbono total:"+ String.valueOf(total));
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
}
