package com.java8study.chapter05;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter05.entities.Trader;
import com.java8study.chapter05.entities.Transaction;

public class PuttingIntoPractice {

	private final static Logger logger = Logger.getLogger(PuttingIntoPractice.class.getName());

	public static void main(String[] args) {

		logger.log(Level.INFO, "************ Creation of entities ************\n");
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList( new Transaction(brian, 2011, 300)
				                                      , new Transaction(raoul, 2012, 1000)
				                                      , new Transaction(raoul, 2011, 400)
				                                      ,	new Transaction(mario, 2012, 710)
				                                      , new Transaction(mario, 2012, 700)
				                                      , new Transaction(alan, 2012, 950)
				                                      );

		List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
		
		logger.log(Level.INFO, "************ Questions ************\n");
		
		// Query 1: Find all transactions from year 2011 and sort them by value (small to high).
		logger.log(Level.INFO, "************ Query 1: Find all transactions from year 2011 and sort them by value (small to high). ************\n");
		
		transactions.stream()
		            .filter( (Transaction transaction) -> transaction.getYear() == 2011)
		            .sorted(comparing( Transaction::getValue) )
		            .collect( toList() )
		            .forEach(System.out::println);
		
		// // Query 2: What are all the unique cities where the traders work?
		logger.log(Level.INFO, "************ Query 2: What are all the unique cities where the traders work? ************\n");
		
		traders.stream().map( (Trader trader) -> trader.getCity() )
		                .distinct()
		                .sorted()
		                .collect( toList() )
		                .forEach( System.out::println);
		
		// Query 3: Find all traders from Cambridge and sort them by name.
		logger.log(Level.INFO, "************ Query 3: Find all traders from Cambridge and sort them by name. ************\n");
		logger.log(Level.INFO, "************ via List<Trader> ************");
		traders.stream().filter( (Trader trader) -> trader.getCity().equals("Cambridge"))
                        .distinct()
		                .sorted( comparing( Trader::getName))
		                .collect( toList() )
		                .forEach( System.out::println );
		
		logger.log(Level.INFO, "************ via List<Transaction> ************");
		transactions.stream()
		            .map( Transaction::getTrader)
		            .filter( (Trader trader) -> trader.getCity().equals("Cambridge"))
		            .distinct()
	                .sorted( comparing( Trader::getName))
	                .collect( toList() )
	                .forEach( System.out::println );

		// Query 4: Return a string of all traders’ names sorted alphabetically.
		logger.log(Level.INFO, "************ Query 4: Return a string of all traders’ names sorted alphabetically. ************\n");
		String traderNames = transactions.stream()
		            					.map( (Transaction transaction) -> transaction.getTrader().getName() )
		            					.distinct()
		            					.sorted()
		            					.reduce("", (n1, n2) -> n1 + " " + n2)
		            					.trim();
		System.out.println("All trader names: " + traderNames);
		
		// Query 5: Are there any trader based in Milan?
		logger.log(Level.INFO, "************ Query 5: Are there any trader based in Milan? ************\n");
		boolean milanBased = transactions.stream()
		            					.anyMatch( (Transaction transaction) -> transaction.getTrader().getCity().equals("Milan") );
		            
		System.out.println(" Are there any trader based in Milan? " + milanBased);
		
		// Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
		logger.log(Level.INFO, "************ Query 6: Update all transactions so that the traders from Milan are set to Cambridge. ************\n");
		System.out.println(transactions);
		transactions.stream()
					.map( (Transaction transaction) -> transaction.getTrader() )
		 			.filter( (Trader trader) -> trader.getCity().equals("Milan"))
		 			.forEach( (Trader trader) -> trader.setCity("Cambridge") );
		System.out.println(transactions);
		
		// Query 7: What's the highest value in all the transactions?
		logger.log(Level.INFO, "************ Query 7: What's the highest value in all the transactions? ************\n");
		
		Optional<Integer> highestValue = transactions.stream()
													.map( (Transaction transaction) -> transaction.getValue() )
													.reduce( Integer::max);
		if (highestValue.isPresent()) {
			System.out.println("The highest value is " + highestValue.get());
		} else {
			System.out.println("The highest value could not be determined.");
		}
		
		// Query 8: Find the transaction with the smallest value.
		logger.log(Level.INFO, "************ Find the transaction with the smallest value. ************\n");
		Optional<Transaction> smallestTransaction = transactions.stream()
																.min( comparing( Transaction::getValue));
		if (smallestTransaction.isPresent()) {
			System.out.println("The transaction with the smallest value is " + smallestTransaction.get() );
		}
		
		
	}

}
