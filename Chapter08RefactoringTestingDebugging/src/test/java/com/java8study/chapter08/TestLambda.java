package com.java8study.chapter08;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.java8study.chapter08.entities.Point;

public class TestLambda {
	
	public TestLambda() {
		// TODO Auto-generated constructor stub
	}
	

	@Test
	public void testMoveRightBy() throws Exception {
		Point point1 = new Point(5, 5);
		Point point2 = point1.moveRightBy(10);
		
		assertEquals(15, point2.getX() );
		assertEquals(5, point2.getY() );
	}
	
	/**
	 * Purpose:
	 * Test that indeed the comparator Point.compareByXAndThenY returns -1 if point1 < point2.
	 * 
	 * Note:
	 * This is a test of a visible lambda.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testComparingTwoPoints() throws Exception {
		Point point1 = new Point(10, 15);
		Point point2 = new Point(10, 20);
		
		int result = Point.compareByXAndThenY.compare(point1, point2);
		assertEquals(-1, result);
	}
	
	/**
	 * Purpose:
	 * Test that static method Point.moveAllPointsRightBy will indeed enhance point.x with a value x for
	 * all Point elements of a List<Point>
	 * 
	 * Note:
	 * Ensure that class Point has indeed implemented method equals().
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMoveAllPointsRightBy() throws Exception {
		List<Point> points = Arrays.asList( new Point(5, 5), new Point(10, 5) );
		List<Point> expectedPoints = Arrays.asList( new Point(10, 5), new Point(15, 5));
		
		List<Point> newPoints = Point.moveAllPointsRightBy(points, 5);
		assertEquals(expectedPoints, newPoints);
	}
}
