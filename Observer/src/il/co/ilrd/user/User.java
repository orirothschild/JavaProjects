package il.co.ilrd.user;

import java.util.function.Consumer;

import il.co.ilrd.observer.CallBack;

public class User<T>{

	private CallBack<T> callback;
	private T tempature;
	private static Integer Roomtemp;
	
	public User(){
		callback = new CallBack<T>((args)-> {tempature = args;}, ()-> {System.out.println(" disconnected callback" + this);});
	}
	
	public User(Consumer<T> cons, Runnable notifyDeath){
		
		callback = new CallBack<T>(cons, notifyDeath);
		
	}
	
	public User(boolean bool){
		callback = new CallBack<T>(this::accept, User::staticSetNotify);
	}
	
	
	public static void staticSetTempature(Integer Roomtemp) {
		User.Roomtemp = Roomtemp;
	}
	
	public static void staticSetNotify() {
		System.out.println("yo soy muerte");
	}
	
	
	public void accept(T t) {
		T temp = null;
		
		if (null == t) {return;}
		
		if (!(t.equals(temp))) {				
			tempature = t;
			System.out.println(" - the temp is now: " + temp);
		} 
	}
	
	public T getTemp() {return tempature;}
	public  CallBack<T> getCallback() {return callback;}
	public void setTemp(T tempature) { this.tempature = tempature;}
	public static void setRoomTemp(Integer arg) {Roomtemp = arg;}
	public static Integer getRoomTemp() {return  Roomtemp;}
}
