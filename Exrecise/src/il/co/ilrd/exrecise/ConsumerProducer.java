package il.co.ilrd.exrecise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


public class ConsumerProducer{
	private static final List<Integer> ProductionList = new ArrayList<>();
	private static final AtomicInteger counter = new AtomicInteger(0);
	private static final Semaphore sem = new Semaphore(10);
	private static final Semaphore listsem = new Semaphore(0);
	private static final Semaphore Barriersem = new Semaphore(10);
	private static final Object lock = new Object();
	private static String Data = "massage";
	
	public static void shuffle(){
        List<Character> characters = new ArrayList<Character>();
        for(char c: Data.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(Data.length());
        while(characters.size()!= 0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
       Data = output.toString();
    }
	
	public class PingPruducer implements Runnable{
		

	@Override
	public synchronized void run() {
		while(true) {
		try {
			if(!sem.tryAcquire()) {
				wait();
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ping!");
		notify();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sem.release();
		}
		
	}
		
	}
	
	public class PongConsumer implements Runnable{

		@Override
		public synchronized void run() {
			while(true) {
			try {
				if(!sem.tryAcquire()){
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("pong!");
			notify();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sem.release();
			}
			
		}
		
	}
	public class BarrierConsume implements Runnable{

		@Override
		public void run() {
				try {
					Barriersem.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(lock) {
				System.out.println(counter.incrementAndGet() + Data);
				if(Barriersem.availablePermits() == 0) {
					lock.notify();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			}	
		}
	
	public class BarrierProduce implements Runnable{

		@Override
		public void run() {
			while(true){
				synchronized(lock) {
				while(Barriersem.availablePermits() > 0) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("pruducer in the house ");
				shuffle();
				Barriersem.release(10);
				}
			}
		}
	}
	
	public class ListProducer implements Runnable{

		@Override
		public void run() {
			synchronized (lock) {
				ProductionList.add(counter.incrementAndGet());
				System.out.println( " inserted " + counter);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.notifyAll();
			}
		}
	}
	

	public class ListConsumer implements Runnable{

		@Override
		public void run() {
		synchronized (lock) {
			if(ProductionList.isEmpty()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(" i got " + ProductionList.remove(0));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
	
	public class ListConsumerSem implements Runnable{

		@Override
		public void run() {
			try {
				listsem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					synchronized (lock) {
						ProductionList.remove(0);
					}
				}
			}
		
	public class ListProducerSem implements Runnable{

		@Override
		public void run() {
			synchronized (lock) {
				ProductionList.add(counter.incrementAndGet());
				System.out.println( " inserted " + counter);
			}
			
			listsem.release();
		}
		
	}
}