package domain.Organizacion;

import domain.Organizacion.Miembro;

import java.util.ArrayList;
import java.util.List;

public class Sector {

    String nombre;
    String actividad;
    List<Miembro> miembrosList;

    public Sector(){};

    public Sector(String nombre, String actividad) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.miembrosList = new ArrayList<>();
    }

    public void agregarMiembro(Miembro miembro){
        this.miembrosList.add(miembro);
    }

    public boolean esMiembro(Miembro miembro){ return miembrosList.contains(miembro); }

    public int cantidadMiembros(){

        int cant = 0;

        for (Miembro miebro : this.miembrosList){
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

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public List<Miembro> getMiembrosList() {
        return miembrosList;
    }

    public void setMiembrosList(List<Miembro> miembrosList) {
        this.miembrosList = miembrosList;
    }
}
