package com.java8study.chapter09.interfaces;

public interface Moveable {
	int getX();
	int getY();
	void setX(int x);
	void setY(int y);
	
	default void moveHorizontally(int distance) {
		setX( getX() + distance );
	}
	
	default void moveVertically(int distance) {
		setY( getY() + distance );
	}
}
