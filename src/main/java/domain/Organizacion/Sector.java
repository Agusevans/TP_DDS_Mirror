package domain.Organizacion;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Organizacion.Miembro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sector")
public class Sector extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @Column
    private String descripcion;

    @Expose
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "miembro_sector",
            joinColumns = @JoinColumn(name = "sector_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
    )
    private List<Miembro> miembros;

    public Sector(){
        this.miembros = new ArrayList<>();
    };

    public Sector(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miembros = new ArrayList<>();
    }

    public void agregarMiembro(Miembro miembro){
        this.miembros.add(miembro);
    }

    public boolean esMiembro(Miembro miembro){ return miembros.contains(miembro); }

    public int cantidadMiembros(){

        int cant = 0;

        for (Miembro miebro : this.miembros){
            cant++;
        }

        return cant;

    }

    //Getters y Setters//
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String actividad) {
        this.descripcion = actividad;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembrosList) {
        this.miembros = miembrosList;
    }
}
