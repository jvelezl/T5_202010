package model.data_structures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

//Esta clase se creó a partir del libro algorithms 
public class LinearProbing <K, V> 
{
	public final static double FACTOR_CARGA_MAXIMA=0.75;
	private int N;
	private int M;
	private Date fecha;
	private int tamanoInicial=0;
	private int numeroResize=0;
	private K[] llaves;
	private V[] valores;

	public LinearProbing(int m)
	{
		tamanoInicial=m;
		M=m;
		N=0;
		llaves = (K[]) new Object[M];
		valores = (V[]) new Object[M];
	}
	public int darTamanoInicial()
	{
		return tamanoInicial;
	}
	private int hash(K llave)
	{
		return (llave.hashCode() & 0xfffffff) % M;
	}

	public void resize(int c)
	{
		LinearProbing<K, V> t;

			t = new LinearProbing<K,V>(c);
			for (int i = 0; i < M; i++) 
				if(llaves[i] != null)
					t.put(llaves[i], valores[i]);
			llaves = t.llaves;
			valores = t.valores;
			M = t.M;
			numeroResize++;
	}
	
	public void put(K llave, V valor)
	{
		 if (llave == null) throw new IllegalArgumentException("first argument to put() is null");



	        if (valor == null) {

	            delete(llave);

	            return;

	        }
		if((double)N/M >= FACTOR_CARGA_MAXIMA) resize(2*M);

		int i;
			for (i = hash(llave); llaves[i] != null; i = (i +1) % M) 
		{
			if(llaves[i].equals(llave)) 
			{
				valores[i] = valor; 
				return;
			}
		}
			
		llaves[i]= llave;
		valores[i] = valor;
		N++;
	}
	
	public V get(K llave)
	{
		for(int i = hash(llave); llaves[i] != null; i = (i +1)%M)
			if(llaves[i].equals(llave))
				return valores[i];
		return null;
	}
	public double factorDeCarga()
	{
		return (double)N/M;
	}
	public boolean contains( K llave)
	{
		return get(llave)!= null;
	}
	public void delete(K llave)
	{
		if(!contains(llave)) return;
		int i = hash(llave);
		while(!llave.equals(llaves[i]))
			i = (i +1) % M;
		llaves[i] = null;
		valores[i] = null;
		i = (i +1) % M;
		while (llaves[i] != null)
		{
			K keyToRedo = llaves[i];
			V valToRedo = valores[i];
			llaves[i] = null ;
			valores[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N--;
		if(N > 0 && N == M/8) resize(M/2);
		
	}
	public int numeroRehash()
	{
		return numeroResize;
	}
	public Iterable<K> llaves()
	{
		Queue<K> queue = new Queue<K>();

        for (int i = 0; i < M; i++)

            if (llaves[i] != null) queue.enqueue(llaves[i]);


        return queue;
	}
	public int darN()
	{
		return N;
	}
	public int darM()
	{
		return M;
	}
	public K[] llavesArreglo()
	{
		return llaves;
	}
	public V[] valoresArreglo()
	{
		return valores;
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
	 	fecha=nueva;
	 	String llave=nueva+claseVehiculo+infraccion;
	 	return llave;
	}
	public Date retornarFechaBuscada()
	{
		return fecha;
	}

}
	