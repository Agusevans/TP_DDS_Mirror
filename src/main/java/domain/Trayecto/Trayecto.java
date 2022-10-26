package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;
import domain.Organizacion.Miembro;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Trayecto extends EntidadPersistente {

    @Expose
    @OneToMany
    @JoinColumn(name = "trayecto_id")
    private List<Tramo> tramos;

    @ManyToMany(mappedBy = "trayectos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Miembro> integrantes;

    @Expose
    @Column
    private int vecesRealizadoXMes;

    public Trayecto(){
        this.tramos = new ArrayList<>();
        this.integrantes = new ArrayList<>();
    };

    public Trayecto(List<Tramo> tramos, List<Miembro> integrantes, int vecesRealizadoXMes) {
        this.tramos = tramos;
        this.integrantes = integrantes;
        this.vecesRealizadoXMes = vecesRealizadoXMes;
    }

    public float getDistancia(){
        float distancia = 0f;
        for (Tramo tramo : tramos) {
            distancia += tramo.getDistancia();
        }
        return distancia;
    }

    public float getDistanciaDeConsumo(){
        float distancia = 0f;
        for (Tramo tramo : tramos) {
            if(tramo.getMedioTransporte().usaCombustible())
                distancia += tramo.getDistancia();
        }
        return distancia;
    }

    private Punto puntoInicio(){
        Tramo primerTramo = this.tramos.get(0);
        return primerTramo.getPuntoInicio();
    }

    private Punto puntoFinal(){
        Tramo ultimoTramo = this.tramos.get( this.tramos.size() -1 );
        return ultimoTramo.getPuntoFin();
    }

    public boolean iniciaOTerminaEn(Punto punto){
        return this.puntoInicio().equals(punto) || this.puntoFinal().equals(punto);
    }

    public boolean esCompartido(){
        return this.integrantes.size() >= 2;
    }

    public int cantidadIntegrantes(){
        return this.integrantes.size();
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) { this.tramos = tramos; }

    public void agregarIntegrante(Miembro miembro){
        this.integrantes.add(miembro);
    }

    public void agregarTramo(Tramo tramo){
        this.tramos.add(tramo);
    }

    public int getVecesRealizadoXMes() {
        return vecesRealizadoXMes;
    }

}
