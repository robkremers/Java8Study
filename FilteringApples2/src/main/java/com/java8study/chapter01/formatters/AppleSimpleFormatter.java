package com.java8study.chapter01.formatters;

import com.java8study.chapter01.entities.Apple;
import com.java8study.chapter01.interfaces.AppleFormatter;

public class AppleSimpleFormatter implements AppleFormatter {

	public AppleSimpleFormatter() {}

	public String accept(Apple apple) {
		
		return "An apple of " + apple.getWeight() + " grams.";
	}

}
