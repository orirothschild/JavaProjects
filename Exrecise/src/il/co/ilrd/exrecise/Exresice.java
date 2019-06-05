package il.co.ilrd.exrecise;

public class Exresice implements ExreciseInterface{
	int x = 0;
	int y = 0;
	static Integer i;
	ExreciseInterface temp;

	public Exresice() {
		i = new Integer(x);
		temp = new ExreciseInterface() {
			@Override
			public void info(String str) {
				System.out.println("this is anonymous class string");
			}
		};
	}
	@Override
	public void info(String str) {
		System.out.println("i dont want to see this");
	}
	
	public static class Java{
		String str;
	
		public Java(){
			i = 4;
			str = "this is static inner class string";
		}
		
		@Override
		public String toString() {
			System.out.println(str);
			return str;
		}
	}
	
	public class Bava{
		String str;
	
		public Bava(){
			i = 4;
			str = "this is inner class string";
		}
		
		@Override
		public String toString() {
			System.out.println(str);
			return str;
		}
	}
	
}
	
