package server;

import domain.Controllers.*;
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

        //OrganizacionController
        OrganizacionController orgController = new OrganizacionController();

        Spark.get("/organizacion", orgController::mostrar);
        Spark.post("/organizacion/:id", orgController::modificar);
        Spark.post("/organizacion", orgController::guardar);
        Spark.delete("/organizacion/:id", orgController::eliminar);

        //FactorEmisionController

        //AgenteSocialController

        //MedicionController


    }
}