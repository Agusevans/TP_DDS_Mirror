package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Organizacion.Organizacion;

import java.util.Collection;
import java.util.Map;

public class ImplementadorFachadaOrg implements FachadaOrg {

    Organizacion organizacion;


    @Override
    public void cargarParametros(Map<String, Float> parametrosSistema) {

    }

    @Override
    public Float obtenerHU(Collection<Medible> mediciones) {
        organizacion.obtenerHU();
    }
}
