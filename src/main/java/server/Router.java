package server;

import middleware.AuthMiddleware;
import persistencia.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){

        OrganizacionController orgController = new OrganizacionController();
        FactorEmisionController factorEmisionController = new FactorEmisionController();
        AgenteSectorialController agenteSectorialController = new AgenteSectorialController();
        MedicionController medicionController = new MedicionController();
        TrayectoController trayectoController = new TrayectoController();
        AuthMiddleware authMiddleware = new AuthMiddleware();

        //AuthMiddleware
        Spark.before("/miembros", authMiddleware::verificarSesion);
        Spark.before("/miembro/:id_m", authMiddleware::verificarSesion);
        Spark.before("/trayecto", authMiddleware::verificarSesion);
        Spark.before("/carga_mediciones", authMiddleware::verificarSesion);

        //LoginController
        LoginController loginController = new LoginController();
        Spark.get("/login", loginController::login, Router.engine);
        Spark.post("/login", loginController::loginPost);
        Spark.get("/logout", loginController::logout);
        Spark.get("/index", loginController::mostrarIndex, Router.engine);

        //OrganizacionController
        Spark.post("/organizacion", orgController::createOrg);
        Spark.get("/organizacion/:id_o", orgController::readOrg);
        Spark.post("/organizacion/:id_o", orgController::updateOrg);
        Spark.delete("/organizacion/:id_o", orgController::deleteOrg);
        Spark.get("/organizacion/:id_o/sector/:id_s", orgController::readSect);
        Spark.post("/organizacion/:id_o/sector/:id_s", orgController::updateSect);
        Spark.delete("/organizacion/:id_o/sector/:id_s", orgController::deleteSect);
        Spark.get("/miembros", orgController::readMiembros, Router.engine);
        Spark.get("/miembro/:id_m", orgController::readMiembro, Router.engine);
        //Spark.post("/organizacion/:id_o/sector/:id_s/miembro/:id_m", orgController::readMiembro);

        //TrayectoController
        Spark.get("/trayecto", trayectoController::create, Router.engine);
        Spark.post("/trayecto", trayectoController::save);
        Spark.delete("/trayecto/:id", trayectoController::delete);

        //FactorEmisionController
        Spark.post("/factor_emision/:id", factorEmisionController::update);

        //AgenteSectorialController
        Spark.post("/agente", agenteSectorialController::create);
        Spark.get("/agente/:id", agenteSectorialController::read);
        Spark.post("/agente/:id", agenteSectorialController::update);
        Spark.delete("/agente/:id", agenteSectorialController::delete);

        //MedicionController
        Spark.get("/carga_mediciones", medicionController::load, Router.engine);
        Spark.get("/mediciones",medicionController::filterMed);
        Spark.get("/mediciones/:id",medicionController::listMed);
        Spark.post("/batch",medicionController::batchAlta);
        Spark.delete("/batch/:id",medicionController::batchBaja);
    }
}