package com.java8study.chapter05;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter05.functionality.Filtering;
import com.java8study.chapter05.functionality.Finding;
import com.java8study.chapter05.functionality.Mapping;
import com.java8study.chapter05.functionality.Reducing;

public class Main {
	
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		logger.log(Level.INFO, "************ Filtering examples ************\n");
		Filtering.getVegetarianDishesJava7();
		
		Filtering.getVegetarianDishesJava8();
		
		Filtering.getUniqueElements();
		
		Filtering.getTruncatedStream();
		
		Filtering.getSkippedStream();
		
		logger.log(Level.INFO, "************ Mapping examples ************\n");
		
		Mapping.getDishNames();
		
		Mapping.getWordlength();
		
		Mapping.getDishNamelength();
		
		Mapping.getTheUniqueDishNameLetters();
		
		Mapping.getSquareOfNumbers();
		
		Mapping.getPairsOfNumbers();
		
		logger.log(Level.INFO, "************ Finding and Matching examples ************\n");
		
		Finding.checkAnyVegetarianDish();
		
		Finding.checkAllMatch();
		
		Finding.checkNoMatch();
		
		Finding.findAnyVegetarianDish();
		
		Finding.findFirstVegetarianDish();

		logger.log(Level.INFO, "************ Reducing examples ************\n");
		
		Reducing.reduceOperation(Arrays.asList(1, 2, 3, 4, 5), 0, (Integer a, Integer b) -> (a + b) );

		Reducing.reduceOperation(Arrays.asList(1, 2, 3, 4, 5), 1, (Integer a, Integer b) -> (a * b) );
		
		Reducing.OptionalReduceOperation(Arrays.asList(1, 2, 3, 4, 5), (Integer a, Integer b) -> (a + b));

		Reducing.OptionalReduceOperation(Arrays.asList(1, 2, 3, 4, 5), (Integer a, Integer b) -> (a * b));

		Reducing.OptionalReduceOperation(Arrays.asList(1, 2, 3, 4, 5), Integer::max);

		Reducing.OptionalReduceOperation(Arrays.asList(1, 2, 3, 4, 5), (Integer a, Integer b) -> a > b ? a : b );

		Reducing.OptionalReduceOperation(Arrays.asList(1, 2, 3, 4, 5), Integer::min);
		
		Reducing.countNumberOfDishes();

	}

}
