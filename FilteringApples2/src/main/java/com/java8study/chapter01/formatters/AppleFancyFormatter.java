package com.java8study.chapter01.formatters;

import com.java8study.chapter01.entities.Apple;
import com.java8study.chapter01.interfaces.AppleFormatter;

public class AppleFancyFormatter implements AppleFormatter {

	public AppleFancyFormatter() {
	}

	public String accept(Apple apple) {
		String characteristic = apple.getWeight() > 150 ? "heavy": "light";
		
		return "A " + characteristic + " " + apple.getColor() + " apple.";
	}

}
