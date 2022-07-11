package persistencia.ReposMemoria;

import domain.Organizacion.Miembro;

import java.util.List;

public class RepoMiembro {

    List<Miembro> listMiembro;

    public Miembro buscar(String dni) {

        Miembro elMiem = new Miembro();

        int dniI = Integer.parseInt(dni);

            for (Miembro miem : listMiembro) {

                if ( miem.getNroDocumento() == dniI ){

                    elMiem = miem;

                }
            }

            return elMiem;

        }
    }

