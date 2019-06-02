package il.co.ilrd.pizza;

public class Bacon extends PizzaDecorator {

	public Bacon(Pizza pizza) {
		super(pizza);
		System.out.println("added bacon");
	}

	@Override
	public String getDiscript() {
		return mypizza.getDiscript() + "bacon";
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return mypizza.getPrice() + 1.00;
	}
	public static void main(String[] args) {
		Pizza pizza = new PlainPizza();
		/*System.out.println(pizza.getDiscript());
		System.out.println(pizza.getPrice());*/
		Pizza mozpizza = new Mozzerela(pizza);c
		/*System.out.println(mozpizza.getDiscript());
		System.out.println(mozpizza.getPrice());
		Pizza baconpizza = new Bacon(pizza);
		System.out.println(baconpizza.getDiscript());
		System.out.println(baconpizza.getPrice());*/
		Pizza mozabaconpizza = new Bacon(mozpizza);
		System.out.println(mozabaconpizza.getDiscript());
		System.out.println(mozabaconpizza.getPrice());
		
	}
}
