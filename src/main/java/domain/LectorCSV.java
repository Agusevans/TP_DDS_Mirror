package domain;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import com.opencsv.CSVReader;

public class LectorCSV implements LectorArchivos {
	private String nombre;
	private	char separador;
	private CSVReader reader;

	public Collection<Medible> mediciones = new ArrayList<>();

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

	//TODO: leerLinea no deberia recibir el archivo que entró en leerMediciones (o simplemente recibir arch)?
	private String[] LeerLinea() {
		try {
		return reader.readNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new String[0]; //TODO: resolver return
	}
	private Medicion convertirLinea(String[] linea) {
		Medicion medicion = new Medicion();
		//cargo la medicion
		medicion.actividad = linea[0];
		medicion.tipoConsumo = linea[1];
		medicion.unidad = linea[2];
		medicion.consumo.valor = Float.valueOf(linea[3]);
		medicion.consumo.periodicidad = TipoPeriodicidad.valueOf(linea[4]);
		medicion.periodoDeImputacion = linea[5];
		medicion.alcance = TipoAlcance.valueOf(linea[6]);
		return medicion;
	}

	//TODO: leerMediciones no deberia recibir un archivo?
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

//  Esto debería hacerlo el calculador:

//	public float leerMedicionTotal() {
//		float total = 0;
//		leerMediciones();
//		for (Medicion medicion:mediciones) {
//			total+=medicion.getConsumo().getValor();
//		}
//
//		return total;
//	}
}
