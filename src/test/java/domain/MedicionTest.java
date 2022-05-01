package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

class MedicionTest {

    //Tests correspondientes al calculo de la Huella de Carbono

    Organizacion orgEmpresa;
    ClasificacionOrg clasificacionOrg; //De momento sin funcionalidad

    Collection<Medible> mediciones;

    DatosActividad datosActividad1;
    Consumo consumo1;
    TipoConsumo tipoConsumo1;
    FactorEmision factorEmision1;

    DatosActividad datosActividad2;
    Consumo consumo2;
    TipoConsumo tipoConsumo2;
    FactorEmision factorEmision2;

    @BeforeEach

    public void init(){
        orgEmpresa = new Organizacion();
        clasificacionOrg = new ClasificacionOrg("Empresa primaria");

        consumo1 = new Consumo(10f, Periodicidad.Mensual);
        tipoConsumo1 = new TipoConsumo();
        factorEmision1 = new FactorEmision();
        datosActividad1 = new DatosActividad();

        consumo2 = new Consumo(10.5f, Periodicidad.Mensual);
        tipoConsumo2 = new TipoConsumo();
        factorEmision2 = new FactorEmision();
        datosActividad2 = new DatosActividad();

        mediciones = new ArrayList<>();
        mediciones.add(datosActividad1);
        mediciones.add(datosActividad2);
    }

    @Test
    void cargaParametrosCorrectamente(){}

    void calculoHuellaCarbono(){

        Float total = orgEmpresa.obtenerHU(mediciones);

        Assert.assertEquals(Optional.of(41f), total);

    }

}
