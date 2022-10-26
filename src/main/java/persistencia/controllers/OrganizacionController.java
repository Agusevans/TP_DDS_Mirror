package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import domain.Organizacion.Sector;
import domain.Organizacion.Usuario;
import persistencia.factories.FactoryRepoMiembro;
import persistencia.factories.FactoryRepoSector;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoMiembro;
import persistencia.repositorio.RepoSector;
import persistencia.repositorio.RepoUsuario;
import persistencia.repositorio.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizacionController {

    private final Repositorio<Organizacion> repoOrganizacion;
    private final RepoSector repoSector;
    private final RepoMiembro repoMiembro;
    private final RepoUsuario repoUsuario;
    Gson gson;

    public OrganizacionController(){
        this.repoSector = FactoryRepoSector.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
        this.repoMiembro = FactoryRepoMiembro.get();
        this.repoUsuario = FactoryRepoUsuario.get();
        this.gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").excludeFieldsWithoutExposeAnnotation().create();
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public Response createOrg(Request request, Response response) {
        Organizacion organizacion = gson.fromJson(request.body(),Organizacion.class);
        this.repoOrganizacion.agregar(organizacion);
        for (Sector sector:organizacion.getSectores()) {
            this.repoSector.agregar(sector);
            //TODO: ver como agregar miembros ya creados a un sector por crearse
        }

        return response;
    }

    public String readOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
        String jsonOrg = gson.toJson(organizacion);

        response.type("application/json");
        return jsonOrg;
    }

    public Response updateOrg(Request request, Response response) {
        if (this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o"))) != null){
        Organizacion organizacion = gson.fromJson(request.body(),Organizacion.class);
        organizacion.setId(Integer.parseInt(request.params("id_o")));
        this.repoOrganizacion.actualizar(organizacion);
        }

        return response;
    }

    public Response deleteOrg(Request request, Response response) {
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
        if( organizacion != null){
            this.repoOrganizacion.borrar(organizacion);
        }
        return response;
    }

    /*
    public Response createSect(Request request, Response response) {
        Organizacion organizacion = readOrg(request, response);
        Sector sector = new Sector();
        asignarAtributosA(sector, request);
        //Miembro miembro = readMiembro(request, response);
        //agregarMiembroASector(miembro,sector);
        organizacion.agregarSector(sector);
        this.repoSector.agregar(sector); //se define en repo
        this.repoOrganizacion.actualizar(organizacion); //puede no ser necesario, depende como se defina el repoOrg
        //organizacion.aceptarMiembros(request.queryParams("archivo"))
        //      --> al crear se recibe el archivo de miembros por queryparams (o un form)
        return response;
    }
    */

    public String readSect(Request request, Response response) {
        Sector sector = repoSector.buscarSector(request.params("id_s"), Integer.parseInt(request.params("id_o")));

        String jsonOrg = gson.toJson(sector);

        response.type("application/json");
        return jsonOrg;
    }

    public Response updateSect(Request request, Response response) {

        if (this.repoSector.buscarSector(request.params("id_s"), Integer.parseInt(request.params("id_o"))) != null){
            Sector sector = gson.fromJson(request.body(),Sector.class);
            sector.setId(Integer.parseInt(request.params("id_s")));
            this.repoSector.actualizar(sector);
        }

        return response;

    }

    public Response deleteSect(Request request, Response response) {
        Sector sector = repoSector.buscarSector(request.params("id_s"), Integer.parseInt(request.params("id_o")));
        if( sector != null){
            this.repoSector.borrar(sector);
        }
        return response;
    }

    private void agregarMiembrosAOrg(Organizacion organizacion){

    }

    private void agregarMiembroASector(Miembro miembro, Sector sector){

        sector.agregarMiembro(miembro);
    }

    public ModelAndView readMiembros (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        List<Miembro> miembros = this.repoMiembro.buscarTodos();
        parametros.put("miembros", miembros);
        return new ModelAndView(parametros, "miembros.hbs");
    }
    public ModelAndView readMiembro (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));
        parametros.put("miembro", miembro);
        return new ModelAndView(parametros, "miembro.hbs");
    }
}
