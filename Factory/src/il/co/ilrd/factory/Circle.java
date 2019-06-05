package il.co.ilrd.factory;

public class Circle<T> implements Shape{
	T Radious;
	
	public Circle() {}
	
	public Circle(T Radious) {
		this.Radious = Radious;
	}
	
	public static <T> Circle<T> CreateCircle(T radious) {
		 return new Circle<>(radious);
	}
	
	public Circle<T> CreateCircleinner(T radious) {
		 return new Circle<>(radious);
	}
	
	
	@Override
	public void print() {
		System.out.println("around and around" + Radious);
	}

}
