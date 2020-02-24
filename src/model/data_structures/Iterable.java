package model.data_structures;


public interface Iterable <T extends Comparable<T>>{

	Iterator<T> iterator();
}

