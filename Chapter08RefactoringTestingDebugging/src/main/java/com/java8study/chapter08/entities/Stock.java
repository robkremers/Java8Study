package com.java8study.chapter08.entities;

import com.java8study.chapter08.interfaces.Product;

public class Stock implements Product {
	
	private String productName;

	public Stock(String productName) {
		this.productName = productName;
	}

	@Override
	public String getName() {
		return productName;
	}

}
