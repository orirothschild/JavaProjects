package il.co.ilrd.reflection;

public class reflectionExcercise {
	private String s;
	private Integer i;
	
	private class InnerClass {
		private String a;
		
		private InnerClass() {
			a = "hello";
			System.out.println("hello form inner class");
		}
		
		public String print() {
			System.out.println("hello form inner class");
			return a;
		}
		
	}
	
	public reflectionExcercise(String s, Integer i) {
		this.s = s;
		this.i = i;
	}
	
public void methodOne() {
	System.out.println("the string is" + s);
}

public void methodTwo() {
	System.out.println("the value is" + i);
}

public void methodThree() {
	System.out.println("the value is" + i + "this is private");
	}
}
