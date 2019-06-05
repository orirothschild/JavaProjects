package ir.co.ilrd.genericmethods;

import java.util.ArrayList;
import java.util.List;

public class GenericPractice <T extends Number> {
	
	List <T> l;
	
	public GenericPractice(List<T> t) {
		l = t;
	}
	
	public int Calculate() {
		int counter = 0;
		for(T value : l) {
			counter += value.intValue();
		}
		return counter;
	}

}
