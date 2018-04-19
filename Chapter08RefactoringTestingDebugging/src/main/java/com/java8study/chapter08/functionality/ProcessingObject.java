package com.java8study.chapter08.functionality;

/**
 * Purpose:
 * Implementation of the Chain of Responsibility design pattern.
 * 	An instance of class ProcessingObject will handle a certain input via method handle().
 * 	If an instance of the same class ProcessinObject exists it will further process the input before it is returned.
 * 
 * @author LTAdmin
 *
 * @param <T>
 */
public abstract class ProcessingObject<T> {

	protected ProcessingObject<T> successor;

	public void setSuccessor( ProcessingObject<T> successor) {
		this.successor = successor;
	}
	
	public T handle( T input) {
		T result = handleWork( input );
		if (successor != null) {
			return successor.handle(result);
		}
		return result;
	}
	
	abstract protected T handleWork(T input);
	
	
}
