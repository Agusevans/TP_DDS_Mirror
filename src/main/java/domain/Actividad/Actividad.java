package domain.Actividad;

import com.google.gson.annotations.Expose;
import domain.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Actividad")
public class Actividad extends EntidadPersistente {

    @Expose
    @Column
    private String nombre;

    @Expose
    @OneToMany
    @JoinColumn(name = "actividad_id")
    private List<TipoConsumo> tiposConsumo;

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private Alcance alcance;

    public Actividad(){
        this.tiposConsumo = new ArrayList<>();
    };

    public Actividad(String nombre, List<TipoConsumo> tiposConsumo, Alcance alcance){
        this.nombre = nombre;
        this.tiposConsumo = tiposConsumo;
        this.alcance = alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    public void setTiposConsumo(List<TipoConsumo> tiposConsumo) {
        this.tiposConsumo = tiposConsumo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public List<TipoConsumo> getTiposConsumo() {
        return tiposConsumo;
    }
}
