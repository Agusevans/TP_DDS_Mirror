package server;

import persistencia.EntityManagerHelper;
import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Server {

   /*
    public static void startEntityManagerFactory() throws URISyntaxException {
        //https://stackoverflow.com/questions/8836834/read-environment-variables-in-persistence-xml-file
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[]{
                //, "javax.persistence.jdbc.url",
                //"javax.persistence.jdbc.user", "javax.persistence.jdbc.password",
                "DATABASE_URL",
                "hibernate.show_sql",
                "ddlauto",
                "javax.persistence.jdbc.driver",
                "javax.persistence.schema-generation.database.action"
        };

        for (String key : keys) {
            if (env.containsKey(key)) {

                if (key.equals("DATABASE_URL")) {
                    // https://devcenter.heroku.com/articles/connecting-heroku-postgres#connecting-in-java
                    String value = env.get(key);

                    URI dbUri = new URI(value);

                    String username = dbUri.getUserInfo().split(":")[0];
                    String password = dbUri.getUserInfo().split(":")[1];
                    //javax.persistence.jdbc.url=jdbc:postgresql://localhost/dblibros
                    value = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();// + "?sslmode=require";
                    configOverrides.put("javax.persistence.jdbc.url", value);
                    configOverrides.put("javax.persistence.jdbc.user", username);
                    configOverrides.put("javax.persistence.jdbc.password", password);
                    //  configOverrides.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

                }

                if (key.equals("ddlauto")) {
                    String value = env.get(key);
                    configOverrides.put("javax.persistence.schema-generation.database.action", value);
                }


            }
        }

    }

    */

    private static int getHerokuAssignedPort() {
        String herokuPort = System.getenv("PORT");
        if (herokuPort != null) {
            return Integer.parseInt(herokuPort);
        }
        return 4567;
    }


    public static void main(String[] args) throws URISyntaxException {
        //Spark.port(9000);
        EntityManagerHelper.getEntityManager();
        Spark.port(getHerokuAssignedPort());

        Router.init();
    }


}