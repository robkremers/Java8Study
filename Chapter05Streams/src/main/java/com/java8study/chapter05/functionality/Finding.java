package com.java8study.chapter05.functionality;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter05.entities.Dish;

/**
 * Examples of paragraph 5.3. Finding and Matching.
 * 
 * @author LTAdmin
 *
 */
public class Finding {

	private final static Logger logger = Logger.getLogger(Finding.class.getName());

	public Finding() {
		// TODO Auto-generated constructor stub
	}

	public static void checkAnyVegetarianDish() {
		logger.log(Level.INFO, "************ Checking whether any vegetarian dish is present ************");
		
		if (Dish.menu.stream()
		         .anyMatch( Dish::isVegetarian)
		         ) {
			System.out.println("Vegetarian dishes can be offered.");
		} else {
			System.out.println("No vegetarian dishes can be offered.");
		}
	}
	
	public static void checkAllMatch() {
		logger.log(Level.INFO, "************ Checking whether all dishes contain less than 1000 calories ************");
		
		if (Dish.menu.stream().allMatch( (Dish dish) -> dish.getCalories() < 1000) ) {
			System.out.println("All dishes contain less than 1000 calories");
		} else {
			System.out.println("Some or all dishes contain at least 1000 calories.");
		}
	}
	
	public static void checkNoMatch() {
		logger.log(Level.INFO, "************ Checking whether no dish contain less than 1000 calories ************");
		
		if (Dish.menu.stream().noneMatch( (Dish dish) -> dish.getCalories() < 1000) ) {
			System.out.println("No dish contains less than 1000 calories");
		} else {
			System.out.println("Some or all dishes contain less than 1000 calories.");
		}		
	}
	
	public static void findAnyVegetarianDish() {
		logger.log(Level.INFO, "************ Returning a vegetarian dish if any exists ************");
		
		Optional<Dish> vegetarianDish = Dish.menu.stream().filter( Dish::isVegetarian).findAny();
		
		if (vegetarianDish.isPresent()) {
			System.out.println("A Vegetarian dish: " + vegetarianDish.get());
		} else {
			System.out.println("No vegetarian dish can be offered.");
		}
	}

	public static void findFirstVegetarianDish() {
		logger.log(Level.INFO, "************ Returning the first vegetarian dish found if any exists ************");
		
		Optional<Dish> vegetarianDish = Dish.menu.stream().filter( Dish::isVegetarian).findFirst();
		
		if (vegetarianDish.isPresent()) {
			System.out.println("The first vegetarian dish found: " + vegetarianDish.get());
		} else {
			System.out.println("No vegetarian dish can be offered.");
		}
	}

}
