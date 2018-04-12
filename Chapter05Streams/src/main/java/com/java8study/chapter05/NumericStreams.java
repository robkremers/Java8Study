package com.java8study.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.java8study.chapter05.entities.Dish;

public class NumericStreams {
	
	public final static Logger logger = Logger.getLogger(NumericStreams.class.getName());

	public NumericStreams() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Using Numeric Streams ************\n");

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		Arrays.stream(numbers.toArray()).forEach( System.out::println );
		
		int calories = Dish.menu.stream()
				                .mapToInt( (Dish dish) -> dish.getCalories())
				                .sum();
		System.out.println("The total number of calories is " + calories);
		
		OptionalInt maxCalories = Dish.menu.stream().mapToInt( Dish::getCalories).max();
		int max;
		if (maxCalories.isPresent()) {
			max = maxCalories.getAsInt();
		} else {
			max = maxCalories.orElse(-1);
		}
		System.out.println("The maximum number of calories for a dish is " + max);
		
		logger.log(Level.INFO, "************ Numeric ranges ************\n");
		IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter( (int i) -> i % 2 == 0 );
		System.out.println("The number of even numbers is " + evenNumbers.count());

		logger.log(Level.INFO, "************ Pythagorean Triples ************\n");
		
		
	}

}
