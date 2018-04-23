package com.java8study.chapter09.utilities;

import java.util.List;

import com.java8study.chapter09.interfaces.Resizable;

public class Utils {

	public static void paint(List<Resizable> shapes) {
		shapes.forEach( resizable -> {
			resizable.setAbsoluteSize(42, 42);
			resizable.draw();
		});
	}
}
