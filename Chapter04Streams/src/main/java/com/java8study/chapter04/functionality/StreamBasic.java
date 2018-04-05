package com.java8study.chapter04.functionality;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java8study.chapter04.entities.Dish;

public class StreamBasic {

	public StreamBasic() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Functionality:
	 * A List<Dish> parameter is processed as follows:
	 * - All dishes with caloric value < 400 are found.
	 * - The dishes are ordered by caloric value.
	 * - A new List<String> variable is created, containing the names of the found and ordered dishes.
	 * - The new List<String> variable is returned. 
	 * 
	 * @param dishes
	 * @return
	 */
	public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes, int caloricValue) {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		
		// Find all dishes with a caloric value < 400.
		for (Dish dish: dishes) {
			if (dish.getCalories() < caloricValue) {
				lowCaloricDishes.add(dish);
			}
		}
		// Order the found dishes by name.
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish dish1, Dish dish2) {
				return Integer.compare(dish1.getCalories(), dish2.getCalories());
			}
			
		});
		// Add the names of the dishes to a new ArrayList lowCaloricDishNames
		List<String> lowCaloricDishNames = new ArrayList<>();
		for (Dish dish: lowCaloricDishes) {
			lowCaloricDishNames.add(dish.getName());
		}
		
		return lowCaloricDishNames;
	}
	
	/**
	 * Functionality:
	 * A List<Dish> parameter is processed as follows:
	 * - All dishes with caloric value < 400 are found.
	 * - The dishes are ordered by caloric value.
	 * - A new List<String> variable is created, containing the names of the found and ordered dishes.
	 * - The new List<String> variable is returned. 
	 * 
	 * @param dishes
	 * @return
	 */
	public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes, int caloricValue) {
		
		List<String> lowCaloricDishNames = new ArrayList<>();
		lowCaloricDishNames = dishes.parallelStream()
				       .filter( ( Dish dish ) -> dish.getCalories() < caloricValue )
		               .sorted( comparing( Dish::getCalories ))
		               .map( Dish::getName )
		               .collect( toList() );
		
		return lowCaloricDishNames;

	}

}
