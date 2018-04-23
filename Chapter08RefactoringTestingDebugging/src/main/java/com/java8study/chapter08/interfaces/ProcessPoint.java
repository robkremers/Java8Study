package com.java8study.chapter08.interfaces;

import com.java8study.chapter08.entities.Point;

@FunctionalInterface
public interface ProcessPoint<Point, Integer> {
	
	public int getPoint(Point point);

}
