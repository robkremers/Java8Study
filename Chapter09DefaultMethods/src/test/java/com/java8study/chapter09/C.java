package com.java8study.chapter09;

import com.java8study.chapter09.interfaces.A;
import com.java8study.chapter09.interfaces.B;

public class C implements A, B {

	public C() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		new C().hello();

	}

	/**
	 * Note:
	 * In this case the interfaces A, B are equal.
	 * So the functionality would not compile.
	 * In order to solve the problem of which method to use the method has to be overridden (and rewritten).
	 * 
	 */
	@Override
	public void hello() {
		System.out.println("Hello from C");
	}

}
