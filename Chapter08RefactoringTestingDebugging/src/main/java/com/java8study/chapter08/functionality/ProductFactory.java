package com.java8study.chapter08.functionality;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.java8study.chapter08.entities.Bond;
import com.java8study.chapter08.entities.Loan;
import com.java8study.chapter08.entities.Stock;
import com.java8study.chapter08.interfaces.Product;

public class ProductFactory {

	final static private Map<String, Function<String, Product>> map = new HashMap<>();

	static {
		map.put("loan", (String productName) -> {
			return new Loan(productName);
		});
		map.put("bond", (String productName) -> {
			return new Bond(productName);
		});
		map.put("stock", (String productName) -> {
			return new Stock(productName);
		});
	}
	
	public static Product createProduct(String productName, String productDetail) {
		switch (productName) {
		case "loan": return new Loan(productDetail);
		case "bond": return new Bond(productDetail);
		case "stock": return new Stock(productDetail);
		default: throw new RuntimeException("No such product: " + productName);
		}
			
	}
	
	public static Product createProductLambda(String productName) {
		Function<String, Product> product = map.get(productName);
		if (product != null) {
			return product.apply(productName);
		}
		throw new RuntimeException("No such product: " + productName);
	}


}
