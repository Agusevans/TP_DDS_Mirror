package persistencia.ReposMemoria;

import domain.Organizacion.Organizacion;
import domain.Organizacion.Sector;

import java.util.List;

public class RepoOrganizacion {

    List<Organizacion> listOrganizacion;


    public void agregar(Organizacion organizacion) {

        listOrganizacion.add(organizacion);

    }

    public Organizacion buscar(String razonSocial) {

        for (Organizacion org : listOrganizacion) {

            if (org.getRazonSocial() == razonSocial);
            {

                return org;

            }
        }

    }

    public void actualizar(Organizacion organizacion) {

        for (Organizacion org : listOrganizacion) {

            if (org.getRazonSocial() == organizacion.getRazonSocial());
            {

                org = organizacion;

            }
        }

        return;

    }

    public void borrar(Organizacion organizacion) {

        int i = 0;
        int index = 0;

        for (Organizacion org : listOrganizacion) {

            i++;

            if (org.getRazonSocial() == organizacion.getRazonSocial());
            {

                index = i;

            }
        }

        listOrganizacion.remove(index);

        return;

    }
}
