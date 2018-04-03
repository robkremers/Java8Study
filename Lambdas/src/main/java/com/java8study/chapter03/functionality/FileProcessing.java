package com.java8study.chapter03.functionality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.java8study.chapter03.interfaces.FileReaderProcessor;

public class FileProcessing {

	public FileProcessing() {
	}

	public static String processFile(String fileName) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			line = br.readLine();
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} 
		return line;
	}


	public static String processFile2( FileReaderProcessor<String> frp, String fileName) {
		
		String line = "";
		try {
			line =  frp.process(fileName);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} 
		return line;
	}

}
