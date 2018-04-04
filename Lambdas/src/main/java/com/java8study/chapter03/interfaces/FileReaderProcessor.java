package com.java8study.chapter03.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface FileReaderProcessor<T, R> {
	
	public R process(T t) throws IOException;
}
