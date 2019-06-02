package il.co.ilrd.waitablepq;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

public class WaitablePQConditionTest {
	List<Runnable> listc = TestClass.listc;
	static final WaitablePQ<Integer> q = TestClass.q;
	static final WaitablePQCondition<Integer> pqc = TestClass.pqc;
	static final WaitablePQCondition<Integer> qccomperator = TestClass.pqccomperator;
	static final List<Runnable> list = TestClass.list;
	
	@Before
	public void setUp() throws Exception {
		listc.add(()->TestClass.enqFivePqc());
	listc.add(TestClass::deqFivePqc);
		listc.add(()->TestClass.enqPqc());
		listc.add(()->{
			try {
				TestClass.deqPqc();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	});
		listc.add(()->{
			try {
				TestClass.deqToPqc();
		} catch (InterruptedException e) {
				System.out.println("interrupts occured");
				e.printStackTrace();
			} catch (TimeoutException e) {
				System.out.println("timeout occured");
			}
		});
	}

	@Test
	public void testWaitablePQCondition() {
		for(int i = 0; i < 3; ++i) {
			list.add(() -> {
				try {
					try {
						TestClass.deqToPqc();
					} catch (TimeoutException e) {
						e.printStackTrace();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			list.add(() -> {
				try {
					TestClass.deqPqc();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		list.add(() -> TestClass.enqPqc());
		Thread t1 = new Thread(list.get(0));
		Thread t2 = new Thread(list.get(1));
		Thread t3 = new Thread(list.get(2));
		Thread t4 = new Thread(list.get(3));
		Thread t5 = new Thread(list.get(4));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t5.start();
		assertTrue(true);
	}

	/*@Test
	public void testWaitablePQConditionComparatorOfQsuperE() {
		pqc = 
	}*/


	/*@Test
	public void testDequeueWithTO() {
		for(Runnable r: listc) {
			Thread t = new Thread(r);
			t.start();
		}
		assertTrue(true);
	}*/

}
