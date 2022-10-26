package domain.Lectores;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
//import com.opencsv.CSVReader;
//TODO: Cambiar este import para que funcione

import java.util.Collection;

//TODO: opencsv cambiarlo a algo exportable a la nube

//import static org.hsqldb.StatementTypes.RETURN_COUNT;

public class LectorCSV implements LectorArchivos {
	/*private String nombre;
	private	char separador;
	//private CSVReader reader;

	public Collection<Medible> mediciones = new ArrayList<>();

	//Segun entendimiento, el archivo al crearse el objeto LectorCSV, queda abierto durante to-do el contexto
	public LectorCSV(String nombreArchivo, char separadorArchivo) {
		nombre = nombreArchivo;
		separador = separadorArchivo;
		//try {
			//reader = new CSVReader(new FileReader(nombre));
			//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	}

	public String[] LeerLinea() {
		try {
		//return reader.readNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new String[0]; //TODO: resolver return
	}
	public DatosActividad convertirLinea(String[] linea) { //TODO: Checkear lectura luego de los cambios en las actividades
		DatosActividad datosActividad = new DatosActividad();
		TipoConsumo consumo = new TipoConsumo();
		//cargo la datosActividad
		//datosActividad.setActividad(linea[0]);
		consumo.setTipo(String.valueOf(linea[1]));
		consumo.setUnidad(Unidad.valueOf(linea[2]));
		//consumo.setValor(Float.valueOf(linea[3]));
		//consumo.setPeriodicidad(Periodicidad.valueOf(linea[4]));
		//datosActividad.getTiposConsumo().add(consumo);
		datosActividad.setPeriodoDeImputacion(linea[5]);
		//datosActividad.setAlcance(Alcance.valueOf(linea[6]));
		return datosActividad;
	}
*/
	@Override
	public Collection<Medible> leerMediciones() {

		/*Collection<Medible> mediciones = new ArrayList<>();
		String[] linea = LeerLinea();

		while(linea != null) {
			mediciones.add(convertirLinea(linea));
			linea = LeerLinea();
		}

		return mediciones;*/
		return null;
	}

	/*public static Map<Sector, Miembro> convertirPostulante(String[] linea){

		Map<Sector,  Miembro> result = new HashMap<>((0));//RETURN_COUNT));

		Miembro miembro = new Miembro();
		miembro.setNombre(linea[0]);
		miembro.setApellido(linea[1]);
		miembro.setTipoDocumento(linea[2]);
		miembro.setNroDocumento(Integer.parseInt(linea[3]));

		Sector sector = new Sector();
		sector.setNombre(linea[4]);
		sector.setActividad(linea[5]);

		result.put(sector, miembro);

		return result;

	}

//  Esto debería hacerlo el calculador:

//	public float leerMedicionTotal() {
//		float total = 0;
//		leerMediciones();
//		for (DatosActividad medicion:mediciones) {
//			total+=medicion.getConsumo().getValor();
//		}
//
//		return total;
//	}*/
}
