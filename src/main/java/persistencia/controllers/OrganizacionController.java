package persistencia.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Actividad.*;
import domain.Organizacion.*;
import domain.Trayecto.CalculadorHUTrayectos;
import domain.Trayecto.Trayecto;
import persistencia.factories.*;
import persistencia.repositorio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizacionController {

    private final Repositorio<Organizacion> repoOrganizacion;
    private final RepoSector repoSector;
    private final RepoMiembro repoMiembro;
    private final RepoUsuario repoUsuario;
    private final Repositorio<Actividad> repoActividad;
    private final RepoDatosActividad repoDatosActividad;
    Gson gson;

    public OrganizacionController(){
        this.repoSector = FactoryRepoSector.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
        this.repoMiembro = FactoryRepoMiembro.get();
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoActividad = FactoryRepositorio.get(Actividad.class);
        this.repoDatosActividad = FactoryRepoDatosActividad.get();
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

    public void agregarOrgAMiembros(Organizacion organizacion){
        for (Miembro miembro : organizacion.obtenerMiembros()) {
            miembro.agregarOrganizacion(organizacion);
        }
    }

    public Response createOrg(Request request, Response response) throws Exception {
        Organizacion organizacion = gson.fromJson(request.body(),Organizacion.class);

        this.agregarOrgAMiembros(organizacion);
        this.repoOrganizacion.agregar(organizacion);

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
            this.agregarOrgAMiembros(organizacion);
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
        Sector sector = repoSector.buscar(Integer.parseInt(request.params("id_s")));

        String jsonOrg = gson.toJson(sector);

        response.type("application/json");
        return jsonOrg;
    }

    public Response updateSect(Request request, Response response) {

        if (this.repoSector.buscar(Integer.parseInt(request.params("id_s"))) != null){
            Sector sector = gson.fromJson(request.body(),Sector.class);
            sector.setId(Integer.parseInt(request.params("id_s")));
            Organizacion org = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));
            this.agregarOrgAMiembros(org);
            this.repoSector.actualizar(sector);
        }

        return response;

    }

    public Response deleteSect(Request request, Response response) {
        Sector sector = repoSector.buscar(Integer.parseInt(request.params("id_s")));
        if( sector != null){
            this.repoSector.borrar(sector);
        }
        return response;
    }

    public Response updateMiembro(Request request, Response response) {
        Miembro miembro = gson.fromJson(request.body(), Miembro.class);
        miembro.setId(Integer.parseInt(request.params("id_m")));
        this.repoMiembro.actualizar(miembro);

        return response;
    }

    public ModelAndView readMiembros (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        List<Miembro> miembros = this.repoMiembro.buscarMiembrosDeOrg(usuario.getOrganizacion().getId());
        parametros.put("miembros", miembros);
        return new ModelAndView(parametros, "miembros.hbs");
    }

    public ModelAndView readMiembro (Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);

        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));
        Organizacion organizacion = this.repoOrganizacion.buscar(usuario.getOrganizacion().getId());

        List<Trayecto> trayectos = filtrarTrayectosPorOrg(organizacion, miembro);

        parametros.put("miembro", miembro);
        parametros.put("trayectos", trayectos);
        return new ModelAndView(parametros, "miembro.hbs");
    }

    public List<Trayecto> filtrarTrayectosPorOrg(Organizacion organizacion, Miembro miembro){

        CalculadorHUTrayectos calculador = organizacion.getCalculadorHUTrayectos();
        return calculador.trayectosDeLaOrg(miembro.getTrayectos());
    }

    public Response updateSectorMiembro(Request request, Response response) throws Exception {

        Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.params("id_m")));
        Sector sector = this.repoSector.buscar(Integer.parseInt(request.params("id_s")));
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.params("id_o")));

        organizacion.aceptarMiembro(miembro, sector);
        this.repoOrganizacion.actualizar(organizacion);

        return response;
    }

    public Response agregarMiembro(Request request, Response response) throws Exception {
        Miembro miembro = gson.fromJson(request.body(), Miembro.class);
        Organizacion organizacion = this.repoOrganizacion.buscar(Integer.parseInt(request.queryParams("organizacion")));
        Sector sector = this.repoSector.buscar(Integer.parseInt(request.queryParams("sector")));
        organizacion.aceptarMiembro(miembro, sector);
        this.repoOrganizacion.actualizar(organizacion);
        return response;
    }

}
