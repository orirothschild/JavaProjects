package il.co.ilrd.genericlinkedlist;

@SuppressWarnings("serial")
public class EmptyListException extends Exception{
	public void print() {
		System.out.println("the list is empty");
	}
}
