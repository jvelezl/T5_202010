package model.data_structures;

public interface IListaEncadenada <T extends Comparable<T>> extends Iterable<T>{
	public boolean agregar(T elemento);
	public T buscar(T elemento);
	public Object[] darArreglo();
	public int darLongitud();
	public boolean eliminar(T elemento);
	public Node<T> avanzarNodo(Node<T> a);
	public Node<T> retrocederNodo(Node<T> a);
}
