package domain.Trayecto;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Table
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TransporteContratado extends MedioTransporte {

    @Expose
    @Column
    @Enumerated(EnumType.STRING)
    private TipoContratado tipo;

    public TransporteContratado(){
        this.usaCombustible = true;
    }

    public TransporteContratado(TipoContratado tipo){
        this.tipo = tipo;
        this.usaCombustible = true;
    };

}
