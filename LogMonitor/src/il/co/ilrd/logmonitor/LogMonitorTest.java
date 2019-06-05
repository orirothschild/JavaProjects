package il.co.ilrd.logmonitor;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class LogMonitorTest {
	private LogMonitor monitor;
	private BackUpLog observer;

	@Before
	public void setUp() throws Exception {
		monitor = new LogMonitor("/var/log/", "syslog");
		observer = new BackUpLog("/home/student/Desktop/backup.log");
		monitor.register(observer);
	}

	@Test
	public void testWatch() {
		try {
			monitor.watch();
		} catch (InterruptedException e) {
			System.out.println("interrupted exception was caught");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
