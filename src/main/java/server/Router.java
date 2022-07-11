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
        AgenteSectorialController agenteSectorialController = new AgenteSectorialController();
        MedicionController medicionController = new MedicionController();

        Spark.post("/organizacion", orgController::createOrg);
        Spark.get("/organizacion/:razonSocial", orgController::readOrg);
        Spark.post("/organizacion/:razonSocial", orgController::updateOrg);
        Spark.delete("/organizacion/:razonSocial", orgController::deleteOrg);
        Spark.post("/organizacion/:razonSocial/sector", orgController::createSect);
        Spark.get("/organizacion/:razonSocial/sector/:nombre", orgController::readSect);
        Spark.post("/organizacion/:razonSocial/sector/:nombre", orgController::updateSect);
        Spark.delete("/organizacion/:razonSocial/sector/:nombre", orgController::deleteSect);
        Spark.post("/organizacion/:razonSocial/sector/:nombre/miembro/:dni", orgController::deleteSect);
        Spark.delete("/organizacion/:razonSocial/sector/:nombre/miembro/:dni", orgController::deleteSect);


        //FactorEmisionController
        Spark.post("/factoremision/:id", factorEmisionController::update);

        //AgenteSectorialController
        Spark.post("/agente", agenteSectorialController::create);
        Spark.get("/agente/:id", agenteSectorialController::read);
        Spark.post("/agente/:id", agenteSectorialController::update);
        Spark.delete("/agente/:id", agenteSectorialController::delete);

        //MedicionController
        Spark.get("/mediciones",medicionController::filterMed);
        Spark.get("/mediciones/:id",medicionController::listMed);

        //batchMediciones
        Spark.post("/mediciones",medicionController::batchAlta);
        Spark.delete("/mediciones",medicionController::batchBaja);
    }
}