package il.co.ilrd.exrecisepackage;

import java.io.IOException;

public class exceptionClassMain {

	public static void main(String[] args) {
		
		//nullpointer and IO eception
		/*exceptionClass.func1();
		try {
			Class<?> objclass = exceptionClass.func2();
		} catch (Exception e) {
			System.out.println("hi im exception");	
			e.printStackTrace();
		}*/
		ExceptionTest attempt = new ExceptionTest();
		ExceptionTest attempt2 = new ExceptionTest();
		try {
			attempt.func4();
			/*attempt.func3();*/
			
		}
		/*catch(MyRuntimeExceptionClass e){
			System.out.println("an exception accored and the exception func is" +e.decreaseValueException(12));
			
		}*/
	catch(MyIOExceptionClass e) {
			System.out.println("i am a io exception" + e.intreturn());
		}
		
	}	
}
