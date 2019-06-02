package il.co.ilrd.waitablepq;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;


public class WaitablePQCondition<E> {
	private final PriorityQueue<E> pq;            // E must be comparable
	private final Object lockForPQ = new Object();

	public WaitablePQCondition() {
		pq = new PriorityQueue<>();
	}
	
	public WaitablePQCondition(Comparator<? super E> comparator) {
		pq = new PriorityQueue<>(comparator);
	}
	
	public boolean enqueue(E arg) {	
		boolean isAdded = false;
	
		synchronized(lockForPQ) {
			isAdded = pq.add(arg);
			lockForPQ.notify();
		}
		
		return isAdded;
	}
	
	public E dequeue() throws InterruptedException {
		E element = null;
		
		synchronized(lockForPQ) {
			if(pq.isEmpty()){lockForPQ.wait();}
			element = pq.poll();
		}
		
		return element;
	}
	
	public E dequeueWithTO(long time, TimeUnit unit) throws InterruptedException{
		E element = null;
		synchronized(lockForPQ) {
			if(pq.isEmpty()){lockForPQ.wait(unit.convert(time,TimeUnit.MILLISECONDS));}
			element = pq.poll();
		}
			
		return element;
	}
}