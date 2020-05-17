package java_study.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ComsumerDemo {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList( 34,23,57,4,67,89,67,57,20);
		
		Consumer<Integer> consumer = e -> System.out.println(e);
		consumer.accept(56);
		
		printElement( list, consumer);
	}
	private static <T> void printElement( List<T> list, Consumer<T> consumer) {
		
		for (T t : list) {
			consumer.accept(t);
		}
		
	}

}
