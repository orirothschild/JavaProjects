package il.co.ilrd.HashMap;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import il.co.ilrd.pair.*;


public class HashMap<K, V> implements Map<K, V>{

    /*-------------------- static variables ------------------*/
    
    private static final int DEFAULT_CAPACITY = 64;
    
    /*-------------------- instance variables ------------------*/
    
    private List<List<Entry<K, V>>> buckets;
    private final int capacity;
    private int modCount = 0;
    private Set<K> keySet;
    private Set<Entry<K,V>> entrySet;
    private Collection<V> valueCollection;
    
    //CTOR
    public HashMap() {
    	this(DEFAULT_CAPACITY);
    }
    
    public HashMap(int capacity) throws IllegalArgumentException{
    	if(capacity <= 0) {throw new IllegalArgumentException();} 
 		this.capacity = capacity;
        buckets = new ArrayList<>(capacity);
            
      for (int i = 0; i < capacity; ++i) {
           buckets.add(i,new LinkedList<Entry<K, V>>());
       }
   }
    
    @Override
    public int size() {
    	
    	int counter = 0;
    	for(List<Entry<K, V>> l: buckets) {
        	counter += l.size();
        }
    	return counter;
    }

    @Override
    public boolean isEmpty() {
       for(List<Entry<K, V>> l: buckets) {
    	   if(!l.isEmpty()) {
    		   return false;
    	   }
       }
       return true;
    }

    @Override
    public boolean containsKey(Object key) {
	      
    	int bucketIndex = hashForKey(key);
	       
	       for(Entry<K, V> e: buckets.get(bucketIndex)) {
	    	  if (sameKey(e, key)) {
	    		  
	    		  return true;
	    	  }
	       }
	       
    	  return false;
       	  }

    @Override
    public boolean containsValue(Object value) {
    	 for(Entry<K, V> e: entrySet()) {
    		 if (samevalue(e, value)){
             		
             		return true;
             	}
    	 }
    	 return false;
    }
   
    @Override
    public V get(Object key) {
		
    	int bucketIndex = hashForKey(key);

        for (Entry<K, V> e : buckets.get(bucketIndex)) {
        	 if (sameKey(e, key)) {
        		return (e.getValue());
        	}
        }
        
        return null;
    }

    @Override
    public V put(K key, V value) {
    	System.out.println(" i am here");
    	int bucketIndex = hashForKey(key);
    	
    	for(Entry<K, V> e: buckets.get(bucketIndex)) {
    		 if (sameKey(e, key)) {
    			
    				V prevValue = e.getValue();
    	    		e.setValue(value);
    	    		
    	    		++modCount;
    	    		
    	    		return prevValue;
    	    	}
    	}
    	
    	buckets.get(bucketIndex).add(Pair.of(key, value));
        ++modCount;
        
        return null;
    }
  
    @Override
    public V remove(Object key) {
    	int bucketIndex = hashForKey(key);
    	V returnedvalue = null;
    	
    	for (Entry<K,V> e: buckets.get(bucketIndex)) {
    		if (e.getKey().equals(key)){
    			returnedvalue = e.getValue();
    			buckets.get(bucketIndex).remove(Pair.of(key, e.getValue()));
    			return returnedvalue;
    		}
    	}
    	
    	return returnedvalue;
    }
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    	Objects.requireNonNull(m,"null map for putall");
    	for(Entry<? extends K, ? extends V> e : m.entrySet()) {
    		put(e.getKey(),e.getValue());
    	}
    	++modCount;
    }

    @Override
    public void clear() {
    	++modCount;
    	 buckets.parallelStream().forEach(List::clear);
        
    }
    
    private boolean samevalue(Entry<K, V> e, Object val) {
    	return Objects.equals(e.getValue(),val);
    }
    
    private boolean sameKey(Entry<K, V> e, Object key) {
    	return Objects.equals(e.getKey(),key);
    }
    /*********************** THE "EASY ONES" ***********************************/
    
    @Override
    public Set<K> keySet() {
       return (keySet == null ? keySet = new KeySet() : keySet);
    }

    @Override
    public Collection<V> values() {
    	 return (valueCollection == null ? valueCollection = new Values () : valueCollection);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
       return (entrySet == null ? entrySet = new EntrySet(): entrySet);
    }
    
    /***************************private classes *******************************/
    private class EntrySet extends AbstractSet<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntriesIterator(GetFirstEntry());
        }

        @Override
        public int size() {
           return HashMap.this.size();
        }
        
        private Entry<K, V> GetFirstEntry(){
        	for(List<Entry<K, V>> l : buckets) {
        		if(!l.isEmpty()) {
        			
        			return l.get(0);
        		}
        	}
        	
        	return null;
        }
        
        /********************* Inner Inner Class - Entries ********************************/
        private class EntriesIterator implements Iterator<Entry<K, V>> {
        	private Entry<K, V> currentEntry;
        	private final int expectedModCount = modCount;
        	private int currentBucketIndex = 0;
        	private  Entry<K ,V> nextEntry;
        	
         	private EntriesIterator(Entry<K, V> entry) {
				currentEntry = entry;
				currentBucketIndex = getFirstBucket();
				nextEntry = getNextEntry(currentEntry);
			}
        	
            @Override
            public boolean hasNext() {
                return currentEntry != null;
            }
            
            @Override
            public Entry<K, V> next() throws ConcurrentModificationException{
            	if(expectedModCount != modCount) {throw new ConcurrentModificationException();}
            	Entry<K, V> returnedEntry = currentEntry;
            	currentEntry = nextEntry;
            	nextEntry = getNextEntry(currentEntry);
            	return returnedEntry;
                
            }
            
            private final int getFirstBucket() {
            	for(List<Entry<K, V>> l : buckets) {
            		if(l.contains(currentEntry)){
            			
            		return buckets.indexOf(l);
            		}
            	}
            	
            	return 0;
            }
            
    		private final Entry<K, V> getNextEntry(Entry <K, V> current){
    			List<Entry<K, V>> l = buckets.get(currentBucketIndex);
    			int entryLocationInList = l.indexOf(current);
    			if(l.size() -1 > entryLocationInList) {
    				return l.get(++entryLocationInList);
    			}
    			
				for(++currentBucketIndex ; currentBucketIndex < capacity -1; ++currentBucketIndex) {
					if(!buckets.get(currentBucketIndex).isEmpty()) {
						return buckets.get(currentBucketIndex).get(0);
					}
				}
				
				return null;
			}
    	}   
	}
    		
    private class KeySet extends AbstractSet<K>  {
    	EntrySet set = new EntrySet();
        @Override
        public Iterator<K> iterator() {
            return new KeysIterator();
        }

        @Override
        public int size() {
            return set.size();
        }
        
        /********************* Inner Inner Class  - Key ********************************/
        private class KeysIterator implements Iterator<K> {
        	Iterator<Entry<K, V>> iter;
        	
        	public KeysIterator() {
				iter = set.iterator();
			}
	        @Override
	        public boolean hasNext() {
	        	
	            return iter.hasNext();
	        }
	        
	        @Override
	        public K next() {
	           return iter.next().getKey();
	        }
        }
     }
    
    private class Values extends AbstractCollection<V>  {
    	EntrySet set = new EntrySet();
        @Override
        public Iterator<V> iterator() {
           return new ValuesIterator();
        }

        @Override
        public int size() {
           return set.size();
        } 
        
        /********************* Inner Inner Class  - Values********************************/ 
        private class ValuesIterator implements Iterator<V> {
        	Iterator<Entry<K, V>> iter;
        	
        	 public ValuesIterator() {
				iter = set.iterator();
			}
	        @Override
	        public boolean hasNext() {
	            return iter.hasNext();
	        }
	        
	        @Override
	        public V next() {
	           return iter.next().getValue();
	        }
    }
        /*******************Private Methods****************************************************/
       
    }
    private int hashForKey(Object key) {
		return key != null ? Math.abs((Objects.hashCode (key) % capacity)): 0;
    }
    
   public void printeverything() {
    	 buckets.parallelStream().forEach(System.out::println);
    }
   
   public void printfindvalue(Entry<K,V> entry) {
	   		 for(Entry<K, V> e : entrySet()) {
    		 if(e.getKey() == entry.getKey()) {
    			   System.out.println(e.getValue());
    		   }
    	   }
   		}
 	}
