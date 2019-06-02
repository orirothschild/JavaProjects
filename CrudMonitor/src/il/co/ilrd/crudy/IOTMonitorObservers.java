package il.co.ilrd.crudy;

import java.io.IOException;

public class IOTMonitorObservers {

	public static void main(String[] args) throws IOException, InterruptedException {
		LogMonitor monitor = new LogMonitor("/var/log/syslog");
		monitor.addObserver(new TomCatCrud("/var/log/syslog",  "Http://127.0.0.1:8080/TomCatIots/iots"));
		
		while (true) {
			monitor.watchFile();
		}
	}
}
