package domain.Controllers;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.TipoTerritorio;
import persistencia.ReposMemoria.RepoAgenteSectorial;
import spark.Request;
import spark.Response;

public class AgenteSectorialController {

    RepoAgenteSectorial repoAgenteSectorial = new RepoAgenteSectorial();

    public Response create(Request request, Response response) {
        AgenteSectorial agenteSocial= new AgenteSectorial();
        asignarAtributosA(agenteSocial, request);
        this.repoAgenteSectorial.add(agenteSocial); //se define en repo
        return response;
    }

    public AgenteSectorial read(Request request, Response response) {
        //considerando que tiene id
        AgenteSectorial agenteSectorial = repoAgenteSectorial.search(request.queryParams("id"));
        return agenteSectorial;
    }

    public Response update(Request request, Response response) {
        AgenteSectorial agenteSocial = this.repoAgenteSectorial.search(request.queryParams("id"));
        asignarAtributosA(agenteSocial, request);
        this.repoAgenteSectorial.update(agenteSocial);
        return response;
    }

    public Response delete(Request request, Response response) {
        AgenteSectorial agenteSocial = this.repoAgenteSectorial.search(request.queryParams("id"));
        if( agenteSocial != null){
            this.repoAgenteSectorial.delete(agenteSocial);
        }
        return response;
    }

    private void asignarAtributosA(AgenteSectorial agenteSectorial, Request request){

        if (request.queryParams("nombre") != null){
            agenteSectorial.setNombre(request.queryParams("nombre"));
        }

        if (request.queryParams("tipo") != null){
            agenteSectorial.setTipoTerritorio(TipoTerritorio.valueOf(request.queryParams("tipo")));
        }

        if (request.queryParams("organizaciones") != null){
            //TODO: ver si entra un listado de organizaciones (y ver como agregarlo), o si se van agregando de a uno
            //agenteSocial.setOrganizaciones(request.queryParams("organizaciones"));
        }

        if (request.queryParams("email") != null){
            agenteSectorial.setEmail(request.queryParams("email"));
        }

        if (request.queryParams("periodo") != null){
            agenteSectorial.setPeriodo(request.queryParams("periodo"));
        }

    }



}
