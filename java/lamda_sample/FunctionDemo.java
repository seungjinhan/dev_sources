package java_study.functional;

import java.util.function.Function;

public class FunctionDemo {

	public static void main(String[] args) {

		Function<String, Integer> func = e -> e.length();
		System.out.println( func.apply("test"));
	}

}
