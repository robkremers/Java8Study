package com.java8study.chapter08.entities;

import com.java8study.chapter08.interfaces.Product;

public class Bond implements Product {
	
	private String productName;

	public Bond(String productName) {
		this.productName = productName;
	}

	@Override
	public String getName() {
		return productName;
	}

}
