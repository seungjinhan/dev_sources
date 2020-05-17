package java_study.functional;

import java.util.function.Supplier;

public class SupplierDemo {

	public static void main(String[] args) {

		System.out.println("test");
		Supplier<String> su = () -> "String";
		System.out.println( su.get());
		
		Supplier<Double> randomNumber = () -> Math.random();
		System.out.println( randomNumber.get());
		
	}

}
