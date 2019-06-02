package il.co.ilrd.BashTree;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tree {
    private DirFile fileRoot;
    java.io.File folder;
	private static final Comparator<java.io.File> comp = new Comparator<java.io.File>() {  /*if you use compare to then impelment compareable*/
	public int compare(java.io.File f1, java.io.File f2) {
		if (((java.io.File) f1).isDirectory() && !((java.io.File) f2).isDirectory()) { return 1;}   /*what are those casts to (java.io.file?)*/
		else if (!((java.io.File) f1).isDirectory() && ((java.io.File) f2).isDirectory()) { return -1;} /*why dont you check the is directory of both f1 and f2  before sending it to this function? it is making it more complicated for no reason*/
		else { return ((java.io.File) f1).getName().compareTo(((java.io.File) f2).getName());}  /*you dont need the last else...*/
		}
	};

	private static void sort(java.io.File listFiles[]) {
		Arrays.sort(listFiles, comp);
	}


    public Tree (String path) throws InvalidPathException{
    	fileRoot = new DirFile(0, path);
    	folder = new java.io.File(path);
    	fileRoot.arrangeArray(folder);
    }

    public void print() {
    	fileRoot.print();
    }

    private interface File {
        public void print();
    }

    private static class DirFile implements File {
        private final int depth;
        private final String name;
        private final List<File> list = new ArrayList<File>();
       

        private DirFile(int depth, String name) {
            this.depth = depth;
            this.name = name;
        }
        
        private void arrangeArray(java.io.File folder) throws InvalidPathException{
        	if(!folder.exists()) {
        		throw new InvalidPathException(name, "no such path");
        	}
        	java.io.File[] listOfFiles = folder.listFiles();
        	Tree.sort(listOfFiles);
        	for (java.io.File f: listOfFiles) {
        		if (f.isDirectory()) {
        			DirFile current = new DirFile(depth + 1, f.getName());
        			list.add(current);
        			current.arrangeArray(f);        			
        		} else {
        			RegularFile current = new RegularFile(f.getName(), depth + 1);
        			list.add(current);
        		}
        	}
        }

        @Override
        public void print() {
        	if (depth == 0) {
        		System.out.println(".");
        	} else {
        		System.out.print("|");
        		for (int i = 0; i < depth - 1; ++i) {
        			System.out.print("   ");
        		}
        		System.out.print("-- ");
        		System.out.println(name);        		
        	}
            for (File f: list) {
            	f.print();
            }
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
        	System.out.print("│");
        	for (int i = 0; i < depth - 1; ++i) {
        		System.out.print("   ");
        	}
        	System.out.print("├-- ");				//you could just println("--" + name);
        	System.out.println(name);
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