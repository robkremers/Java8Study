package com.java8study.chapter08.entities;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Point implements Serializable {

	private static final long serialVersionUID = 7599925643734842062L;
	
	private final int x;
	private final int y;

	/**
	 * Purpose:
	 * Custom Comparator where points are compared first by x and then by y.
	 */
	public final static Comparator<Point> compareByXAndThenY = comparing( Point::getX ).thenComparing(Point::getY);

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point moveRightBy(int x) {
		return new Point( getX() + x, getY() );
	}
	
	/**
	 * Purpose:
	 * Return a List<point> parameter where all values of point.x have been enhanced with input parameter x.
	 * 
	 * The Function for map is: Function<Point, Point>.
	 * 
	 * @param points
	 * @param x
	 * @return
	 */
	public static List<Point> moveAllPointsRightBy( List<Point> points, int x ) {
		
		return points.stream()
				     .map( (Point point) -> new Point(point.getX() + x, point.getY()))
				     .collect( toList() );
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
