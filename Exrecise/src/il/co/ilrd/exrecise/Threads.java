package il.co.ilrd.exrecise;

public class Threads{
	public static int counter = 0;
	
	public class StopThread implements Runnable{
	@Override
	public void run() {
		int counter = 1;
		for(int i = 0; i < 10; ++i) {
		System.out.println("im awake for the " + counter++ + "time");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("im interruppted");
				}
			}
		}
	}
	
	
	public class ThreadInterruptExample implements Runnable {
		 
	    public void run() {
	        while(true) {
	        	int i = 0;
	            System.out.println("This is message #" + ++i);
	 
	            if (Thread.interrupted()) {		/*checks if interrupt falg was raised*/
	                System.out.println("I'm about to stop");
	                return;
	            }
	        }
	    }
	}
}
//	public static void main(String [] args) {
//	/*	Thread r = new Thread(new Threads().new StopThread());
//		r.start();
//		try {
//			Thread.sleep(3000);
//			System.out.println("i cant wait i have to say tihs");
//			r.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("im done");
////	*/	
////		   Thread t1 = new Thread(new Threads().new ThreadInterruptExample());
////	        t1.start();
////	 
////	        try {
////	            Thread.sleep(5000);
////	            t1.interrupt();
////	 
////	        } catch (InterruptedException ex) {
////	            // do nothing
////	        }
////	 
////	    }
////	}
//		
//	}
//}
