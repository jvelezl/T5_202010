package model.data_structures;

//Esta clase se creó a partir del libro algorithms 

public class SeparateChaning <K, V> 

{
	private int N;
	private int M;
	private SequentialSearch<K,V>[] st;


	public SeparateChaning()
	{
		this(997);
	}


	public SeparateChaning(int M) {
		this.M = M;

		st = (SequentialSearch<K, V>[]) new SequentialSearch[M];	
		for (int i = 0; i < M; i++) 
		{
			st[i] = new SequentialSearch<>();

		}
	}

	private int hash(K llave)
	{
		return(llave.hashCode() & 0x7fffffff)% M;
	}

	private V get(K llave)
	{
		return (V) st[hash(llave)].get(llave);
	}

	public void put (K llave, V valor)
	{
		st[hash(llave)].put(llave, valor);
	}

	public void delete(K llave)
	{
		put(llave, null);
	}

	public Iterable<K> llaves()
	{

	}


}
