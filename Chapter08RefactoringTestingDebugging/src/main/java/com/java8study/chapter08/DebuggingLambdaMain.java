package com.java8study.chapter08;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter08.entities.Point;

public class DebuggingLambdaMain {
	private final static Logger logger = Logger.getLogger(DebuggingLambdaMain.class.getName());


	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Lambda Debugging ************\n");
	
		logger.log(Level.INFO, "\n************ The following will fail due to a NullPointerException ************\n");
		List<Point> points = Arrays.asList( new Point(12, 2), null);
		points.stream().map( (Point point) -> point.getX()).forEach(System.out::println);
	}

}
