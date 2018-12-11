package com.andrewlevan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Readable {
	
	private BufferedReader reader;
	
	public Reader(File file) throws FileNotFoundException {
		 this.reader = new BufferedReader(new FileReader(file));
	}

	@Override
	public List<String[]> readFile() throws IOException {
		List<String[]> fileLines = new ArrayList<>();
		String currentLine;
		while ((currentLine = reader.readLine()) != null) {
			fileLines.add(currentLine.split(","));
		}
		return fileLines;
	}

}
