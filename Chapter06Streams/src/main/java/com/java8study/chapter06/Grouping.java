package com.java8study.chapter06;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import com.java8study.chapter06.entities.Dish;
import com.java8study.chapter06.enums.CaloricLevel;

public class Grouping {

	private final static Logger logger = Logger.getLogger(Grouping.class.getName());

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Grouping actions ************\n");

		groupDishesByType();
		groupDishNamesByType();
		printDishesByCaloricLevel();

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

}
