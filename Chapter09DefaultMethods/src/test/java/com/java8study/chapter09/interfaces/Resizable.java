package com.java8study.chapter09.interfaces;

public interface Resizable extends Drawable {
	int getWidth();
	int getHeight();
	void setWidth(int width);
	void setHeight(int height);
	void setAbsoluteSize(int width, int height);
	
	// Adding additional methods after a lot implementation has already been done can cause problems.
//	void setRelativeSize( int wFactor, int hFactor);
	
	// Instead create a default method:
	default void setRelativeSize( int wFactor, int hFactor) {
		setAbsoluteSize( getWidth() / wFactor, getHeight() / hFactor);
	}
}
