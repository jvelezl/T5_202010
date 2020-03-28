package model.data_structures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import sun.print.resources.serviceui;

//Esta clase se creó a partir del libro algorithms 

public class SeparateChaning <K, V> 

{
	public final static int TAMANO_INICIAL=4;
	private int N;
	private int M;
	private int posicionArreglo;
	private int numeroResize=0;
	public int tamanoRealRes=0;
	public final static double FACTOR_DE_CARGA_MAXIMO=5.0;
	public SequentialSearch<K,V>[] st;


	public SeparateChaning()
	{
		this(TAMANO_INICIAL);
	}


	public SeparateChaning(int M) {
		this.M = M;

		st = (SequentialSearch<K, V>[]) new SequentialSearch[M];	
		for (int i = 0; i < M; i++) 
		{
			st[i] = new SequentialSearch<K,V>();

		}
	}

	private int hash(K llave)
	{
		return(llave.hashCode() & 0x7fffffff)% M;
	}

	public V get(K llave)
	{
		posicionArreglo=hash(llave);
		V res= (V) st[posicionArreglo].get(llave);
		return res;
	}

	public void put (K llave, V valor)
	{
		  if (llave == null) throw new IllegalArgumentException("first argument to put() is null");

	        if (valor == null) {

	            delete(llave);

	            return;

	        }




	        if ((double)N/M >= FACTOR_DE_CARGA_MAXIMO) resize(2*M);



	        int i = hash(llave);

	        if (!st[i].contains(llave)) N++;

	        st[i].put(llave, valor);
	        
	}

	public void delete(K llave)
	{
		put(llave, null);
	}

	public double darFactorDeCarga()
	{
		return (double)N/M;
	}
	 private void resize(int chains) {

	        SeparateChaning<K, V> temp = new SeparateChaning<K, V>(chains);

	        for (int i = 0; i < M; i++) {

	            for (K key : st[i].llaves()) {

	                temp.put(key, st[i].get(key));

	            }

	        }

	        this.M  = temp.M;

	        this.N  = temp.N;

	        this.st = temp.st;
	        
	        numeroResize++;
	    }
	public Iterable<K> llaves() {

        Queue<K> queue = new Queue<K>();

        for (int i = 0; i < M; i++) {

            for (K key : st[i].llaves())

                queue.enqueue(key);

        }

        return queue;

    }
	public int numeroResize()
	{
		return numeroResize; 		
	}

	public boolean contains(K key) {

        if (key == null) throw new IllegalArgumentException("argument to contains() is null");

        return get(key) != null;

    }
	public int darM()
	{
		return M;
	}
	public int darN()
	{
		return N;
	}
	public String procesarLlave(String[] x)
	{
		String fecha2=x[0];
		String claseVehiculo=x[1].trim().toUpperCase();
		if(claseVehiculo.equals("AUTOMOVIL"))
		{
			claseVehiculo="AUTOMÓVIL";
		}
		String infraccion=x[2].trim().toUpperCase();
		SimpleDateFormat formato1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
	 	Date nueva = null;
	 	try {

	 	nueva = formato1.parse(fecha2);

	 	} catch (ParseException ex) {

	 	ex.printStackTrace();

	 	}
	 	String llave=nueva+claseVehiculo+infraccion;
	 	return llave;
	}
	public int obtenerPosicionArreglo()
	{
		return posicionArreglo;
	}
	public Comparendos[] darComparendosPorLlave(int pos,String key)
	{
		Comparendos[] res= st[pos].darComparendosConLlave(key);
		tamanoRealRes=st[pos].tamanoRealArreglo();
		return res;
	}
	public int tamanoRealArreglo()
	{
		return tamanoRealRes;
	}
	
}
