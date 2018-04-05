package com.java8study.chapter04.functionality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {

	public StreamVsCollection() {
		// TODO Auto-generated constructor stub
	}

	public static void processArray() {
		
		List<String> names = Arrays.asList("Java8", "lambdas", "in", "action");
		
		Stream<String> strNames= names.stream();
		strNames.forEach( System.out::println );
		
		/**
		 * Uncommenting the following line would result in the following exception:
		 * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
		 */
//		strNames.forEach( System.out::println );
	}
}
