package com.java8study.chapter08;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java8study.chapter08.entities.Point;
import com.java8study.chapter08.interfaces.ProcessPoint;

public class DebuggingLambdaMain {
	private final static Logger logger = Logger.getLogger(DebuggingLambdaMain.class.getName());


	public static void main(String[] args) {
		logger.log(Level.INFO, "************ Lambda Debugging ************\n");
	
		logger.log(Level.INFO, "\n************ The following will fail due to a NullPointerException ************\n");
		List<Point> points = Arrays.asList( new Point(12, 2), null);
//		points.stream().map( (Point point) -> point.getX() ).forEach(System.out::println);
//		points.stream().map( Point::getX ).forEach(System.out::println);

//		ProcessPoint<Point, Integer> getPoint;
//		points.stream().map( getPoint  ).forEach(System.out::println);
		
//		Function<Point, Integer> getPointX = (Point point) -> point.getX();
//		points.stream().map( getPointX  ).forEach(System.out::println);
		
		DebuggingLambdaMain.getPoints(points);
		
	}

	public static void getPoints( List<Point> points) {
		points.stream().map( (Point point) -> point.getX() ).forEach(System.out::println);
		
	}
}
