package java_study.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		
		list.add("1234");
		list.add("qwer");
		list.add("");
		list.add("zxcv");
		
		Predicate<String> pd = s -> !s.isEmpty();
		
		list
				.stream()
				.filter( s -> pd.test(s))
				.collect( Collectors.toList())
				.forEach(System.out::println);
	}

}
