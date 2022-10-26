package domain;

import domain.Actividad.*;
import domain.Organizacion.*;
import domain.Trayecto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import persistencia.EntityManagerHelper;
import persistencia.testMemoData.DataMedicion;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTest {

    @Test
    public void persistirClases(){

        Punto ubicOrg = new Punto(1f,1f);
        Organizacion organizacion = new Organizacion();
        organizacion.setRazonSocial("Palmera");
        organizacion.setTipo(TipoOrg.Empresa);
        organizacion.setClasificacion(ClasificacionOrg.Pyme);
        organizacion.setUbicacion(ubicOrg);

        Punto ubicMiem = new Punto(2f,2f);
        Miembro miembro = new Miembro();
        miembro.setNombre("Juan");
        miembro.setApellido("Perez");
        miembro.setTipoDocumento("DNI");
        miembro.setNroDocumento(Integer.parseInt("28956475"));
        miembro.setDomicilio(ubicMiem);

        FactorEmision factor = new FactorEmision();
        factor.setUnidad(Unidad.m3);
        factor.setValor(4F);


        TipoConsumo tconsumo = new TipoConsumo("Gas Natural", Unidad.m3, factor);
        ArrayList<TipoConsumo> consumoList = new ArrayList<TipoConsumo>();
        consumoList.add(tconsumo);


        Actividad act = new Actividad("Combustion fija", consumoList, Alcance.Directa);

        Sector sector = new Sector();
        sector.setNombre("Gerencia");

        SectorTerritorial sectorT = new SectorTerritorial("Patagonico", TipoSectorTerritorial.departamento);
        organizacion.setTerritorio(sectorT);

        AgenteSectorial agente = new AgenteSectorial();
        agente.setNombre("Roberto");
        agente.setEmail("test@gmail.com");
        agente.setPeriodo("022022");
        agente.setSectorTerritorial(sectorT);


        organizacion.agregarSector(sector);
        organizacion.aceptarMiembro(miembro, sector);
        organizacion.setAgente(agente);
        agente.agregarOrganizacion(organizacion);
        miembro.agregarOrganizacion(organizacion);

        Medicion medicion = new Medicion(tconsumo,5F,Periodicidad.Mensual);
        Medicion medicion2 = new Medicion(tconsumo,6F,Periodicidad.Anual);

        DatosActividad datosActividad = new DatosActividad(act, medicion, LocalDate.of(2022,10,06));
        DatosActividad datosActividad2 = new DatosActividad(act, medicion2, LocalDate.of(2022,10,06));
        ArrayList<DatosActividad> datosList = new ArrayList<DatosActividad>();
        datosList.add(datosActividad);
        datosList.add(datosActividad2);

        BatchDatosActividad batch = new BatchDatosActividad(datosList, organizacion);

        Parada p1 = new Parada("Constitucion", ubicMiem);
        Parada p2 = new Parada("alem", ubicOrg);
        List<Parada> paradasTren = new ArrayList<>();
        paradasTren.add(p1);
        paradasTren.add(p2);
        TransportePublico tren = new TransportePublico("Tren roca","C", TipoPublico.tren, paradasTren);


        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.getEntityManager().persist(sector);
        EntityManagerHelper.getEntityManager().persist(act);
        EntityManagerHelper.getEntityManager().persist(tconsumo);
        EntityManagerHelper.getEntityManager().persist(factor);
        EntityManagerHelper.getEntityManager().persist(agente);
        EntityManagerHelper.getEntityManager().persist(sectorT);
        EntityManagerHelper.getEntityManager().persist(medicion);
        EntityManagerHelper.getEntityManager().persist(medicion2);
        EntityManagerHelper.getEntityManager().persist(datosActividad);
        EntityManagerHelper.getEntityManager().persist(datosActividad2);
        EntityManagerHelper.getEntityManager().persist(batch);
        EntityManagerHelper.getEntityManager().persist(ubicOrg);
        EntityManagerHelper.getEntityManager().persist(ubicMiem);

        EntityManagerHelper.getEntityManager().persist(p1);
        EntityManagerHelper.getEntityManager().persist(p2);
        EntityManagerHelper.getEntityManager().persist(tren);

        EntityManagerHelper.commit();
    }

    @Test
    void recuperarPersistidos(){
        Organizacion org = (Organizacion) EntityManagerHelper.createQuery("from Organizacion where razonSocial = 'Palmera'").getSingleResult();
        Miembro miemb = (Miembro) EntityManagerHelper.createQuery("from Miembro where nroDocumento = 28956475").getSingleResult();
        TransportePublico tren = (TransportePublico) EntityManagerHelper.createQuery("from TransportePublico where tipo = 'tren' and linea = 'C'").getSingleResult();

        Assert.assertEquals("Palmera", org.getRazonSocial());
        Assert.assertEquals(28956475, miemb.getNroDocumento());
        Assert.assertEquals("Tren roca", tren.getNombre());
    }

    @Test
    void persistirYRecuperarUnObjeto(){

        MedioTransporte transporteAPie = new MedioTransporte("A pie");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(transporteAPie);
        EntityManagerHelper.commit();

        MedioTransporte transporteObtenido = (MedioTransporte) EntityManagerHelper.createQuery("from MedioTransporte where id = " + transporteAPie.getId()).getSingleResult();

        Assert.assertEquals("A pie", transporteObtenido.getNombre());
        Assert.assertFalse(transporteObtenido.usaCombustible());
    }

}
