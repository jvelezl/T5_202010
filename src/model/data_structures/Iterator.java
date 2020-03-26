

package model.data_structures;

public interface Iterator <T extends Comparable<T>>{
		boolean hasNext();
		T next();
		void remove();
	}


