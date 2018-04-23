package com.java8study.chapter08;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeekMain {

	private final static Logger logger = Logger.getLogger(PeekMain.class.getName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Logging a Lambdausing Peek ************\n");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		logger.log(Level.INFO, "************ Printing the numbers ************\n");
		numbers.stream()
		       .map( (Integer x) -> x + 17)
		       .filter( (Integer x) -> x % 2 == 0 )
		       .limit(3)
		       .forEach( System.out::println );

		logger.log(Level.INFO, "************ Printing the numbers using Peek ************\n");
		numbers.stream()
		       .peek( x -> System.out.println("\nfrom stream: " + x) )
		       .map( (Integer x) -> x + 17)
		       .peek( x -> System.out.println("after map: " + x) )
		       .filter( (Integer x) -> x % 2 == 0 )
		       .peek( x -> System.out.println("after filter: " + x) )
		       .limit(3)
		       .forEach( System.out::println );

	}

}
