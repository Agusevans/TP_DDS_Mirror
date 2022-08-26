package persistencia.repositorio;

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
            if (org.getRazonSocial().equals(razonSocial)) {
                laOrg = org;
                break;
            }
        }

        return laOrg;
    }

    public void actualizar(Organizacion organizacion) {

        for (Organizacion org : listOrganizacion) {
            if (org.getRazonSocial() == organizacion.getRazonSocial()) {
                org = organizacion;
                break;
            }
        }

    }

    public void borrar(Organizacion organizacion) {
        this.listOrganizacion.remove(organizacion);
    }
}
