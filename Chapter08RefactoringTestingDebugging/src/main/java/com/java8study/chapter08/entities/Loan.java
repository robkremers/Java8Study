package com.java8study.chapter08.entities;

import com.java8study.chapter08.interfaces.Product;

public class Loan implements Product {
	
	private String productName;

	public Loan(String productName) {
		this.productName = productName;
	}

	@Override
	public String getName() {
		return productName;

	}

}
