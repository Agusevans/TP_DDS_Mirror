package persistencia.ReposMemoria;

import domain.Organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class RepoOrganizacion {

    List<Organizacion> listOrganizacion = new ArrayList<Organizacion>();


    public void agregar(Organizacion organizacion) {

        listOrganizacion.add(organizacion);

    }

    public Organizacion buscar(String razonSocial) {

        Organizacion laOrg = new Organizacion();

        for (Organizacion org : listOrganizacion) {

            if (org.getRazonSocial() == razonSocial);
            {

                laOrg = org;

            }
        }

        return laOrg;
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

    }
}
