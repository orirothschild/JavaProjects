package il.co.ilrd.pizza;

public abstract class PizzaDecorator implements Pizza {

	protected Pizza mypizza;
	
	public PizzaDecorator(Pizza pizza) {
		mypizza = pizza;
	}
	@Override
	public String getDiscript() {
		return mypizza.getDiscript();
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return mypizza.getPrice();
	}
}
