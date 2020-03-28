package model.data_structures;

import model.data_structures.Node;

public class ListaEncadenada<T extends Comparable<T>> implements IListaEncadenada<T> {
	private Node<T> primero;
	private Node<T> ultimo;
	private int longitud;
	private int longitudAc;


	public Node<T> darPrimero()
	{
		return primero;
	}
	public Node<T> darUltimo()
	{
		Node<T> actual2=primero;
		while(actual2.darSiguiente()==null)
		{
			ultimo=actual2;
			actual2=actual2.darSiguiente();
		}
		return ultimo;
		
	}
	@Override
	public boolean agregar(T elemento) {
		Node<T> aAgregar= new Node<T>(elemento);
		Node<T> actual=primero;
		if(primero==null)
		{
			primero=aAgregar;
			longitud++;
			ultimo=aAgregar;
			return true;
		}
		else{
			while(actual.darSiguiente()!=null)
			{
				actual=actual.darSiguiente();
			}
			actual.cambiarSiguiente(aAgregar);
			ultimo=aAgregar;
			longitud++;
			return true;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public T buscar(T elemento) {
		Node<T> actual=primero;
		while(actual.darSiguiente()!=null && actual.darElemento().compareTo(elemento)!=0)
		{
			actual=actual.darSiguiente();
		}
		// TODO Auto-generated method stub
		return actual.darElemento();
	}

	@Override
	public Object[] darArreglo() {
		Object[] arreglo= new Object[darLongitud()];
		Node<T> actual=primero;
		for(int i=0;i<longitud;i++)
		{
			arreglo[i]=actual;
			actual=actual.darSiguiente();
		}

		// TODO Auto-generated method stub
		return arreglo;
	}

	@Override
	public int darLongitud() {
		// TODO Auto-generated method stub
		return longitud;
	}

	@Override
	public boolean eliminar(T elemento) {
		if(primero!=null){
			return false;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node<T> avanzarNodo(Node<T> a) {
		a=a.darSiguiente();
		return a;
		// TODO Auto-generated method stub

	}

	@Override
	public Node<T> retrocederNodo(Node<T> a) {
		Node<T> actual=primero;
		while(actual.darSiguiente().darSiguiente()!=null 
				&& actual.darSiguiente().darSiguiente().darElemento().compareTo(a.darElemento())==0 )
		{
			actual=actual.darSiguiente();
		}
		return actual;
		
		// TODO Auto-generated method stub
	

	}
}
