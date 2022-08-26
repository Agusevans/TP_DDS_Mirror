package persistencia.testMemoData;

import domain.entities.EntidadPersistente;
import domain.entities.usuario.Contacto;
import domain.entities.usuario.Persona;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPersona {

    private static List<Persona> personas = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(personas.size() == 0) {

            Contacto c_federico = new Contacto();
            c_federico.setEmail("fcaminoa@email.com");
            c_federico.setTelefono(12345678);
            Persona federico = new Persona();
            federico.getUsuario().setNombreUsuario("fede123");
            federico.getUsuario().setContrasenia("fede123");
            federico.getUsuario().setId(1);
            federico.setNombre("Federico");
            federico.setApellido("Caminoa");
            federico.setDireccion("Av Corrientes 1234");
            federico.setFechaNac(LocalDate.parse("1998-11-11"));
            federico.setContactos(c_federico);

            Contacto c_esteban = new Contacto();
            c_esteban.setEmail("equintana@email.com");
            c_esteban.setTelefono(12345678);
            Persona esteban = new Persona();
            esteban.getUsuario().setNombreUsuario("est123");
            esteban.getUsuario().setContrasenia("est123");
            esteban.getUsuario().setId(2);
            esteban.setNombre("Esteban");
            esteban.setApellido("Quintana");
            esteban.setDireccion("Av Rivadavia 12340");
            esteban.setFechaNac(LocalDate.parse("1998-10-10"));
            esteban.setContactos(c_esteban);

            Contacto c_nicolas = new Contacto();
            c_nicolas.setEmail("nbertoni@email.com");
            c_nicolas.setTelefono(12345678);
            Persona nicolas = new Persona();
            nicolas.getUsuario().setNombreUsuario("nico123");
            nicolas.getUsuario().setContrasenia("nico123");
            nicolas.getUsuario().setId(3);
            nicolas.setNombre("Nicolas");
            nicolas.setApellido("Bertoni");
            nicolas.setDireccion("Av Belgrano 4321");
            nicolas.setFechaNac(LocalDate.parse("1998-09-09"));
            nicolas.setContactos(c_nicolas);

            addAll(federico, esteban, nicolas);
        }
        return (List<EntidadPersistente>)(List<?>) personas;
    }

    private static void addAll(Persona ... personas){
        Collections.addAll(DataPersona.personas, personas);
    }

}
