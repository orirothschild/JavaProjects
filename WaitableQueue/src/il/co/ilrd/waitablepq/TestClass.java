package il.co.ilrd.waitablepq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestClass {
	static int counter = 0;
	static final WaitablePQ<Integer> q = new WaitablePQ<>();
	static final WaitablePQCondition<Integer> pqc = new WaitablePQCondition<>();
	static final WaitablePQCondition<Integer> pqccomperator = new WaitablePQCondition<>((a,b)-> a - b);
	static final List<Runnable> list = new ArrayList<>();
	static final List<Runnable> listc = new ArrayList<>();
	
	public static void deqFive() {
		for(int i = 0; i < 4; ++i) {
			try {
				--counter;
				System.out.println(" i dequed " + q.dequeue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deqFivePqc() {
		for(int i = 0; i < 4; ++i) {
			try {
				--counter;
				System.out.println(" i dequed " + pqc.dequeue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void deq()  throws InterruptedException{
		--counter;
		q.dequeue();
		}
	
	public static void deqPqc()  throws InterruptedException{
		System.out.println(pqc.dequeue());
		}
	
	
	public static void enq() {
		++counter;
		q.enqueue(0);
		}
	
	public static void enqPqc() {
		pqc.enqueue(0);
		}
	
	public static void deqTo() throws InterruptedException, TimeoutException{
		--counter;	 
		q.dequeueWithTO(1L,TimeUnit.NANOSECONDS);
	}
	

	public static void deqToPqc() throws InterruptedException, TimeoutException{
		--counter;	 
		System.out.println(pqc.dequeueWithTO(1L,TimeUnit.NANOSECONDS));
	}
	
	public static void enqFive() {
		for(int i = 0; i < 4; ++i) {
			++counter;
			q.enqueue(i);
		}
	}
	
	public static void enqFivePqc() {
		for(int i = 0; i < 4; ++i) {
			++counter;
			pqc.enqueue(i);
		}
	}
}
	
	
//	public static void main(String [] args) {
		/*for(int i = 0; i < 3; ++i) {
			listc.add(() -> {
				try {
					deqPqc();
				} catch (InterruptedException e) {
					// a Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		listc.add(() -> enqPqc());
		Thread t1 = new Thread(listc.get(0));
		Thread t2 = new Thread(listc.get(1));
		Thread t3 = new Thread(listc.get(2));
		Thread t4 = new Thread(listc.get(3));
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		t4.start();*/
//		
//		listc.clear();
//		listc.add(()->enqFivePqc());
//		listc.add(TestClass::deqFivePqc);
//		listc.add(()->enqPqc());
//		listc.add(()->{
//			try {
//				deqPqc();
//			} catch (InterruptedException e) {
//				// a Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		listc.add(()->{
//			try {
//				deqToPqc();
//			} catch (InterruptedException e) {
//				System.out.println("interrupts occured");
//				e.printStackTrace();
//			} catch (TimeoutException e) {
//				System.out.println("timeout occured");
//			}
//		});
//		for(int i = 0; i < 4; ++i) {
//			Thread t = new Thread(list.get(i));
//			t.start();
//		}
			/*listc.add(0, TestClass:: enq);
			});*/
		/*EnqueueThread e = new EnqueueThread(q);
		DequeueThread d = new DequeueThread(q);
		Thread t1 = new Thread(e);
		Thread t2 = new Thread(d);
		t1.start();
		t2.start();
		t1.interrupt();*/
/*		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// a Auto-generated catch block
			e1.printStackTrace();
		}
		list.add(() -> {
			try {
				deqTo();
			} catch (InterruptedException e1) {
				System.out.println("interrupt");
				e1.printStackTrace();
			} catch (TimeoutException e1) {
				System.out.println("timeout");
				e1.printStackTrace();
			}
		});*/
		/*list.add(0,TestClass:: enq);
		list.add(0,() -> {
			try {
				deq();
			} catch (InterruptedException e1) {
				System.out.println("interrupt");
				e1.printStackTrace();
			}
		});
		list.add(0,() -> enqFive());
		list.add(0,() -> deqFive());
		list.add(0,() -> {
			try {
				deqTo();
			} catch (InterruptedException e) {
				System.out.println("interrupt");
				e.printStackTrace();
			} catch (TimeoutException e) {
				System.out.println("timeout");
			}
		});
	
		for(Runnable r: list) {
			Thread t = new Thread(r);
			t.start();
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(counter);*/
