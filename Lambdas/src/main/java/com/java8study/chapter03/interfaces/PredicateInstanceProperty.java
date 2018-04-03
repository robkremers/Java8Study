package com.java8study.chapter03.interfaces;

@FunctionalInterface
public interface PredicateInstanceProperty<T, P> {
	
	public boolean accept (T t, P p);

}
