package com.java8study.chapter07.functionality;

import java.io.Serializable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;


/**
 * Functionality:
 * 	The elements of an array of long numbers are to be added up, using the fork/join setup.
 * 	If the length of the array is smaller than THRESHOLD the numbers will be added up sequentially.
 *  Otherwise:
 *  	The numbers will be split, recursively creating two new instances of the class,
 *  	over which the numbers will be divided.
 *  	The results of the two instances will be added up and returned.
 * 
 * @author LTAdmin
 *
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> implements Serializable {
	
	private static final long serialVersionUID = -3887770319034289791L;
	private final static Logger logger = Logger.getLogger(ForkJoinSumCalculator.class.getName());
	
	public final static long THRESHOLD = 10_000L;
	
	private long[] numbers;
	private final long start;
	private final long end;

	
	private ForkJoinSumCalculator(long[] numbers, long start, long end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}


	/**
	 * The constructor that will be called to execute the for/join operation on a long[] array.
	 * Internally this public constructor calls the private constructor, filling in the necessary paramters.
	 * 
	 * @param numbers
	 */
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	@Override
	protected Long compute() {
		
		long length = end - start;
		if (length < THRESHOLD) {
			return computeSequentially();
		}
		
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator( numbers, start, start + length / 2 );
		leftTask.fork();
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator( numbers, start + length / 2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = leftTask.join();
		
		return leftResult + rightResult;

	}
	
	/**
	 * Note:
	 * Indeed the Java 7 style for loop can be replaced by the LongStream.range method.
	 * However: the processing time will now vary from slighty slower (47 msec vs. 44 / 45 msec) to very much
	 * slower: 115 msec.
	 * So the Java 7 style loop is the best and will be used.
	 * 
	 * @return
	 */
	private long computeSequentially() {
		long sum = 0;
		for ( int i = ( int)start; i < end; i++ ) {
			sum += numbers[ i ];
		}
		
//		sum = LongStream.range(start, end).reduce( Long::sum).getAsLong();
		return sum;
	}

	
	public static long forkJoinSum(long limitation) {
		logger.log(Level.INFO, "************ Parallel calculation of a sum using the Fork-Join method ************\n");
		
		long[] numbers = LongStream.rangeClosed(0, limitation).toArray();
		
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		
		return new ForkJoinPool().invoke(task);
	}

}
