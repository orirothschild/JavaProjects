package il.co.ilrd.thermo;

import il.co.ilrd.observer.Subject;

public class Thermo<T>{
	
	private final Subject<T> sub = new Subject<>();
	
	public Subject<T> getInstance(){
		return sub;
	}
	
	public void getCallbacks(){
		sub.nameObservers();
	}
}
