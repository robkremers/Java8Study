package com.java8study.chapter01.predicates;

import java.util.function.Predicate;

import com.java8study.chapter01.entities.Apple;

/**
 * Purpose:
 * Tests positive (true) in case the apple is heavier than 150 g.
 * 
 * @author LTAdmin
 *
 */
public class AppleWeightPredicate implements Predicate<Apple> {
	
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}
