package domain.Trayecto;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MedioTransporte extends EntidadPersistente {

    @Expose
    @Column
    public String nombre;

    @Column
    public boolean usaCombustible;

    public MedioTransporte(){
        this.usaCombustible = false;
    };

    public MedioTransporte(String nombre){
        this.nombre = nombre;
        this.usaCombustible = false;
    }

    public float calcularDistancia(Punto puntoInicio, Punto puntoFin){
        //TODO: aca hay que poner el servicio externo, porque lo usan todos menos el publico que hace override

        return puntoInicio.distanciaAPunto(puntoFin);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean usaCombustible(){
        return this.usaCombustible;
    }

}
