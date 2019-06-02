package TestFolder;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import il.co.ilrd.SinlgyList.ClassSinglyLinkedList;
import il.co.ilrd.Iterator.*;

public class jUnitTest {
	
	ClassSinglyLinkedList toCheck = null;
	@Before
	public void setUp() throws Exception {
		toCheck = new ClassSinglyLinkedList();
	}

	@Test
	public void testClassSinglyLinkedList() {
		
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, toCheck.isEmpty());
		toCheck.pushFront(5);
		assertEquals(false, toCheck.isEmpty());
	}

	@Test
	public void testSize() {
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		assertEquals(13, toCheck.size());
		
	}

	@Test
	public void testPopFront() {
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.pushFront(5);
		toCheck.popFront();
		toCheck.popFront();
		toCheck.popFront();
		Iterator runner = toCheck.iterator();
		assertEquals(false, runner.hasNext());
	}

	@Test
	public void testFind() {
		toCheck.pushFront(6);
		toCheck.pushFront(7);
		toCheck.pushFront(5);

		assertEquals(5,toCheck.Find(5).next());
		
	}

	@Test
	public void testIterator() {
		toCheck.pushFront(6);
		toCheck.pushFront(7);
		toCheck.pushFront(5);
		Iterator runner = toCheck.iterator();
		while(runner.hasNext()) {
			runner.next();
		}
		assertEquals(0xdeadbeef,runner.next());
		
	}
}
