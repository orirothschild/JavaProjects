package il.co.ilrd.trying;

import java.util.function.Function;

public class Factoryattempt<T,R> {
		T t;
		R r;
		public void someFunction(T a , Function<T,R> f){
			f.apply(a);
		}
		
		public void someFunctionbutwithClosure(T a , Function<T,R> f){
			f.apply(a);
		}
}