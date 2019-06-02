package il.co.ilrd.pizza;

public class Mozzerela extends PizzaDecorator {

	public Mozzerela(Pizza pizza) {
		super(pizza);
		System.out.println("added mozzerela");
		
	}
	
	@Override
	public String getDiscript() {
		return mypizza.getDiscript() + "Mozzerela";
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return mypizza.getPrice() + 2.00;
	}
}
