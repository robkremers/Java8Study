package com.java8study.chapter01;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter01.entities.Apple;
import com.java8study.chapter01.formatters.AppleFancyFormatter;
import com.java8study.chapter01.formatters.AppleSimpleFormatter;
import com.java8study.chapter01.interfaces.AppleFormatter;
import com.java8study.chapter01.predicates.AppleColorPredicate;
import com.java8study.chapter01.predicates.AppleRedHeavyPredicate;
import com.java8study.chapter01.predicates.AppleWeightPredicate;

public class FilteringApples {
	
	private final String color;
	private final int boundary;

	private final static Logger logger = Logger.getLogger(FilteringApples.class.getName());

	/**
	 * 
	 * Examples of the use of predicates and Lambda functions.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		logger.setLevel(Level.INFO);
		
		List<Apple> inventory = Arrays.asList( new Apple(80, "green")
				                             , new Apple(155, "green")
				                             , new Apple(120, "red")
				                             , new Apple(166, "green red")
				                             , new Apple(160, "red")
				
				);
		
		FilteringApples self = new FilteringApples("green", 150);
		
		/* ************ The use of a filter using Java 7 functionality and behavior parameterization ************ */
		logger.log(Level.INFO, "************ The use of a filter using Java 7 functionality ************");
		List<Apple> greenApples = FilteringApples.filterApples(inventory, self::isColorApple );
		System.out.println("Green Apples: " + greenApples);
		
		List<Apple> heavyApples = FilteringApples.filterApples(inventory, self::isHeavyApple);
		System.out.println("Heavy Apples: " + heavyApples);
		
		List<Apple> greenApples2 = FilteringApples.filterApples( inventory
				                                    , (Apple apple) -> self.getColor().equals( apple.getColor() ) 
				                                    );
		System.out.println("Green Apples: " + greenApples2);
		
		List<Apple> heavyApples2 = FilteringApples.filterApples(inventory
				                                    , (Apple apple) -> ( apple.getWeight() > self.getBoundary() )
				                                    );
		System.out.println("Heavy Apples: " + heavyApples2);
		
		List<Apple> weirdApples = FilteringApples.filterApples( inventory
				                                   , (Apple apple) -> (apple.getWeight() < 80 || apple.getColor().equals("brown") )
				                                   );
		System.out.println("Weird Apples: " + weirdApples);
		
		
		/* ************ The use of a filter using stream functionality and behavior parameterization ************ */
		logger.log(Level.INFO, "************ The use of a filter using stream functionality and behavior parameterization ************");
		List<Apple> greenApples3 = filterApplesStream( inventory
				                                     , (Apple apple) -> self.getColor().equals( apple.getColor() ) );
		System.out.println("Green Apples via Stream: " + greenApples3);
		
		List<Apple> heavyApples3 = filterApplesParallelStream( inventory
				                                             , (Apple apple) -> ( apple.getWeight() > self.getBoundary() ) 
				                                             );
		System.out.println("Heavy Apples: " + heavyApples3 );
		
		/* ************ The use of predicate classes ************ */
		logger.log(Level.INFO, "************ The use of predicate classes ************");
		List<Apple> greenApples4 = filterApplesStream( inventory, new AppleColorPredicate() );
		System.out.println("The apples found using the AppleColorPredicate: " + greenApples4 );
		
		List<Apple> heavyApples4 = filterApplesStream( inventory, new AppleWeightPredicate() );
		System.out.println("Heavy Apples using the AppleWeightPredicate: " + heavyApples4 );

		List<Apple> redHeavyApples = filterApplesStream( inventory, new AppleRedHeavyPredicate() );
		System.out.println("Heavy Apples using the AppleRedHeavyPredicate: " + redHeavyApples );

		/* ************ The use of a lambda instead of predicate classes ************ */
		logger.log(Level.INFO, "************ The use of a lambda to directly pass the required expression instead defining multiple Predicate classes ************");		
		List<Apple> redHeavyApples2 = filterApplesStream( inventory
				                                        , (Apple apple) -> (apple.getWeight() > 150 && apple.getColor().equals("red") ) 
				                                        );
		System.out.println("Heavy Apples using a lambda for setting the predicate: " + redHeavyApples2 );
		
		logger.log(Level.INFO, "************ The use of a lambda in a generic filter function to directly pass the required expression instead defining multiple Predicate classes ************");
				
		logger.log(Level.INFO, "************ Sorting the inventory ************");
		// Note that in order to make this work the return of getWeight needs to be an object, like Integer.
		// In case of a primitive int to be returned this would not work.
		inventory.sort( (Apple apple1, Apple apple2) -> apple1.getWeight().compareTo( apple2.getWeight() ) );
		
		List<Apple> greenApples5 =  filterStream( inventory
				                                , (Apple apple) -> (apple.getColor().equals("green") ) 
				                                );
		System.out.println("Green Apples using a lambda for setting the predicate: " + greenApples5 );
		
		
		/*      ************ Using the AppleFormatter interface ************ */
		
		logger.log(Level.INFO, "\n************ The use of a Formatter interface ************\n");
		
		logger.log(Level.INFO, "\n************ The use of class AppleFancyFormatter ************\n");
		prettyPrintApple(inventory, new AppleFancyFormatter() );

		logger.log(Level.INFO, "\n************ The use of class AppleSimpleFormatter ************\n");
		prettyPrintApple(inventory, new AppleSimpleFormatter() );
		
/*      ************ Finding hidden and visible files ************ */
		// Find all files that are hidden in the base directory, Java 7 Style:
		logger.log(Level.INFO, "\n************ Find all files that are hidden in the base directory, Java 7 Style: ************\n");
		File[] hiddenFiles = new File(".").listFiles( new FileFilter() {

			@Override
			public boolean accept(File file) {
				
				return file.isHidden();
			}
			
		});
		
		// Java 8 style, using component reference:
		logger.log(Level.INFO, "************ Find all files that are hidden in the base directory, Java 8 style, using component reference: ************");
		hiddenFiles = new File(".").listFiles( File::isHidden );
		
		System.out.println("Overview of hidden files:");
		for(File file: hiddenFiles) {
			System.out.println("File " + file.getAbsolutePath());
		}
		
		// Find all files that are visible in the base directory, also in Java 8 style):
		// Note that I can not use: !File::isHidden.
		logger.log(Level.INFO, "************ Find all files that are visible in the base directory, Java 8 style, using component reference: ************");
		File[] visibleFiles = new File(".").listFiles(  file -> !file.isHidden() );
		
		System.out.println("Overview of visible files:");
		for(File file: visibleFiles) {
			System.out.println("File " + file.getAbsolutePath());
		}		
		
	}
	
	
	public FilteringApples(String color, int boundary) {
		super();
		this.color = color;
		this.boundary = boundary;
	}



	public String getColor() {
		return color;
	}

	public int getBoundary() {
		return boundary;
	}


	// Java 7 Style of retrieving all Apple instances that meet the criteria:
	public List<Apple> filterGreenApples(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		
		for (Apple apple: inventory) {
			if (color.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	public List<Apple> filterHeavyApples(List<Apple> inventory, int boundary) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory) {
			if (apple.getWeight() > boundary) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	// Java 8 style conditions that can be passed as arguments to a Predicate (accepting booleans).
	public boolean isGreenApple(Apple apple) {
		
		return "green".equals(apple.getColor());
	}

	public boolean isColorApple(Apple apple) {
		
		return this.getColor().equals(apple.getColor());
	}
	
	public boolean isHeavyApple(Apple apple) {
		return (apple.getWeight() > this.getBoundary() );
	}
	
	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		for (Apple apple: inventory) {
			if (predicate.test(apple)) {
				result.add(apple);
			}
		}
		
		return result;
	}

	// Now using Stream functionality:
	public static List<Apple> filterApplesStream(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		result = inventory.stream().filter(predicate).collect( toList() );	
		return result;
	}

	public static List<Apple> filterApplesParallelStream(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		result = inventory.parallelStream().filter(predicate).collect( toList() );	
		return result;
	}
	
// Now Abstract on the List type to go beyond the problem domain of Apples.
// Note that now <T> has been added to begin with.
	public static <T> List<T> filterStream(List<T> inventory, Predicate<T> predicate ) {
		List<T> result = new ArrayList<>();
		
		result = inventory.stream().filter(predicate).collect( toList() );	
		return result;
	}
	
	/**
	 * Quiz 2.1: Write a flexible prettyPrintApple method.
	 * 
	 * Functionality:
	 * Can generate a String output from an apple, in multiple ways, via a parameter.
	 * 
	 * Input: 
	 * - a List of Apples.
	 * - Parameter.
	 * 
	 */
	
	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
		
		for (Apple apple: inventory) {
			String output = appleFormatter.accept(apple);
			System.out.println(output);
		}
		System.out.println("");
	}


}

