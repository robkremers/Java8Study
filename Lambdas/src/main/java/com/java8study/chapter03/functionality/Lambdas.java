package com.java8study.chapter03.functionality;

import static java.util.stream.Collectors.toList;

import java.util.List;
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
public class Lambdas {
	
	private final static Logger logger = Logger.getLogger(Lambdas.class.getName());


	public Lambdas() {
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
}
