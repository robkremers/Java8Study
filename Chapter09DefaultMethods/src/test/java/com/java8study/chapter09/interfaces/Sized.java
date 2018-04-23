package com.java8study.chapter09.interfaces;

public interface Sized {
	int sized();
	default boolean isEmpty() {
		return sized() == 0;
	}
}
