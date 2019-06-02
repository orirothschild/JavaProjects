package il.co.ilrd.waitablepq;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class WaitablePQ<E> {
	private final PriorityQueue<E> pq;            // E must be comparable
	private final Object lockForPQ = new Object();
	private final Semaphore sem = new Semaphore(0);

	public WaitablePQ() {
		pq = new PriorityQueue<>();
	}
	
	public boolean isEmpty() { return pq.isEmpty();}
	public WaitablePQ(Comparator<? super E> comparator) {
		pq = new PriorityQueue<>(comparator);
	}
	
	public boolean enqueue(E arg) {	
		boolean isAdded = false;
	
		synchronized(lockForPQ) {
			isAdded = pq.add(arg);
		}
		sem.release();
		
		return isAdded;
	}
	
	public E dequeue() throws InterruptedException {
		sem.acquire();
		synchronized(lockForPQ) {
			return pq.poll();
		}
		
	}
	
	public E dequeueWithTO(long time, TimeUnit unit) throws InterruptedException, TimeoutException {
		if(!sem.tryAcquire(time, unit)) { throw new TimeoutException();}
		synchronized(lockForPQ) {
			return pq.poll();
		}
			
	}
}