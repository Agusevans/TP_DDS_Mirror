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
        FactorEmisionController factorEmisionController = new FactorEmisionController();
        AgenteSocialController agenteSocialController = new AgenteSocialController();
        MedicionController medicionController = new MedicionController();

        Spark.post("/organizacion", orgController::createOrg);
        Spark.get("/organizacion/:id", orgController::readOrg);
        Spark.post("/organizacion/:id", orgController::updateOrg);
        Spark.delete("/organizacion/:id", orgController::deleteOrg);
        Spark.post("/organizacion/:id_o/sector", orgController::createSect);
        Spark.get("/organizacion/:id_o/sector/:id_s", orgController::readSect);
        Spark.post("/organizacion/:id_o/sector/:id_s", orgController::updateSect);
        Spark.delete("/organizacion/:id_o/sector/:id_s", orgController::deleteSect);


        //FactorEmisionController
        Spark.post("/factoremision/:id", factorEmisionController::update);

        //AgenteSocialController
        Spark.post("/agente", agenteSocialController::create);
        Spark.get("/agente/:id", agenteSocialController::read);
        Spark.post("/agente/:id", agenteSocialController::update);
        Spark.delete("/agente/:id", agenteSocialController::delete);

        //MedicionController


    }
}