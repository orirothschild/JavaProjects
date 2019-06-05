package il.co.ilrd.logmonitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class BackUpLog implements Observer{
	
	private Path filePath;
	private BufferedWriter writer;
	private CRUDFile crudFile = new CRUDFile();

	public BackUpLog(String fileName) {
		filePath = Paths.get(fileName);
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		try {
			crudFile.create((String)arg);
		} catch (IOException e) {
			((LogMonitor)o).deleteObserver(this);
		}
	}
	
	public int retriveLineNumber() throws IOException {
			return ((int)Files.lines(filePath).count());
	}
	
	public Path getpath() {
		return filePath;
	}

	private class CRUDFile implements CRUD<String,Integer> {
		
		@Override
		public Integer create(String entity) throws IOException {
			writer.write(entity);
			writer.flush();
			writer.newLine();
			return ((int)Files.lines(filePath).count());
		}
		
	};
}
