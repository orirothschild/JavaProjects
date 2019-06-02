package il.co.ilrd.crudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Objects;
import java.util.Observable;

import static java.nio.file.StandardWatchEventKinds.*;

public class LogMonitor extends Observable{
	private final Path path;
	private final File file;
	private final WatchService watcher;
	private WatchKey watchKey;
	
	public LogMonitor(String path) throws IOException {
		Objects.requireNonNull(path, "path cannot be null");
		
		file = new File(path);
		if (!file.isFile()) { throw new InvalidPathException(path, "invalid path"); }
		
		this.path = Paths.get(file.getParent());
		
		watcher = FileSystems.getDefault().newWatchService();
		watchKey = this.path.register(watcher,  ENTRY_CREATE,
								                ENTRY_DELETE,
								                ENTRY_MODIFY);
	}
	
	public File getMonitoredFile() {
		return file;
	}
	
	public void watchFile() throws InterruptedException, IOException {
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = null;
		
			watchKey = watcher.take();
			
		    for (WatchEvent<?> event : watchKey.pollEvents()) {
		    	Path changed = (Path) event.context();
		    	
	            if (changed.endsWith(file.getName())) {
	            	while ((line = input.readLine() )!= null) {
		               	setChanged();
		            	notifyObservers(line);
	            	}
	            }		   
		    
		    watchKey.reset();
		}
		
		input.close();
	}
}
