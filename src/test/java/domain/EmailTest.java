package domain;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.Comunicacion.Email;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void enviarMail(){

        AgenteSectorial agenteSectorial = new AgenteSectorial();
        agenteSectorial.setEmail("javiquintana99@hotmail.com");

        Email email = new Email();
        email.notificar(agenteSectorial,12.3f);

        //TODO: Probar que se envio el mail
    }
}
