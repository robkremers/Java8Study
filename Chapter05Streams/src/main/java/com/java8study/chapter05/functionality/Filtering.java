package com.java8study.chapter05.functionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

import com.java8study.chapter05.entities.Dish;

public class Filtering {

	private final static Logger logger = Logger.getLogger(Filtering.class.getName());

	public Filtering() {
	}

	public static void getVegetarianDishesJava7() {
		logger.log(Level.INFO, "************ Finding all vegetarian dishes the Java 7 way ************");

		List<Dish> vegetarianDishes = new ArrayList<>();
		for (Dish dish : Dish.menu) {
			if (dish.isVegetarian()) {
				vegetarianDishes.add(dish);
			}
		}
		System.out.println("Overview of vegetarian dishes:");
		for (Dish dish : vegetarianDishes) {
			System.out.println(dish);
		}
	}

	public static void getVegetarianDishesJava8() {
		logger.log(Level.INFO, "************ Finding all vegetarian dishes the Java 8 way ************");

		Dish.menu.stream()
		         .filter(Dish::isVegetarian)
		         .collect(toList())
		         .forEach(System.out::println);

	}
	
	public static void getUniqueElements() {
		logger.log(Level.INFO, "************ Filtering the unique elements of an integer list using distinct() ************");
		
		List<Integer> numbers = Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 2);
		
		numbers.stream()
		       .distinct()
		       .forEach(System.out::println);
		
	}
	
	public static void getTruncatedStream() {
		logger.log(Level.INFO, "************ Truncating a stream, filtered by calories > 300, using limit() ************");
		
		Dish.menu.stream()
		         .filter( (Dish dish) -> dish.getCalories() > 300 )
		         .limit(4)
		         .collect( toList() )
		         .forEach(System.out::println);
		
	}
	
	public static void getSkippedStream() {
		logger.log(Level.INFO, "************ Filtering a stream, showing only MEAT dishes, discarding the first item of a stream using skip() ************");
		
		Dish.menu.stream()
		         .filter( (Dish dish) -> dish.getType() == Dish.Type.MEAT)
		         .skip(1)
		         .limit(1)
		         .collect( toList() )
		         .forEach( System.out::println );
	}
}
