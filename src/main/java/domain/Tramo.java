package domain;

import java.util.List;

public class Tramo {

    MedioTransporte medioTransporte;
    public Punto puntoInicio;
    public Punto puntoFin;
    List<Miembro> compartidoPor;

    public float calcularTramo(){
        float totalDistancia = 0f;

        totalDistancia = (float) Math.sqrt(Math.abs(Math.pow(puntoFin.longitud - puntoInicio.longitud, 2) - Math.pow(puntoFin.latitud - puntoInicio.latitud, 2)));

        return totalDistancia;
    }

}
