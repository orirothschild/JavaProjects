package il.co.ilrd.BashTree;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import il.co.ilrd.factory.Factory;


	public class BashTree{
    private final DirFile root;
    public BashTree(String path) throws InvalidPathException {
    	path = Objects.requireNonNull(path,"this is a null path");
    	java.io.File file = new java.io.File(path);
        if(!file.exists()) { throw new InvalidPathException(path, "file is nonexistent");}
        	if(!file.isDirectory()) {throw new InvalidPathException(path, "file is not a dir");}
        	
        	root = new DirFile(0, file);
    }
    
    private static final class factoryFile{
    	private final int depth;
    	private final java.io.File filename;
    	
    	public factoryFile(int depth, java.io.File filename) {
    		this.depth = depth;
    		this.filename = filename;
    	}
    	
    	public static final DirFile factoryinit(factoryFile ff) {
    		return new DirFile(ff.depth, ff.filename);
    	}
    }
    
    public void print() {
        root.print();
    }

    private interface File {
        public void print();
        default void printIndentation(int depth, String name) {
        	if(depth == 0) {
        		System.out.println(".");
        	}else {System.out.print("|");}
        	
        	for(int i = 0; i < depth ; ++ i){    
        		System.out.print("   ");        		
        	}
        	System.out.println("|___" + name); 
        }
    }

    private static class DirFile implements File {
        private final int depth;
        private final String name;
        private final List<File> list = new ArrayList<File>();
        
        private DirFile(int depth, java.io.File file) {
            this.depth = depth;
            name = file.getName();
            recAddFileToList(file, depth);
         
        }
        
        private void recAddFileToList(java.io.File file, int depth) {
            java.io.File[] inner = file.listFiles();
            Factory<String/*K*/,factoryFile/*T*/,File/*R*/> factory = new Factory<>();
            factory.Add("create reg", (f)-> new RegularFile(f.filename.getName(),f.depth));
            factory.Add("create dir", factoryFile :: factoryinit);
            
            for (java.io.File runner : inner) {
            	if(!runner.isHidden()) {
            		if(runner.isDirectory()) {
            			factoryFile ff = new factoryFile(depth +1, runner);
            			list.add(factory.Create("create dir",ff));
	                }
            		
	                else {
	                	list.add((factory.Create("create reg", new factoryFile(depth +1,file))));
	                }
	            }
	        }
	    }
        
        @Override
        public void print() {
        	for(File f: list) {
        		f.printIndentation(depth, name);
        		f.print();
        	}
        }

        private static class RegularFile implements File {
        private final int depth;
        private final String name;

        private RegularFile(String name, int depth) {
            this.name = name;
            this.depth = depth;
        }

        @Override
        public void print() {
        	printIndentation(depth, name);
        	System.out.println("|___" + name);
            System.out.println("");
        }
     }
 }
 
    public static void main(String[] args) {
    	
        BashTree test;
		try {
			test = new BashTree("/home/student/ori-rothschild/fs/java/ComplexNumber");
			test.print();
		} catch (InvalidPathException e) {
			System.out.println("the file is none existent");
			e.printStackTrace();
		}
        
    }
}




/*	//DirFile df = (new DirFile(depth +1, runner));
            			//list.add(df);
            			/*df.recAddFileToList(runner, depth + 1);
            		  @Override
        public void print() {
        	list.forEach(DirFile::printer);
        	}
        }
        
        public void printer() {
        	this.printIndentation(depth, name);
        	this.printer();
        }	 * */
