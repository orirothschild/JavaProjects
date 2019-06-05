package ir.co.ilrd.genericmethods;

public class GenericMethods {
	static Integer[] integerarr = {1,1,41,26,453,74};
	static Double[] doublearr = {1.2,1.14,4.241 ,526.9, 353.1, 274.12};
	static String[] stringarr = {"tal","ori","michal","zzzz"};
	
	public static <T extends Comparable<T>> T comparemethod(T []objects){
		System.out.println(objects);
		T max = objects[0];
		for(T o: objects) {
		max = max.compareTo(o) > 0 ? max : o;
		}
		return max;
		
	}
	public class OrderedPairKey<K> {
		private K key;
		public K getKey(){ return key; }
		
	}

	public class OrderedPair<K, V> implements Pair<K, V> {

	    private K key;
	    private V value;

	    public OrderedPair(K key, V value) {
		this.key = key;
		this.value = value;
	    }
	    @Override 
	    public K getKey(){ return key; }
	    @Override
	    public V getValue() { return value; }

	    @SuppressWarnings("hiding")
		public <K,V> boolean genericCompareBooleandiffrent (Pair<K,V> p2, Double number) {
	    	
	    	return getKey().equals(number) && getKey().equals(p2.getKey());
	    }
	}
	
	public static <K extends Comparable<K>> K  genericCompareableReturnsK(OrderedPairKey<K> p1,OrderedPairKey<K> p2) {
		return  p1.getKey().compareTo(p2.getKey()) > 0 ? p1.getKey(): p2.getKey();

	}
	
	public static <K,V> boolean genericCompareBoolean (Pair<K,V> p1, Pair<K,V> p2) {
	   return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2);
	}

	
	public static void main (String[]args) {
		GenericMethods i = new GenericMethods();
		OrderedPair<String, String> p1 = new GenericMethods().new OrderedPair<String, String>("Even", "Steven");
		Pair <String,String> p2 = new GenericMethods().new OrderedPair<>("hi","hello");
		OrderedPair <Double,Double> p3 = new GenericMethods().new OrderedPair<>(1.2,4.2);
		OrderedPair <Double,Double> p4 = new GenericMethods().new OrderedPair<>(1.22,4.32);
		Pair rawp = new GenericMethods().new OrderedPair(1.2,"hello");   /*this is a raw type,its implicit for the types of T and V we want to avoid raw types*/
		System.out.println("biggest integer" + GenericMethods.comparemethod(GenericMethods.integerarr));
		System.out.println("biggest double" + GenericMethods.comparemethod(GenericMethods.doublearr));
		System.out.println("biggest String" + GenericMethods.comparemethod(GenericMethods.stringarr));
		System.out.println("this should be false boolean \t" + GenericMethods.<String, String>genericCompareBoolean(p1, p2));
		System.out.println("this should be false boolean \t" + GenericMethods.genericCompareBoolean(p1, p2));
		System.out.println("this is my method should be false \t" + p3.genericCompareBooleandiffrent(p4, new Double(5.4)));
		
		
		
	}
	
	private interface Pair <K,V> {
		public K getKey();
		public V getValue();
	}
	
	
}	


