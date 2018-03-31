package com.java8study.chapter01.predicates;

import java.util.function.Predicate;

import com.java8study.chapter01.entities.Apple;

public class AppleColorPredicate implements Predicate<Apple> {

	public boolean test(Apple apple) {
		
		return "green".equals(apple.getColor() );
	}

}
