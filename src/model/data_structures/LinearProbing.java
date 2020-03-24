package model.data_structures;

import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;

public class LinearProbing <K, V>
{

	private int N;
	private int M ;
	private K[] llaves;
	private V[] valores;

	public LinearProbing(int M)
	{
		llaves = (K[]) new Object[M];
		valores = (V[]) new Object[M];
	}

	private int hash(K llave)
	{
		return (llave.hashCode() & 0x7fffffff ) % M;
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

	}
	
	public void put(K llave, V valor)
	{
		if(N >= M/2) resize(2*M);

		int i;
		for (i = hash(llave); llaves[i] != null; i = (i +1) % M) 
			if(llaves[i].equals(llave)) {valores[i] = valor; return;}
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


}
