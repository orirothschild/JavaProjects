package il.co.ilrd.exrecisepackage;

import java.io.IOException;

public class MyIOExceptionClass extends IOException{
	private int i = 10;
	
	public int intreturn() {
		System.out.println("i am an IO exception method");
		return i;
	}
}
