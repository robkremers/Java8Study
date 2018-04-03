package com.java8study.chapter03.comparators;

import java.io.Serializable;
import java.util.Comparator;

import com.java8study.chapter03.entities.Apple;

public class ComparatorByInverseWeight implements Comparator<Apple>, Serializable {

	private static final long serialVersionUID = -8392389338743513366L;

	public ComparatorByInverseWeight() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Apple apple1, Apple apple2) {
		
		return apple2.getWeight().compareTo(apple1.getWeight());
	}

}
