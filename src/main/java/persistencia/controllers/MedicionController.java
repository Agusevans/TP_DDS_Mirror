package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Lectores.LectorCSV;
import domain.Actividad.*;
import domain.Organizacion.Organizacion;
import domain.Organizacion.ReporteHU;
import domain.Organizacion.Usuario;
import persistencia.factories.*;
import persistencia.repositorio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;

public class MedicionController {

    RepoBatch repoBatch;
    RepoDatosActividad repoDatosActividad;
    RepoActividad repoActividad;
    Repositorio<Medicion> repoMedicion;
    Repositorio<TipoConsumo> repoTipoConsumo;
    RepoUsuario repoUsuario;
    Repositorio<Organizacion> repoOrganizacion;
    Gson gson;

    public MedicionController(){
        this.repoBatch = FactoryRepoBatchs.get();
        this.repoDatosActividad = FactoryRepoDatosActividad.get();
        this.repoActividad = FactoryRepoActividad.get();
        this.repoMedicion = FactoryRepositorio.get(Medicion.class);
        this.repoTipoConsumo = FactoryRepositorio.get(TipoConsumo.class);
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").create();
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public ModelAndView load(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "carga_mediciones.hbs");
    }

    public ModelAndView reporte(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        //TODO faltan los params de mes y anio
        return new ModelAndView(parametros, "reporte.hbs");
    }

    public String filterMed(Request request, Response response){
        List<DatosActividad> listMed = repoDatosActividad.filter(request);

        String jsonOrg = gson.toJson(listMed);

        response.type("application/json");
        return jsonOrg;
    }
    public String listMed(Request request, Response response){
        List<DatosActividad> listMed = repoDatosActividad.list(Integer.parseInt(request.queryParams("offset")), Integer.parseInt(request.queryParams("limit")));


        String jsonOrg = gson.toJson(listMed);

        response.type("application/json");
        return jsonOrg;
    }

    //Batch
    public Response batchAlta(Request request, Response response) throws Exception {

        LectorCSV lectorCSV = new LectorCSV();
        BatchDatosActividad batchDatosActividad = lectorCSV.leerBatchDeString(request.body());

        Organizacion org = this.repoOrganizacion.buscar(Integer.parseInt(request.queryParams("OrgId")));

        if(org != null)
            batchDatosActividad.setOrganizacion(org);
        else
            throw new Exception("Se debe indicar la organizacion a la que se quiere cargar el batch");

        this.repoBatch.agregar(batchDatosActividad);

        return response;
    }
    public Response batchBaja(Request request, Response response){
        BatchDatosActividad batchDatosActividad = this.repoBatch.buscar(Integer.parseInt(request.params("id")));

        if( batchDatosActividad != null)
            this.repoBatch.borrar(batchDatosActividad);
        else
            throw new RuntimeException("No existe el batch con id: " + request.params("id"));

        return response;
    }

    public ModelAndView calculoHU(Request request, Response response) throws IOException {

        int anio = Integer.parseInt(request.queryParams("anio"));
        int mes = Integer.parseInt(request.queryParams("mes"));

        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        Organizacion organizacion = this.repoOrganizacion.buscar(usuario.getOrganizacion().getId());

        Actividad actividadTrayectos;
        try{
            actividadTrayectos = repoActividad.buscarActividadTrayectos();
            organizacion.setActividadTrayectos(actividadTrayectos);
        }
        catch(Exception e){
            throw new RuntimeException("No se encontro la actividad de trayectos");
        }

        List<DatosActividad> listMed = repoBatch.filterDAs(anio, mes, organizacion.getId());
        organizacion.setDatosActividad(listMed);

        ReporteHU reporte = organizacion.obtenerReporteHU();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("reporte", reporte);
        parametros.put("usuario", usuario);
        parametros.put("anio", anio);
        parametros.put("mes", mes);

        return new ModelAndView(parametros, "resultado.hbs");
    }

    public String apiCalculoHU(Request request, Response response) throws IOException {

        int anio = Integer.parseInt(request.queryParams("anio"));
        int mes = Integer.parseInt(request.queryParams("mes"));

        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id")));

        Actividad actividadTrayectos;
        try{
            actividadTrayectos = repoActividad.buscarActividadTrayectos();
            organizacion.setActividadTrayectos(actividadTrayectos);
        }
        catch(Exception e){
            throw new RuntimeException("No se encontro la actividad de trayectos");
        }

        List<DatosActividad> listMed = repoBatch.filterDAs(anio, mes, organizacion.getId());
        organizacion.setDatosActividad(listMed);

        ReporteHU reporte = organizacion.obtenerReporteHU();

        response.type("String");
        return reporte.reporteAString();
    }



}
