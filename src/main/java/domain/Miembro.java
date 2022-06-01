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
        for (Organizacion organizacion : organizacionlist) {
            for (Sector sector : organizacion.sectorlist){
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

    public void cargarMedicion(List<DatosActividad> mediciones, String organizacion) {
        Organizacion org = new Organizacion(); //Cuando haya persistencia, el parametro "organizacion" sera la ID de la organizacion
        org.cargarMedicion(mediciones);
    }

    public void agregarTrayecto(Trayecto trayecto){
        trayectos.add(trayecto);
    }

    public ArrayList<Float> calcularHUPorTramo(Trayecto trayecto, float factorEmision){
        ArrayList<Float> huellaPorTramo = new ArrayList<Float>();
        trayecto.tramos.forEach(tramo ->{
            huellaPorTramo.add(tramo.calcularTramo()*factorEmision);
        });
        return huellaPorTramo;
    }

    public ArrayList<Float> calcularHUPorTrayecto(float factorEmision){
        ArrayList<Float> totalPorTrayecto = new ArrayList<Float>();
        ArrayList<Float> totalPorTramo = new ArrayList<Float>();
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

    public float calcularHU(float factorEmision){
        float total = 0;
        List<Float> huellaPorTrayecto = calcularHUPorTrayecto(factorEmision);
        for(int i = 0; i < huellaPorTrayecto.size();i++){
            total += huellaPorTrayecto.get(i);
        }

        return total;

    }

    public void mostrarHUMiembro(float factorEmision){

        float huTotal = 0;

        huTotal = this.calcularHU(factorEmision);

        System.out.println("Huella Carbono total:"+ String.valueOf(huTotal));

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
