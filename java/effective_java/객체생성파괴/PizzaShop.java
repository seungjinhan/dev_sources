package java_study.builder;

import java_study.builder.Pizza.Topping;
import java_study.builder.SeoulPizza.Size;

public class PizzaShop {

	public static void main(String[] args) {

		SeoulPizza sp = new SeoulPizza.Builder( Size.SMALL)
									.addTopping( Topping.HAM)
									.addTopping( Topping.ONION)
									.build();
		
		JinjuPizza jp = new JinjuPizza.Builder()
									.addTopping( Topping.SAUSAGE)
									.souce()
									.build();
		
		System.out.println( sp.toString());
		System.out.println( jp.toString());
	}

}
