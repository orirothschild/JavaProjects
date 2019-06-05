package il.co.ilrd.exrecise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.*;


class ThreadsTest {
	Thread counterThread = null;
	static AtomicInteger counteratomic;
	final Object lock = new Object();
	static int counter = 0;
	List <Runnable> l = new ArrayList<>();

	public class ThreadCountersync implements Runnable {
	    public synchronized void run() {
	    	for(int i = 0; i < 10000000; ++i) {
	    		ThreadsTest.counter++;
	    	}
        }
    }
	
	public class ThreadCounterBlock implements Runnable {
	    public void run() {
	    	synchronized (lock) {
	    	for(int i = 0; i < 10000000; ++i) {
	    		ThreadsTest.counter++;
	    		}
	    	}
        }
    }
	
	@BeforeEach
	void setUp() throws Exception {
	}
	/*@Test void testThreadCounter() {
		l.add(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100000000; ++i) {
					++counter;
				}
				
			}
		});
l.add(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100000000; ++i) {
					++counter;
				}
				
			}
		});
		long start = System.nanoTime();
		for(Runnable r: l) {
			Thread t = new Thread(r);
				t.run();
			}
		long end = System.nanoTime();
		System.out.println(end - start);
		System.out.println(counter);
		counter = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}*/

	@Test 
	void testThreadCounter2() {
		long start = System.nanoTime();
		for (int i = 0; i < 2; ++i) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int i = 0; i < 100000000; ++i) {
						++counter;
					}
					
				}
			}).run();
		}
		System.out.println("test 1");
		System.out.println(System.nanoTime() - start);
	}
	
	@Test 
	void testThreadCounter3() {
		long start = System.nanoTime();
		for (int i = 0; i < 2; ++i) {
			new Thread(new ThreadCountersync()::run);
		}
		System.out.println("test 2");
		System.out.println(System.nanoTime() - start);
	}
	
	@Test 
	void testThreadCounter4() {
		long start = System.nanoTime();
		for (int i = 0; i < 2; ++i) {
			Thread t = new Thread(new ThreadCounterBlock()::run);
			t.run();
		}
		System.out.println("test 3");
		System.out.println(System.nanoTime() - start);
	}
	
	@Test 
	void testThreadCounter5() {
		long start = System.nanoTime();
		for (int i = 0; i < 2; ++i) {
			Thread t = new Thread(()->{for(int j = 0; j < 10000000; ++j) {counteratomic.incrementAndGet();}});
			t.run();
		}
		System.out.println("test 4");
		System.out.println(System.nanoTime() - start);
	}	
	
	@Test 
	void testThreadCounter6() {
		ReentrantLock lock = new ReentrantLock();
		long start = System.nanoTime();
		for(int i = 0; i < 2; ++i) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000000; ++i) {
					lock.lock();
					++counter;
					lock.unlock();
					}
				}
			});t.run();
		}
		System.out.println("test 5");
		System.out.println(System.nanoTime() - start);
	};
}