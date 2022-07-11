package domain.Controllers;

import domain.Actividad.DatosActividad;
import persistencia.ReposMemoria.RepoMedicion;
import spark.Request;
import spark.Response;

import java.util.*;

public class MedicionController {

    RepoMedicion repoMedicion =new RepoMedicion();

    public List<DatosActividad> filterMed(Request request, Response response){ //TODO:Fixear parametros
        //List<DatosActividad> filterList = repoMedicion.filter(request.queryParams());
        //return filterList;
        return repoMedicion.getMediciones();
    }
    public List<DatosActividad> listMed(Request request, Response response){
        List<DatosActividad> listMed = repoMedicion.list(request.body());
        //TODO: debe paginarse.
        return listMed;
    }
    //Batch
    public Response batchAlta(Request request, Response response){
        repoMedicion.addAll(request.body());
        return response;
    }
    public Response batchBaja(Request request, Response response){
        repoMedicion.deleteAll(request.body());
        return response;
    }


}
