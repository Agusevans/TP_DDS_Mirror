package domain;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hsqldb.StatementTypes.RETURN_COUNT;

public class LectorCSV implements LectorArchivos {
	private String nombre;
	private	char separador;
	private CSVReader reader;

	public Collection<Medible> mediciones = new ArrayList<>();

	//Segun entendimiento, el archivo al crearse el objeto LectorCSV, queda abierto durante to-do el contexto
	public LectorCSV(String nombreArchivo, char separadorArchivo) {
		nombre = nombreArchivo;
		separador = separadorArchivo; 
		try {
			reader = new CSVReader(new FileReader(nombre));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] LeerLinea() {
		try {
		return reader.readNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new String[0]; //TODO: resolver return
	}
	public DatosActividad convertirLinea(String[] linea) {
		DatosActividad datosActividad = new DatosActividad();
		//cargo la datosActividad
		datosActividad.actividad = linea[0];
		datosActividad.tipoConsumo.tipo = linea[1];
		datosActividad.tipoConsumo.unidad = Unidad.valueOf(linea[2]);
		datosActividad.consumo.valor = Float.valueOf(linea[3]);
		datosActividad.consumo.periodicidad = Periodicidad.valueOf(linea[4]);
		datosActividad.periodoDeImputacion = linea[5];
		datosActividad.alcance = Alcance.valueOf(linea[6]);
		return datosActividad;
	}

	@Override
	public Collection<Medible> leerMediciones() {

		Collection<Medible> mediciones = new ArrayList<>();
		String[] linea = LeerLinea();

		while(linea != null) {
			mediciones.add(convertirLinea(linea));
			linea = LeerLinea();
		}

		return mediciones;
	}

	public static Map<Sector, Miembro> convertirPostulante(String[] linea){

		Map<Sector,  Miembro> result = new HashMap<>(RETURN_COUNT);

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
//	}
}
