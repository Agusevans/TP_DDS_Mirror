package domain.Controllers;

import domain.Actividad.FactorEmision;
import domain.Actividad.Unidad;
import domain.Organizacion.Organizacion;
import persistencia.ReposMemoria.RepoFactorEmision;
import spark.Request;
import spark.Response;

public class FactorEmisionController {

    RepoFactorEmision repoFactorEmision = new RepoFactorEmision();

    public Response update(Request request, Response response){
        FactorEmision factorEmision = this.repoFactorEmision.search(request.queryParams("id"));
        asignarAtributosA(factorEmision, request);
        this.repoFactorEmision.update(factorEmision);
        return response;
    }

    private void asignarAtributosA(FactorEmision factorEmision, Request request){

        if (request.queryParams("valor") != null){
            factorEmision.setValor(Float.valueOf(request.queryParams("valor")));
        }

        if (request.queryParams("unidad") != null){
            factorEmision.setUnidad(Unidad.valueOf(request.queryParams("unidad")));
        }
    }

}
