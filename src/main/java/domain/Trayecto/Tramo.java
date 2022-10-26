package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class Tramo extends EntidadPersistente {

    @Expose
    @ManyToOne
    @JoinColumn(name = "medioTransporte_id", referencedColumnName = "id")
    private MedioTransporte medioTransporte;

    @Expose
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_inicio_id", referencedColumnName = "id")
    private Punto puntoInicio;

    @Expose
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_fin_id", referencedColumnName = "id")
    private Punto puntoFin;

    @Column
    private float distancia;

    public Tramo(){
        this.distancia = 0;
    };

    public Tramo(MedioTransporte transporte, Punto inicio, Punto fin){
        this.medioTransporte = transporte;
        this.puntoInicio = inicio;
        this.puntoFin = fin;
        this.distancia = transporte.calcularDistancia(inicio, fin);
    }

    public float getDistancia(){
        if (distancia == 0){
            distancia = medioTransporte.calcularDistancia(puntoInicio, puntoFin);
        }
        return this.distancia;
    }

    public MedioTransporte getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(MedioTransporte medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public Punto getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(Punto puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public Punto getPuntoFin() {
        return puntoFin;
    }

    public void setPuntoFin(Punto puntoFin) {
        this.puntoFin = puntoFin;
    }

}
