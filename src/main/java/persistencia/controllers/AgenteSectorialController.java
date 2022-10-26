package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Organizacion.*;
import persistencia.factories.FactoryRepoAgente;
import persistencia.repositorio.RepoAgenteSectorial;
import spark.Request;
import spark.Response;


public class AgenteSectorialController {

    RepoAgenteSectorial repoAgenteSectorial;
    Gson gson;

    public AgenteSectorialController(){
        this.repoAgenteSectorial = FactoryRepoAgente.get();
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").create();
    }

    public Response create(Request request, Response response) {

        AgenteSectorial agenteSectorial = gson.fromJson(request.body(),AgenteSectorial.class);
        this.repoAgenteSectorial.agregar(agenteSectorial);

        return response;
    }

    public String read(Request request, Response response) {

        AgenteSectorial agenteSectorial = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id")));
        String jsonOrg = gson.toJson(agenteSectorial);

        response.type("application/json");
        return jsonOrg;
    }

    public Response update(Request request, Response response) {

        if (this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id"))) != null){
            AgenteSectorial agenteSectorial = gson.fromJson(request.body(),AgenteSectorial.class);
            agenteSectorial.setId(Integer.parseInt(request.params("id")));
            this.repoAgenteSectorial.actualizar(agenteSectorial);
        }

        return response;
    }

    public Response delete(Request request, Response response) {

        AgenteSectorial agenteSectorial = this.repoAgenteSectorial.buscar(Integer.parseInt(request.params("id")));

        if( agenteSectorial != null){
            this.repoAgenteSectorial.borrar(agenteSectorial);
        }

        return response;
    }
}
