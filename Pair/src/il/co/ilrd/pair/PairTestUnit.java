package il.co.ilrd.pair;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PairTestUnit {
	Pair<String, Integer> p = null;
	Pair<String, Integer> p1 = null;
	Pair<String, Integer> p2 = null;
	Pair<Integer, String> pswap = null;
	Pair<Integer, String> p1swap = null;
	Pair<Integer, String> p2swap = null;

	@Before
	public void setUp() throws Exception {
		
		p = Pair.of("Hello",6);
		p1 = Pair.of("yellow",8);
		p2 = Pair.of("Hello",4);

	}

	@Test
	public void testHashCode() {
		p2.setValue(6);
		assertEquals(p2.hashCode(),p.hashCode());
	}

	@Test
	public void testGetKey() {
		assertEquals("Hello",p.getKey());
	}

	@Test
	public void testEqualsObject() {
		p.equals(p2);
		p2.setValue(6);
		assertTrue(p.equals(p2));
		p2 = null;
		p1 = null;
		assertTrue(p1.equals(p2));
		
		}
/*	@Test
	public void testSwap() {
		Pair.printPair(p);
		Pair.printPair(p1);
		Pair.printPair(p2);
		pswap = Pair.swap(p);
		p1swap = Pair.swap(p2);
		p2swap = Pair.swap(p1);
		Pair.printPair(pswap);
		Pair.printPair(p1swap);
		Pair.printPair(p2swap);
		Integer i = pswap.getKey();
		assertEquals(i.intValue(),6);
		i = p1swap.getKey();
		assertEquals(i.intValue(),4);
		i = p2swap.getKey();
		assertEquals(i.intValue(),8);
	}

	@Test
	public void testMinmax() {
		Integer [] arr = {p.getValue(),p1.getValue(),p2.getValue()};
		Pair.printPair(Pair.minmax(arr));
	}

	@Test
	public void testMinMaxLamda() {
		Integer [] arr = {p.getValue(),p1.getValue(),p2.getValue()};
		Pair.printPair(Pair.minMax(arr,(a,b) -> a-b));
	}
	
	@Test
	public void testMinMaxLamdaForEach() {
		Integer [] arr = {p.getValue(),p1.getValue(),p2.getValue()};
		Pair.printPair(Pair.minMax(arr,Integer:: compareTo));
	}
	
	@Test
	public void testMinMaxAnonymous() {
		Integer [] arr = {p.getValue(),p1.getValue(),p2.getValue()};
		Pair.printPair(Pair.minMax(arr,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.intValue() - o2.intValue();
			}
			
		}));
		@Test
	public void testMinimp() {
		Integer [] arr = {p.getValue(),p1.getValue(),p2.getValue()};
		Pair.printPair(Pair.minmaxImp(arr));
	}
	}*/

}
