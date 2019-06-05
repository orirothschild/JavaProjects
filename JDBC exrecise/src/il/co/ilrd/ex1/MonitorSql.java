package il.co.ilrd.ex1;

import java.io.IOException;
import il.co.ilrd.crudy.LogMonitor;

public class MonitorSql {

	public static void main(String[] args) throws IOException, InterruptedException {
		LogMonitor monitor = new LogMonitor("/var/log/syslog");
		monitor.addObserver(new TomCatSql("/var/log/syslog",  "Http://127.0.0.1:8080/TomCatSql/CrudSql"));
		while (true) {
			monitor.watchFile();
		}
	}
}
