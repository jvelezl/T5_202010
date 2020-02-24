package model.logic;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import model.data_structures.Comparendos;
import model.data_structures.Iterator;
import model.data_structures.ListaEncadenada;
import model.data_structures.IListaEncadenada;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private final static String PATH = "./data/comparendos_dei_2018_small.geojson"; // Processing JSONObject
	private ListaEncadenada<Comparendos> datos;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ListaEncadenada<Comparendos>();
	}
public IListaEncadenada<Comparendos> cargarDatos() {
		
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10
		ListaEncadenada<Comparendos> datos = new ListaEncadenada<Comparendos>();

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonParser parserr2=new JsonParser();
			JsonElement elem = parserr2.parse(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();
			
			
			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				
				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 
				
				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();
				
				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendos c = new Comparendos(OBJECTID, FECHA_HORA, MEDIO_DETE,CLASE_VEHI, TIPO_SERVI, INFRACCION,  DES_INFRAC, LOCALIDAD, longitud, latitud);
				datos.agregar(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;	
		
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darLongitud();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Comparendos dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Comparendos buscar(Comparendos dato)
	{
		return datos.buscar(dato);
	}
	public Comparendos buscarPorId(int Id)
	{
		Iterator<Comparendos> c= datos.iterator();
		Comparendos comp=null;
		Comparendos aBuscar=null;
		Boolean a=false;
		while(c.hasNext() && !a){
			comp=c.next();
			if(comp.darId()==Id)
			{
				a=true;
				aBuscar=comp;
			}
				
		}
		return aBuscar;
		
	}
	public Comparendos darUltimo()
	{
		Iterator<Comparendos> c= datos.iterator();
		Comparendos comp=null;
		while(c.hasNext() ){
			comp=c.next();	
		}
		return comp;
	}
	public ListaEncadenada<Comparendos> darLista(){
		return (ListaEncadenada<Comparendos>) datos;
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public boolean eliminar(Comparendos dato)
	{
		return datos.eliminar(dato);
	}


}