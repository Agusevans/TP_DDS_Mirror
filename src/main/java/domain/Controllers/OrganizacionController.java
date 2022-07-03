package domain.Controllers;

import domain.Organizacion.ClasificacionOrg;
import domain.Organizacion.Organizacion;
import domain.Organizacion.TipoOrg;
import persistencia.ReposMemoria.RepoOrganizacion;
import spark.Request;
import spark.Response;

public class OrganizacionController {

    RepoOrganizacion repoOrganizacion = new RepoOrganizacion();

    public Response create(Request request, Response response) {
        Organizacion organizacion = new Organizacion();
        asignarAtributosA(organizacion, request);
        this.repoOrganizacion.add(organizacion); //se define en repo
        return response;
    }

    public Organizacion read(Request request, Response response) {
        //considerando que tiene id
        Organizacion organizacion = repoOrganizacion.search(request.queryParams("id"));
        return organizacion;
    }

    public Response update(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.search(request.queryParams("id"));
        asignarAtributosA(organizacion, request);
        this.repoOrganizacion.update(organizacion);
        return response;
    }

    public Response delete(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.search(request.queryParams("id"));
        if( organizacion != null){
            this.repoOrganizacion.delete(organizacion);
        }
        return response;
    }

    private void asignarAtributosA(Organizacion organizacion, Request request){
        if (request.queryParams("razonSocial") != null){
            organizacion.setRazonSocial(request.queryParams("razonSocial"));
        }

        if (request.queryParams("tipo") != null){
            organizacion.setTipo(TipoOrg.valueOf(request.queryParams("tipo")));
        }

        if (request.queryParams("clasificacion") != null){
            ClasificacionOrg clasificacionOrg = new ClasificacionOrg(request.queryParams("clasificacion"));
            organizacion.setClasificacion(clasificacionOrg);
        }

        //TODO: definir como entra un Punto por queryparams
    }
}
