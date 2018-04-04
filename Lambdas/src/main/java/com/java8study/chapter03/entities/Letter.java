package com.java8study.chapter03.entities;

public class Letter {

	public Letter() {
	}

	public static String addHeader(String text) {
		return "From Rob: \n\n" + text;
	}
	
	public static String addFooter(String text) {
		return text + "\n\nKind regards";
	}
	
	public static String checkSpelling(String text) {
		return text.replaceAll("abda", "ambda");
	}
}
