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
    public float calcularRecorridoPorSeccion(){
        float total = 0f;

        for (int i = 0; i <= tramos.size() ; i++) {
            float j = this.tramos.get(i).calcularTramo();
            total += j;
            System.out.println("Recorrido seccion " + i + ": " + j);
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
