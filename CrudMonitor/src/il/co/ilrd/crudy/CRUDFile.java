package il.co.ilrd.crudy;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.file.InvalidPathException;
import java.util.Objects;

public class CRUDFile implements CRUD<String, Integer>{
	private final File crudFile;
	private int lineNumber = 0;
	private PrintWriter outputStream;
	
	public CRUDFile(String path) {
		Objects.requireNonNull(path, "the path cannot be null");
		
		crudFile = new File(path);
		if (!crudFile.isFile()) { throw new InvalidPathException(path, "the path is not a file"); }

		try {
			outputStream = new PrintWriter(crudFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeToFile(String str) throws FileNotFoundException {
		outputStream.println(str);
		outputStream.flush();
		++lineNumber;	
	}

	@Override
	public Integer create(String entity) {
		try {
			writeToFile(entity);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lineNumber;
	}

	@Override
	public String read(Integer key) {
		throw new UnsupportedAddressTypeException();
	}

	@Override
	public void update(Integer id, String entity) {
		throw new UnsupportedAddressTypeException();
	}
}
