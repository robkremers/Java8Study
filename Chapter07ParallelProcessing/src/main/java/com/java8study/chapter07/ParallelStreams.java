package com.java8study.chapter07;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.java8study.chapter07.functionality.Accumulator;

public class ParallelStreams {
	private final static Logger logger = Logger.getLogger(ParallelStreams.class.getName());


	public ParallelStreams() {
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Parallel Streaming actions ************\n");

		long limitation = 10_000_000;
		iterativeSum(limitation);
		sequentialSum(limitation);
		parallelSum(limitation);
		checkAvailableCores();
		rangedSum(limitation);
		parallelRangedSum(limitation);
		sideEffectSum(limitation);
		sideEffectParallelSum(limitation);
	}
	
	/**
	 * Note:
	 * The Java 7 style is low level. Futhermore no Boxing is required.
	 * Therefore the performance can be relatively good.
	 * 
	 * @param limitation
	 * @return
	 */
	public static long iterativeSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of an Iterative Sum using Java 7 style for loop ************\n");
		
		long summation = 0L;
		
		for ( long i = 0; i <= limitation; i++ ) {
			summation += i;
		}
		
//		System.out.println("The iterative sum with limitation " + limitation + " is " + summation);
		
		return summation;
		
	}
	/**
	 * Functionality:
	 * - public interface java.util.stream.Stream:
	 * 		A sequence of elements supporting sequential and parallel aggregate operations.
	 * 
	 * - static <T> Stream<T>	iterate(T seed, UnaryOperator<T> f)
			Returns an infinite sequential ordered Stream produced by iterative application of a function f 
			to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
			
	 * - interface java.util.Function.UnaryOperator<T>
	 * 		Represents an operation on a single operand that produces a result of the same type as its operand.
     *			
	 * - T	reduce(T identity, BinaryOperator<T> accumulator)
	 *		Performs a reduction on the elements of this stream, using the provided identity value
	 *		and an associative accumulation function, and returns the reduced value.
	 *		- T identity is the seed to which the accumulator adds values.
	 *
	 * Note:
	 * 	Stream.iterate:
	 * - works at a higher level, uses internally Boxing / Unboxing of the primitive values.
	 * 
	 */
	public static long sequentialSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of a Sequential Sum ************\n");
		
		long summation = Stream.iterate( 1L
									   , ( i) -> ++i)
							   .limit( limitation )
							   .reduce( 0L
									  , Long::sum);
		
//		System.out.println("The sequential sum with limitation " + limitation + " is " + summation);
		
		return summation;
	}
	
	/**
	 * Note:
	 * - public interface Stream<T> inherits the parallel() method from java.util.stream.BaseStream.
	 * 		This is an intermediate function.
	 * - According to Jav8InAction Stream.iterate is relatively difficult to parallelize.
	 * 		The iterate function is hard to split into chunks that can be executed indpendently
	 * 		because the input of one function application always depends on the result of the previous
	 * 		application.
	 * 
	 */
	public static long parallelSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of a Sequential Sum executed in parallel ************\n");
		
		long summation = Stream.iterate( 1L
									   , ( i) -> ++i)
							   .limit( limitation )
							   .parallel()
							   .reduce( 0L
									  , Long::sum);
		
//		System.out.println("The sequential sum with limitation " + limitation + " is " + summation);
		return summation;
	}
	
	public static void checkAvailableCores() {
		logger.log(Level.INFO, "************ Determination of the available cores for parallel processing ************\n");
		
		int nrAvailableCores = Runtime.getRuntime().availableProcessors();
		System.out.println("The number of available cores is " + nrAvailableCores);
	}
	
	/**
	 * Note:
	 * - The advantage of LongStream.rangeClosed is that it works on primitive values.
	 * 		So no boxing / unboxing is necessary.
	 * - Longstream.rangeClosed produces ranges of numbers, which can be easily
	 *   split into independent chunks.
	 *   Therefore the parallelization (see next method parallelRangedSum() will be
	 *   relatively efficient.
	 * 
	 * @param limitation
	 * @return
	 */
	public static long rangedSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of the summation over a given range ************\n");
		
		long startInclusive = 0L;
		long endInclusive = limitation;
		
		long summation = LongStream.rangeClosed(startInclusive, endInclusive)
				                .reduce( Long::sum)
				                .getAsLong();
		
//		System.out.println("The summation over the inclusive range from " + startInclusive + " until (incl) " + endInclusive + " is " + summation + ".");
		return summation;
	}

	public static long parallelRangedSum(long limitation) {
		logger.log(Level.INFO, "************ Parallel calculation of the summation over a given range using reduce for the result calculation ************\n");
		
		long startInclusive = 0L;
		long endInclusive = limitation;
		
		long summation = LongStream.rangeClosed(startInclusive, endInclusive)
				                .parallel()
				                .reduce( Long::sum)
				                .getAsLong();
		
//		System.out.println("The parallel summation over the inclusive range from " + startInclusive + " until (incl) " + endInclusive + " is " + summation + ".");
		return summation;
	}
	
	/**
	 * Functionality:
	 * In this situation the LongStream does not return a value.
	 * The reason for this is that LongStream.forEach() has a LongConsumer as parameter: (T) -> void.
	 * forEach does not return a value of any kind.
	 * 
	 * java.util.stream.LongStream:
	 * void	forEach(LongConsumer action)
	 * 	Performs an action for each element of this stream.
	 * 
	 * Instead the values of the range are added to a parameter in class Accumulator2.
	 * Reason for this is that because of the use of LongConsumer no return value is possible.
	 * Yet in this case a stateful action is necessary: the numbers in the range need to be added up.
	 * BUT: this is vulnerable in case of parallel processing: see the next method sideEffectParallelSum().
	 *
	 * 
	 */
	public static long sideEffectSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of the summation over a given range using a custom Accumulator ************\n");
		
		Long startInclusive = 0L;
		Long endInclusive = limitation;
		
		long result = 0;

		Accumulator accumulator = new Accumulator();
		Accumulator2 accumulator2 = new Accumulator2();
		
		LongStream.rangeClosed(startInclusive, endInclusive)
		          .forEach( accumulator2::addToTotalSerial );

//		System.out.println("The summation over the inclusive range from " + startInclusive + " until (incl) " + endInclusive + " is " + accumulator2.getTotal() + ".");
		return accumulator2.getTotal();
	}
	
	/**
	 * Check this situation: 
	 * In this case the parallel processing does not return a coherent, correct answer.
	 * Every run results in a different result.
	 * 
	 * Method to repair this:
	 * Add the keyword 'synchronized' to the method accumulator2.addToTotal().
	 * This will solve the problem by ensuring that the use of method addToToal() will be atomic.
	 * However this will result in a very bad performance.
	 */
	public static long sideEffectParallelSum(long limitation) {
		logger.log(Level.INFO, "************ Calculation of the summation, using parallel processing, over a given range using a custom Accumulator ************\n");
		
		Long startInclusive = 0L;
		Long endInclusive = limitation;
		
		long result = 0;

		Accumulator accumulator = new Accumulator();
		Accumulator2 accumulator2 = new Accumulator2();
		
		LongStream.rangeClosed(startInclusive, endInclusive)
		          .parallel()
		          .forEach( accumulator2::addToTotalParallel );

		System.out.println("The parallel summation over the inclusive range from " + startInclusive + " until (incl) " + endInclusive + " is " + accumulator2.getTotal() + ".");
		return accumulator2.getTotal();
	}
	
	
    public static class Accumulator2 {
        private long total = 0;

        public synchronized void addToTotalParallel(long value) {
            total += value;
        }
        
        public void addToTotalSerial(long value) {
        	total += value;
        }
        
        public long getTotal() {
        	return total;
        }
        
        
    }

}
