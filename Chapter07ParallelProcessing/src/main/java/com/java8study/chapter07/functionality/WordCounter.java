package com.java8study.chapter07.functionality;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Purpose:
 * Support for counting words in a functional way.
 * 
 * Functionality:
 * An instance of the class and it's methods will be used as input for java.util.stream.Stream.reduce:
 * 
 * <U> U	reduce( U identity
 *                , BiFunction<U,? super T,U> accumulator
 *                , BinaryOperator<U> combiner
 *                )
 * 		Performs a reduction on the elements of this stream, using the provided identity, accumulation and combining functions.
 * 
 * u = WordCounter.
 * 
 * The methods for accumulator and combiner will be implemented.
 * 
 * @author LTAdmin
 *
 */
public class WordCounter {
	
	private final static Logger logger = Logger.getLogger(WordCounter.class.getName());

	private final int counter;
	private final boolean lastSpace;
	
	public WordCounter(int counter, boolean lastSpace) {
		super();
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	/**
	 * Purpose:
	 * 	The accumulate method traverses the Characters one by one as done by the iterative algorithm.
	 * 
	 * Functionality:
	 * 	if the current character is a whitespace:
	 * 		- if lastSpace is true the current WordCounter instance will be returned (nothing changes).
	 * 			The functionality is passing through multiple whitespaces.
	 * 		- if lastSpace is false ( the first whitespace after a word has ended is being processed) 
	 * 		  a new WordCounter instance with lastSpace is true will be returned. 
	 *  if the current character is not a whitespace (so part of a word):
	 * 		- if lastSpace is true (the character is the first on of a new word) 
	 * 		  a new WordCounter with lastSpace false will be returned
	 *		- if lastSpace is false the current WordCounter will be returned.
	 *			The functionality is passing through a word.
	 * 
	 * Note:
	 * 	The method acts as a BiFunction<WordCounter, Character, WordCounter>.
	 * @param c
	 * @return
	 */
	public WordCounter accumulate( Character ch) {
//		logger.log(Level.INFO, "************ Accumulate BiFunction<WordCounter, Character, WordCounter> ************\n");
		
		if (Character.isWhitespace(ch)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {
			return lastSpace ? new WordCounter(counter + 1, false) : this;
			
		}
	}
	
	/**
	 * Purpose:
	 * A new WordCounter instance will be returned with the following properties:
	 * 	- The counter of the current WordCounter instance and the parameter WordCounter instance will be added up.
	 *  - The value for the new instance will be the value for the parameter WordCounter instance.
	 *  
	 * Note:
	 * The method acts as a BinaryOperator<WordCounter>.
	 * 
	 * @param wordCounter
	 * @return
	 */
	public WordCounter combine (WordCounter wordCounter) {
//		logger.log(Level.INFO, "************ Combine BinaryOperator<WordCounter, WordCounter, WordCounter> using WordCounter " + wordCounter + " ************\n");
		return new WordCounter( counter + wordCounter.counter, wordCounter.lastSpace);
	}
	
	public int getCounter() {
		return counter;
	}

	@Override
	public String toString() {
		return "WordCounter [counter=" + counter + ", lastSpace=" + lastSpace + "]";
	}

	
}
