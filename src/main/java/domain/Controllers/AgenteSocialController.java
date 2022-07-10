package domain.Controllers;

import domain.Organizacion.AgenteSocial;
import domain.Organizacion.Organizacion;
import domain.Organizacion.TipoTerritorio;
import spark.Request;
import spark.Response;

public class AgenteSocialController {

    RepoAgenteSocial repoAgenteSocial = new RepoAgenteSocial();

    public Response create(Request request, Response response) {
        AgenteSocial agenteSocial= new AgenteSocial();
        asignarAtributosA(agenteSocial, request);
        this.repoAgenteSocial.add(agenteSocial); //se define en repo
        return response;
    }

    public AgenteSocial read(Request request, Response response) {
        //considerando que tiene id
        AgenteSocial agenteSocial = repoAgenteSocial.search(request.queryParams("id"));
        return agenteSocial;
    }

    public Response update(Request request, Response response) {
        AgenteSocial agenteSocial = this.repoAgenteSocial.search(request.queryParams("id"));
        asignarAtributosA(agenteSocial, request);
        this.repoAgenteSocial.update(agenteSocial);
        return response;
    }

    public Response delete(Request request, Response response) {
        AgenteSocial agenteSocial = this.repoAgenteSocial.search(request.queryParams("id"));
        if( agenteSocial != null){
            this.repoAgenteSocial.delete(agenteSocial);
        }
        return response;
    }

    private void asignarAtributosA(AgenteSocial agenteSocial, Request request){

        if (request.queryParams("nombre") != null){
            agenteSocial.setNombre(request.queryParams("nombre"));
        }

        if (request.queryParams("tipo") != null){
            agenteSocial.setTipoTerritorio(TipoTerritorio.valueOf(request.queryParams("tipo")));
        }

        if (request.queryParams("organizaciones") != null){
            //TODO: ver si entra un listado de organizaciones (y ver como agregarlo), o si se van agregando de a uno
            //agenteSocial.setOrganizaciones(request.queryParams("organizaciones"));
        }

        if (request.queryParams("email") != null){
            agenteSocial.setEmail(request.queryParams("email"));
        }

        if (request.queryParams("periodo") != null){
            agenteSocial.setPeriodo(request.queryParams("periodo"));
        }

    }



}
