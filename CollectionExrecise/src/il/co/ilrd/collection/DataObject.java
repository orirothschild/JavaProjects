package il.co.ilrd.collection;

public class DataObject<K ,V extends Number> {

	private K keydata;
	private V value;
	
	public DataObject(K keydata, V value){
		this.keydata = keydata;
		this.value = value;
		
	}
	
	public K getKey() {
		return keydata;
	}
	
	public V getValue() {
		return value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	   public boolean equals(Object keydata) {
		keydata = (K)keydata;
		return this.keydata == keydata;
	}
	
	

}


