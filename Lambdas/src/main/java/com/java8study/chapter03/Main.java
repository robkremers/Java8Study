package com.java8study.chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Comparator.comparing;

import com.java8study.chapter03.comparators.ComparatorByInverseWeight;

//import java.util.function.*;

import com.java8study.chapter03.entities.Apple;
import com.java8study.chapter03.entities.Letter;
import com.java8study.chapter03.functionality.FileProcessing;
import com.java8study.chapter03.functionality.LambdaProcessing;
import com.java8study.chapter03.interfaces.FileReaderProcessor;
import com.java8study.chapter03.interfaces.MyStringInterface;
import com.java8study.chapter03.interfaces.NoInputConsumerInterface;
import com.java8study.chapter03.interfaces.PredicateInstanceProperty;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class.getName());

	public Main() {
	}

	public static void main(String[] args) {
		logger.setLevel(Level.INFO);

		List<Apple> inventory = Arrays.asList( new Apple(155, "green"), new Apple(80, "green"), new Apple(120, "red"),
				new Apple(166, "green red"), new Apple(160, "red"), new Apple(160, "green")

		);

		// Find all green apples
		logger.log(Level.INFO, "************ Finding all green apples ************");
		List<Apple> greenApples = LambdaProcessing.filterStream(inventory, (Apple apple) -> {
			return "green".equals(apple.getColor());
		});
		LambdaProcessing.prettyPrintApples(greenApples);

		// Sorting the inventory, using a Comparator.
		logger.log(Level.INFO, "************ Sorting the apples per weight ************");
		greenApples.sort((Apple apple1, Apple apple2) -> (apple1.getWeight().compareTo(apple2.getWeight())));

		LambdaProcessing.prettyPrintApples(greenApples);

		logger.log(Level.INFO,
				"************ Sorting the apples inversely per weight using a Comparator class ************");
		greenApples.sort(new ComparatorByInverseWeight());

		LambdaProcessing.prettyPrintApples(greenApples);

		logger.log(Level.INFO,
				"************ Sorting the apples per weight using a locally defined Comparator ************");
		Comparator<Apple> byWeight = (Apple apple1, Apple apple2) -> apple1.getWeight().compareTo(apple2.getWeight());
		greenApples.sort(byWeight);
		LambdaProcessing.prettyPrintApples(greenApples);

		logger.log(Level.INFO,
				"************ Sorting the apples inversely per weight using a locally defined Comparing ************");
		greenApples.sort(comparing(Apple::getWeight).reversed());
		LambdaProcessing.prettyPrintApples(greenApples);

		/**
		 * The result of a Lambda must be returned to a functional interface. An
		 * interface with exactly one abstract method is called Functional Interface.
		 * 
		 */
		MyStringInterface myStringInterface = (String string) -> string + " Tested.";

		String stringOutput = myStringInterface.testMethod("test");
		System.out.println(stringOutput);

		/**
		 * Examples from Listing 3.1. Valid Lambda expressions in Java 8.
		 * 
		 */
		logger.log(Level.INFO,
				"************ Examples from Listing 3.1. Valid Lambda expressions in Java 8. ************");

		ToIntFunction<String> supplier = (String s) -> s.length();
		System.out.println("Length of String 'test string' using functional interface ToIntFunction<String>: "
				+ supplier.applyAsInt("test string"));

		Function<String, Integer> getStringLength = (String s) -> s.length();
		System.out.println("Length of String 'test string' using functional interface Function<String, Integer>: "
				+ getStringLength.apply("test string"));

		Function<Apple, Boolean> testAppleWeight = (Apple apple) -> (apple.getWeight() > 150);
		System.out.println(
				"Test whether an apple is heavier than 150 gram: " + testAppleWeight.apply(new Apple(180, "green")));

		BiConsumer<Integer, Integer> printSum = (Integer x, Integer y) -> System.out
				.println("Result BiConsumer<Integer, Integer> printSum: " + (x + y));

		printSum.accept(5, 7);

		BiConsumer<Integer, Integer> printSum2 = (Integer x, Integer y) -> {
			System.out.print("Result of BiConsumer<Integer, Integer> printSum2: ");
			System.out.println((x + y));

		};

		printSum2.accept(12, 34);

		logger.log(Level.INFO, "************ Examples of an empty lambda ************");

		NoInputConsumerInterface noInputConsumer = () -> {
			System.out.println("test");
		};

		System.out.println("Executing noInputConsumer.execute(): ");
		noInputConsumer.execute();
		System.out.println("That's it :-)");

		logger.log(Level.INFO,
				"************ A custom Lambda that accepts an instance of class T and returns an instance of class R ************");
		// Note that once the objects are defined it is no longer necessary to define
		// them again in the lambda.
		// But for clarity it may still be convenient.
		PredicateInstanceProperty<Apple, Integer> appleWeight = (apple, value) -> (apple.getWeight() > value);
		final int weightOfApple = 200;
		boolean heavy = appleWeight.accept(new Apple(180, "green"), weightOfApple);
		System.out.println("Is the apple heavy? " + heavy);
		

		logger.log(Level.INFO,	"************ File Processing in Java 7 style. ************");
		String fileToBeProcessed = "data.txt";
		
		String line = FileProcessing.processFile(fileToBeProcessed);
		System.out.println("line = " + line);

		logger.log(Level.INFO,	"************ File Processing in Java 8 style, processing one line. ************");
		FileReaderProcessor<String, String> oneLineReader = (String fileName) -> {
			String lineRead = "";
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				if (br.ready())
				lineRead = br.readLine();
			}
			return lineRead;
		};

		String oneLine = FileProcessing.processFile2(oneLineReader, fileToBeProcessed);
		System.out.println("oneLine = " + oneLine);

		logger.log(Level.INFO,	"************ File Processing in Java 8 style, processing two line. ************");
		FileReaderProcessor<String, String> twoLineReader = (String fileName) -> {
			String lineRead = "";
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				if (br.ready())
				lineRead = br.readLine() + " " + br.readLine();
			}
			return lineRead;
		};

		String twoLine = FileProcessing.processFile2(twoLineReader, fileToBeProcessed);
		System.out.println("twoLine = " + twoLine);

		logger.log(Level.INFO,	"************ File Processing in Java 8 style, processing all lines. ************");
		/**
		 * Note:
		 * Instead of FileReaderProcessor I could have used java.util.function.Function<T, R>.
		 * 
		 * This example shows how to:
		 * - Define a Functional Interface.
		 * - Use it as parameter in a method.
		 * - Assign a Lambda with the correct signature to the Functional Interface.
		 * 
		 */
		FileReaderProcessor<String, String> fileReader = (fileName) -> {
			String lineRead = "";
			String currentLine = "";
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				while ( (currentLine = br.readLine()) != null )
				lineRead += " " + currentLine;
			}
			lineRead = lineRead.trim();
			return lineRead;
		};
		
		String fileContent = FileProcessing.processFile2(fileReader, fileToBeProcessed);
		System.out.println("fileContent = " + fileContent);
		
		logger.log(Level.INFO,	"************ Example of the use of java.util.function.Function<T, R> ************");
		/**
		 * Technically:
		 * Function<String, Integer> function = (String string) -> string.length();
		 * 
		 */
		List<Integer> list = LambdaProcessing.map( Arrays.asList("lambdas", "in", "action")
				                                 , (String string) -> { return string.length(); }
				                                 );
		System.out.println("list = " + list);
		
		logger.log(Level.INFO,	"************ Example of the use of java.util.function.IntPredicate. ************");
		
		IntPredicate evenNumbers = (int i) ->  i % 2 == 0;
		System.out.println("Test whether 1000 is an even number using IntPredicate: " + evenNumbers.test(1000));
		
		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
		System.out.println("Test whether 1000 is an odd number using Predicate<Integer>: " + oddNumbers.test(1000));
		
		logger.log(Level.INFO,	"************ Example of the use of java.util.concurrent.Callable<Integer>; acting as a Supplier. ************");
		Callable<Integer> callable = () -> 42;
		try {
		System.out.println("Callable: " + callable.call() );
		} catch( Exception e) {
			System.err.println(e.getMessage());
		}
		
		logger.log(Level.INFO,	"************ Example showing that a local variable defined in an enclosing scope must be final or effectively final. ************");
		// A local variable defined in an enclosing scope must be final or effectively final.
		// So can not be changed anymore.
		int portNumber = 123;
		Runnable r = () -> System.out.println(portNumber);
		//portNumber = 1234;
		r.run();
		
		logger.log(Level.INFO,	"************ Example of the use of method reference. ************");
		/**
		 * http://javarevisited.blogspot.nl/2017/08/how-to-convert-lambda-expression-to-method-reference-in-java8-example.html
		 * 
		 */
		Consumer <String> printString = (String string) -> { System.out.println(string); };
		printString.accept("test string");
		
//		Consumer <String> printString2 = (String string) -> (System.out::println);
		
		/**
		 * It is not possible to use method reference if the argument is modified before passing to another method.
		 * e.g. the following lambda can not be replaced by a method reference.
		 */
		BiFunction<String, Integer, String> printSubstring = (String string, Integer position) -> { return string.substring(position); };
		System.out.println(printSubstring.apply("Substring of teststring", 2));
		
//		printSubstring = (String string, Integer position) -> String::substring;
		
		inventory.sort( comparing( Apple::getWeight ).reversed().thenComparing( Apple::getColor ) );
		System.out.println( inventory);
		
		logger.log(Level.INFO,	"************ Composing functions. ************");
		
		Function<Integer, Integer> f = (x) -> ++x;
		Function<Integer, Integer> g = (x) -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		System.out.println("Result after executing h(1): f.andThen(g); first f, then g: " + h.apply(1));
		
		Function<Integer, Integer> h2 = f.compose(g);
		System.out.println("Result after executing h2(1): f.compose(g); first g, then f: " + h2.apply(1));
		
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
		
		System.out.println("transformationPipeline: \n" + transformationPipeline.apply("Hello Labda World!"));
		
	}

}
