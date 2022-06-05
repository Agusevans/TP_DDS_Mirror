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
    Punto ubicacion;
    List<Sector> sectorlist;
    List<DatosActividad> datosActividadList;

    public Organizacion(){};

    public Organizacion(String razonSocial, TipoOrg tipo,Punto ubicacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.sectorlist = new ArrayList<>();
    }

    public List<Miembro> obtenerMiembros() {
        List<Miembro> miembrosList = new ArrayList<>();
        for (Sector sector : sectorlist) {
            miembrosList.addAll(sector.miembrosList);
        }

        return miembrosList;
    }

    public void cargarMedicion(List<DatosActividad> mediciones) {
        this.datosActividadList.addAll(mediciones);
    }

    public void aceptarMiembro(Miembro miembro, Sector sector){
        sector.agregarMiembro(miembro);
        miembro.agregarOrganizacion(this);
    }

    public void aceptarMiembros(String archivo) {

        LectorCSV lector = new LectorCSV(archivo, ',');

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
            this.aceptarMiembro(miembro, sector);

            linea = lector.LeerLinea();
        }
    }

    public void agregarSector(Sector sector){
        this.sectorlist.add(sector);
    }

    @Override
    public void cargarParametros(Map<String, Float> parametrosSistema) {
        //TODO
    }

    @Override
    public Float obtenerHU(Collection<Medible> mediciones) {
        Float total = 0f;
        int factorEmision = 2; //TODO revisar por donde entra el factor de emision
        //El factor de emision probablemente entre por params

        for (Medible medicion:mediciones) {
            total += medicion.getValor() * factorEmision;
        }
        return total;
    }

    public Float obtenerHUPorcentualDeMiembro(Miembro miembro, Float factorEmision){
        Float HUMiembro = 0f;
        Float HUOrganizacion = obtenerHU(datosActividadList);//No toma el datosActividad como Medible;
        Tramo tramo;
        for(Trayecto trayecto:miembro.trayectos){
            tramo = miembro.detectarTramo(trayecto,this);
            if(tramo != null) {
                HUMiembro = miembro.calcularHUDelTramo(tramo, factorEmision);
            }
        }
        return HUMiembro*100/HUOrganizacion;
    }

    public float obtenerHUMiembros(float factorEmision) {
        float huMiembros = 0f;

        for (Sector sector : sectorlist){
            for (Miembro miebro : sector.getMiembrosList()){
                // huMiembros += miembro.calcularHU(factorEmision, this);
            }
        }
        return huMiembros;
    }

    /* Se debe permitir la visualización de un indicador de HC / Cant. de miembros por cada uno de los sectores/áreas de las Organizaciones. */

    public void mostrarHCSectorMiembros(){

        int factorEmision = 1;
        float huMiembros = 0;

        for (Sector sector : sectorlist){
            for (Miembro miembro : sector.getMiembrosList()){
                huMiembros += miembro.calcularHU(factorEmision);
            }

            System.out.println("El Sector " + sector.nombre + " tiene " + sector.cantidadMiembros() + "de miembros y su HC total es " + huMiembros);

        }

    }

    //Getters & setters
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public TipoOrg getTipo() {
        return tipo;
    }
    public void setTipo(TipoOrg tipo) {
        this.tipo = tipo;
    }
    public ClasificacionOrg getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(ClasificacionOrg clasificacion) {
        this.clasificacion = clasificacion;
    }
    public Punto getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Float latitud, Float longitud) {
        this.ubicacion =new Punto(latitud,longitud);
    }
    public List<Sector> getSectorlist() {
        return sectorlist;
    }
    public void setSectorlist(List<Sector> sectorlist) {
        this.sectorlist = sectorlist;
    }
    public List<DatosActividad> getDatosActividadList() {
        return datosActividadList;
    }
    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }
    public enum TipoContratado { //Y esto de donde salio??
        taxi,
        remis
    }
}
