package domain.Controllers;

import domain.Organizacion.*;
import persistencia.ReposMemoria.RepoOrganizacion;
import spark.Request;
import spark.Response;

import java.util.List;

public class OrganizacionController {

    RepoOrganizacion repoOrganizacion = new RepoOrganizacion();
    RepoSector repoSector = new RepoSector();
    RepoMiembros repoMiembros = new RepoMiembros();

    public Response createOrg(Request request, Response response) {
        Organizacion organizacion = new Organizacion();
        asignarAtributosA(organizacion, request);
        agregarMiembrosAOrg(organizacion);
        this.repoOrganizacion.add(organizacion); //se define en repo
        //response.redirect("/organizacion/:id_o/sector") --> creado la org, se crean los sectores
        return response;
    }

    public Organizacion readOrg(Request request, Response response) {
        //considerando que tiene id
        Organizacion organizacion = repoOrganizacion.search(request.queryParams("id"));
        return organizacion;
    }

    public Response updateOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.search(request.queryParams("id"));
        asignarAtributosA(organizacion, request);
        this.repoOrganizacion.update(organizacion);
        return response;
    }


    public Response deleteOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.search(request.queryParams("id"));
        if( organizacion != null){
            this.repoOrganizacion.delete(organizacion);
        }
        return response;
    }

    public Response createSect(Request request, Response response) {
        Organizacion organizacion = repoOrganizacion.search(request.queryParams("id_o"));
        Sector sector = new Sector();
        asignarAtributosA(sector, request);
        agregarMiembrosASector(sector,organizacion);
        organizacion.agregarSector(sector);
        this.repoSector.add(sector); //se define en repo
        this.repoOrganizacion.update(organizacion); //puede no ser necesario, depende como se defina el repoOrg
        //organizacion.aceptarMiembros(request.queryParams("archivo"))
        //      --> al crear se recibe el archivo de miembros por queryparams (o un form)
        return response;
    }

    public Sector readSect(Request request, Response response) {
        //considerando que tiene id
        Sector sector = repoSector.search(request.queryParams("id_s"));
        return sector;
    }

    public Response updateSect(Request request, Response response) {
        Sector sector = this.repoSector.search(request.queryParams("id_s"));
        asignarAtributosA(sector, request);
        this.repoSector.update(sector);
        return response;
    }


    public Response deleteSect(Request request, Response response) {
        Sector sector = this.repoSector.search(request.queryParams("id_s"));
        if( sector != null){
            this.repoSector.delete(sector);
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

    private void asignarAtributosA(Sector sector, Request request){
        if (request.queryParams("nombre") != null){
            sector.setNombre(request.queryParams("nombre"));
        }

        if (request.queryParams("actividad") != null){
            sector.setActividad(request.queryParams("actividad"));
        }

    }

    private void agregarMiembrosAOrg(Organizacion organizacion){

    }

    private void agregarMiembrosASector(Sector sector, Organizacion organizacion){
        List<Miembro> miembros = repoMiembros.listBy(organizacion);
        for (Miembro miembro:miembros) {
            sector.agregarMiembro(miembro);
        }
    }
}
