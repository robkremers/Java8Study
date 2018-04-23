package com.java8study.chapter09;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyCollectionMain {

	private final static Logger logger = Logger.getLogger(MyCollectionMain.class.getName());
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Testing MyCollection ************\n");

		Predicate<Integer> checkEven = (Integer x ) ->  x % 2 == 0;
		Predicate<Integer> checkUneven = ( Integer x ) -> x % 2 != 0;
		/**
		 * Arrays.asList returns  a fixed-size List.
		 * Therefore any effort trying to remove an element of the List will 
		 * result in java.lang.UnsupportedOperationException
		 * !!!
		 * That was a waste of time.
		 */
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		/**
		 * Instead: create a new ArrayList with a constructor accepting a Collection).
		 */
		List<Integer> numbers = new java.util.ArrayList<>( Arrays.asList(1, 2, 3, 4, 5, 6) );
		System.out.println("numbers: " + numbers);
		
//		numbers.removeIf( checkUneven );
//		System.out.println("numbers after removeIf: " + numbers);

//		MyList<Integer> numbers = (MyList<Integer>) Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		/**
		 * This works the other way:
		 * We are filtering for elements that MATCH the criterium.
		 */
		List<Integer> remainingNumbers = numbers.stream()
												.filter(  checkEven )
												.collect( toList() );
		System.out.println("remainingNumbers: " + remainingNumbers);
		
		MyCollectionMain.removeIf(numbers, checkEven );
		System.out.println("numbers after MyCollectionMain.removeIf: " + numbers);
	}

	/**
	 * Functionality:
	 * 	Remove an element of the List<E> if the criterium is matched.
	 * 
	 * Note:
	 * This is an example:
	 * The same, but probably more efficient is done by List.removeIf().
	 * Okay: yes, more efficient. I have added the missing code to indicate how professional code should be written.
	 * 
	 * @param numbers
	 * @param filter
	 * @return
	 */
	private static boolean removeIf(List<Integer> numbers, Predicate<Integer> filter)  {
		
		boolean removed = false;
		Objects.requireNonNull(filter);
		final Iterator<Integer> iterator = numbers.iterator();
		while ( iterator.hasNext() ) {
			Integer element = iterator.next();
			if ( filter.test( iterator.next() ) ) {
				iterator.remove();
				removed = true;
			}
		}
		
		removed = numbers.removeIf(filter);
		
		return removed;
	}
}
