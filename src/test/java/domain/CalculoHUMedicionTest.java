package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.*;
import domain.Organizacion.ClasificacionOrg;
import domain.Organizacion.Organizacion;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

class CalculoHUMedicionTest {

    public void init(){

    }

    @Test
    void cargaParametrosCorrectamente(){}

    @Test
    void calculoHuellaCarbono(){

        Organizacion org = new Organizacion();

        FactorEmision fe1 = new FactorEmision(10f, Unidad.lt);

        TipoConsumo tipoConsumo1 = new TipoConsumo("Gas natural", Unidad.lt, fe1);
        List<TipoConsumo> tiposConsumos1 = new ArrayList<TipoConsumo>();
        tiposConsumos1.add(tipoConsumo1);

        Medicion medicion1 = new Medicion(tipoConsumo1, 10f, Periodicidad.Mensual);
        List<Medicion> mediciones1 = new ArrayList<Medicion>();
        mediciones1.add(medicion1);

        Actividad actividad1 = new Actividad("Combustion fija", tiposConsumos1, Alcance.Directa);
        Medible datosActividad1 = new DatosActividad(actividad1, mediciones1, "MM");

        List<Medible> mediciones;
        mediciones = new ArrayList<Medible>();
        mediciones.add(datosActividad1);

        Float hu = 100f;
        Assert.assertEquals(hu, org.obtenerHU(mediciones));
    }

}
