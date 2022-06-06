package domain;

import domain.Actividad.FactorEmision;
import domain.Organizacion.*;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TrayectoTest {

    //Tests del calculo de HC con Trayecto

    FactorEmision factorEmision;

    //Tramos
    ArrayList<Tramo> tramos1;
    ArrayList<Tramo> tramos2;
    Tramo tramo1ida;
    Tramo tramo1vuelta;
    Tramo tramo2ida;
    Tramo tramo2vuelta;

    //Miembros
    Miembro miembro1;
    Miembro miembro2;

    //Orgs
    Organizacion orgEmpresa;
    Sector dev;

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
        miembro2 =  new Miembro("Ledro","Finiers","DNI",25001,new Punto(5f,-4f));

        //Org
        dev = new Sector("Dev", "Desarrollo apps");
        dev.agregarMiembro(miembro1);
        dev.agregarMiembro(miembro2);
        orgEmpresa = new Organizacion("pepito s.a.", TipoOrg.Empresa, new Punto(1.0f,2.0f));
        ClasificacionOrg clasificacionOrgEmp = new ClasificacionOrg("Empresa Primaria");
        orgEmpresa.setClasificacion(clasificacionOrgEmp);
        orgEmpresa.agregarSector(dev);

        //Tramos1
        tramos1 = new ArrayList<>();
        tramo1ida = new Tramo();
        tramo1vuelta = new Tramo();
        tramo1ida.setPuntoInicio(miembro1.getDomicilio());
        tramo1ida.setPuntoFin(new Punto(1f,-1f));
        tramo1vuelta.setPuntoInicio(new Punto(1f,-1f));
        tramo1vuelta.setPuntoFin(miembro1.getDomicilio());
        tramos1.add(tramo1ida);
        tramos1.add(tramo1vuelta);
        //Tramos2
        tramos2 = new ArrayList<>();
        tramo2ida = new Tramo();
        tramo2vuelta = new Tramo();
        tramo2ida.setPuntoInicio(miembro2.getDomicilio());
        tramo2ida.setPuntoFin(new Punto(1f,-1f));
        tramo2vuelta.setPuntoInicio(new Punto(1f,-1f));
        tramo2vuelta.setPuntoFin(miembro2.getDomicilio());
        tramos2.add(tramo2ida);
        tramos2.add(tramo2vuelta);

        //Trayecto1
        trayecto1 = new Trayecto();
        trayecto1.setTramos(tramos1);
        //trayecto2
        trayecto2 = new Trayecto();
        trayecto2.setTramos(tramos2);

        miembro1.agregarTrayecto(trayecto1);
        miembro2.agregarTrayecto(trayecto2);
    }
    @Test
    void calculoHUMiembroHastaPuntoRandom(){
        Float total = miembro1.calcularHU(factorEmision.getValor());
        Float resultado = 6f;
        Assert.assertEquals(resultado,total);
    }

    @Test
    void calculoHUTotalMiembrosSobreOrg(){
        Float total = orgEmpresa.obtenerHUMiembros(factorEmision.getValor());
        Float resultado = 16f;
        Assert.assertEquals(resultado, total);
    }

    @Test

    void calculoHUPorcentualPorMiembro(){ //Falla porque no toma los DatosActividad de la Org en Org.ObtenerHU()
        Float total = orgEmpresa.obtenerHUPorcentualDeMiembro(miembro1, factorEmision.getValor());
        Float resultado = 16f;
        Assert.assertEquals(resultado, total);
    }

}
