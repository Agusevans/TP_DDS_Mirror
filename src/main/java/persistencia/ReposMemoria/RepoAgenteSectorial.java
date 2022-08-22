package persistencia.ReposMemoria;

import domain.Organizacion.AgenteSectorial;

import java.util.ArrayList;
import java.util.List;

public class RepoAgenteSectorial {

    List<AgenteSectorial> agenteSectorialList = new ArrayList<AgenteSectorial>();

    public void add(AgenteSectorial agente) {
        agenteSectorialList.add(agente);
    }

    public AgenteSectorial search(String nombre) {
        AgenteSectorial agenteBuscado = new AgenteSectorial();
        for (AgenteSectorial agente : agenteSectorialList)
            if (agente.getNombre().equals(nombre)) {
                agenteBuscado = agente;
                break;
            }
        return agenteBuscado;
    }

    public void update(AgenteSectorial agente) {
        AgenteSectorial agenteBuscado = this.search(agente.getNombre());
        this.delete(agenteBuscado);
        this.add(agente);
    }

    public void delete(AgenteSectorial agente) {
        agenteSectorialList.remove(agente);
    }

}
