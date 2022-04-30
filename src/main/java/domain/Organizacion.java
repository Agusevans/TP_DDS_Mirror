package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Organizacion implements FachadaOrg {

    String razonSocial;
    TipoOrg tipo;
    ClasificacionOrg clasificacion;
    String ubicacion;
    List<Sector> sectorlist;
    List<DatosActividad> datosActividadList;

    public Organizacion(){};

    public Organizacion(String razonSocial, TipoOrg tipo, String ubicacion) {
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

    public void cargarMedicion(List<DatosActividad> mediciones){

        this.datosActividadList.addAll(mediciones);

    }

    public void aceptarMiembros() {

        LectorCSV lector = new LectorCSV("Arch", ',');

        String[] linea = lector.LeerLinea();

        while(linea != null) {

        Miembro miembro = new Miembro();
        miembro.setNombre(linea[0]);
        miembro.setApellido(linea[1]);
        miembro.setTipoDocumento(linea[2]);
        miembro.setNroDocumento(Integer.parseInt(linea[3]));

        Sector sector = new Sector();
        sector.setNombre(linea[4]);
        sector.setActividad(linea[5]);

    //Va a hacer la aprobacion de la postulacion cargada en el archivo de postulaciones
    // asumo que el archivo el la combinacion "Miembro-Sector"
    //El archivo tranquilamente puede ser un csv
    //Y en algun momento va a llamar al metodo del sector que agrega el miembro
        sector.miembrosList.add(miembro);
        //Podría tambien agregarse en la lista de organizaciones del miembro
        miembro.organizacionlist.add(this);

        }

    }

    public void agregarSector(Sector sector){
        this.sectorlist.add(sector);
    }

    @Override
    public void cargarParametros(Map<String, Float> parametrosSistema) {

    }

    @Override
    public Float obtenerHU(Collection<Medible> mediciones) {
        Float total = 0f;
        int factorEmision = 0; //TODO revisar por donde entra el factor de emision
        //El factor de emision probablemente entre por params

        for (Medible medicion:mediciones) {
            total += medicion.getValor() * factorEmision;
        }

        return total;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoOrg getTipoOrg() {
        return tipo;
    }

    public void setTipoOrg(TipoOrg tipo) {
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
        this.sectorlist.addAll(sectorlist);
    }


}
