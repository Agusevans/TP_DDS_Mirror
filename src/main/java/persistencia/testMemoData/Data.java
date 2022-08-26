package persistencia.testMemoData;

import domain.entities.EntidadPersistente;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<EntidadPersistente> getData(Class type){
        List<EntidadPersistente> entidades = new ArrayList<>();
        if(type.getName().equals(Mascota.class.getName())){
            entidades = DataMascota.getList();
        }
        else{
            if(type.getName().equals(Persona.class.getName())){
                entidades = DataPersona.getList();
            }
        }

        return entidades;
    }
}
