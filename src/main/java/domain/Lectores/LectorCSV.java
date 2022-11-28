package domain.Lectores;

import ar.edu.frba.utn.dds.mihuella.fachada.Medible;
import domain.Actividad.*;
import persistencia.EntityManagerHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.valueOf;

public class LectorCSV implements LectorArchivos {
	private String archivo;
	private	String separador;
	public BufferedReader buffer = null;

	public Collection<Medible> mediciones = new ArrayList<>();

	public LectorCSV(){}

	public LectorCSV(String nombreArchivo, String separadorArchivo) {
		archivo = nombreArchivo;
		separador = separadorArchivo;
		try	{
			buffer = new BufferedReader(new FileReader(valueOf(archivo)));
		}catch (Exception error) {
			error.printStackTrace();
		}
	}

	public String[] LeerLinea(){
		String[] campos = null;
		String linea = null;
		try {
			 linea = this.buffer.readLine();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(linea != null)
			campos = linea.split(this.separador);
		return campos;
	}
	public DatosActividad convertirLinea(String[] linea) {

		DatosActividad datosActividad = new DatosActividad();
		Actividad actividad = new Actividad();
		TipoConsumo consumo = new TipoConsumo();
		List<TipoConsumo> consumos= new ArrayList<>();
		Medicion medicion = new Medicion();

		//Carga consumos
		consumo.setNombre(linea[1]);
		consumos.add(consumo);

		//Carga Actividad
		actividad.setNombre(linea[0]);
		actividad.setTiposConsumo(consumos);

		if(linea[0] == "Combustión fija" || linea[0] == "Combustión móvil") //Hardcodeo, deberia borrarse
			actividad.setAlcance(Alcance.Directa);
		else if (linea [0] == "Electricidad adquirida y consumida")
			actividad.setAlcance(Alcance.Indirecta);
		else
			actividad.setAlcance(Alcance.NoControladas);

		//Carga Medicion
		medicion.setTipoConsumo(consumo);
		medicion.setPeriodicidad(Periodicidad.valueOf(linea[3]));
		medicion.setValor(Float.valueOf(linea[2]));

		//Carga DatosActividad
		datosActividad.setActividad(actividad);
		datosActividad.setMedicion(medicion);

		datosActividad.setPeriodoDeImputacion(LocalDate.parse(linea[4]));

		return datosActividad;
	}
	@Override
	public Collection<Medible> leerMediciones() {

		String[] linea = LeerLinea();
		DatosActividad medicion;

		while(linea != null) {
			medicion = convertirLinea(linea);
			this.mediciones.add(medicion);

			linea = LeerLinea();
		}

		return mediciones;
	}

	public DatosActividad parsearLinea(String[] linea) {

		String query = "from Actividad where nombre = " + ("'" + linea[0] + "'");
		Actividad actividad = (Actividad) EntityManagerHelper.createQuery(query).getSingleResult();

		TipoConsumo consumo = actividad.getTipoConsumo(linea[1]);
		Medicion medicion = new Medicion(consumo, Float.valueOf(linea[2]), Periodicidad.valueOf(linea[3]));
		LocalDate periodo = LocalDate.parse(linea[4]);
		DatosActividad datosActividad = new DatosActividad(actividad, medicion, periodo);

		return datosActividad;
	}

	public BatchDatosActividad leerBatch() {

		BatchDatosActividad batch = new BatchDatosActividad();

		String[] linea = LeerLinea();
		while(linea != null) {
			DatosActividad medicion = parsearLinea(linea);
			batch.getDatosAct().add(medicion);

			linea = LeerLinea();
		}

		return batch;
	}

	public BatchDatosActividad leerBatchDeString(String csv) {

		BatchDatosActividad batch = new BatchDatosActividad();

		String[] mediciones = csv.split("\r\n");
		for(String medicion : mediciones) {
			String[] linea = medicion.split(",");
			DatosActividad datosActividad = parsearLinea(linea);
			batch.getDatosAct().add(datosActividad);
		}

		return batch;
	}


}
