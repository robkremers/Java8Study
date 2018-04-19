package com.java8study.chapter08;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter08.functionality.ProcessingObject;

public class ChainOfResponsibilityMain {
	
	private final static Logger logger = Logger.getLogger(ChainOfResponsibilityMain.class.getName());


	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Chain of Responsibility Java 7 Style ************\n");
		
		ChainOfResponsibilityMain self = new ChainOfResponsibilityMain();
		self.execute();
		
		logger.log(Level.INFO, "************ Chain of Responsibility Java 8 Style ************\n");

		// Function<T, T>
		UnaryOperator<String> headerProcessing = (String text) -> {
			return "From Raoul, Mario and Allen: " + text;
		};
		
		UnaryOperator<String> spellCheckerProcessing = (String text) -> {
			return text.replaceAll("labda", "lambda");
		};
		
		// .antThen() returns default <V> Function<T,V> so I have to use Function instead of UnaryOperator.
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		System.out.println("Result: " + pipeline.apply("Aren't labdas really sexy?"));
		
		
	}
	
	private void execute() {
		ProcessingObject<String> headerTextProcessing = new HeaderTextProcessing();
		ProcessingObject<String> spellCheckerProcessing = new SpellCheckerProcessing();
		
		headerTextProcessing.setSuccessor(spellCheckerProcessing);
		
		String result1 = headerTextProcessing.handle("Aren't labdas really sexy?");
		System.out.println("result1 = " + result1 + "\n");
	}
	
	private class HeaderTextProcessing extends ProcessingObject<String> {

		@Override
		protected String handleWork(String input) {
			
			return "From Raoul, Mario and Allen: " + input;
		}		
	}

	private class SpellCheckerProcessing extends ProcessingObject<String> {

		@Override
		protected String handleWork(String input) {
			
			return input.replaceAll("labda", "lambda");
		}		
	}

}
