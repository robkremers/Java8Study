package com.java8study.chapter09.interfaces;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public interface MyCollection<E> extends Collection<E> {

	/**
	 * Purpose:
	 * Remove elements of a collection of type E if a specific condition is met.
	 * 
	 * Functionality:
	 * 	For each element of a collection will be checked whether the given condition is met.
	 * 	If this is true the element will be removed.
	 * 	If at least one element is removed true will be returned, otherwise false.
	 * 
	 * 
	 */
	default boolean removeIf( Predicate<? super E> filter ) {
		
		boolean removed = false;
		
		/**
		 * Note: 
		 * 	Use of:
		 * 		Iterator<E> java.util.Collector.iterator():
		 * 		Returns an iterator over the elements in this collection. 
		 * 		There are no guarantees concerning the order in which the elements 
		 * 		are returned (unless this collection is an instance of some class that provides a guarantee).
		 * 
		 */
		Iterator<E> each = iterator();
		while ( each.hasNext() ) {
			if ( filter.test( each.next() ) ) {
				each.remove();
				removed = true;
			}
		}
		
		return removed;
	}
}
