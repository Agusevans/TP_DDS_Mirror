package domain.Trayecto;

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

    public List<Tramo> getTramos() {
        return tramos;
    }
    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

}
