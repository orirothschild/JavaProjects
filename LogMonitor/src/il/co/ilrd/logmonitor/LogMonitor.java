package il.co.ilrd.logmonitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class LogMonitor extends Observable{
	
	//------------------------------------------------------------------------/
  	//---------------------MonitorLog private fields--------------------------/
  	//------------------------------------------------------------------------/
	
	private final Path watchedDir;
	private final String watchedFile;
	private boolean running = true;
	private boolean initialized = false;
    private WatchService watcher;
    private BufferedReader reader;
    private int amoutOfLinesInCrudFile;

    //------------------------------------------------------------------------/
  	//-------------------------MonitorLog public API--------------------------/
  	//------------------------------------------------------------------------/
    
    public LogMonitor(String dir, String file) {
    	Objects.requireNonNull(dir,"null directory in LogMonitor");
    	Objects.requireNonNull(file,"null file in LogMonitor");
    	
    	this.watchedFile = file;
    	this.watchedDir = Paths.get(dir);
    }  
    
    public LogMonitor(String dirPathAndFile) {
    	Objects.requireNonNull(dirPathAndFile,"null directory in LogMonitor");
    	File file = new File(dirPathAndFile);
    	this.watchedFile = file.getName();
    	this.watchedDir = Paths.get(file.getParent());
    }  
    
    
    public void watch() throws InterruptedException, IOException {
    	monitorInit();
    	running = true;
    	WatchKey key = null;
    	
    	while (running) {
    		
    	    key = watcher.take();
 
    	    for (WatchEvent<?> event: key.pollEvents()) {
    	        
    	        Path changed = (Path)event.context();
    	        if (changed.endsWith(watchedFile)) {
    	        	for(int i = 0; i < amoutOfLinesInCrudFile;++i) {
    	        		reader.readLine();
    	        	}
    	        for(String line;(line = reader.readLine()) != null;) {
					setChanged();
					notifyObservers(line);
				}
    	    }
    	    }
    	    
    	    key.reset();
    	}
    	
    	reader.close();
    }
    
    //------------------------------------------------------------------------/
  	//-----------------------MonitorLog private methods-----------------------/
  	//------------------------------------------------------------------------/
    
    private void monitorInit() throws IOException{
    	if (!initialized) {
    		watcher = FileSystems.getDefault().newWatchService();
    		this.watchedDir.register(watcher,  
    				StandardWatchEventKinds.ENTRY_MODIFY);
    		File file = new File(watchedDir + "/" + watchedFile);
    		if(!file.exists()) { throw new FileNotFoundException();}
    		reader = new BufferedReader(
    				new FileReader(new File(watchedDir + "/" + watchedFile)));
    		initialized = true;
    	}
    }
    
    public void register(Observer o) {
    	if(o instanceof BackUpLog) {
    	try {
			amoutOfLinesInCrudFile = ((BackUpLog)o).retriveLineNumber();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
    	}
    	addObserver(o);
    }
    
    void stop() {
    	running = false;
    }
    


	public void updateLineNumber(int count) {
		amoutOfLinesInCrudFile = count;
		
	}
}
