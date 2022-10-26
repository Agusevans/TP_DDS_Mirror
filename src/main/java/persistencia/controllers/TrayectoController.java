package persistencia.controllers;

import domain.Organizacion.*;
import domain.Trayecto.MedioTransporte;
import domain.Trayecto.Punto;
import domain.Trayecto.Tramo;
import domain.Trayecto.Trayecto;
import persistencia.factories.FactoryRepoMiembro;
import persistencia.factories.FactoryRepoUsuario;
import persistencia.factories.FactoryRepositorio;
import persistencia.repositorio.RepoMiembro;
import persistencia.repositorio.RepoUsuario;
import persistencia.repositorio.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrayectoController {

    Repositorio<Trayecto> repoTrayecto;
    RepoMiembro repoMiembro;
    Repositorio<MedioTransporte> repoTransporte;
    RepoUsuario repoUsuario;

    public TrayectoController(){
        this.repoTrayecto = FactoryRepositorio.get(Trayecto.class);
        this.repoMiembro = FactoryRepoMiembro.get();
        this.repoTransporte = FactoryRepositorio.get(MedioTransporte.class);
        this.repoUsuario = FactoryRepoUsuario.get();
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
        List<MedioTransporte> medioTransportes = this.repoTransporte.buscarTodos();
        parametros.put("medio_transportes", medioTransportes);

        return new ModelAndView(parametros, "trayecto.hbs");
    }

    public Response save(Request request, Response response) {
        Trayecto trayecto = new Trayecto();
        asignarAtributosA(trayecto, request);
        this.repoTrayecto.agregar(trayecto);
        //response.redirect("/");
        return response;
    }

    public Response delete(Request request, Response response) {
        Trayecto trayecto = this.repoTrayecto.buscar(Integer.parseInt(request.params("id")));
        if( trayecto != null){
            this.repoTrayecto.borrar(trayecto);
        }
        return response;
    }

    private void asignarAtributosA(Trayecto trayecto, Request request){
        if (request.queryParams("compartido") == "true"){
            if(request.queryParams("miembro_1") != null){
            trayecto.agregarIntegrante(this.repoMiembro.buscarMiembro(Integer.parseInt(request.queryParams("miembro_1"))));
            }
            if(request.queryParams("miembro_2") != null) {
                trayecto.agregarIntegrante(this.repoMiembro.buscarMiembro(Integer.parseInt(request.queryParams("miembro_2"))));
            }
            if(request.queryParams("miembro_3") != null) {
                trayecto.agregarIntegrante(this.repoMiembro.buscarMiembro(Integer.parseInt(request.queryParams("miembro_3"))));
            }
        }

        if (request.queryParams("transporte_1") != null){
            MedioTransporte transporte = this.repoTransporte.buscar(Integer.parseInt(request.queryParams("transporte_1")));
            Punto punto_ini = new Punto(request.queryParams("punto_inicio_1"));
            Punto punto_fin = new Punto(request.queryParams("punto_fin_1"));

            Tramo tramo1 = new Tramo(transporte, punto_ini, punto_fin);
            trayecto.agregarTramo(tramo1);
        }
        if (request.queryParams("transporte_2") != null){
            MedioTransporte transporte = this.repoTransporte.buscar(Integer.parseInt(request.queryParams("transporte_2")));
            Punto punto_ini = new Punto(request.queryParams("punto_inicio_2"));
            Punto punto_fin = new Punto(request.queryParams("punto_fin_2"));

            Tramo tramo1 = new Tramo(transporte, punto_ini, punto_fin);
            trayecto.agregarTramo(tramo1);
        }
        if (request.queryParams("transporte_3") != null){
            MedioTransporte transporte = this.repoTransporte.buscar(Integer.parseInt(request.queryParams("transporte_3")));
            Punto punto_ini = new Punto(request.queryParams("punto_inicio_3"));
            Punto punto_fin = new Punto(request.queryParams("punto_fin_3"));

            Tramo tramo1 = new Tramo(transporte, punto_ini, punto_fin);
            trayecto.agregarTramo(tramo1);
        }

    }

}
