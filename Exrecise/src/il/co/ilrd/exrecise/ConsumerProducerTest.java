package il.co.ilrd.exrecise;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import il.co.ilrd.exrecise.ConsumerProducer.ListConsumer;


public class ConsumerProducerTest {
	@Before
	public void setUp() throws Exception {
	}

	/*@Test
	public void test() {
		List <Runnable> l = new ArrayList<>();
		l.add(new ConsumerProducer(). new BarrierProduce():: run);
		l.add(new ConsumerProducer(). new BarrierConsume():: run);
		new Thread(l.get(0)).start();
		for(int i = 0 ; i < 10000; ++ i) {
			new Thread(l.get(1)).start();
		}
		
	}*/
	
	@Test
	public void test2() {
		List <Runnable> l = new ArrayList<>();
		l.add(new ConsumerProducer().new ListConsumer()::run);
		l.add(new ConsumerProducer().new ListProducer()::run);
		for(int i = 0 ; i < 100; ++ i) {
			new Thread(l.get(1)).start();
			new Thread(l.get(0)).start();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
