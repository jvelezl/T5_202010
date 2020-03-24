package model.data_structures;

//Esta clase se creó a partir del libro algorithms 

public class SequentialSearch <K, V>

{
	private Node primero;

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
		for(Node x = primero; x != null; x = x.siguiente)
		
			if(llave.equals(x.llave))
			{
				x.valor = valor;
				return;
			}
		primero = new Node(llave, valor, primero.siguiente);
		
		
	}

}
