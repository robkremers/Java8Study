package com.java8study.chapter09.interfaces;

public interface A {
	
	default void hello() {
		System.out.println("Hello from interface A");
	}

}
