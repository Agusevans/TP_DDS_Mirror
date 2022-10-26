package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Trayecto.Punto;
import domain.Trayecto.Trayecto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Miembro")
public class Miembro extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @Column
    private String apellido;

    @Expose
    @Column
    private String tipoDocumento;

    @Expose
    @Column
    private int nroDocumento;

    @Expose
    @OneToOne
    private Punto domicilio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "miembro_organizacion",
            joinColumns = @JoinColumn(name = "miembro_id"),
            inverseJoinColumns = @JoinColumn(name = "organizacion_id")
    )
    private transient List<Organizacion> organizacionlist;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "miembro_trayecto",
            joinColumns = @JoinColumn(name = "miembro_id"),
            inverseJoinColumns = @JoinColumn(name = "trayecto_id")
    )
    private List<Trayecto> trayectos;

    public Miembro(){
        this.organizacionlist = new ArrayList<>();
        this.trayectos = new ArrayList<>();
    };

    public Miembro(String nombre, String apellido, String tipoDocumento, int nroDocumento, Punto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.organizacionlist = new ArrayList<>();
        this.trayectos = new ArrayList<>();
        this.domicilio = domicilio;
    }

    public List<Sector> obtenerSectores(){

        List<Sector> sectoresList = new ArrayList<>();
        for (Organizacion organizacion : organizacionlist) {
            for (Sector sector : organizacion.getSectores()){
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
    public void setDomicilio(Punto domicilio) {
        this.domicilio = domicilio;
    }
    public Punto getDomicilio() {
        return domicilio;
    }

    public void setTrayectos(ArrayList<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }
}
