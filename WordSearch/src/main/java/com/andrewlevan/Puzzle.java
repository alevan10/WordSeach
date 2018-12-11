package com.andrewlevan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Puzzle {
	
	private String[] targetWords;
	private List<String[]> puzzle;
	private int puzzleSize;
	
	public Puzzle(List<String[]> puzzle) {
		this.targetWords = puzzle.get(0);
		List<String[]> readPuzzle = new ArrayList<>();
		for (int i = 1; i < puzzle.size(); i++) {
			readPuzzle.add(puzzle.get(i));
		}
		this.puzzle = readPuzzle;
		this.puzzleSize = readPuzzle.get(0).length;
	}
	
	public String[] getTargetWords() {
		return this.targetWords;
	}
	
	public void setTargetWords(String[] targetWords) {
		this.targetWords = targetWords;
	}
	
	public List<String[]> getPuzzle() {
		return this.puzzle;
	}
	
	public void setPuzzle(List<String[]> puzzle) {
		this.puzzle = puzzle;
	}
	
	public int getPuzzleSize() {
		return this.getPuzzleRows(false)[0].length();
	}
	
	public String[] getPuzzleRows(boolean reverse) {
		List<String> rows = new ArrayList<>();
		for (String[] row : puzzle) {
			String currentRow = Arrays.stream(row).collect(Collectors.joining(""));
			rows.add(reverse(currentRow, reverse));
		}
		return rows.toArray(new String[rows.size()]);
	}

	public String[] getPuzzleColumns(boolean reverse) {
		List<String> columns = new ArrayList<>();
		for (int i = 0; i < puzzleSize; i++) {
			String temp = "";
			for (int j = 0; j < puzzleSize; j++) {
				temp += puzzle.get(j)[i];
			}
			columns.add(reverse(temp, reverse));
		}
		return columns.toArray(new String[columns.size()]);
	}
	
	public String[] getPuzzleDiagAsc(boolean reverse) {
		List<String> diagAsc = new ArrayList<>();
		for (int i = 0; i < puzzleSize; i++) {
			int currentRow = i;
			int currentColumn = 0;
			String temp = "";
			while (currentColumn <= i) {
				if (currentRow == 0) {
					temp += this.puzzle.get(currentRow)[currentColumn];
					break;
				}
				temp += this.puzzle.get(currentRow)[currentColumn];
				currentColumn++;
				currentRow--;
			}
			diagAsc.add(reverse(temp, reverse));
		}
		for (int i = 1; i < puzzleSize; i++) {
			int currentRow = puzzleSize - 1;
			int currentColumn = i;
			String temp = "";
			while (currentColumn <= puzzleSize - 1) {
				temp += this.puzzle.get(currentRow)[currentColumn];
				currentColumn++;
				currentRow--;
			}
			diagAsc.add(reverse(temp, reverse));
		}
		return diagAsc.toArray(new String[diagAsc.size()]);
	}

	public String[] getPuzzleDiagDesc(boolean reverse) {
		List<String> diagAsc = new ArrayList<>();
		for (int i = puzzleSize - 1; i >= 0; i--) {
			int currentRow = i;
			int currentColumn = 0;
			String temp = "";
			while (currentColumn < puzzleSize && currentRow < puzzleSize) {
				temp += this.puzzle.get(currentRow)[currentColumn];
				currentColumn++;
				currentRow++;
			}
			diagAsc.add(reverse(temp, reverse));
		}
		for (int i = 1; i < puzzleSize; i++) {
			int currentRow = 0;
			int currentColumn = i;
			String temp = "";
			while (currentColumn < puzzleSize) {
				temp += this.puzzle.get(currentRow)[currentColumn];
				currentColumn++;
				currentRow++;
			}
			diagAsc.add(reverse(temp, reverse));
		}
		return diagAsc.toArray(new String[diagAsc.size()]);
	}
	
	private String reverse(String str, boolean reverse) {
		if (reverse)
			return StringUtils.reverse(str);
		return str;
	}

}
