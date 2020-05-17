package java_study.functional;

import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {

	public static void main(String[] args) {

		BinaryOperator<Integer> bo = ( a,b) -> (a + b) * 10;
		System.out.println( bo.apply(2, 4));
	}

}
