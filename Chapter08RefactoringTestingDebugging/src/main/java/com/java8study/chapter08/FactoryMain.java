package com.java8study.chapter08;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter08.entities.Loan;
import com.java8study.chapter08.functionality.ProductFactory;
import com.java8study.chapter08.interfaces.Product;

public class FactoryMain {

	private final static Logger logger = Logger.getLogger(FactoryMain.class.getName());
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Factory Design Pattern ************\n");
		
		FactoryMain self = new FactoryMain();
		self.execute();

	}

	public void execute() {
		Product product1 = ProductFactory.createProduct("loan", "Poor Schmuck");
		System.out.println("product1: " + product1.getName() );
		
		// I'm using an input parameter so I can not use Loan::new.
		Function<String, Product> loanSupplier = (String productName) -> {
			return new Loan(productName);
		};
		Product product2 = loanSupplier.apply("another loan");
		System.out.println("product2: " + product2.getName());
		
		BiFunction<String, String,String> bi = (x, y) -> {
			return x + y;
		};
		
		Product product3 = ProductFactory.createProductLambda("loan");
		System.out.println("product3: " + product3.getName() );
		
	}
}
