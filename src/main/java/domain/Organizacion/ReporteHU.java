package domain.Organizacion;

import domain.Actividad.Actividad;
import domain.Actividad.TipoConsumo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteHU {

    float huTrayectos;
    float huMediciones;

    List<DesgloseSector> desgloseSectores;
    List<DesgloseActividad> desgloseActividades;

    public ReporteHU(){
        huTrayectos = 0;
        huMediciones = 0;
        this.desgloseSectores = new ArrayList<>();
        this.desgloseActividades = new ArrayList<>();
    }

    public void agregarHUMedicion(Actividad actividad, TipoConsumo tipoConsumo, float hu){
        huMediciones += hu;
        DesgloseActividad desgloseActividad = desgloseActividades.stream().findFirst().filter(desglose -> desglose.actividad == actividad).orElse(null);

        if(desgloseActividad == null){
            desgloseActividad = new DesgloseActividad(actividad);
            desgloseActividades.add(desgloseActividad);
        }

        Float huTC = desgloseActividad.mapDesgloseTipoConsumo.get(tipoConsumo);
        if(huTC == null)
            desgloseActividad.mapDesgloseTipoConsumo.put(tipoConsumo, hu);
        else
            desgloseActividad.mapDesgloseTipoConsumo.put(tipoConsumo, hu + huTC);

    }

    public void agregarHUTrayecto(Sector sector, Miembro miembro, float hu){
        huTrayectos += hu;
        DesgloseSector desgloseSector = desgloseSectores.stream().findFirst().filter(desglose -> desglose.sector == sector).orElse(null);

        if(desgloseSector == null){
            desgloseSector = new DesgloseSector(sector);
            desgloseSectores.add(desgloseSector);
        }

        Float huMiembro = desgloseSector.mapDesgloseMiembro.get(miembro);
        if(huMiembro == null)
            desgloseSector.mapDesgloseMiembro.put(miembro, hu);
        else
            desgloseSector.mapDesgloseMiembro.put(miembro, hu + huMiembro);
    }

    public void mostrarReporte(){
        System.out.println("-HU por sector: ");
        desgloseSectores.forEach(desgloseSector -> {
            System.out.println("    Sector: " + desgloseSector.sector.getNombre());
            desgloseSector.mapDesgloseMiembro.forEach((miembro, hu) -> {
                System.out.println("        Miembro: " + miembro.getNombre() + " - HU: " + hu + " - %: " + (hu/huTrayectos)*100);
            });
        });
        System.out.println("-HU por actividad: ");
        desgloseActividades.forEach(desgloseActividad -> {
            System.out.println("    Actividad: " + desgloseActividad.actividad.getNombre());
            desgloseActividad.mapDesgloseTipoConsumo.forEach((tipoConsumo, hu) -> {
                System.out.println("        Tipo de consumo: " + tipoConsumo.getNombre() + " - HU: " + hu + " - %: " + (hu/huMediciones)*100);
            });
        });
        System.out.println("-HU por mediciones: " + huMediciones);
        System.out.println("-HU por trayectos: " + huTrayectos);
        System.out.println("HU total: " + (huTrayectos + huMediciones));
    }

    private class DesgloseSector{
        Sector sector;
        Map<Miembro, Float> mapDesgloseMiembro;

        public DesgloseSector(Sector sector) {
            this.sector = sector;
            this.mapDesgloseMiembro = new HashMap<>();
        }
    }

    private class DesgloseActividad{
        Actividad actividad;
        Map<TipoConsumo, Float> mapDesgloseTipoConsumo;

        public DesgloseActividad(Actividad actividad) {
            this.actividad = actividad;
            this.mapDesgloseTipoConsumo = new HashMap<>();
        }
    }

}


