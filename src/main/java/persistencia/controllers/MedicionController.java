package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Actividad.*;
import domain.Organizacion.Usuario;
import persistencia.factories.FactoryRepoDatosActividad;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoDatosActividad;
import persistencia.repositorio.RepoUsuario;
import persistencia.repositorio.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class MedicionController {

    Repositorio<BatchDatosActividad> repoBatch;
    RepoDatosActividad repoDatosActividad;
    Repositorio<Actividad> repoActividad;
    Repositorio<Medicion> repoMedicion;
    Repositorio<TipoConsumo> repoTipoConsumo;
    RepoUsuario repoUsuario;
    Gson gson;

    public MedicionController(){
        this.repoBatch = FactoryRepositorio.get(BatchDatosActividad.class);
        this.repoDatosActividad = FactoryRepoDatosActividad.get();
        this.repoActividad = FactoryRepositorio.get(Actividad.class);
        this.repoMedicion = FactoryRepositorio.get(Medicion.class);
        this.repoTipoConsumo = FactoryRepositorio.get(TipoConsumo.class);
        this.repoUsuario = FactoryRepoUsuario.get();
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
    public Response batchAlta(Request request, Response response){
        BatchDatosActividad batchDatosActividad = gson.fromJson(request.body(), BatchDatosActividad.class);
        this.repoBatch.agregar(batchDatosActividad);

        for (DatosActividad datosActividad:batchDatosActividad.getDatosAct()) {
            this.repoDatosActividad.agregar(datosActividad);
        }

        return response;
    }
    public Response batchBaja(Request request, Response response){
        BatchDatosActividad batchDatosActividad = this.repoBatch.buscar(Integer.parseInt(request.params("id")));

        if( batchDatosActividad != null){

            for (DatosActividad datosActividad:batchDatosActividad.getDatosAct()) {
                this.repoDatosActividad.borrar(datosActividad);
            }

            this.repoBatch.borrar(batchDatosActividad);

        }

        return response;
    }


}
