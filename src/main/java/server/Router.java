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
        Spark.get("/organizacion/:razonSocial", orgController::readOrg);
        Spark.post("/organizacion/:razonSocial", orgController::updateOrg);
        Spark.delete("/organizacion/:razonSocial", orgController::deleteOrg);
        Spark.post("/organizacion/:razonSocial/sector", orgController::createSect);
        Spark.get("/organizacion/:razonSocial/sector/:id_s", orgController::readSect);
        Spark.post("/organizacion/:razonSocial/sector/:id_s", orgController::updateSect);
        Spark.delete("/organizacion/:razonSocial/sector/:id_s", orgController::deleteSect);


        //FactorEmisionController
        Spark.post("/factoremision/:id", factorEmisionController::update);

        //AgenteSocialController
        Spark.post("/agente", agenteSocialController::create);
        Spark.get("/agente/:id", agenteSocialController::read);
        Spark.post("/agente/:id", agenteSocialController::update);
        Spark.delete("/agente/:id", agenteSocialController::delete);

        //MedicionController
        Spark.get("/mediciones",medicionController::filterMed);
        Spark.get("/mediciones/:id",medicionController::listMed);

        //batchMediciones
        Spark.post("/mediciones",medicionController::batchAlta);
        Spark.delete("/mediciones",medicionController::batchBaja);
    }
}