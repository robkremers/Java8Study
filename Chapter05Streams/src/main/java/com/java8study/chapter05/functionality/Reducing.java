package com.java8study.chapter05.functionality;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter05.entities.Dish;

/**
 * Examples of paragraph 5.4. Reducing.
 * 
 * @author LTAdmin
 *
 */

public class Reducing {

	private final static Logger logger = Logger.getLogger(Reducing.class.getName());

	public Reducing() {
		// TODO Auto-generated constructor stub
	}
	
	public static void reduceOperation(List<Integer> numbers, int seed, BinaryOperator<Integer> accumulator ) {
		logger.log(Level.INFO, "************ Using a general reduce setup for a List<Integer> ************");
		
		int result = numbers.stream().reduce(seed, accumulator);
		System.out.println("Of List<Integer> numbers the result is: " + result);
	}

	public static void OptionalReduceOperation(List<Integer> numbers, BinaryOperator<Integer> accumulator ) {
		logger.log(Level.INFO, "************ Using a general reduce setup for a List<Integer> assigning the result to an Optional<Integer> variable ************");
		
		Optional<Integer> result = numbers.stream().reduce( accumulator );
		if (result.isPresent()) {
			System.out.println("Of List<Integer> numbers " + numbers + " the result is: " + result.get());
		} else {
			System.out.println("No result is present.");
		}	
	}
	
	public static void countNumberOfDishes() {
		logger.log(Level.INFO, "************ Determining the number of dishes in a List<Dish> ************");
		
		Optional<Integer> sum = Dish.menu.parallelStream().map( (Dish dish) -> 1 ).reduce( (Integer a, Integer b) -> (a + b));
		
		if (sum.isPresent()) {
			System.out.println("The number of dishes is: " + sum.get());
		} else {
			System.out.println("The nmber of dishes is" + sum.orElse(0));
		}
	}

}
