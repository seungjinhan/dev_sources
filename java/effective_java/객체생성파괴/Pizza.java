package java_study.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

	public enum Topping { HAM, MUSHROOM, ONION, PEPER, SAUSAGE}
	
	final Set< Topping> toppings;
	
	abstract static class Builder< T extends Builder<T>> {
		
		EnumSet< Topping> toppings = EnumSet.noneOf( Topping.class);
		
		public T addTopping( Topping t) {
			
			toppings.add( Objects.requireNonNull(t));
			return self();
		}
		
		abstract Pizza build();
		
		protected abstract T self();
	}
	
	Pizza(Builder<?> builder){
		toppings = builder.toppings.clone();
	}
}
