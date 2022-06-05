package ar.edu.frba.utn.dds.mihuella;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.ImplementadorFachadaOrg;
import domain.LectorArchivos;
import domain.LectorCSV;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.Collection;


public class CalculadorTrayecto {

    public static void main(String[] args) {


        ArgumentParser parser = ArgumentParsers.newFor("Checksum").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-m", "--datosOrg").required(true)
                .help("Archivo de datos organizaciones");
        parser.addArgument("-p", "--trayectos").required(true)
                .help("Archivo con trayectos");
        parser.addArgument("-p", "--transportes").required(true)
                .help("Archivo con transportes y paradas");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }


        System.out.println("Archivo de datos organizaciones " + ns.get("datosOrg"));
        System.out.println("Archivo con trayectos: " + ns.get("trayectos"));
        System.out.println("Archivo con transportes y paradas: " + ns.get("transportes"));


        // calcular huella de las actividades y el total
        LectorArchivos lectorCSV = new LectorCSV(ns.get("trayectos"), ','); //TODO revisar por donde entra el archivo de mediciones
/*        LectorArchivos lectorJSONOrg = new LectorJSON(ns.get("datosOrg" ...));
        LectorArchivos lectorJSONTrasporte = new LectorJSON(ns.get("transportes" ...));
*/
        FachadaOrg fachadaOrg = new ImplementadorFachadaOrg();

        Collection<Medible> trayectos = lectorCSV.leerMediciones();
        Float huellaCarbono = fachadaOrg.obtenerHU(trayectos);

        System.out.println("Huella de carbono: " + huellaCarbono);
    }

}
