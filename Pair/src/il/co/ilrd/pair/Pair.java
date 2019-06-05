package il.co.ilrd.pair;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiFunction;

public class Pair<K, V> implements Entry<K, V> {
	private final K key;
	private V value;
	
	//CTOR
	private Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}
	
	@Override
	public boolean equals(Object o) {
	Objects.requireNonNull(o, "argument is null");
   	if (!(o instanceof Pair<?, ?>)) { return false; }
   	Pair<?, ?> pair = (Pair<?, ?>) o;
   	Object userKey = pair.getKey();		
	Object userValue = pair.getValue();
	
	return (userKey == null ? key == null : userKey.equals(key)) &&
            (userValue == null ? value == null : userValue.equals(value));
}
	
	@Override
	public int hashCode() {
		return 31 * ((key == null ?0: key.hashCode()) + (value == null ? 0 :value.hashCode()));
	}
	
	@Override
	public String toString() {
		return (key + ":" + value);
	}
	
	public static <K, V> Pair<V, K> swap(Pair<K, V> pair){
		Objects.requireNonNull(pair,"swap method recived null");
		
		return new Pair<>(pair.getValue(), pair.getKey());
	}
	
	public static <K, V> Pair<K, V> of(K key, V value){
		return new Pair<>(key, value);
	}
	
	public static <T extends Comparable<? super T>> Pair<T, T> minmax(T[] arr){
	
		return minMax(arr,T :: compareTo);
	}
	
	public static <T> Pair<T,T> minMax (T[] arr, Comparator<? super T> comp) {  
		Objects.requireNonNull(comp,"comperator method is null");
		Objects.requireNonNull(arr,"array is null");
		int lastindex = arr.length -1;
		T min = arr[0];
		T max = arr[lastindex];
		for(int i = 0; i < arr.length/2 ;++i) {
			if(comp.compare(arr[i],arr[lastindex -1]) > 0) {
				if(comp.compare(arr[i], max) > 0) {
					max = arr[i];
				}
				if(comp.compare(arr[lastindex -i],min) < 0) {
					min = arr[lastindex -i];
				}
			}
			else {
				if(comp.compare(arr[i],min) < 0) {
					min = arr[i];
				}
					if(comp.compare(arr[lastindex - i], max) > 0) {
						max = arr[i];
				
					}
			}
		}
		return new Pair<>(min, max);
	}
		
	@SuppressWarnings("unused")
	private static <T> Pair<T,T> minMaxImp (T[] arr, BiFunction<T, T, Integer> b) {
		Objects.requireNonNull(arr,"array is null");
		int lastindex = arr.length -1;
		T min = arr[0];
		T max = arr[lastindex];
		for(int i = 0; i < arr.length/2;++i) {
			if(b.apply(arr[i],arr[lastindex -1]) >0) {
				if(b.apply(arr[i], max) > 0) {
					max = arr[i];
				}
				if(b.apply(arr[lastindex -i],min) < 0) {
					min = arr[lastindex -i];
				}
			}
			else {
				if(b.apply(arr[i],min) < 0) {
					min = arr[i];
				}
					if(b.apply(arr[lastindex - i], max) > 0) {
						max = arr[i];
				
					}
			}
		}
		return new Pair<>(min, max);
	}
	
	public static <K,V> void printPair(Pair<K,V> p){
		System.out.println(p.toString());
		System.out.println("*******************************************");
	}
	
	
	public static <K,V> void printPair(Pair<K,V> []p){
		for(Pair<?,?> pme: p) {
			System.out.println(pme.toString());
		}
		System.out.println("*******************************************");
	}
	/*@Override
	public boolean equals(Object o) {
   	if (!(o instanceof Pair<?, ?>) || o == null) { return false;}
   	Pair<?, ?> pair = (Pair<?, ?>) o;
   	Object userkey = pair.getKey();		
	Object uservalue = pair.getValue();
	
	return (Objects.equals(userkey, key)) && ( Objects.equals(uservalue, value));
}
	
	@Override
	public int hashCode() {
        return (Objects.hash(key) + Objects.hash(value));
	}
	 */
}
