package domain.Organizacion.Comunicacion;

import domain.Organizacion.AgenteTerritorial;

public interface Comunicacion {
    public void notificar(AgenteTerritorial agenteTerritorial, String mensaje);
}
