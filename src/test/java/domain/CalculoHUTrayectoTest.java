package domain;

import domain.Actividad.FactorEmision;
import domain.Organizacion.Miembro;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

class CalculoHUTrayectoTest {

    FactorEmision factorEmision;
    ArrayList<Tramo> tramos;
    Tramo tramoNuevo;
    //Miembros
    Miembro miembro1;
    Miembro miembro2;
    //Trayectos
    Trayecto trayecto1;
    Trayecto trayecto2;
    ArrayList<Trayecto> trayectos;

    @BeforeEach
    public void init (){
        factorEmision = new FactorEmision();
        factorEmision.setValor(1f);
        //Miembros
        miembro1 = new Miembro("Pedro","Liniers","DNI",25000,new Punto(1f,2f));
        miembro2 =  new Miembro("Ledro","Finiers","DNI",25001,new Punto(2f,-2f));
        //Tramos1
        tramoNuevo = new Tramo();
        tramoNuevo.setPuntoInicio(miembro1.getDomicilio());
        tramoNuevo.setPuntoFin(new Punto(1f,-1f));
        tramos.add(tramoNuevo);
        tramoNuevo.setPuntoInicio(new Punto(1f,-1f));
        tramoNuevo.setPuntoFin(miembro1.getDomicilio());
        tramos.add(tramoNuevo);
        //Trayecto1
        trayecto1 = new Trayecto();
        trayecto1.setTramos(tramos);
        tramos.clear();
        //Tramo2
        tramoNuevo.setPuntoInicio(miembro2.getDomicilio());
        tramoNuevo.setPuntoFin(new Punto(1f,-1f));
        tramos.add(tramoNuevo);
        tramoNuevo.setPuntoInicio(new Punto(1f,-1f));
        tramoNuevo.setPuntoFin(miembro2.getDomicilio());
        tramos.add(tramoNuevo);
        //trayecto2
        trayecto2 = new Trayecto();
        trayecto2.setTramos(tramos);

        miembro1.agregarTrayecto(trayecto1);
        miembro2.agregarTrayecto(trayecto2);
    }
    @Test
    void calculoHUMiembro(){
        Float total = miembro1.calcularHU(factorEmision.getValor());
        Assert.assertEquals(Optional.of(3f),total);
    }


}
