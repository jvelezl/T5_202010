package model.data_structures;

//Esta clase se creó a partir del libro algorithms 

public class SequentialSearch <K, V>

{
	private Node primero;
	private int tamano;
	private int tamanoReal=0;
	private class Node 
	{
		K llave;
		V valor;
		Node siguiente;

		public Node(K llave, V valor, Node siguiente)
		{
			this.llave = llave;
			this.valor = valor;
			this.siguiente = siguiente;
		}
	}

	public V get( K llave)
	{
		for(Node x = primero; x!= null; x = x.siguiente)
		{
			if (llave.equals(x.llave))
			{
				return x.valor;
			}
		}
		return null;
		
	}

	public void put( K llave, V valor)
	{
		 if (llave == null) throw new IllegalArgumentException("first argument to put() is null"); 
	        if (valor == null) {

	            delete(llave);

	            return;

	        }
		for(Node x = primero; x != null; x = x.siguiente)
		{
			if(llave.equals(x.llave))
			{
				x.valor = valor;
				return;
			}
			
	}
		primero = new Node(llave, valor, primero);
		tamano++;
	}
	public Iterable<K> llaves()  {

        Queue<K> queue = new Queue<K>();

        for (Node x = primero; x != null; x = x.siguiente)

            queue.enqueue(x.llave);

        return queue;

    }
	public void delete(K key) {

        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 

        primero = delete(primero, key);

    }
	private Node delete(Node x, K key) {

        if (x == null) return null;

        if (key.equals(x.llave)) {


            return x.siguiente;

        }

        x.siguiente = delete(x.siguiente, key);

        return x;

    }
	  public boolean contains(K key) {

	        if (key == null) throw new IllegalArgumentException("argument to contains() is null");

	        return get(key) != null;
	  }
	  public int darTamano()
	  {
		  return tamano;
	  }
	  public Comparendos[] darComparendosConLlave(String key)
		{
			Comparendos[] comparendos=new Comparendos[tamano];
			for(Node x=primero;x!=null;x=x.siguiente)
			{
				if(x!=null)
				{
					if(x.llave.equals(key))
					{
						comparendos[tamanoReal]=(Comparendos)x.valor;
						tamanoReal++;
					}	
				}
				
			}
			return comparendos;
		}
	  public int tamanoRealArreglo()
	  {
		  return tamanoReal;
	  }
	  
}
