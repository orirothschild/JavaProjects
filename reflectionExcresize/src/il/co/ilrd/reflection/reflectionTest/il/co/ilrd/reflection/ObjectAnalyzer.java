package il.co.ilrd.reflection;
import java.lang.reflect.Method; 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor; 
  
import static org.junit.Assert.*;

import org.junit.Test;

public class ObjectAnalyzer {
	Object object;
	reflectionExcercise obj = new reflectionExcercise("hi",5);
	Class reflectClass = obj.getClass();//holds the static class that the class.lang created  reflectionexrecise
	
	public void printAncestor() {
	        System.out.println("The name of ancestor class is " + reflectClass.getSuperclass()); 
	}
	
	public void printConstructor() {
        // Getting the constructor of the class through the 
        // object of the class 
        Constructor constructor = null;
		try {
			constructor = reflectClass.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println("The name of constructor is " + 
                            constructor.getName()); 
		}
	
	public void printMethods() {
		//this methods array gives us the methods represented in reflection class
        System.out.println("The public methods of class are : "); 
        Method[] methods = reflectClass.getMethods(); 
  
        // Printing method names 
        for (Method method:methods) {
            System.out.println(method.getName()); 
        }
	}
	
	public void callDeclearedMethods() {
        // the getDeclaredMethod returns the method specefied with a string and input
        Method methodcallone = null;
        Method methodcallTwo = null;
        Method methodcallThree = null;
		try {
			methodcallone = reflectClass.getDeclaredMethod("methodOne", null);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // calls the method in runtime 
        try {
			methodcallone.invoke(obj,null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        try {
			methodcallTwo = reflectClass.getDeclaredMethod("methodTwo");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        // invokes the method at runtime 
        try {
			methodcallTwo.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
  
        // Creates object of the desired method by providing 
        // the name of method as argument to the  
        // getDeclaredMethod method 
        try {
			methodcallThree = reflectClass.getDeclaredMethod("method3");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        methodcallThree.setAccessible(true); 
        try {
			methodcallThree.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // allows the object to access the method irrespective  
        // of the access specifier used with the method 
        // invokes the method at runtime 
	}
	public void printDeclearedFields() { 
        // creates object only for the declared field
        Field field = null;
        Field field2 = null;
		try {
			field = reflectClass.getDeclaredField("s");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			field2 = reflectClass.getDeclaredField("i");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("declared methods" + field.toString());
		System.out.println("declared methods" + field2.toString());
	}
	
	public void printFields() {  // will return fields of the class and the super class!! while declared fields will return only subclass fields
		Field[] fields = reflectClass.getFields();
		for(Field field:fields) {
			System.out.println("the fields are " + field.toString());
		}
	}
	
	public void printmodifiers() {
		 Field[] fields = reflectClass.getDeclaredFields();

		    for (Field f: fields) {
		      Class type = f.getType();
		      String name = f.getName();
		      System.out.print((f.getModifiers()));
		      System.out.println(" " + type.getCanonicalName() + " " + name + ";");
		    }	
		}
	
	public static Object givemeinnerclass() throws Exception {
		
		reflectionExcercise outerinstance = new reflectionExcercise("hi",5);
		Class<?> reflectClass = outerinstance.getClass();
		Class<?> innerclass = reflectClass.getDeclaredClasses()[0];
		Constructor<?> constructor = innerclass.getDeclaredConstructors()[0];
		constructor.setAccessible(true);    /*allows to activate a constructor that was private*/
		return constructor.newInstance(outerinstance);
	}
	
	
		
		
	}