package java_study.functional;

public class Demo {

	public static void main(String[] args) {

		FunctionalGenerics<String, String> d = s -> s.substring(1,5);
		System.out.println( d.execute("my name is han"));
		
		FunctionalGenerics<String, Integer> d2 = s -> s.length();
		System.out.println( d2.execute("qwer1234"));
	}

}
