package com.java8study.chapter05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

	public final static Logger logger = Logger.getLogger(BuildingStreams.class.getName());

	public BuildingStreams() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Building Streams ************\n");
		
		logger.log(Level.INFO, "************ Creating a Stream using Stream.of(T... values) ************\n");
		Stream<String> stringStream = Stream.of("Java8", "Lambdas", "In", "Action");
		stringStream.map(String::toUpperCase).forEach(System.out::println);
		
		logger.log(Level.INFO, "************ Creating an empty Stream ************\n");
		Stream <String> emptyStream = Stream.empty();
		emptyStream.forEach(System.out::println);
		
		logger.log(Level.INFO, "************ Creating a Stream from Arrays ************\n");
		Integer[] numbers = {2, 3, 5, 8, 13, 21 };
		int sum = Arrays.stream(numbers).reduce(0, (a, b) -> a + b);
		System.out.print("The sum of array ");
		List<Integer> listNumbers = Arrays.asList(numbers);
		System.out.print(listNumbers);
		System.out.println( " is " + sum);

		logger.log(Level.INFO, "************ Creating a Stream from a file ************\n");
		long nrUniqueWords = 0;
		List<String> fileLines = new ArrayList<>();
		try ( Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset() ) ) {
			
			lines.forEach( (String line) -> fileLines.add(line));
			
			fileLines.stream().forEach( (String line) -> System.out.println(line));
			
//			nrUniqueWords = lines.flatMap( (String line) -> Arrays.stream( line.split(" ")) ).distinct().count();
			
			nrUniqueWords = fileLines.stream().flatMap( (String line) -> Arrays.stream( line.split( " "))).distinct().count();
			
		} catch( IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.println("The number of unique words in the file is: " + nrUniqueWords);
		
		logger.log(Level.INFO, "************ Creating infinite Streams using Stream.iterate() ************\n");
		
		Stream.iterate(0, (Integer i) -> i + 2 ).limit(10).forEach( System.out::println);

		logger.log(Level.INFO, "************ Creating Fibonacci tuples series with iterate ************\n");
		/**
		 * Fibonacci series:
		 * 0, 1, 1, 2, 3, 5, 8, 13, 21,...
		 * Fibonacci tuple series:
		 * (0,1), (1,1), (1,2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21),.....
		 * 
		 * Task:
		 * Generate the first 20 elements of the Fibonacci tuple series using Stream.iterate(T seed, UnaryOperator<T>).
		 * 
		 * java.util.function.UnaryOperator<T>
		 * Represents an operation on a single operand that produces a result of the same type as its operand. 
		 * This is a specialization of Function for the case where the operand and result are of the same type.
		 * This is a functional interface whose functional method is Function.apply(Object).
		 * 
		 */
		
		Stream.iterate( new int[] {0,  1}, (int[] t) -> new int[] {t[1], t[0] + t[1]  })
		      .limit(10)
		      .forEach( (int[] t) -> System.out.println("(" + t[0] + ", " + t[1] + ")") );
		
		logger.log(Level.INFO, "************ Creating Fibonacci series with iterate ************\n");
		Stream.iterate( new int[] {0,  1}, (int[] t) -> new int[] {t[1], t[0] + t[1]  })
	      .limit(10)
	      .forEach( (int[] t) -> System.out.println( t[0] ) );
		
		logger.log(Level.INFO, "************ Creating a random stream of doubles with Stream.generate ************\n");
		Stream.generate( Math::random).limit(10).forEach( System.out::println);
		
		logger.log(Level.INFO, "************ Creating a stream of '1' using Stream.generate ************\n");		
		Stream.generate( () -> 1).limit(5).forEach( System.out::println);
		
		logger.log(Level.INFO, "************ Creating an instance of a functional interface by providing the implementation of the method directly inline. ************\n");		
		/**
		 * Creating an instance of a functional interface by providing the implementation of the method directly inline.
		 */
		IntStream instStreamTwos = IntStream.generate( new IntSupplier() {

			@Override
			public int getAsInt() {
				
				return 2;
			}
			
		}).limit(10);
		instStreamTwos.forEach( System.out::println);
		
		logger.log(Level.INFO, "************ Creating a Fibonacci series using a stateful IntSupplier functional interface ************\n");
		/**
		 * A stateful functional interface is not save to use in parallel mode!
		 * 
		 */
		IntSupplier fibonacci = new IntSupplier() {

			private int previous = 0;
			private int current = 1;
			@Override
			public int getAsInt() {
				
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				
				return oldPrevious;
			}
			
		};
		
		IntStream.generate(fibonacci).limit(10).forEach( System.out::println);
		
	}

}
