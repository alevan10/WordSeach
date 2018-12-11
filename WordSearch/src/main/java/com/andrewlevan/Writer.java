package com.andrewlevan;

import java.util.List;

public class Writer implements Writable{
	
	@Override
	public void write(List<String> lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}
	
	
	
}
