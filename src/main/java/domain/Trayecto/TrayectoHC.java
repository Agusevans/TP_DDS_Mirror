package domain.Trayecto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBeanBuilder;
import domain.Organizacion.*;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class trayectoMiembro{

    @CsvBindByPosition(position = 0)
    private int dni;

    @CsvBindByPosition(position = 1)
    private ArrayList<trayectoCSV> tramos;

    public int getDni() {
        return dni;
    }

    public ArrayList<trayectoCSV> getTramos() {
        return tramos;
    }
}

class trayectoCSV{

    String dni;
    String puntoI;
    String puntoF;
    String FE;
    String dniComparte;

}


public class TrayectoHC {
    // Requerimiento 6
    public static void main(String[] args) throws FileNotFoundException {

        ArgumentParser parser = ArgumentParsers.newFor("Calculador HC organizaciones y miembros").build()
                .defaultHelp(true)
                .description("Calcula HC de organizaciones y cada uno de sus miembros");
        parser.addArgument("-o", "--organizaciones").required(true)
                .help("Archivo de organizaciones");
        parser.addArgument("-t", "--trayectos").required(true)
                .help("Archivo con trayectos");
        parser.addArgument("-p", "--paradas").required(true)
                .help("Archivo con transportes y paradas");
        Namespace ns = null;

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        String fileTrayecto = ns.get("trayectos");

        // Parse CSV Trayectos

        List<trayectoMiembro> trayectos = new CsvToBeanBuilder(new FileReader(fileTrayecto)).withType(trayectoMiembro.class).build().parse();


        //Parse archivo Organizaciones
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader((String) ns.get("organizaciones")))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray organizacionesList = (JSONArray) obj;

            organizacionesList.forEach(org -> parseOrganizacion( (JSONObject) org, (List<trayectoMiembro>) trayectos ) );


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Organizacion parseOrganizacion(JSONObject org, List<trayectoMiembro> trayectos) {

        //Get employee object within list
        JSONObject orgObject = (JSONObject) org.get("Organizacion");

        String razonSocial = (String) orgObject.get("razonSocial");

        String tipo = (String) orgObject.get("tipo");

        ClasificacionOrg clasificacion = (ClasificacionOrg) orgObject.get("clasificacion");

        ArrayList ubicacion = (ArrayList) orgObject.get("ubicacion");

        Organizacion orga = new Organizacion();

        orga.setRazonSocial(razonSocial);
        orga.setTipo(TipoOrg.valueOf(tipo));
        orga.setUbicacion((Float) ubicacion.get(0),(Float) ubicacion.get(1) );
        orga.setClasificacion(clasificacion);

        JSONArray sectorList = (JSONArray) orgObject.get("Sector");

        sectorList.forEach(sector -> parseSector( (JSONObject) sector, (Organizacion) orga, (List<trayectoMiembro>) trayectos ) );

    }

    private static void parseSector(JSONObject sector, Organizacion org, List<trayectoMiembro> trayectos) {

        JSONObject sectorObject = (JSONObject) sector.get("Sector");

        String nombre = (String) sectorObject.get("nombre");

        String actividad = (String) sectorObject.get("actividad");

        Sector sec = new Sector();

        sec.setNombre(nombre);
        sec.setActividad(actividad);

        JSONArray miembrosList = (JSONArray) sectorObject.get("Miembros");

        miembrosList.forEach(miembro -> parseMiembro( (JSONObject) miembro, (Sector) sec, (List<trayectoMiembro>) trayectos) );

        org.agregarSector(sec);
    }

    private static void parseMiembro(JSONObject miembro, Sector sector, List<trayectoMiembro> trayectos) {

        JSONObject miembroObject = (JSONObject) miembro.get("Miembros");

        String nombre = (String) miembroObject.get("nombre");
        String apellido = (String) miembroObject.get("apellido");
        String tipoDocumento = (String) miembroObject.get("tipoDocumento");
        int nroDocumento = (int) miembroObject.get("nroDocumento");
        ArrayList domicilio = (ArrayList) miembroObject.get("domicilio");

        Miembro miem = new Miembro();
        miem.setNombre(nombre);
        miem.setApellido(apellido);
        miem.setTipoDocumento(tipoDocumento);
        miem.setNroDocumento(nroDocumento);
        miem.setDomicilio((Float) domicilio.get(0),(Float) domicilio.get(1));

        for (trayectoMiembro tray: trayectos
             ) {

        }

        for (trayectoMiembro tray: trayectos){

            if (tray.getDni() == nroDocumento) {

                ArrayList<trayectoCSV> tramos = tray.getTramos();

                miem.tramos.get(0);

            }


        }
        sector.agregarMiembro(miem);

    }


}
