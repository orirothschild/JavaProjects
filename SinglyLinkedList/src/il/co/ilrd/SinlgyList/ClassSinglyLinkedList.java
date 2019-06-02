package il.co.ilrd.SinlgyList;
import il.co.ilrd.Iterator.*;
/**
 * 
 * @author Ori Rothschild
 *
 */
public class ClassSinglyLinkedList{
		private Node head = new Node(0xdeadbeef,null);
		private int size;
		
		private class ClassIterator implements Iterator{
			private Node iterator;
			
			public ClassIterator(Node node){
				iterator = node;
			}
			/**/
			@Override
			public boolean hasNext() {
				return (iterator.nextNode != null);
				
			}
			
			@Override
			public Object next() {
					Object Thisdata = iterator.data;
					iterator = iterator.nextNode;
					return (Thisdata);
			}
		}	
		
		public boolean isEmpty() {
			return head.nextNode == null; 
		}
		
		public int size() {
		        return this.size;
		    }
		
		public void pushFront(Object value) {
				
				head = new Node(value,head);
				++size;
		}
		
		public Object popFront() {
			if(isEmpty()) {
				System.out.println("the list is empty");
				return null;
			}
			
			Object data = head.data;
			head = head.nextNode;
			--size;
			return data;
		}
		
		public Iterator Find(Object toFind) {
			 Node runner = head;

		        while (null != runner.nextNode && !runner.data.equals(toFind)) {
		            runner = runner.nextNode;
		        }
		        
		        if(!runner.data.equals(0xdeadbeef)){
		        	return new ClassIterator(runner);
		    }
		
			return null;
			
		}
		
		public Iterator iterator(){
		        return new ClassIterator(head);
		}
		
		/*tool methods*/
		
		@Override
		public  String toString(){
	        return "node value: " + head.data;
	     }
		
		private class Node {
			private Object data;
			private Node nextNode;
			
			private Node(Object value, Node nextNode){
				data = value;
				this.nextNode = nextNode;
			}
			
			/*tool methods*/
		     @Override
		    public  String toString(){
		        return "node value: " + data;
		    }
		}
	}

