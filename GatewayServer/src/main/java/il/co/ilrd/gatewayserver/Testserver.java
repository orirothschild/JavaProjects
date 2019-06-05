package il.co.ilrd.gatewayserver;

public class Testserver {
	public static void main(String [] args) {
		new GateWayServer("10.1.0.85", 55555).start();
	}
}
