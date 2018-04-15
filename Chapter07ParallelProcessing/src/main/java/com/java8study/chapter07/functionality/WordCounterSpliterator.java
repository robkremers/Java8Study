package com.java8study.chapter07.functionality;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Purpose:
 * In case of calculating the words present in a sentence in parallel, the sentence will need to be split correctly
 * in the separate words. 
 * Otherwise the words will be split erratically and the the wrong answer will be returned.
 * 
 * Note:
 * This spliterator will attempt to subdivide the string (in this case containing a sentence) in order to process
 * the string correctly via a parallel process.
 * The string itself will not be analyzed.
 * 
 * @author LTAdmin
 *
 */
public class WordCounterSpliterator implements Spliterator<Character> {
	
	// Variable that will contain the string to be processed.
	private final String string;
	// Variable that will contain the current position in the string.
	private int currentChar = 0;

	public WordCounterSpliterator(String string) {
		this.string = string;
	}

	/**
	 * Purpose:
	 * If a remaining element exists, performs the given action on it, returning true; else returns false.
	 * In this case returns true if further characters remain to be consumed.
	 * This method is intended for traversing elements individually.
	 * 
	 * Functionality:
	 * The method consumes the current Character.
	 * The method returns the specified character of the instance variable string, but this is not returned.
	 * After this the instance variable currentChar is enhanced with one.
	 * A boolean value is returned indicating whether currentChar < string.length().
	 * 
	 * Note:
	 * As defined the action consumes: () -> void, i.e. does not return anything.
	 * But as shown the Consumer.accept() method can process the instance variables of this class.
	 * In this case the instance parameters are processed as far as necessary.
	 * 
	 * @param action
	 * @return
	 */
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {

		action.accept( string.charAt(currentChar++));
		return currentChar < string.length();
	}

	/**
	 * Purpose:
	 * 	If this spliterator can be partitioned, returns a Spliterator covering elements, 
	 * 	that will, upon return from this method, not be covered by this Spliterator.
	 * 
	 * Functionality:
	 * 	If the number of characters in the instance string is < 10 nothing will happen.
	 * 	Otherwise the string will be split and a Spliterator instance will be returned
	 * 	with the latter part of the string.
	 *  Note that the latter part of the string will start where a whitespace is detected.
	 *  In this way the current spliterator will remain with a complete word.
	 * 
	 * @return
	 */
	@Override
	public Spliterator<Character> trySplit() {
		
		// The remaining number of characters.
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if ( Character.isWhitespace(string.charAt(splitPos)) ) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
	}

	/**
	 * Returns an estimate of the number of elements that would be encountered by a 
	 * forEachRemaining(java.util.function.Consumer<? super T>) traversal, or returns 
	 * Long.MAX_VALUE if infinite, unknown, or too expensive to compute.
	 * 
	 * @return
	 */
	@Override
	public long estimateSize() {
		
		return string.length() - currentChar;
	}

	/**
	 * Returns a set of characteristics of this Spliterator and its elements.
	 * 
	 * @return
	 */
	@Override
	public int characteristics() {
		
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

}
