package com.java8study.chapter06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.java8study.chapter06.entities.Transaction;
import com.java8study.chapter06.enums.Currency;


public class GroupingTransactions {
	
	private final static Logger logger = Logger.getLogger(GroupingTransactions.class.getName());

    public static List<Transaction> transactions = Arrays.asList( 
    		new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) 
            );
    
	public GroupingTransactions() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		groupImperatively();
		groupFunctionally();
	}

	/**
	 * Grouping transaction values per currency in the style of Java 7.
	 * 
	 * Functionality:
	 * - A HashMap<Currency, List<Transaction>> is created.
	 * - For each transaction:
	 *   - Determine the currency.
	 *   - Get the correct List<Transaction> based on the currency from HashMap<Currency, List<Transaction>>.
	 *   - If the List<Transaction> for the given currency does not yet exist, it is created.
	 *   - Add the transaction to the List<Transaction>.
	 *   - Add the List<Transaction> to HashMap<Currency, List<Transaction>> for the given transaction.
	 *  - Print the result.
	 */
	public static void groupImperatively() {
		logger.log(Level.INFO, "************ Grouping transactions per currency programming imperatively ************\n");
		
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		
		for (Transaction transaction: transactions) {
			Currency currency = transaction.getCurrency();
			
			List<Transaction> transactionsPerCurrency = transactionsByCurrencies.get(currency);
			if (transactionsPerCurrency == null) {
				transactionsPerCurrency = new ArrayList<>();
				transactionsByCurrencies.put(currency, transactionsPerCurrency);
			}
			transactionsPerCurrency.add(transaction);
		}
//		System.out.println(transactionsByCurrency);
		
		for (Currency currency: transactionsByCurrencies.keySet() ) {
			System.out.println("Currency: " + currency);
			System.out.println("\t" + transactionsByCurrencies.get(currency));
		}
		
	}
	/**
	 * Grouping transaction values per currency in the style of Java 8 (functional programming ~~ using streams).
	 * 
	 * Collector:
	 * Grouping by classifier: java.util.function.Function<Transaction, Currency>
	 */
	public static void groupFunctionally() {
		logger.log(Level.INFO, "************ Grouping transactions per currency programming functionally ************\n");
		
		Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
				                                                                .collect(Collectors.groupingBy(
				                                                                		(Transaction transaction) -> { 
				                                                                			return transaction.getCurrency();
				                                                                			}
				                                                                		));
		
		for (Currency currency: transactionsByCurrencies.keySet() ) {
			System.out.println("Currency: " + currency);
			System.out.println("\t" + transactionsByCurrencies.get(currency));
		}
				
	}


}
