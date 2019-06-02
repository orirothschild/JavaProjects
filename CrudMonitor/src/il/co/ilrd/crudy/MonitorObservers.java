package il.co.ilrd.crudy;
import java.io.IOException;

public class MonitorObservers{
	
	public static void main(String[] args) throws IOException, InterruptedException {
		LogMonitor monitor = new LogMonitor("/var/log/syslog");
		CRUD<String, Integer> crudFile = new CRUDFile("/home/space_moses/my_log.txt");
		monitor.addObserver((x, y)->{
			try {
				crudFile.create((String)y);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		
		while (true) {
			monitor.watchFile();
		}
	}
}
