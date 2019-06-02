package il.co.ilrd.SinlgyList;
import il.co.ilrd.Iterator.*;

public class ClassSinglyLinkedList{
        private Node head;
        private int size; //this is bad practice
        
        private class ClassIterator implements Iterator{
            private Node iterator;
            
            public ClassIterator(Node node){
                iterator = node;
            }
            
            @Override
            public boolean hasNext() {
                return (iterator.nextNode != null); //this.nextNode
                
            }
            
            @Override
            public Object next() {
                if(this.hasNext()){  //no need for this check...
                    Object Thisdata = iterator.data; //this.data
                    iterator = iterator.nextNode; //same as above
                    return (Thisdata);
                }
                return null; //why are you returning null, this node might have data, even if it does not have a next node.
            }
        }    
        
        public ClassSinglyLinkedList() {
            head = new Node(0xdeadbeef);
        }
        
        public boolean isEmpty() {
            return this.head == null; //when you create a list, you give it a head, so according to this method, it is never empty.
        }
        
        public int Size() {
                return this.size; //bad practice, dont keep a size variable in the list, instead, count it's nodes.
            }
        
        public void pushFront(Object value) {
                
                Node newHead = new Node(value);
                newHead.nextNode = this.head;
                this.head = newHead;
                ++this.size;
        }
        
        public Object popFront() {
            if(true == isEmpty()) {
                System.out.println("the list is empty"); //you should not be printing here, user should check that list is not empty before popping.
                return null;
            }
            
            Object data = this.head.data;
            this.head = this.head.nextNode;
            --this.size;
            return data;
        }
        
        public Object Find(Object toFind) {
             Node runner = this.head;

                while (!runner.checkData(0xdeadbeef) && !runner.checkData(toFind)) {
                    runner = runner.nextNode;
                }
                
                if(!runner.checkData(0xdeadbeef)){
                    return new ClassIterator(runner); //this looks like it should work, but I think the assignment was to use the iterator methods, and then return the iterator...
            }
        
            return null; 
            
        }
        
        public Iterator iterator(){
                return new ClassIterator(head); //spacing is off by a tab
        }
        
        /*tool methods*/
        
        private Object getData() {
            return this.head.data;
        }

        @Override
        public  String toString(){
            return "node value: " + this.getData(); //why did you add this public method? this was not in the API
         }
        
        private class Node {
            private Object data;
            private Node nextNode;
            
            private Node(Object value){
                data = value;
                nextNode = null;
            }                            //could you think of another useful constructor here that could save you some work in pushFront?
            
            /*tool methods*/
            
            private Object getData(){
                  return data;
            }
            
            
            private boolean checkData(Object toFind) {
                if(data.equals(toFind)) {
                    return true;
                }
                return false;
            }
            
             @Override
            public  String toString(){
                return "node value: " + getData(); //why did you add this public method? this was not in the API
            }
        }
        
        
        //why these unnecessary lines?
    }//why is everything indented an extra tab?

