package persistencia.controllers;

import domain.Organizacion.*;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;
import org.jetbrains.annotations.NotNull;
import persistencia.factories.FactoryRepoMiembro;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoMiembro;
import persistencia.repositorio.RepoUsuario;
import persistencia.repositorio.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrayectoController {

    Repositorio<Trayecto> repoTrayecto;
    RepoMiembro repoMiembro;
    Repositorio<MedioTransporte> repoTransporte;
    RepoUsuario repoUsuario;
    Repositorio<Organizacion> repoOrganizacion;

    public TrayectoController(){
        this.repoTrayecto = FactoryRepositorio.get(Trayecto.class);
        this.repoMiembro = FactoryRepoMiembro.get();
        this.repoTransporte = FactoryRepositorio.get(MedioTransporte.class);
        this.repoUsuario = FactoryRepoUsuario.get();
        this.repoOrganizacion = FactoryRepositorio.get(Organizacion.class);
    }

    private void asignarUsuarioSiEstaLogueado(Request request, Map<String, Object> parametros){
        if(!request.session().isNew() && request.session().attribute("id") != null){
            Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));
            if( usuario != null){
                parametros.put("usuario", usuario);
            }
        }
    }

    public ModelAndView create(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        Usuario usuario = repoUsuario.buscar(request.session().attribute("id"));

        List<MedioTransporte> medioTransportes = this.repoTransporte.buscarTodos();
        List<Miembro> miembros = this.repoMiembro.buscarMiembrosDeOrg(usuario.getOrganizacion().getId());

        parametros.put("medio_transportes", medioTransportes);
        parametros.put("miembros", miembros);

        return new ModelAndView(parametros, "trayecto.hbs");
    }

    public Response save(Request request, Response response) throws IOException {
        Trayecto trayecto = new Trayecto();
        //TODO: Borrar trayecto anterior a cada miembro
        this.asignarAtributosA(trayecto, request);
        response.redirect("/success");
        return response;
    }

    public ModelAndView success(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        asignarUsuarioSiEstaLogueado(request, parametros);
        return new ModelAndView(parametros, "success.hbs");
    }

    public Response delete(Request request, Response response) {
        Trayecto trayecto = this.repoTrayecto.buscar(Integer.parseInt(request.params("id")));
        if( trayecto != null){
            this.repoTrayecto.borrar(trayecto);
        }
        return response;
    }

    private void asignarAtributosA(Trayecto trayecto,Request request) throws IOException {

        if(request.queryParams("cant").isEmpty())
            trayecto.setVecesRealizadoXMes(21); //hardcodeo por las dudas
        else
            trayecto.setVecesRealizadoXMes(Integer.parseInt(request.queryParams("cant")));

        for (int i=1; i<4; i++){
            if(!request.queryParams("transporte_"+i).equals("null")){
                MedioTransporte transporte = this.repoTransporte.buscar(Integer.parseInt(request.queryParams("transporte_"+i)));
                Punto punto_ini = new Punto(request.queryParams("punto_inicio_"+i));
                Punto punto_fin = new Punto(request.queryParams("punto_fin_"+i));

                Tramo tramo1 = new Tramo(transporte, punto_ini, punto_fin);
                trayecto.agregarTramo(tramo1);
            }
        }

        for(int i = 1; i<5; i++){
            if(!request.queryParams("miembro_"+i).equals("null")){
                Miembro miembro = this.repoMiembro.buscar(Integer.parseInt(request.queryParams("miembro_"+i)));
                miembro.agregarTrayecto(trayecto);
                this.repoMiembro.agregar(miembro);
            }
        }

    }

    public List<Miembro> filtrarMiembros(List<Miembro> miembros, Organizacion organizacion){
        List<Miembro> aux = new ArrayList<>();
        for (Miembro miembro: miembros) {
            if(miembro.getOrganizacionlist().contains(organizacion)){
                aux.add(miembro);
            }
        }

        return aux;
    }

}
