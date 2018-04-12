package com.java8study.chapter06;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.reducing;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.java8study.chapter06.entities.Dish;

public class Summarizing {

	private final static Logger logger = Logger.getLogger(Summarizing.class.getName());

	public Summarizing() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Summarizing actions ************\n");

		System.out.println("Nr. of dishes: " + howManyDishes());
		System.out.println("The most caloric dish is: " + findMostCaloricDish());
		System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator() );
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
        
		logger.log(Level.INFO, "************ Several other methods for counting the number of calories ************\n");
        int totalCalories = Dish.menu.stream().collect( reducing(0, Dish::getCalories, (i, j) -> i + j));
        totalCalories = Dish.menu.stream().collect( reducing(0, Dish::getCalories, Integer::sum));
        totalCalories = Dish.menu.stream().map( Dish::getCalories).reduce( Integer::sum ).get();

	}
	
	public static long howManyDishes() {
		
		return Dish.menu.stream().collect( counting() );
	}
	
	private static Dish findMostCaloricDish() {
		
		return Dish.menu.stream()
				        .collect( reducing( (Dish dish1, Dish dish2 ) -> { 
				        	return dish1.getCalories() > dish2.getCalories() ? dish1 : dish2;
				        	}  
				        ) ).get();
		
	}
	
	private static Dish findMostCaloricDishUsingComparator() {
		
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt( Dish::getCalories );
		
		Optional<Dish> mostCaloriesDish = Dish.menu.stream().collect( Collectors.maxBy( dishCaloriesComparator) );
		
		return mostCaloriesDish.get();
	}
	
	private static int calculateTotalCalories() {
		
		return Dish.menu.stream().collect(Collectors.summingInt( Dish::getCalories));
	}
	
//	private static int calculateTotalCaloriesUsingReducing() {
//		
//		return Dish.menu.stream().collect( reducing( 0, Dish::getCalories, (int x, int y) -> x + y));
//	}
	

	private static double calculateAverageCalories() {
		return Dish.menu.stream().collect( Collectors.averagingDouble( Dish::getCalories));
	}
	
	private static IntSummaryStatistics calculateMenuStatistics() {
		return Dish.menu.stream().collect( Collectors.summarizingInt( Dish::getCalories));
	}
	
	private static String getShortMenuCommaSeparated() {
		
		/**
		 * Two alternative ways to 
		 */
		String shortMenu = Dish.menu.stream().map( Dish::getName).collect( Collectors.reducing( (String s1, String s2) -> s1 + " " + s2)).get();
		
		shortMenu = Dish.menu.stream()
				.collect( reducing("", Dish::getName, (String s1, String s2) -> s1 + " " + s2) );
		
		return Dish.menu.stream().map( (Dish dish) -> dish.getName()).sorted().collect( Collectors.joining(", "));
	}
	
	
}
