package lectorArchivos;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class LectorCSV {
	private String nombre;
	private	char separador;
	private CSVReader reader;
	public ArrayList<Medicion> mediciones = new ArrayList<Medicion>();
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
	private String[] LeerLinea() {
		try {
		return reader.readNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private Medicion convertirLinea(String linea) {
		Medicion medicion = new Medicion();
		//cargo la medicion
		medicion.actividad = linea[0];
		medicion.tipoConsumo = linea[1];
		medicion.unidad = linea[2];
		medicion.consumo.valor = Integer.valueOf(linea[3]);
		medicion.consumo.periodicidad = linea[4];
		medicion.periodoDeImputacion = linea[5];
		medicion.alcance = linea[6];
		return medicion;
	}
	private void cargarMediciones() {
		String[] linea = LeerLinea();
		while(!linea.equals(null)) {
			mediciones.add(convertirLinea(linea));
			linea = LeerLinea();
		}
	}
	public float leerMedicionTotal() {
		float total = 0;
		cargarMediciones();
		mediciones.forEach((medicion)=> total+=medicion.consumo.valor);
		return total;
	}	
}
