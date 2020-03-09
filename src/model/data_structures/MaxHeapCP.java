package model.data_structures;

public class MaxHeapCP <Key extends Comparable <Key>>
{
	
	private Key[] cp;
	private int N = 0;
	
	public MaxHeapCP (int maxN)
	{
		cp= (Key[]) new Comparable[maxN +1];
	}
	
	public boolean esVacia()
	{
		return N ==0;
	}
	
	public int darNumeroDeElementos() 
	{
		return N;
	}
	
	public <T> void agregar( Key v)
	{
		cp[++N] = v;
		swim(N);
		
	}
	
	public <T> T sacarMax()
	{
		Key max = cp[1];
		exch(cp, 1, N--);
		cp[N+1] = null;
		sink(1);
		return (T) max;
		
	}
	
	
	
	
	//Métodos auxiliares
	
	private static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w)< 0;
	}
	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable t = a[i]; 
		a[i] = a [j] ;
		a[j] = t;
	}
	
	private void swim(int k)
	{
		while (k > 1  && less (k/2, k))
		{
			exch(cp, k/2, k);
			k= k/2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N)
		{
			int j = 2*k;
			if(j < N && less (j, j+1)) j++;
			if(!less(k,j)) break;
			exch(cp, k,j);
			k=j;
		}
	}
	

}
