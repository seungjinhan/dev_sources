package java_study.functional;

import java.util.function.BiFunction;

public class BiFunctionDemo {

	public static void main(String[] args) {

		BiFunction<String, String, Integer> bf = (a, b ) -> a.length() + b.length();
		
		System.out.println( bf.apply("test", "as"));
	}

}
