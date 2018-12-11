package com.andrewlevan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordSearch {
	
	private static Searcher searcher;
	private static Reader reader;
	private static File file;
	private static Puzzle puzzle;
	private static Writer writer;
	
	private static void setup(String filePath) throws IOException {
		file = new File(filePath);
		reader = new Reader(file);
		puzzle = new Puzzle(reader.readFile());
		searcher = new Searcher(puzzle);
		writer = new Writer();
	}

	public static void main(String[] args) throws IOException {
		setup(args[0]);
		writer.write(searcher.search());
	}

}
