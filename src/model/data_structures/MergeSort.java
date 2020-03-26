package model.data_structures;


import model.data_structures.*;

public class MergeSort {
		private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, Comparator<Comparendos> b)
		{
		for (int k = lo; k <= hi; k++)
		{
			aux[k] = a[k];
		}
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
		if (i > mid) a[k] = aux[j++];
		else if (j > hi) a[k] = aux[i++];
		else if (less(aux[j], aux[i],b)) a[k] = aux[j++];
		else a[k] = aux[i++];
		}
		}
		private static boolean less(Comparable v,Comparable w, Comparator<Comparendos> b)
		{
			return (b.compare(((Comparendos)v),((Comparendos) w))<0);
		}
		public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi,Comparator<Comparendos> b)
		{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid, b);
		sort(a, aux, mid+1, hi, b);
		merge(a, aux, lo, mid, hi, b);
		}
		public static void sort2(Comparable[] a,Comparator<Comparendos> b)
		{
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1,b);
		}
	}
