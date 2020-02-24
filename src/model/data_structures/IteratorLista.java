package model.data_structures;


import java.util.NoSuchElementException;

public class IteratorLista <T extends Comparable<T>> implements Iterator<T> {
	Node<T> proximo;
	IteratorLista(Node<T> primero)
	{
		proximo=primero;
	}
	public boolean hasNext()
	{
		return proximo!=null;
	}
	public T next()
	{
		if(proximo==null)
		{
			throw new NoSuchElementException("No hay próximo");
		}
		T elemento=proximo.darElemento();
		proximo=proximo.darSiguiente();
		return elemento;
	}
	public void remove() throws UnsupportedOperationException, IllegalStateException
	{ // No se puede eliminar porque se tiene la referencia al proximo nodo
		throw new UnsupportedOperationException("No implementada");
	}
}
