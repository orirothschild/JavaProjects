package ir.co.ilrd.genericmethods;

import java.util.ArrayList;
import java.util.List;

public class GenericWorksheet {

	
	public void testmethod() {
		List rawList;
		List<?> listOfAnyType;
		List<Object> listOfObjects = new ArrayList<Object>();
		List<String> listOfStrings = new ArrayList<String>();
		List<Integer> listOfIntegers = new ArrayList<Integer>();
		//rawList = listOfAnyType;
		rawList = listOfStrings;
		rawList = listOfIntegers;
		listOfAnyType = listOfStrings;
		listOfAnyType = listOfIntegers;
		//listOfObjects = (List<Object>)listOfStrings;
	}
	
	public static <T> void testmethod2(List<T> l) {
		System.out.println(l);

	}
}
