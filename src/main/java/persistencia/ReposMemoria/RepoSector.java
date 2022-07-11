package persistencia.ReposMemoria;

import domain.Organizacion.Sector;

import java.util.ArrayList;
import java.util.List;

public class RepoSector {

    List<Sector> listSector = new ArrayList<Sector>();

    public void agregar(Sector sector) {

        listSector.add(sector);

    }

    public Sector buscar(String nombre) {

        Sector elSector = new Sector();

        for (Sector sec : listSector) {

            if (sec.getNombre() == nombre) ;
            {

                elSector = sec;

            }
        }

        return elSector;

    }

    public void actualizar(Sector sector) {

        for (Sector sec : listSector) {

            if (sec.getNombre() == sector.getNombre()) ;
            {

                sec = sector;

            }
        }

    }

    public void borrar(Sector sector) {

        int i = 0;
        int index = 0;

        for (Sector sec : listSector) {

            i++;

            if (sec.getNombre() == sector.getNombre()) ;
            {

                index = i;

            }
        }

        listSector.remove(index);
    }
}
