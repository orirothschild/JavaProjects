package il.co.ilrd.observer;

import il.co.ilrd.thermo.*;
import il.co.ilrd.user.User;

public class TestObservers {
	
	public static void main(String[] args) {
		Thermo<Integer> thermo = new Thermo<>();
		thermo.getCallbacks();
		User<Integer> kumkum = new User<>();
		User<Integer> airconditioner = new User<>((arg)-> {User.setRoomTemp(arg);}, ()-> {System.out.println(" i am dead");});
		User<Integer> electricbottle = new User<>(true);
		Subject<Integer> sub = thermo.getInstance();
		
		sub.register(kumkum);
		sub.register(airconditioner);
		sub.register(electricbottle);
		thermo.getCallbacks();
		System.out.println("************before notify*************");
		System.out.println(airconditioner.getTemp());
		System.out.println(kumkum.getTemp());
		System.out.println(User.getRoomTemp());
		System.out.println("************after notify*************");
		sub.notifyAllObservers(100);
		System.out.println(electricbottle.getTemp());
		System.out.println(kumkum.getTemp());
		System.out.println(User.getRoomTemp());
		
		sub.stop();
		}; 
	}

