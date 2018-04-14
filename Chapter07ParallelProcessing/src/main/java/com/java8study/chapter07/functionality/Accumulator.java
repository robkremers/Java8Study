package com.java8study.chapter07.functionality;

import java.io.Serializable;

public class Accumulator implements Serializable {

	private static final long serialVersionUID = 4382303821127693890L;
	private static long total = 0;
	
	public Accumulator() {
	}
	
	public static void addToTotal (long value) {
		total += value;
	}
	
	public static long getTotal( ) {
		return total;
	}

}
