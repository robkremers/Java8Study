package com.java8study.chapter05.functionality;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter05.entities.Dish;

public class Mapping {
	
	private final static Logger logger = Logger.getLogger(Mapping.class.getName());


	public Mapping() {
	
	}
	
	public static void getDishNames() {
		logger.log(Level.INFO, "************ Showing the names of the menus ************");
		
		Dish.menu.stream()
		         .map( Dish::getName )
		         .sorted( Comparator.reverseOrder() )
		         .collect( toList() )
		         .forEach( System.out::println);
	}
	
	public static void getWordlength() {
		logger.log(Level.INFO, "************ Printing the length of the words 'Java8 in Action' ************");

		List<String> words = Arrays.asList("Java", "in", "Action");
		List<Integer> wordlengths = words.stream().map( String::length).collect( toList());
		
		wordlengths.forEach( System.out::println);
	}
	
	public static void getDishNamelength() {
		logger.log(Level.INFO, "************ Printing the sorted, distinct length of the dish names ************");
		
		Dish.menu.stream()
		         .map( Dish::getName)
		         .map( String::length)
		         .sorted()
		         .distinct()
		         .collect( toList() )
		         .forEach( System.out::println);
	}
	
	/**
	 * Find all dish names, sort and print the unique letters that these names contain.
	 */
	public static void getTheUniqueDishNameLetters() {
		logger.log(Level.INFO, "************ Printing the unique letters present in the dish names, using flatMap ************");
		
		List<String> dishNames = Dish.menu.stream()
		         .map( Dish::getName )										// A Stream<String[]> is returned.
		         .flatMap( (String name) -> Arrays.stream(name.split("")))	// All the separate streams are now combined in one stream.
		         .distinct()
		         .sorted()
		         .collect( toList() );
		
		dishNames.forEach( System.out::println );
			
	}
	
	/**
	 * Functionality:
	 * Given a list of numbers a list of the square of the numbers is returned.
	 * 
	 */
	public static void getSquareOfNumbers() {
		logger.log(Level.INFO, "************ Given a list of numbers a list of the square of the numbers is returned. ************");
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squareNumbers = numbers.stream()
				                             .map( (Integer number) -> number * number)
				                             .collect(toList());
		System.out.println("The original numbers: ");
		numbers.forEach( System.out::println);
		System.out.println("The squares of the numbers: ");
		squareNumbers.forEach( System.out::println);
		
	}
	
	/**
	 * Functionality:
	 * Given two lists of numbers, return all pairs of numbers.
	 * 
	 */
	public static void getPairsOfNumbers() {
		logger.log(Level.INFO, "************ Given two lists of numbers, return all pairs of numbers. ************");
		logger.log(Level.INFO, "************ Only pairs whose sum is divisible by 3 is admissible. ************");
		
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		System.out.println("The first set of numbers: " + numbers.toString());
		List<Integer> numbers2 = Arrays.asList(3, 4);
		System.out.println("The second set of numbers: " + numbers2.toString());
		
		List<int[]> pairs = numbers.stream()
				                   .flatMap( (Integer i) -> numbers2.stream()
				                		                            .filter(  (Integer j) -> (i + j) % 3 == 0)
				                		                            .map( (Integer j) -> new int[] {i, j} 
				                		                                 )
				                		    )
				                   .collect( toList());
		
		pairs.forEach( (int[] pair) -> System.out.println( "(" + pair[0] + ", " + pair[1] +")" ) );
		
	}

}
