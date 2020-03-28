package model.logic;
import java.io.FileNotFoundException;



import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.data_structures.Comparendos;
import model.data_structures.Comparator;
import model.data_structures.LinearProbing;
import model.data_structures.ListaEncadenada;
import model.data_structures.MergeSort;
import model.data_structures.Node;
import model.data_structures.QuickSort;
import model.data_structures.SeparateChaning;
import model.data_structures.IListaEncadenada;
import model.data_structures.Shell;
import model.data_structures.MergeSort;

import com.google.gson.Gson;
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
	String key;
	String primeraKey;
	int i=0;
	int U=0;
	private SeparateChaning<String ,Comparendos> datos;
	private LinearProbing<String, Comparendos> datos2;
	private Comparendos primero;
	private Comparendos x=null;
	Comparendos.compararXFecha compXFecha=new Comparendos.compararXFecha();
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
	
	}
public Comparendos[] cargarDatos() {
		
	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10
		Comparendos[] ultimoYPrimero=new Comparendos[2];
		String llaveUltimoElemento="";
		datos = new SeparateChaning<String,Comparendos>();
		datos2=new LinearProbing<String, Comparendos>(20);
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
				key=FECHA_HORA+CLASE_VEHI+INFRACCION;
				if(i==0)
				{
					primeraKey=key;
				}
				Comparendos x = new Comparendos(OBJECTID, FECHA_HORA, MEDIO_DETE,CLASE_VEHI, TIPO_SERVI, INFRACCION,  DES_INFRAC, LOCALIDAD, longitud, latitud);
				datos.put(key, x);
				datos2.put(key, x);
				llaveUltimoElemento=key;
				i++;
			}
			ultimoYPrimero[0]=datos.get(primeraKey);
			ultimoYPrimero[1]=datos.get(llaveUltimoElemento);
			
		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ultimoYPrimero;
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Comparendos buscar(String llave)
	{
		return datos.get(llave);
	}
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public void eliminar(String llave)
	{
		datos.delete(llave);
		datos2.delete(llave);
	}
	public SeparateChaning separateChaning()
	{
		return datos;
	}
	public LinearProbing linearProbing()
	{
		return datos2;
	}
	public Comparendos ultimoComparendo()
	{
		return x;
	}
	public Comparendos primerComparendo()
	{
		return primero;
	}
	//Req 3 Taller 5
	public String llaveComparendoLinear(String[] x)
	{
		String res=linearProbing().procesarLlave(x);
		return res;
	}
	public String llaveComparendoChaining(String[] x)
	{
		return separateChaning().procesarLlave(x);
	}
	public Comparable[] copiarComparendos()
	{
		Comparable[] a=new Comparable[linearProbing().darN()];
		int y=0;
		for(int i=0;i<linearProbing().darM();i++)
		{
			if(linearProbing().valoresArreglo()[i]!=null)
			{
				a[y]=(Comparable)linearProbing().valoresArreglo()[i];
				y++;
			}
			
		}
		return a;
	}
	public Comparendos[] darComparendosPorLlaveLinear(String key)
	{	
		U=0;
		Comparendos[] res=new Comparendos[linearProbing().darN()];
		Comparable[] w=copiarComparendos();
		Comparable[] a=ordenarPorFecha(w);
		Date buscada=linearProbing().retornarFechaBuscada();
		int primerIndiceArreglo=primeraKey(a, 0, a.length, buscada, 0);
		int ultimoIndiceArreglo;
		if(primerIndiceArreglo==a.length-1)
		{
			ultimoIndiceArreglo=a.length;
		}
		else
		{
			ultimoIndiceArreglo=ultimaKey(a, 0, a.length, buscada, 0);
		}
		String[] porFechas=new String[0];
		if(primerIndiceArreglo==0 && (ultimoIndiceArreglo>primerIndiceArreglo || ultimoIndiceArreglo==primerIndiceArreglo))
		{
			porFechas=new String[(ultimoIndiceArreglo-primerIndiceArreglo)+1];
			int j=0;
			for(int i=primerIndiceArreglo;i<porFechas.length;i++)
			{
				porFechas[j]=((Comparendos)a[i]).darFecha()+((Comparendos)a[i]).darClaseVehi()+((Comparendos)a[i]).darInfraccion();
				j++;
			}
		}
		else if(primerIndiceArreglo==ultimoIndiceArreglo){
			porFechas=new String[1];
			ultimoIndiceArreglo++;
			int j=0;
			for(int i=primerIndiceArreglo;i<ultimoIndiceArreglo;i++)
			{
				porFechas[j]=((Comparendos)a[i]).darFecha()+((Comparendos)a[i]).darClaseVehi()+((Comparendos)a[i]).darInfraccion();
				j++;
			}
		}
		else
		{
			porFechas=new String[ultimoIndiceArreglo-primerIndiceArreglo];
			int j=0;
			for(int i=primerIndiceArreglo;i<ultimoIndiceArreglo;i++)
			{
				porFechas[j]=((Comparendos)a[i]).darFecha()+((Comparendos)a[i]).darClaseVehi()+((Comparendos)a[i]).darInfraccion();
				j++;
			}
		}
		int t=0;
		for(int i=0;i<porFechas.length;i++)
		{
			if(porFechas[i].equalsIgnoreCase(key))
				{
					res[t]=(Comparendos)linearProbing().get(key);
					t++;
					U++;
				}
		}
		
		return res;
	}
	public  int primeraKey(Comparable[] arr , int low, int high, Date x, int n) 
    { 
	
        if(high >= low) 
        { 
            int mid = low + (high - low)/2; 
            if( ( mid == 0 || x.after(((Comparendos) arr[mid-1]).darFecha())) && x.equals(((Comparendos) arr[mid]).darFecha())) 
                return mid; 
             else if(x.after(((Comparendos) arr[mid]).darFecha())) 
                return primeraKey(arr, (mid + 1), high, x, n ); 
            else
                return primeraKey(arr, low, (mid -1), x, n); 
        } 
    return -1; 
    } 
   
    public  int ultimaKey(Comparable[] arr , int low, int high, Date x, int n) 
    { 
    	
        if (high >= low) 
        { 
            int mid = low + (high - low)/2; 
            if (( mid == n-1|| x.before(((Comparendos)arr[mid+1]).darFecha()) ) && x.equals(((Comparendos)arr[mid]).darFecha())) 
                 return mid; 
            else if (x.before(((Comparendos)arr[mid]).darFecha())) 
                return ultimaKey(arr, low, (mid -1), x, n); 
            else
                return ultimaKey(arr, (mid + 1), high, x, n); 
        } 
    return -1;
    }
	 public Comparable[] ordenarPorFecha(Comparable[] a)
	 {
		 mergeSort(a,compXFecha);
		 return a;
	 }
	 public void mergeSort(Comparable[] a, Comparator<Comparendos> b)
		{
			MergeSort.sort2(a, b);
		}
	 public int retornarUReq2()
	 {
		 return U;
	 }
	 //Req4
		public Comparendos[] darComparendosPorLlaveSeparate(String llave)
		{
			Comparendos x=(Comparendos)separateChaning().get(llave);
			int posicionArreglo=separateChaning().obtenerPosicionArreglo();
			Comparendos[] res=separateChaning().darComparendosPorLlave(posicionArreglo,llave);
			return res;
		}
		public int tamanoRealRes()
		{
			return separateChaning().tamanoRealArreglo();
		}

}
	
