package domain.Organizacion.Comunicacion;

import domain.Organizacion.AgenteSocial;

public interface Comunicacion {
    public void notificar(AgenteSocial agenteTerritorial, String mensaje);
}
