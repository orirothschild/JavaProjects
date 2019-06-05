package il.co.ilrd.exrecisepackage;

import java.io.IOException;

public class ExceptionTest {
	Integer i = 100;
	
	public static void func1(){
		try {
			throw new IOException();
		} catch (IOException e) {
			System.out.println("hi im IOException");
			e.printStackTrace();
		}
	}
	
	public static Class func2() throws NullPointerException{
		Object obj = null;
		return obj.getClass();
	}

	
	public void func3() throws MyRuntimeExceptionClass{
		if(i < 101) {
			throw new MyRuntimeExceptionClass(100,"name");
		}
	}
	
	public void func4() throws MyIOExceptionClass{
		if(i != 102) {
			throw new MyIOExceptionClass();
		}
	}

}
