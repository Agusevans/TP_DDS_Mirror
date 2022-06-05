package domain.Trayecto;

import domain.Organizacion.Miembro;

import java.util.List;

public class Tramo {

    MedioTransporte medioTransporte;
    Punto puntoInicio;
    Punto puntoFin;
    List<Miembro> compartidoPor;

    public float calcularTramo(){
        float totalDistancia = 0f;

        totalDistancia = (float) Math.sqrt(Math.abs(Math.pow(puntoFin.longitud - puntoInicio.longitud, 2) - Math.pow(puntoFin.latitud - puntoInicio.latitud, 2)));
        totalDistancia /= (compartidoPor.size()+1);
        return totalDistancia;
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

    public List<Miembro> getCompartidoPor() {
        return compartidoPor;
    }

    public void setCompartidoPor(List<Miembro> compartidoPor) {
        this.compartidoPor = compartidoPor;
    }
}
