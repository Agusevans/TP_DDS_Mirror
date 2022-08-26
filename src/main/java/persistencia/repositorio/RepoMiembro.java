package persistencia.repositorio;

import domain.Organizacion.Miembro;

import java.util.ArrayList;
import java.util.List;

public class RepoMiembro {

    List<Miembro> listMiembro = new ArrayList<Miembro>();

    public Miembro buscar(String dni) {

        Miembro elMiem = new Miembro();
        int dniI = Integer.parseInt(dni);

            for (Miembro miem : listMiembro) {
                if ( miem.getNroDocumento() == dniI ){
                    elMiem = miem;
                    break;
                }
            }

            return elMiem;
        }
    }

