package model.data_structures;

import java.util.Iterator;

//Esta clase se creó a partir del libro algorithms 

public class SeparateChaning <K, V> 

{
	public final static int TAMANO_INICIAL=4;
	private int N;
	private int M;
	private int numeroResize=0;
	public final static double FACTOR_DE_CARGA_MAXIMO=5.0;
	private SequentialSearch<K,V>[] st;


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
		return (V) st[hash(llave)].get(llave);
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
<<<<<<< HEAD
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
=======

	public ListaEncadenada llaves()
	{
		ListaEncadenada lista = new ListaEncadenada() ;
		for (int i = 0; i < M; i++) 
		{
			lista.agregar(i);
		}
		
		return lista;
>>>>>>> c7d5212408878dc8ee764658d34568a5545088fb
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
}
