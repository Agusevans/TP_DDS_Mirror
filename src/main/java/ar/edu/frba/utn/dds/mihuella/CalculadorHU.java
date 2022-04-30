package ar.edu.frba.utn.dds.mihuella;

import ar.edu.frba.utn.dds.mihuella.fachada.FachadaOrg;
import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.LectorArchivos;
import domain.LectorCSV;
import domain.Organizacion;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.Collection;


public class CalculadorHU {

    public static void main(String[] args) {


        ArgumentParser parser = ArgumentParsers.newFor("Checksum").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-m", "--mediciones").required(true)
                .help("Archivo de mediciones");
        parser.addArgument("-p", "--params").required(true)
                .help("Archivo con parámetros de configuración");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }


        System.out.println("Archivo de mediciones: " + ns.get("mediciones"));
        System.out.println("Archivo de parametros: " + ns.get("params"));

        // calcular huella de las actividades y el total
        LectorArchivos lector = new LectorCSV(ns.get("mediciones"), ','); //TODO revisar por donde entra el archivo de mediciones
        FachadaOrg fachadaOrg = new Organizacion();

        Collection<Medible> mediciones = lector.leerMediciones();
        Float huellaCarbono = fachadaOrg.obtenerHU(mediciones);

        System.out.println("Huella de carbono: " + huellaCarbono);
    }

}
