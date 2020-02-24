package model.data_structures;

public class Node <T extends Comparable<T>>{
	
	private T elemento;
	Node<T> siguiente;
	public Node(T pElemento)
	{
		siguiente=null;
		elemento=pElemento;
	}
	public Node<T> darSiguiente()
	{
		return siguiente;
	}
	public void cambiarSiguiente(Node<T> pSig)
	{
		siguiente=pSig;
	}
	public T darElemento()
	{
		return elemento;
	}
}