package il.co.ilrd.singleton;

public class singletonEager {
	private static singletonEager instance = new singletonEager(); //eager initilaisation,created before ever being needed

	private singletonEager() {
		
	}
	public static singletonEager getinstance() {
		return instance;
	}
}
