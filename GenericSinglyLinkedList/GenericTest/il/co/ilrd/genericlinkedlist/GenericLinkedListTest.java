package il.co.ilrd.genericlinkedlist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GenericLinkedListTest {
	GenericLinkedList<Integer> intlist = null;
	GenericLinkedList<Integer> intlistempty = null;
	GenericLinkedList<Integer> intlistempty2 = null;
	GenericLinkedList<Number> numberlist = null;
	GenericLinkedList<String> strlist = null;
	GenericLinkedList<String> strlist2 = null;
	@Before
	public void setUp() throws Exception {
		intlist = new GenericLinkedList<>();
		intlistempty = new GenericLinkedList<>();
		intlistempty2 = new GenericLinkedList<>();
		numberlist = new GenericLinkedList<>();
		strlist = new GenericLinkedList<>();
		strlist2 = new GenericLinkedList<>();
		intlist.pushFront(5);
		intlist.pushFront(6);
		intlist.pushFront(3);
		
		numberlist.pushFront(2);
		numberlist.pushFront(10);
		/*for(int i = 0; i< 10; ++i) {
			intlist.pushFront(i);
		}
		
		for(int i = 10; i< 20; ++i) {
			numberlist.pushFront(i);
			}*/
		
		strlist.pushFront("the");
		strlist.pushFront("from");
		strlist.pushFront("hello");
		
		strlist2.pushFront("side");
		strlist2.pushFront("other");
		
	}

	@Test
	public void testIterator() {
		int a = intlist.iterator().next();
		assertEquals(a,3);
	}

	@Test
	public void testIsEmpty() {
		for(int i = 0; i < 3; ++i) {
			try {
				intlist.popFront();
			} catch (EmptyListException e) {
				e.print();
				e.printStackTrace();
				}
			}
		assertTrue(intlist.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(3, intlist.size());
	}
	
	/*@Test
	public void testFind() {
		int a = 5;
		
	}*/

	@Test
	public void testAddAll() {
		numberlist.addAll(intlist);
		GenericLinkedList.print(numberlist);
		System.out.println("********************************");
		GenericLinkedList.print(strlist);
		System.out.println("********************************");
		strlist.addAll(strlist2);
		GenericLinkedList.print(strlist);
		
	}

	@Test
	public void testGetAll() {
		intlist.getAll(numberlist);
		GenericLinkedList.print(numberlist);
		System.out.println("********************************");
		strlist.getAll(strlist2);
		GenericLinkedList.print(strlist2);
	}

	/*@Test
	public void testNewReverse() {
		intlistempty = GenericLinkedList.newReverse(intlist);
		Iterator iterator = intlist.iterator();
		for(int i = 0;i <3; ++i) {
			assertEquals(iterator.next(), );
		}
	}*/

}
