package com.java8study.chapter08.interfaces;

import java.util.function.Consumer;

/**
 * Purpose:
 * Define the processing of a subject by the right Observer.
 * 
 * @author LTAdmin
 *
 */
public interface Subject {
	void registerObserver(Observer observer);
	void notifyObservers(String tweet);
	void notifyObserversLambda( String string, Consumer<String> tweet);
}
