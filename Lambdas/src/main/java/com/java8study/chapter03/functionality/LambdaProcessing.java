package com.java8study.chapter03.functionality;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter03.entities.Apple;

/**
 * Class implementing some Lambda functionality.
 * 
 * @author LTAdmin
 *
 */
public class LambdaProcessing {
	
	private final static Logger logger = Logger.getLogger(LambdaProcessing.class.getName());


	public LambdaProcessing() {
	}
	
	/**
	 * Filters a List of type T, using a given Predicate of type T.
	 * 
	 * @param inventory
	 * @param predicate
	 * @return
	 */
	public static <T> List<T> filterStream( List<T> inventory, Predicate<T> predicate) {
		
		return inventory.stream().filter(predicate).collect(toList() );
		
	}
	
	/**
	 * An implementation of the above defined filterStream for type Apple.
	 * 
	 * @param inventory
	 * @param predicate
	 * @return
	 */
	public static List<Apple> appleFilterStream( List<Apple> inventory, Predicate<Apple> predicate) {
		
		return inventory.stream().filter(predicate).collect( toList() );
	}
	
	public static void prettyPrintApples(List<Apple> inventory ) {
		logger.log(Level.INFO, "************ Pretty printing the apples ************");
		inventory.forEach( (Apple apple) -> {
			System.out.println("A " + apple.getColor() + " Apple of " + apple.getWeight() + " grams.");
		});
	}
	
	
	public static <T, R> List<R> map( List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for (T t: list) {
			result.add( function.apply(t));
		}
		return result;
	}
	
}
