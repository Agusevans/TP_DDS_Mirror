package domain.Controllers;

import domain.Organizacion.*;
import persistencia.ReposMemoria.RepoMiembro;
import persistencia.ReposMemoria.RepoOrganizacion;
import persistencia.ReposMemoria.RepoSector;
import spark.Request;
import spark.Response;

public class OrganizacionController {

    RepoOrganizacion repoOrganizacion = new RepoOrganizacion();
    RepoSector repoSector = new RepoSector();
    RepoMiembro repoMiembros = new RepoMiembro();

    public Response createOrg(Request request, Response response) {
        Organizacion organizacion = new Organizacion();
        asignarAtributosA(organizacion, request);
        agregarMiembrosAOrg(organizacion);
        this.repoOrganizacion.agregar(organizacion); //se define en repo
        //response.redirect("/organizacion/:id_o/sector") --> creado la org, se crean los sectores
        return response;
    }

    public Organizacion readOrg(Request request, Response response) {
        //considerando que tiene id
        Organizacion organizacion = repoOrganizacion.buscar(request.queryParams("razonSocial"));
        return organizacion;
    }

    public Response updateOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(request.queryParams("razonSocial"));
        asignarAtributosA(organizacion, request);
        this.repoOrganizacion.actualizar(organizacion);
        return response;
    }

    public Response deleteOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(request.queryParams("razonSocial"));
        if( organizacion != null){
            this.repoOrganizacion.borrar(organizacion);
        }
        return response;
    }

    public Response createSect(Request request, Response response) {
        Organizacion organizacion = repoOrganizacion.buscar(request.queryParams("razonSocial"));
        Miembro miembro = repoMiembros.buscar(request.queryParams("dni"));
        Sector sector = new Sector();
        asignarAtributosA(sector, request);
        agregarMiembrosASector(miembro,sector);
        organizacion.agregarSector(sector);
        this.repoSector.agregar(sector); //se define en repo
        this.repoOrganizacion.actualizar(organizacion); //puede no ser necesario, depende como se defina el repoOrg
        //organizacion.aceptarMiembros(request.queryParams("archivo"))
        //      --> al crear se recibe el archivo de miembros por queryparams (o un form)
        return response;
    }

    public Sector readSect(Request request, Response response) {
        //considerando que tiene id
        Sector sector = repoSector.buscar(request.queryParams("nombre"));
        return sector;
    }

    public Response updateSect(Request request, Response response) {
        Sector sector = this.repoSector.buscar(request.queryParams("nombre"));
        asignarAtributosA(sector, request);
        this.repoSector.actualizar(sector);
        return response;
    }


    public Response deleteSect(Request request, Response response) {
        Sector sector = this.repoSector.buscar(request.queryParams("nombre"));
        if( sector != null){
            this.repoSector.borrar(sector);
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

    private void agregarMiembrosASector(Miembro miembro, Sector sector){

        sector.agregarMiembro(miembro);
    }
}
