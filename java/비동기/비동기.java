package java_sample;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class 비동기 {

	List<Shop> shops = Arrays.asList( 
			new Shop("test1"),new Shop("test2"),new Shop("test3"),new Shop("test4"),new Shop("test5")
			);
	
	final Executor executor = Executors.newFixedThreadPool( Math.min(shops.size(), 100),
										new ThreadFactory() {
											
											@Override
											public Thread newThread(Runnable r) {
												Thread t = new Thread(r);
												t.setDaemon(true);
												return t;
											}
										});
	
	public static void main(String[] args) {
		
		비동기 b = new 비동기();
//		Shop s = new Shop("Good");
//		long start = System.nanoTime();
//		
//		Future<Double> fp = s.getPriceAsync("my name is han");
//		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//		System.out.println("Invocation return after " + invocationTime + "msecs");
//
//		try {
//			Thread.sleep(200L);
//		} catch (InterruptedException e) {
//			throw new RuntimeException();
//		}
//		
//		
//		try {
//			double price = fp.get();
//			System.out.printf("Price : %.2f%n" , price );
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		
//		long retrievcalTime = ((System.nanoTime() - start)/1_000_000);
//		System.out.println("price returned after " + retrievcalTime+"msecs");
//		
//		
//		

		
		long start = System.nanoTime();
		
//		shops
//				.stream()
//				.map( s -> String.format("%s price is %.2f", s.getName() , s.getPrice("abc")))
//				.collect( Collectors.toList())
//				.forEach(System.out::pringln));
//		
//		System.out.println( System.nanoTime() - start);
		
		start = System.nanoTime();
		
		b.shops
				.parallelStream()
				.map( s -> String.format("%s price is %.2f", s.getName() , s.getPrice("abc")))
				.forEach(System.out::println);
				//.collect( Collectors.toList());
		
		System.out.println( System.nanoTime() - start);
		
		start = System.nanoTime();
		
		List< CompletableFuture<String>> priceFutures = b.shops.stream()
					.map( 
							s -> CompletableFuture.supplyAsync(
									()-> String.format("%s price is %.2f", s.getName(), s.getPrice("abc")),b.executor ))
					.collect( Collectors.toList());
		
		List<String> result = priceFutures.stream()
			.map( CompletableFuture::join)
			.collect( Collectors.toList());
		
		result.forEach(System.out::println);
		System.out.println( System.nanoTime() - start);
		
	}

}

class Shop{

	private String name;
	public Shop( String name) {
		this.name = name;
	}

	private double cals( String product) {
		delay();
		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
	}

	
	public String getName() {
		return name;
	}

	public double getPrice( String product) {
		
		return this.cals(product);
	}
	
	// 간단하게 처리
	public Future<Double> getPriceAsyncs( String product){
		return CompletableFuture.supplyAsync(() -> cals(product));
	}
	
	public Future<Double> getPriceAsync( String product){
		CompletableFuture<Double> futurePrice = new CompletableFuture<Double>();
		new Thread( () -> {
			try {
				double price = cals(product);
				futurePrice.complete(price);
			} catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		
		return futurePrice;
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}
}