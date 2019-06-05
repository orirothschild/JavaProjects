package il.co.ilrd.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
	HashMap <String, Integer> m = null;
	HashMap <String, Integer> m2 = null;
	HashMap <String, Integer> nullmap = null;


	@Before
	public void setUp() throws Exception {
		nullmap = new HashMap<>(10);
	/*	m = new HashMap<>(10);
		m2 = new HashMap<>(10);
		m.put("SUNDAY", 1);
		m.put("MONDAY", 2);
		m.put("THUSDAY", 3);
		m.put("WENDSDAY", 4);
		m.put("THURSDAY", 5);
		m.put("FRIDAY", 6);
		m.put("SATURDAY", 7);
		m2.put("SUNDAY", 1);
		m2.put("MONDAY", 2);
		m2.put("THUSDAY", 3);
		m2.put("WENDSDAY", 4);
		m2.put("THURSDAY", 5);
		m2.put("FRIDAY", 6);
		m2.put("SATURDAY", 7);*/
	}

/*
	@Test
	public void testSize() {
		assertEquals(m.size(), 7);
	
	}

	@Test
	public void testIsEmpty() {
		m.clear();
		assertTrue(m.isEmpty());
	}

	@Test
	public void testContainsKey() {
		m.put("hello",1);
		assertTrue(m.containsKey("hello"));
	}

	@Test
	public void testContainsValue() {
		m.put("hello",1);
		assertTrue(m.containsValue(1));
	}

	@Test
	public void testPut() {
		m.put("hello",1);
		assertEquals(m.size(), 8);
		m.put("yello", 9);
		System.out.println(m.get("yello"));
	}

	@Test
	public void testRemove() {
		assertTrue(m.containsKey("SUNDAY"));
		System.out.println(m.remove("SUNDAY"));
		assertFalse(m.containsKey("SUNDAY"));
	}

	@Test
	public void testPutAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		m.put("hello",1);
		assertEquals(m.size(), 8);
		m.put("yello", 2);
		m.clear();
		assertEquals(0,m.size());
	}

	
	@Test
	public void testKeySet() {
		Set<String> keyset = m.keySet();
		Iterator<String> iter = keyset.iterator();
		for(String e : m.keySet()) {
			System.out.println(e);
		}
	}

	@Test
	public void testValues() {
		for(Integer e: m.values()) {
			System.out.println(e);
		}
	}

	@Test
	public void testEntrySet() {
		Iterator<Entry<String, Integer>> iter = m.entrySet().iterator();
		System.out.println("*************************************");
	System.out.println(m.size());
	while(iter.hasNext()){*/
			/*System.out.println(iter.next()); */   /*iternext returns a map entry that is *data* of the iterator*/
		/*}
		System.out.println("*******************************************");
		assertTrue(!iter.hasNext());*/
	//}

	@Test
	public void testfornull() {
			nullmap.put(null,11);
			System.out.println(nullmap.containsValue(11));
			
		}
	}
//	@Test
	/*public void exceptionTest() {
		Iterator<Entry<String, Integer>> iter = m.entrySet().iterator();
		while(iter.hasNext()) {
			try {
				iter.next();
				m.remove("SUNDAY");
			}
			catch{c
		}
		int i = 0;
		System.out.println("*************************************");
	System.out.println(m.size());
				while(iter.hasNext()){
				m.remove("SUNDAY");
				iter.next();
			
		}
		
	}	


}*/

