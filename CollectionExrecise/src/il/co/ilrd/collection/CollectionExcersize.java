package il.co.ilrd.collection;

import java.util.*;

import java.util.HashMap;
import java.util.Map;


public class CollectionExcersize<E> {
	
	public static <E> void printArrayAsList(Integer [] a){
			System.out.println(Arrays.toString(a));
			List <Integer> l = new ArrayList<>();
			l = Arrays.asList(a);
			for(Integer i:l) {
				System.out.println(i.toString());
			}
			System.out.println("*******************************************");
	}
	public static <K,V> void printHashMapSet(Map<K,V> h){
		Set <Map.Entry<K, V>> st = h.entrySet();
		for(Map.Entry<K, V> me: st) {
			System.out.println(me.getValue() +":"+ me.getKey());
		}
		System.out.println("*******************************************");
	}
		
	public static <K,V> void printHashMapForEach(Map<K,V> h){
		for(Map.Entry<K, V> me:h.entrySet()) {				/*we are iterating over a mapentrys and not the K and V values in the foreach loop*/
			System.out.println(me.getValue() +":"+ me.getKey());
		}
		System.out.println("*******************************************");
	}
	
	public static <K,V> void printHashMapIterator(Map<K,V> h){
		Iterator<Map.Entry<K, V>> iter = h.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<K, V> entryfromiterator = iter.next();    /*iternext returns a map entry that is *data* of the iterator*/
			System.out.println(entryfromiterator);
		}
		System.out.println("*******************************************");
	}
	
	public static<K,V extends Number> void mapSumOfValues(/*Map<K,V> h,*/ DataObject<K, V> []dataarray) {
		Map<K,V> h = new HashMap<>();
		Integer sum = 0;
		for(DataObject<K,V> d: dataarray) {
			h.merge(d.getKey(),d.getValue().intValue(), Integer:: sum);
		}
	/*	Integer num = 0;
		for(int i = 0; i< dataarray.length-1; ++i) {
			for( int j = 0; j < dataarray.length-1; ++j) {
				if(dataarray[i].equals(dataarray[j].getData())){
					num = dataarray[i].getValue().intValue() + dataarray[j].getValue().intValue(); 
				}
			}
			
			h.put(dataarray[i].getData(),(V)num);
		}*/
		printHashMapSet(h);
	}

	public static void main(String [] args) {
		Integer [] a = new Integer[1];
		Map<Integer,String> h= new HashMap<>();
		/*for(int i = 0; i < 10; ++i) {
			a[i] = i;
		}*/
		h.put(1,"SUNDAY");
		h.put(2,"MONDAY");
		h.put(3,"THUSDAY");
		h.put(4,"WEDNSDAY");
		h.put(5,"THURSDAY");
		h.put(6,"FRIDAY");
		h.put(7,"SATURDAY");
		/*printHashMapForEach(h);
		printHashMapIterator(h);
		printHashMapSet(h);
		printArrayAsList(a);*/
		System.out.println("******************************");
		DataObject<String, Integer>[] dataarray = new DataObject[10];
		DataObject<String, Integer> k = new DataObject<>("SUNDAY", 1);
		DataObject<String, Integer > b = new DataObject<>("SUNDAY", 2);
		DataObject<String, Integer > c = new DataObject<>("SUNDAY", 3);
		DataObject<String, Integer > d = new DataObject<>("MONDAY", 1);
		DataObject<String, Integer > e = new DataObject<>("MONDAY", 1);
		DataObject<String, Integer > f = new DataObject<>("MONDAY", 1);
		dataarray[0] = k;
		dataarray[1] = b;
		dataarray[2] = c;
		dataarray[3] = d;
		dataarray[4] = e;
		dataarray[5] = f;
		mapSumOfValues(dataarray);
	}
	
	
}
