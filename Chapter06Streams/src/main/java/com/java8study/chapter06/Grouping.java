package com.java8study.chapter06;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.java8study.chapter06.entities.Dish;
import com.java8study.chapter06.enums.CaloricLevel;

public class Grouping {

	private final static Logger logger = Logger.getLogger(Grouping.class.getName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Grouping actions ************\n");

		groupDishesByType();
		groupDishNamesByType();
		printDishesByCaloricLevel();
		printDishesByTypeCaloricLevel();
		printingDishesInGroups();
		printMaxCaloriesDishPerType();
		printTotalCaloriesPerType();

	}

	private static void groupDishesByType() {
		logger.log(Level.INFO, "************ Grouping dishes per type ************\n");

		Map<Dish.Type, List<Dish>> dishesGroupedPerType = Dish.menu.stream()
				                                                   .collect(Collectors.groupingBy(Dish::getType));
		
		logger.log(Level.INFO, "************ Printing dishes per type Java 7 style ************\n");
		for (Dish.Type type : dishesGroupedPerType.keySet()) {
			System.out.println("Type: " + type);
			List<Dish> dishes = dishesGroupedPerType.get(type);
			for (Dish dish: dishes) {
				System.out.println("\t" + dish);				
			}
		}
		
		logger.log(Level.INFO, "************ Printing dishes per type Java 8 style ************\n");
		dishesGroupedPerType.forEach( (type, dishes) -> { 
			System.out.println( "Type: " + type);
			dishes.forEach( ( Dish dish) -> System.out.println("\t" + dish));
		} );
	}

	private static void groupDishNamesByType() {
		logger.log(Level.INFO, "************ Grouping dishes per name ************\n");
		
		/**
		static <T,K,A,D> Collector<T,?,Map<K,D>>	groupingBy( Function<? super T,? extends K> classifier
		                                                      , Collector<? super T,A,D> downstream
		                                                      )

		Returns a Collector implementing a cascaded "group by" operation on input elements of type T, 
		grouping elements according to a classification function, 
		and then performing a reduction operation on the values associated with a given key using the specified downstream Collector.
		
		As Collector is used: java.util.stream.Collectors.mapping():
		
		static <T,U,A,R> Collector<T,?,R>	mapping( Function<? super T,? extends U> mapper
		                                           , Collector<? super U,A,R> downstream
		                                           )
		
		Adapts a Collector accepting elements of type U to one accepting elements of type T 
		by applying a mapping function to each input element before accumulation.
		
		 */
		Map<Dish.Type, List<String>> dishesGroupedPerName = Dish.menu.stream()
				                                                    .collect( groupingBy( Dish::getType
				                                                    		                       , mapping(Dish::getName, toList()) 
				                                                    		                       )
				                                                    		);
		
		for (Dish.Type type: dishesGroupedPerName.keySet() ) {
			System.out.println("Type: " + type);
			System.out.println("\t" + dishesGroupedPerName.get(type));
		}
		
	}
	
	/**
	 * Functionality:
	 * Map the dishes per enum CaloricLevel:
	 * - DIET	: calories < 400
	 * - NORMAL	: calories between 400 and 700
	 * - FAT	: calories > 700
	 */
	private static void printDishesByCaloricLevel() {
		logger.log(Level.INFO, "************ Grouping dishes per Caloric Level ************\n");
		
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream()
				                                                      .collect( Collectors.groupingBy (
				                                                    		  	(Dish dish) -> {
				                                                    		  		if (dish.getCalories() < 400)
				                                                    		  			return CaloricLevel.DIET;
				                                                    		  		else if (dish.getCalories() <= 700)
				                                                    		  			return CaloricLevel.NORMAL;
				                                                    		  			else
				                                                    		  				return CaloricLevel.FAT;
				                                                    		  	}));
		dishesByCaloricLevel.forEach( (level, dishes) -> { 
			System.out.println( "Caloric Level: " + level);
			dishes.forEach( ( Dish dish) -> System.out.println("\t" + dish));
			
		});
	}
	
	/**
	 * Functionality:
	 * - The dishes will firstly be grouped by Dish::getType.
	 * - Secondly a mapping will be used consisting of a second grouping, now per CaloricLevel (custom set up).
	 * 
	 */
	private static void printDishesByTypeCaloricLevel() {
		logger.log(Level.INFO, "************ Grouping dishes per Type and per Caloric Level ************\n");
		
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = 
				Dish.menu.stream()
				         .collect( 
				        		 Collectors.groupingBy( Dish::getType,
				        				 // A mapping collector:
				        				 Collectors.groupingBy(
				        						 (Dish dish) -> {
                                     		  		if (dish.getCalories() < 400)
                                     		  			return CaloricLevel.DIET;
                                     		  		else if (dish.getCalories() <= 700)
                                     		  			return CaloricLevel.NORMAL;
                                     		  			else
                                     		  				return CaloricLevel.FAT;
                                     		  	}
				        						 )
				        		 ));
		
		dishesByTypeCaloricLevel.forEach( (type, mapDishesByCaloricLevel) -> {
			System.out.println("Dish type: " + type);
			mapDishesByCaloricLevel.forEach( (caloricLevel, dishes) -> {
				System.out.println("\tCaloric Level: " + caloricLevel);
				dishes.forEach( (Dish dish) -> System.out.println("\t\t" + dish));
			});
		});
	}
	
	/**
	 * static <T,K,A,D> Collector<T,?,Map<K,D>>	groupingBy( Function<? super T,? extends K> classifier
	 *                                                    , Collector<? super T,A,D> downstream
	 *                                                    )

	 Returns a Collector implementing a cascaded "group by" operation on input elements of type T, 
	 grouping elements according to a classification function, and then performing a reduction operation 
	 on the values associated with a given key using the specified downstream Collector.

	 */
	private static void printingDishesInGroups() {
		logger.log(Level.INFO, "************ Grouping and counting the number of dishes per type ************\n");
		
		Map<Dish.Type, Long> typesCount = Dish.menu.stream()
				                                   .collect( Collectors.groupingBy( Dish::getType
				                                		    					  , Collectors.counting() 
				                                		    					  )
				                                		   );
		
		typesCount.forEach( (type, nrOfDishes) -> {
			System.out.println("Dish type: " + type + ", nr of dishes: " + nrOfDishes );
		}); 
		
	}
	
	private static void printMaxCaloriesDishPerType() {
		logger.log(Level.INFO, "************ Group the maximum number of calories per dish type ************\n");
		
		Map<Dish.Type, Optional<Dish> > maxCaloriesPerType = Dish.menu.stream()
				                                                 .collect( groupingBy( Dish::getType
				                                                		             , Collectors.maxBy( Comparator.comparingInt( Dish::getCalories))
				                                                		             )
				                                                		 );
		
		maxCaloriesPerType.forEach( (type, optionalDish) -> {
			System.out.println("Dish type: " + type);
			if (optionalDish.isPresent()) {
				System.out.println("\tDish: " + optionalDish.get() );
			} else {
				System.out.println("No dishes found for this Dish type.");
			}
		});
		
	}

	private static void printTotalCaloriesPerType() {
		logger.log(Level.INFO, "************ Group the total number of calories per dish type ************\n");
		
		Map<Dish.Type, Integer> totalCaloriesPerType = Dish.menu.stream() 
				                                                .collect( Collectors.groupingBy( Dish::getType
				                                                		            , Collectors.summingInt( Dish::getCalories)
				                                                		            )
				                                                		 
				                                                		);
		totalCaloriesPerType.forEach( (type, totalCalories) -> {
			System.out.println("Dish type: " + type);
			System.out.println("\tTotal number of calories: " + totalCalories);
		});
	}
}
