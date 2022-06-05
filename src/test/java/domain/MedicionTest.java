package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.DatosActividad;
import domain.Actividad.FactorEmision;
import domain.Actividad.Periodicidad;
import domain.Actividad.TipoConsumo;
import domain.Organizacion.ClasificacionOrg;
import domain.Organizacion.Organizacion;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

class MedicionTest {

    //Tests correspondientes al calculo de la Huella de Carbono

    Organizacion orgEmpresa;
    ClasificacionOrg clasificacionOrg; //De momento sin funcionalidad

    Collection<Medible> mediciones;

    DatosActividad datosActividad1;
    TipoConsumo tipoConsumo1;
    FactorEmision factorEmision1;

    DatosActividad datosActividad2;
    TipoConsumo tipoConsumo2;
    FactorEmision factorEmision2;

    @BeforeEach

    public void init(){
        orgEmpresa = new Organizacion();
        clasificacionOrg = new ClasificacionOrg("Empresa primaria");

        tipoConsumo1 = new TipoConsumo();
        factorEmision1 = new FactorEmision();
        datosActividad1 = new DatosActividad();

        tipoConsumo2 = new TipoConsumo();
        factorEmision2 = new FactorEmision();
        datosActividad2 = new DatosActividad();

        mediciones = new ArrayList<>();
        mediciones.add(datosActividad1);
        mediciones.add(datosActividad2);
    }

    @Test
    void cargaParametrosCorrectamente(){}

    @Test
    void calculoHuellaCarbono(){

        Float total = orgEmpresa.obtenerHU(mediciones);

        Assert.assertEquals(Optional.of(41f), total);

    }

}
