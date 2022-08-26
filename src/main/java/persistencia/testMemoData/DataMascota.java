package persistencia.testMemoData;

import db.EntityManagerHelper;
import domain.entities.EntidadPersistente;
import domain.entities.Mascota.Mascota;
import domain.entities.usuario.Persona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataMascota {

    private static List<Mascota> mascotas = new ArrayList<>();

    public static List<EntidadPersistente> getList() {
        if (mascotas.size() == 0) {

            Mascota perro = new Mascota();
            Persona federico = new Persona();
            federico.setNombre("Federico");
            federico.setApellido("Caminoa");
            perro.setId(1);
            perro.setNombre("Perro");
            perro.setPersona(federico);
            perro.setTipo("Siberiano");
            perro.setEdad(4);

            Mascota gato = new Mascota();
            gato.setId(2);
            gato.setNombre("Gato");
            gato.setPersona(federico);
            gato.setTipo("Siberiano");
            gato.setEdad(4);

            addAll(perro, gato);

            EntityManagerHelper.beginTransaction();

            EntityManagerHelper.getEntityManager().persist(perro); //SE PREPARA INSERT
            EntityManagerHelper.getEntityManager().persist(gato); //SE PREPARA INSERT
            EntityManagerHelper.getEntityManager().persist(federico); //SE PREPARA INSERT
            EntityManagerHelper.commit(); //SE EJECUTAN TODAS LAS SENTENCIAS
        }

        return (List<EntidadPersistente>) (List<?>) mascotas;
    }

    private static void addAll(Mascota... mascotas) {
        Collections.addAll(DataMascota.mascotas, mascotas);
    }


}