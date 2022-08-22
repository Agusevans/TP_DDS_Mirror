package domain;

import domain.Actividad.FactorEmision;
import domain.Actividad.Unidad;
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

    Trayecto trayecto1;
    ArrayList<Tramo> tramos;
    Tramo tramoNuevo1;
    Tramo tramoNuevo2;

    Miembro miembro1;

    @BeforeEach
    public void init (){
        factorEmision = new FactorEmision(2f, Unidad.km); //TODO: Borar cuando se implemente en la actividad

        Punto domicilio = new Punto(1f,1f);
        miembro1 = new Miembro("Pedro","Liniers","DNI",25000, domicilio);

        Punto punto = new Punto(4f, 1f);

        tramos = new ArrayList<Tramo>();
        tramoNuevo1 = new Tramo(null, miembro1.getDomicilio(), punto, new ArrayList<Miembro>());
        tramoNuevo2 = new Tramo(null, miembro1.getDomicilio(), punto, new ArrayList<Miembro>());
        tramos.add(tramoNuevo1);
        tramos.add(tramoNuevo2);

        trayecto1 = new Trayecto(tramos);

        miembro1.agregarTrayecto(trayecto1);
    }

    @Test
    void calculoHUMiembro(){
        Float hu = 12f;
        Assert.assertEquals(hu, miembro1.calcularHU(factorEmision.getValor()));
    }

    @Test
    void calculoHUCompartido(){
        //TODO: Probar que se haga bien la cuenta de trayectos compartidos
    }

}
