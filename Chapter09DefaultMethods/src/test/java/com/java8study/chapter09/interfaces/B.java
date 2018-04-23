package com.java8study.chapter09.interfaces;

public interface B {

	default void hello() {
		System.out.println("Hello from interface B");
	}
}
