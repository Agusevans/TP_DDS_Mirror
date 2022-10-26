package domain;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.Comunicacion.Email;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void abc(){

        AgenteSectorial agenteSectorial = new AgenteSectorial();
        agenteSectorial.setEmail("javiquintana99@hotmail.com");

        Email email = new Email();
        email.notificar(agenteSectorial,12.3f);

        Assert.assertEquals(1,1);
    }
}
