package il.co.ilrd.gatewayserver;

public class Testserver {
	public static void main(String [] args) {
		new GateWayServer("127.0.0.1", 55555).start();
	}
}
