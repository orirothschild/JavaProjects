package il.co.ilrd.exrecisepackage;

public class MyRuntimeExceptionClass extends RuntimeException{
	private int i;
	private String s;
	
	MyRuntimeExceptionClass(int value, String name) {
		i = value;
		s = name;
	}
	
	public Integer decreaseValueException(int value) {
		System.out.println("this is runtime exception from extended class");
		return i - value;
	}
}
