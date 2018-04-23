package com.java8study.chapter09;

import java.util.Arrays;
import java.util.List;

import com.java8study.chapter09.interfaces.Resizable;
import com.java8study.chapter09.shapes.Ellipse;
import com.java8study.chapter09.shapes.Rectangle;
import com.java8study.chapter09.shapes.Square;

import static com.java8study.chapter09.utilities.Utils.paint;

public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		List<Resizable> resizableShapes = Arrays.asList( new Square(), new Rectangle(), new Ellipse() );
		
		paint( resizableShapes );
	}

}
