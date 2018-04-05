package com.java8study.chapter04;

import static com.java8study.chapter04.entities.Dish.menu;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter04.functionality.StreamBasic;
import com.java8study.chapter04.functionality.StreamVsCollection;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class.getName());


	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		logger.log(Level.INFO, "************ Finding all dishes with caloric value < 400, ordered by caloric value and return a list with their names ************");
		logger.log(Level.INFO, "************ Using Java 7 methods. ************");

		StreamBasic.getLowCaloricDishesNamesInJava7(menu, 500).forEach( System.out::println);

		logger.log(Level.INFO, "************ Using Java 8 methods. ************");
		StreamBasic.getLowCaloricDishesNamesInJava8(menu, 450).forEach( System.out::println );

		logger.log(Level.INFO, "************ Example showing that a stream is traversable only once. ************");
		
		StreamVsCollection.processArray();

	}

}
