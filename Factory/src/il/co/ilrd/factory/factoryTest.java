package il.co.ilrd.factory;

public class factoryTest<T> {
	final T k;
	T t;
	Factory<String, T, Shape> f;
	
	public factoryTest(T k, Factory<String,T,Shape> f) {
		this.k = k;
		this.f = f;
		
	}
	
	public void someMethod() {
		f.Create("Circle",k).print();
	}
}
