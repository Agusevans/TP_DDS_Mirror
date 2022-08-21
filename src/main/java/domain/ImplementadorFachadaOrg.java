package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.Actividad;
import domain.Actividad.Medicion;
import domain.Organizacion.Organizacion;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ImplementadorFachadaOrg implements FachadaOrg {

    Organizacion organizacion;

    public ImplementadorFachadaOrg(Organizacion org) {
        this.organizacion = org;
    }

    @Override
    public void cargarParametros(Map<String, Float> parametrosSistema) {
        //TODO
    }

    @Override
    public Float obtenerHU(Collection<Medible> mediciones) {
        return organizacion.obtenerHU(mediciones);
    }
}
