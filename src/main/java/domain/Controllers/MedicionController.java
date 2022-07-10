package domain.Controllers;

import domain.Actividad.Alcance;
import domain.Actividad.DatosActividad;
import persistencia.ReposMemoria.RepoMedicion;
import persistencia.ReposMemoria.RepoOrganizacion;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class MedicionController {

    RepoMedicion repoMedicion =new RepoMedicion();

    public List<DatosActividad> filterMed(Request request, Response response){
        List<DatosActividad> filterList = repoMedicion.filter(request.queryParams());
        return filterList;
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
