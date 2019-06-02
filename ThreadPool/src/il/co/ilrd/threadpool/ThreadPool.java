package il.co.ilrd.threadpool;

import il.co.ilrd.waitablepq.WaitablePQ;
import java.util.concurrent.*;

public class ThreadPool{
	private enum State {WAITING, DONE, CANCELLED}  /* for future*/
	public enum Priority {MIN,DEFAULT,MAX}
    private volatile int numOfThread;
    private final WaitablePQ<Task<?>> workQ = new WaitablePQ<>(((task1, task2) -> task2.priority - task1.priority));
    private final Semaphore PAUSING_SEMAPHORE = new Semaphore(0);
    private final Semaphore TERMINATION_SEMAPHORE = new Semaphore(0);
    private volatile boolean isShutdown = false;
    private volatile boolean isPaused = false;
    
    /*************************************threadpool utilities************************************************/
        
	public ThreadPool(int numOfThread) {
        this.numOfThread = numOfThread;
    }

    public void exec() {
    	for (int i = 0; i < numOfThread ; ++i) { 
    		new myThread().start();
    	}
    }
    
    private void exec(int num) {
    	for (int i = 0; i < num ; ++i) { 
    		new myThread().start();
    	}
    }

    /*************************************Main working Thread ************************************************/
    
    private class myThread extends Thread {
    	private boolean isRunning = true;
    	
		@Override
		public void run() {
			while (isRunning) {
				try {
					workQ.dequeue().run();
				} catch (Exception e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}
			}

	        	if(isShutdown) {
	        		TERMINATION_SEMAPHORE.release();
	        	}
		}
    }
    
    /*************************************Task utilites ************************************************/
    
    private static class Task<V>{
    	private Callable<V> callable;
        private final int priority;
        private State state = State.WAITING;
        private Semaphore futureSem = new Semaphore(0);
        private Exception exception = null;
        private final MyFuture future = new MyFuture();
        
        private Task(Callable<V> callable, int priority) {
            this.callable = callable;
            this.priority = priority;
        }
        
        public void run() throws Exception {
        	setResult(callable.call());
        	setDoneStatusToTrue();
        }
        
        private void setDoneStatusToTrue() {
        	futureSem.release();
	        state = State.DONE;
        }
        
        private void setResult (V resVal) {
        	future.result = resVal;
        }
        
        
        /*************************************Future utilites ************************************************/
        
        private class MyFuture implements Future<V> {
            private V result;

            @Override
            public boolean cancel(boolean b) {
            	if (isDone() || isCancelled()) {return false;}
            	
            	callable = ()->{
            					System.out.println("Mission (aka Task) is Cancelled"); 
            					return null;};
            					state = State.CANCELLED;
                return true; 
            }
            
            @Override
            public boolean isCancelled() {
            	return state.equals(State.CANCELLED);
            }

            @Override
            public boolean isDone() {
            	return state.equals(State.DONE);
            }

            @Override
            public V get() throws InterruptedException, ExecutionException {
            	if (state == State.CANCELLED) {throw new CancellationException();}
            	if (exception != null)	{throw new ExecutionException(exception);}
            	futureSem.acquire();
            	futureSem.release();
            	
                return result;
            }
            
            @Override
            public V get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            	if (state == State.CANCELLED) {throw new CancellationException();}
            	if (exception != null)	{throw new ExecutionException(exception);}
            	if (false == futureSem.tryAcquire(l, timeUnit)) {throw new TimeoutException();}
            	futureSem.release();
            	
            	return result;
            }
        }
    }
    /*******************************************threadpool functionality************************************************/
    
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
    	if (!isShutdown) { return false;} //Shutdown must be called before running the method.
    	return TERMINATION_SEMAPHORE.tryAcquire(numOfThread, timeout, unit);
    }
    
    public void shutdown() {
    	killThreads(numOfThread, Priority.MIN.ordinal() - 1);
    	isShutdown = true;
    }

    public void pause() {
    	
    	isPaused = true;
    	for(int i = 0; i < numOfThread; ++i) {
	    	submit(() -> {try {
				PAUSING_SEMAPHORE.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}},Priority.MAX.ordinal() +1);
    	}
    	
    }
    
    public void resume() {
    	if(!isPaused) {return;}
    	PAUSING_SEMAPHORE.release(numOfThread);
    	isPaused = false;
    }

    private void pauseTheNewThreads(int num) {
    	for(int i = 0; i < num; ++i) {
    		submit(() -> {try {
    			PAUSING_SEMAPHORE.acquire();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}},Priority.MAX.ordinal() + 1);
    	}
    	
    }

    public synchronized void setNumOfThreads(int numOfThread) {
    	if(numOfThread < 0) { throw new IllegalArgumentException();}
		int diff = this.numOfThread - numOfThread;
	 	this.numOfThread = numOfThread; 
	 	if(diff == 0) {return;}
	 	if(diff > 0) {killThreads(diff, Priority.MAX.ordinal() + 1); return;} 
	 	else if(isPaused) {pauseTheNewThreads(-diff);}
 	
	 	exec(-diff);
    }
    
    private void killThreads(int num, int p) {
		for (int i = 0 ; i < num; ++i) {
			submit(()->{ 
				((myThread)Thread.currentThread()).isRunning = false; 
			}, p);}
		
	}
    
    private Future<Void> submit(Runnable task, int priority) {
    	return submit(Executors.callable(task, null), priority);
    }
    
    private <V> Future<V> submit(Callable<V> call, int priority) {
    	Task<V> newTask = new Task<>(call, priority);
    	workQ.enqueue(newTask);
    	return newTask.future;
    	}
    
    public Future<Void> submit(Runnable task, Priority min) {
    	return submit(Executors.callable(task, null), min);
    }

	public <V> Future<V> submit(Runnable task, Priority priority, V value) {
    	return submit(Executors.callable(task, value), priority);
    }


	public <V> Future<V> submit(Callable<V> call) {
    	return submit(call, Priority.DEFAULT);
    }
    
	public <V> Future<V> submit(Callable<V> call, Priority priority) {
		if (isShutdown) {return null;}
		
    	Task<V> newTask = new Task<V>(call, priority.ordinal());
    	workQ.enqueue(newTask);
    	return newTask.future;
    }
}

