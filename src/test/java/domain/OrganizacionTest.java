package domain;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrganizacionTest {

    Organizacion orgEmpresa;
    Organizacion orgONG;
    Sector dev;
    Sector callcenter;
    Miembro pablo;
    Miembro jose;

    @BeforeEach
    public void init() {
        pablo = new Miembro("Pablo", "Gomez", "DNI", 12345678);
        jose = new Miembro("Jose", "Paz", "DNI", 87654321);

        dev = new Sector("Dev", "Desarrollo apps");
        dev.agregarMiembro(pablo);
        dev.agregarMiembro(jose);

        callcenter = new Sector("Callcenter", "Llamadas");
        callcenter.agregarMiembro(pablo);

        orgEmpresa = new Organizacion("pepito s.a.", TipoOrg.Empresa, "av. tal 123");
        ClasificacionOrg clasificacionOrgEmp = new ClasificacionOrg("Empresa Primaria");
        orgEmpresa.setClasificacion(clasificacionOrgEmp);
        orgEmpresa.agregarSector(dev);

        orgONG = new Organizacion("ONG", TipoOrg.ONG, "av. tal 345");
        ClasificacionOrg clasificacionOrgONG = new ClasificacionOrg("ONG");
        orgONG.setClasificacion(clasificacionOrgONG);
        orgONG.agregarSector(callcenter);

        pablo.agregarOrganizacion(orgEmpresa);
        pablo.agregarOrganizacion(orgONG);
        jose.agregarOrganizacion(orgEmpresa);
    }

    @Test
    void obtenerMiembrosDeUnaOrganizacion() {
        List<Miembro> miembros = new ArrayList<>();
        miembros.add(pablo);
        miembros.add(jose);
        Assert.assertEquals(miembros, orgEmpresa.obtenerMiembros());
    }

    @Test
    void obtenerSectoresDeMiembro(){
        List<Sector> sectores = new ArrayList<>();
        sectores.add(dev);
        sectores.add(callcenter);
        Assert.assertEquals(sectores, pablo.obtenerSectores());
    }

}