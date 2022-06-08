package domain.Trayecto;

import domain.Organizacion.Organizacion;

import java.util.List;

public class Trayecto {

    List<Tramo> tramos;

    public float calcularRecorrido(){
        float total = 0f;

        for (Tramo tramo:tramos) {
            total += tramo.calcularTramo();
        }
        System.out.println("Recorrido Total: " + total);

        return total;
    }
    public Float calcularRecorridoPorSeccion(){
        Float total = 0f;
        Float parcial = 0f;
        for (Tramo tramo: tramos) {
            parcial = tramo.calcularTramo();
            total += parcial;
            System.out.println("Recorrido seccion " + tramos.indexOf(tramo) + ": " + parcial);
        }
        System.out.println("Recorrido Total: " + total);
        return total;
    }
    public Float tramoOrganizacion(Organizacion organizacion) {
        Tramo tramoDeOrganizacion = this.detectarTramo(organizacion);
        Float distanciaVuelta = tramos.get(tramos.size() - 1).calcularTramo();
        return (tramoDeOrganizacion.calcularTramo() + (distanciaVuelta / tramos.size()));
    }
    public Tramo detectarTramo(Organizacion organizacion){
        Tramo tramoEncontrado = null;
        for(Tramo tramo: tramos){
            if(tramo.getPuntoFin().getLatitud() == organizacion.getUbicacion().getLatitud()){
                if (tramo.getPuntoFin().getLongitud() == organizacion.getUbicacion().getLongitud())
                    tramoEncontrado = tramo;
            }
        }
        return tramoEncontrado;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }
    public void setTramos(List<Tramo> tramos) {

        this.tramos = tramos;
    }

}
