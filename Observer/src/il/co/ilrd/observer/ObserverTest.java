package il.co.ilrd.observer;

import org.junit.Before;
import org.junit.Test;

import il.co.ilrd.thermo.Thermo;
import il.co.ilrd.user.User;

public class ObserverTest {
	Thermo<Integer> thermo = null;
	User<Integer> kumkum = null;
	User<Integer> airconditioner = null;
	User<Integer> electricbottle = null;
	
	@Before
	public void setUp() throws Exception {
		Thermo<Integer> thermo = new Thermo<>();
		thermo.getCallbacks();
		User<Integer> kumkum = new User<>();
		User<Integer> airconditioner = new User<>((arg)-> {User.setRoomTemp(arg);}, ()-> {System.out.println(" i am dead");});
		User<Integer> electricbottle = new User<>(true);
	}

	@Test
	public void basicCaseTest() {
		Subject<Integer> sub = thermo.getInstance();
		sub.register(kumkum);
		sub.register(airconditioner);
		sub.register(electricbottle);
		sub.notifyAllObservers(50);
		System.out.println("**********************after notifying*************************");
		System.out.println();
	}

}
