/*package TestFolder;

import il.co.ilrd.Iterator.Iterator;
import il.co.ilrd.SinlgyList.*;

public class TestClassSingly{
	private static final int SIZE = 10;
	public static void main(String[] args) {
		
		ClassSinglyLinkedList list = new ClassSinglyLinkedList();
		Iterator returnedItem;
		int[] toPush = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int i = 0;
		int err_count = 0;
		
		for(i = 0; i < SIZE; ++i)
		{
			list.pushFront(i);
			System.out.println(list.toString());
			
		}
		for(i = 0; i < SIZE; ++i)
		{
			returnedItem = list.();
			System.out.println(returnedItem);
		}
		
		while(false == list.isEmpty())
		{
			list.popFront();
		}
		
		for(i = 0; i < SIZE; ++i)
		{
			list.pushFront(i);
			System.out.println(list.getSize());
			System.out.println("\n");
			
		}

		returnedItem = list.getItr();
			System.out.println(list.getSize());
			
			while(returnedItem.hasNext()) {
				 System.out.println(returnedItem.Next());
				
		}


	}

}*/
