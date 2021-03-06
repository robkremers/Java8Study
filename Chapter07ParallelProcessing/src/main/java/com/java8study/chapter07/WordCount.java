package com.java8study.chapter07;

import java.util.Spliterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.java8study.chapter07.functionality.WordCounter;
import com.java8study.chapter07.functionality.WordCounterSpliterator;

public class WordCount {
	
	private final static Logger logger = Logger.getLogger(WordCount.class.getName());


    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
            "mi  ritrovai in una  selva oscura" +
            " che la  dritta via era   smarrita ";
    
    
	public static void main(String[] args) {
		
		System.out.println("Number of words found: " + countWordsIteratively(SENTENCE) );
		
		Stream<Character> characterStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		System.out.println("Number of words found: " + countWordsFunctionally( characterStream));

		Stream<Character> characterStreamParallel = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt).parallel();
		System.out.println("Number of words found in parallel: " + countWordsFunctionally( characterStreamParallel));

		System.out.println("Number of words found in parallel using a spliterator: " + countWordsSpliterator(SENTENCE));

	}
	
	private static int countWordsIteratively(String string) {
		logger.log(Level.INFO, "************ Counting the words of Dante iteratively ************\n");
		int counter = 0;
		boolean lastSpace = true;
		
		for ( char ch: string.toCharArray() ) {
			if ( Character.isWhitespace(ch)) {
				lastSpace = true;
			} else {
				if(lastSpace) {
					counter++;
					lastSpace = false;
				}
			}
		}
		
		return counter;
	}
	
	private static int countWordsFunctionally(Stream<Character> characterStream) {
		logger.log(Level.INFO, "************ Counting the words of Dante functionally ************\n");
		
		WordCounter wordCounter = characterStream.reduce( new WordCounter(0, true)
				                                        , WordCounter::accumulate
				                                        , WordCounter::combine
				                                        );
		return wordCounter.getCounter();
		
	}
	
	private static int countWordsSpliterator(String string) {
		logger.log(Level.INFO, "************ Counting the words of Dante using the WordCounterSpliterator ************\n");
		Spliterator<Character> spliterator = new WordCounterSpliterator(string);
		
		Stream<Character> characterStream = StreamSupport.stream(spliterator, true);
		
		return countWordsFunctionally( characterStream);
	}
	

}
