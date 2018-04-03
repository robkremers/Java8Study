package com.java8study.chapter03.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface FileReaderProcessor<T> {
	
	public String process(T t) throws IOException;
}
