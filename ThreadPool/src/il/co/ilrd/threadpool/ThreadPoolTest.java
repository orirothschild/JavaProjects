package il.co.ilrd.threadpool;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import il.co.ilrd.threadpool.ThreadPool.Priority;

public class ThreadPoolTest {

	static volatile int counter = 0;
	 @Rule
     public ExpectedException thrown= ExpectedException.none();

/*	@Test
	void testSubmitRunnablePriority() {
		ThreadPool tp = new ThreadPool(5);
		tp.exec();
		for (int i = 0 ; i < 6 ; ++i) { 
		tp.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("Im mission " + counter++ + " from Thread named: "+ Thread.currentThread());
				return null;
			}
			
		});
		}	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		}	
		
		assertTrue(true);
	}*/

/*	@Test
	public void testSubmitCallableOfTPriority() throws InterruptedException {
		System.out.println("**************** NEW TEST *******************");
		
		ThreadPool tp = new ThreadPool(100);
		
		for (int j = 0; j < 2; ++j) {
			tp.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("last one");
					
				}
			}, Priority.MIN, "STAM");
			Thread.sleep(100);
		}
		for (int j = 0; j < 2; ++j) {
		tp.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("This should be 3rd");
			}
		}, Priority.DEFAULT);
		Thread.sleep(100);
		}
		for (int j = 0; j < 2; ++j) {
		tp.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("This is a String Callable and should be 2st");
				return "YEHH";
			}
			
		}, Priority.MAX);
		Thread.sleep(100);
		}
		
		tp.exec();
	}*/
	
	/*
	@Test
	public void testAwaitTermination() {
		System.out.println("**************** NEW TEST *******************");
		
		ThreadPool tp = new ThreadPool(5);
		
		for (int i = 0 ; i < 110; ++i) {
			final int temp = i;
			tp.submit(()->{ Thread.sleep(500); System.out.println("mission running" + temp); return null;});
		}

		tp.exec();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		}
		tp.shutdown();
		System.out.println("*****Shutdown Commerced****");
		tp.submit(()->{ Thread.sleep(500); System.out.println("i cant be seen"); return null;});

		
		try {
			System.out.println(tp.awaitTermination(8, TimeUnit.SECONDS));
			
		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		}
		

	}*/
	
	/*@Test
	public void testShutdown() {
		System.out.println("**************** NEW TEST *******************");
		
		ThreadPool tp = new ThreadPool(2);
		tp.exec();
		
		for (int i = 0; i < 10000 ; ++i) {
			final int temp = i;
			tp.submit(()-> {System.out.println("This should be seen - item no." + temp); return null;});
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		}
		tp.shutdown();
		
		for (int i = 10000; i < 20000 ; ++i) {
			final int temp = i;
			tp.submit(()-> {System.out.println("This should be seen - item no." + temp); return null;}, Priority.MAX);
		}
		
		assertTrue(true);
		
	}*/
	
	
		
	@Test
    public void testSetNumOfThreads() throws InterruptedException {
            System.out.println("**************** NEW TEST *******************");
            ThreadPool tp = new ThreadPool(5);
            
            for (int i =0 ; i < 2000; ++i) {
                tp.submit(()-> {Thread.sleep(800); return null;}, Priority.MIN);
            }
            tp.exec();
           
            for (int i = 0 ; i < 2000; ++i) {
                tp.submit(()-> {Thread.sleep(1500); return null;}, Priority.MAX);

            }
            tp.setNumOfThreads(1);
            Thread.sleep(2000);
            
            System.out.println(Thread.activeCount());
            Thread.sleep(2000);
            
            tp.resume();
            
            
            tp.setNumOfThreads(10);
            System.out.println("************after increase***********");
			Thread.sleep(2000);
			System.out.println(Thread.activeCount());
			
            System.out.println("************after decrease***********");
            tp.setNumOfThreads(2);
            Thread.sleep(2000);
            System.out.println(Thread.activeCount());
            
            
            
            
            try {
                Thread.sleep(10000);
                
            } catch (InterruptedException e) {
                // a
                e.printStackTrace();
            }
            
	}

	@Test
	public void testForGet() {
		ThreadPool tp = new ThreadPool(10);
		Future<String> f1 = tp.submit(()->{return "GOOD JOB!";});
		Future<String> f2 = tp.submit(()->{return "GOOD JOB II !";});
		Future<String> f3 = tp.submit(()->{return "GOOD JOB III!";});
		Future<String> f4 = tp.submit(()->{Thread.sleep(2000); return "GOOD JOB! IV";});
		Future<String> f5 = tp.submit(()->{Thread.sleep(10000); return "bahhh";});
		
		tp.exec();
		
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());

		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		} catch (ExecutionException e) {
			// a
			e.printStackTrace();
		}

		try {
			System.out.println(f4.get(5, TimeUnit.SECONDS));
			System.out.println(f5.get(6, TimeUnit.SECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// a
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// a
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void testSubmitCallableOfV() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubmitCallableOfVPriority() {
		fail("Not yet implemented");
	}
*/
}
