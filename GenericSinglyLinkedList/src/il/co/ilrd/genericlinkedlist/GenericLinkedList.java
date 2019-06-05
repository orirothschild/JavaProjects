package il.co.ilrd.genericlinkedlist;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
/**
 * Linked List generic implmetation
 * @author ori Rothschild
 *
 */


public class GenericLinkedList<E> implements Iterable<E> {
    private Node<E> head = null;
    private volatile int modCount = 0;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })  /*the deadbeef is not of type E so the compiler will shout*/
	public GenericLinkedList() {
		head = new Node(0xdeadbeef,null);
	}
    
    /**
     * the iterator method allows us to run the list from its begining
     * @return an iterator to the lists begining
     */
    
    @Override
    public Iterator<E> iterator() {
    	
        return new ListIterator(head);
    }
    
    /**
     * checks if the list is empty
     * @return a boolean value
     */
    
    public boolean isEmpty() {
    	
         return head.next == null;
    }
    
    /**
     * counts the amount of existing nodes in the list
     * @return an int with the size of the list
     */
    
    @SuppressWarnings("unused") 
    public int size() {
    	int counter = 0;
    	//forEach((ele)-> ++counter);
    	for(E listrunner : this) {
    		++counter;
    	}
    	
        return counter;
    }
    
    /**
     * pushes a value of the type of param to the list
     * @param a generic param to be pushed
     */
    
    public void pushFront(E data) {
    	head = new Node<>(data, head);
    	++modCount;
    }
    
    /**
     * pops a vlaue from the list,FIFO
     * @return a generic param representing the value of the removed Node
     * @throws EmptyListException list was called to pop when it was empty
     */
    
    public E popFront()  throws EmptyListException{
    	if(isEmpty()) {throw new EmptyListException();}
		E data = head.data;
		head = head.next;
		++modCount;
		
		return data;
    }
    
    /**
     * finds a generic value E in the list
     * @return an iterator to the location of the valuo
     * @throws null exception
     */
    
    public Iterator<E> find(E data) throws NoSuchElementException {
    	for(ListIterator i = (ListIterator)iterator(); i.hasNext();i.next()) {
    		if(i.current.data == data) { return i;}
    	}
    	throw new NoSuchElementException ();
    	/*Node<E> runner = head;
    	while (null != runner.next && !runner.data.equals(data)) {
    		runner = runner.next;
		}
    	
    	return (runner == null) ? null: new ListIterator(head);*/
}
    /**
     * appends to this list the pruducer list
     * @param producer is not null
     */
    
    public void addAll(GenericLinkedList<? extends E> producer) {
    	producer = Objects.requireNonNull(producer);
    	if(!producer.isEmpty()) {
    		GenericLinkedList<E> reversedList = null;
    		
    	if(!isEmpty()) {reversedList = newReverse(this);}
	    Iterator<? extends E> runner = producer.iterator(); 
	    	 
	    while(runner.hasNext()) {
    		 reversedList.pushFront(runner.next());
    	 }
    	 this.head = newReverse(reversedList).head;
    	 this.modCount = reversedList.modCount;
    	 ++modCount;
		}
	}
    
    /**
     * prepends this list to the begining of the consumer list
     * @param consumer is not null
     */
    
    public void getAll(GenericLinkedList<? super E> consumer) {
    	consumer = Objects.requireNonNull(consumer);
    	if(!isEmpty()) {
	    	GenericLinkedList<E> reversedConsumerList = newReverse(this);
	    	Iterator<E> runner = reversedConsumerList.iterator(); 
	    	while(runner.hasNext()) {
	    		consumer.pushFront(runner.next());
	    	}
    	++modCount;
       }
    }
    
    /**
     * reverses the nods in the list
     * @param list is not null
     * @return a reference to the new reversed list
     */
    
    public static <E> GenericLinkedList<E> newReverse(GenericLinkedList<? extends E> list){/*static methods gets a new E everytime!*/
    	list = Objects.requireNonNull(list);
    	GenericLinkedList<E> reversedList = new GenericLinkedList<>();
    	list.forEach(reversedList::pushFront); // for every element of the list.do a pushlist to the new list
    	
    	return reversedList;
    }
    /*
    	for(E listrunner: list) {
    		reversedList.pushFront(listrunner);
    	}*/
    
    /**
     * prints the values of the list
     * @param list generic parameters
     */
    
    public static void print(GenericLinkedList<?> list) {
    	list.forEach((ele)-> System.out.println(ele + "*"));
    	//list.forEach(System.out :: print);
    }

    private static class Node<E> {
        private Node<E> next;
        private E data;
        
        private Node (E data, Node <E> next) {
        	this.data = data;
        	this.next = next;
        }
    }
   
    private class ListIterator implements Iterator<E> {
        private Node<E> current;
        private final int expectedModCount = modCount;
        
        public ListIterator(Node<E> head) {
			current = head;
		}

        @Override
        public boolean hasNext() {
        	
            return current.next != null;
        }

        @Override
        public E next() {
        	if(expectedModCount != modCount) {throw new ConcurrentModificationException();}
        	if(!hasNext()) {throw new NoSuchElementException();}
        	E data = current.data;
        	current = current.next;
        	
            return data;
        }
    }
}
