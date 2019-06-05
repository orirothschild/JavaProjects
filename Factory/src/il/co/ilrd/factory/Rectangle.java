package il.co.ilrd.factory;

public class Rectangle implements Shape {
	
	private int width;
	private int height;
	
	public Rectangle() {
		width = 5;
		height = 5;
	}
	
	public Rectangle(int width, int height) {
		width = 5;
		height = 5;
	}
	
	public static Rectangle CreateRectangle(int width, int height) {
		 return new Rectangle(width, height);
	}
	@Override
	public void print() {
		System.out.println("im squerie the squere" + width +":" + height);
	}

}
