package il.co.ilrd.factory;

import java.util.HashMap;
import java.util.function.Function;


public class Factory <K,T,R>{
	private HashMap<K, Function<T, ? extends R>> h = new HashMap<>();
	public void Add(K key, Function<T, ? extends R> f) {
		h.put(key,f);
	}
	
	public R Create(K key,T args) {
		return(h.get(key).apply(args));
	}
	
	public static void main(String [] args) {
		Factory<String, Integer, Shape> f= new Factory<>();
		f.Add("Circle" ,(t)-> new Circle<>(t));
		f.Add("Rectangle", new Function<Integer, Shape>() {

			@Override
			public Shape apply(Integer t) {
				return new Rectangle(t,t); 
			}
		});
		f.Add("ref Circle",Circle::CreateCircle);
		f.Add("new circle",new Circle<>() :: CreateCircleinner);

		f.Create("Circle", 1).print();
		f.Create("Rectangle", 3).print();
		f.Create("ref Circle", 10).print();
		f.Create("new circle", 20).print();
		System.out.println("ths ***************");
		new factoryTest<Integer>(5,f).someMethod();
	}
	
}
