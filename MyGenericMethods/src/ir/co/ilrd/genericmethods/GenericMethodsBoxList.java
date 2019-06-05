package ir.co.ilrd.genericmethods;
import java.util.ArrayList;
import java.util.List;
public class GenericMethodsBoxList <K>{
	private K lastboxdata;
	private List<Box<K>> boxcollection;
	
	public GenericMethodsBoxList(){
		lastboxdata = null;
		boxcollection = new ArrayList<>();
	}
	public void setCollection(List<Box<K>> boxes) {boxcollection = boxes;}
	public List<Box<K>> getCollection() {return boxcollection;}
	public K getKey() {return lastboxdata;}
	public void setKey(K lastboxdata) {this.lastboxdata = lastboxdata;}
	
	public void addBox(K u) {
		Box<K> box = new Box <>();
		box.setKey(u);
		lastboxdata = box.getKey();
		boxcollection.add(box);
	}
	
	public void boxOutPut() {
		for(Box<K> box: boxcollection) {
			System.out.println(box.toString() +" "+ box.getKey());
		}
	}
	
	private class Box <K>{
		private K value;
		
		public K getKey() {return value;}
		public void setKey(K value) {this.value = value;}
		}
	public static void main(String[]args) {
		GenericMethodsBoxList<Integer> a = new GenericMethodsBoxList<>();
		a.<Integer>addBox(4);
		a.addBox(4);
		a.addBox(4);
		a.addBox(4);
		a.addBox(4);
		a.boxOutPut();
		
		
	}
	
}
