package il.co.ilrd.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.nio.channels.MembershipKey;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

public class ObjectAnalayzer {
	private class innerClassToAnalize {
		int z = 100;
		
		private innerClassToAnalize() {
			System.out.println("Hello from Innter Class");	
		}
		
		public void printMyName() {
			System.out.println("MY NAME!");
		}
		
		int getZ() {
			return z;
		}
	}
	
	public static class foo {
		public int x = 0;
		public double y  = 1.2;
		public void f1(int number, double number2) {
			System.out.println("numbers are: " + number + " " + number2);
		}
	}
		
	public ObjectAnalayzer() {
		System.out.println("Hi from Ctor of outer class");
	}
	public static void printAncestor(Object obj) {
		System.out.println(obj.getClass().getSuperclass());
	}
		
	public static void printModifiersAndInterfaces(Object obj) {
		String s = obj.getClass().getName();
		Class<?> myClass = s.getClass();
		
		System.out.println("Number of modifiers: " + obj.getClass().getModifiers());
/*		System.out.println("Interfacs: " + obj.getClass().getInterfaces());
*/		System.out.println(Modifier.toString(myClass.getModifiers()));
	}
	
	public static void listMemebers(Object obj) {
		for (Field st: obj.getClass().getFields()) {
			System.out.println(st.getName());
		}
	}
	
	public static foo getNewinstance(Object obj) {
		foo f = null;
		
		try {
			f =  (foo) obj.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	public static Object function()  throws Exception{
		
		ObjectAnalayzer outer = new ObjectAnalayzer();
		
		Class<?> reflectClass = outer.getClass();
		
		Class<?> innerReflectClass = reflectClass.getDeclaredClasses()[0];
		
		Constructor<?> innerCtor = innerReflectClass.getDeclaredConstructors()[0];
		
		innerCtor.setAccessible(true);
		
		return innerCtor.newInstance(outer);
		
		
		
		

		
	}
	
	public static void main(String args[]) {
		Object myFoo = new foo();
		printAncestor(myFoo);
		printModifiersAndInterfaces(myFoo);
		listMemebers(myFoo);
		getNewinstance(myFoo);
		foo f = getNewinstance(myFoo);
		
		Object inner = null;
		new foo();
		
		
		try {
			inner = foo.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f.f1(10, 10.2);	
		
		/***********************/
		
			Object newInner;
			try {
				newInner = function();
				System.out.println(newInner.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}		

