package java_study.functional;

import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {

	public static void main(String[] args) {

		
		UnaryOperator<Integer> uo =  i-> i * 100;
		System.out.println( uo.apply(20));

	}

}
