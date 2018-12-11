package com.andrewlevan;

import java.util.ArrayList;
import java.util.List;

public class Searcher {
	
	private static final String STRING_FORMAT = "%s: (%d,%d)";
	private static final String ADDITONAL_COORDINATES = ",(%d,%d)";
	private Puzzle puzzle;
	private boolean forward = false;
	private boolean reverse = false;
	private int puzzleSize;
	
	public Searcher(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.puzzleSize = puzzle.getPuzzleSize();
	}
	
	public List<String> search() {
		List<String> completePuzzle = new ArrayList<>();
		for (String target : puzzle.getTargetWords()) {
			String location = "";
			location = searchRows(target);
			if (!location.isEmpty()) {
				completePuzzle.add(location);
				continue;
			}
			location = searchColumns(target);
			if (!location.isEmpty()) {
				completePuzzle.add(location);
				continue;
			}
			location = searchDiagonalAscending(target);
			if (!location.isEmpty()) {
				completePuzzle.add(location);
				continue;
			}
			location = searchDiagonalDescending(target);
			completePuzzle.add(location);
		}
		return completePuzzle;
	}
	
	private String searchRows(String target) {
		reset();
		String[] rows = puzzle.getPuzzleRows(false);
		String[] reversedRows = puzzle.getPuzzleRows(true);
		String location = "";
		int column = 0;
		int row = 0;
		
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].contains(target)) {
				row = i;
				column = rows[i].indexOf(target);
				forward = !forward;
				break;
			}
			if (reversedRows[i].contains(target)) {
				row = i;
				column = (puzzleSize - 1) - reversedRows[i].indexOf(target);
				reverse = !reverse;
				break;
			}
		}
		
		if (!forward && !reverse)
			return location;
		
		for (int i = 0; i < target.length(); i++) {
			if (i == 0)
				location += String.format(STRING_FORMAT, target, column, row);
			else
				location += String.format(ADDITONAL_COORDINATES, column, row);
			if (forward)
				column++;
			else
				column--;
		}
		
		return location;
	}
	
	private String searchColumns(String target) {
		reset();
		String[] columns = puzzle.getPuzzleColumns(false);
		String[] reversedColumns = puzzle.getPuzzleColumns(true);
		String location = "";
		int row = 0;
		int column = 0;
		
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].contains(target)) {
				row = columns[i].indexOf(target);
				column = i;
				forward = true;
				break;
			}
			if (reversedColumns[i].contains(target)) {
				row = (puzzleSize - 1) - reversedColumns[i].indexOf(target);
				column = i;
				reverse = true;
				break;
			}
		}
		
		if (!forward && !reverse) {
			return location;
		}
		
		for (int i = 0; i < target.length(); i++) {
			if (i == 0)
				location += String.format(STRING_FORMAT, target, column, row);
			else
				location += String.format(ADDITONAL_COORDINATES, column, row);
			if (forward)
				row++;
			else
				row--;
		}
		
		return location;
	}
	
	private String searchDiagonalAscending(String target) {
		reset();
		String[] diagAsc = puzzle.getPuzzleDiagAsc(false);
		String[] diagAscReversed = puzzle.getPuzzleDiagAsc(true);
		int row = 0;
		int column = 0;
		String location = "";
		
		for (int i = 0; i < diagAsc.length; i++) {
			if (diagAsc[i].length() < target.length())
				continue;
			if (diagAsc[i].contains(target)) {
				forward = !forward;
				if (i >= puzzleSize) {
					row = puzzleSize - 1;
					column = i - (puzzleSize - 1);
				} else {
					row = i;
					column = 0;
				}
				break;
			}
			if (diagAscReversed[i].contains(target)) {
				reverse = !reverse;
				if (i >= puzzleSize) {
					row = i - (puzzleSize - 1);
					column = puzzleSize - 1;
				} else {
					row = 0;
					column = i;
				}
				break;
			}
		}
		
		if (!forward && !reverse)
			return location;
		
		for (int i = 0; i < target.length(); i++) {
			if (i == 0) {
				location += String.format(STRING_FORMAT, target, column, row);
				if (forward) {
					column++;
					row--;
				} else {
					column--;
					row++;
				}
				continue;
			}
			if (forward) {
				location += String.format(ADDITONAL_COORDINATES, column, row);
				column++;
				row--;
			} else {
				location += String.format(ADDITONAL_COORDINATES, column, row);
				column--;
				row++;
			}
		}
		return location;
	}
	
	private String searchDiagonalDescending(String target) {
		reset();
		String[] diagDesc = puzzle.getPuzzleDiagDesc(false);
		String[] diagDescReversed = puzzle.getPuzzleDiagDesc(true);
		int row = 0;
		int column = 0;
		String location = "";
		
		for (int i = 0; i < diagDesc.length; i++) {
			if (diagDesc[i].length() < target.length())
				continue;
			if (diagDesc[i].contains(target)) {
				forward = !forward;
				if ((puzzleSize - 1) - i <= 0) {
					row = 0 + diagDesc[i].indexOf(target);
					column = (i - (puzzleSize - 1)) + diagDesc[i].indexOf(target);
				} else {
					row = (puzzleSize - 1) - i;
					column = 0;
				}
				break;
			}
			if (diagDescReversed[i].contains(target)) {
				reverse = !reverse;
				if ((puzzleSize - 1) - i <= 0) {
					row = ((puzzleSize - 1) - (i - (puzzleSize - 1))) - diagDescReversed[i].indexOf(target);
					column = (puzzleSize - 1) - diagDescReversed[i].indexOf(target);
				} else {
					row = puzzleSize - 1;
					column = i;
				}
				break;
			}
		}
		
		if (!forward && !reverse)
			return location;
		
		for (int i = 0; i < target.length(); i++) {
			if (i == 0) {
				location += String.format(STRING_FORMAT, target, column, row);
				if (forward) {
					column++;
					row++;
				} else {
					column--;
					row--;
				}
				continue;
			}
			if (forward) {
				location += String.format(ADDITONAL_COORDINATES, column, row);
				column++;
				row++;
			} else {
				location += String.format(ADDITONAL_COORDINATES, column, row);
				column--;
				row--;
			}
		}
		return location;
	}
	
	private void reset() {
		this.forward = false;
		this.reverse = false;
	}
	
	
	
	
}
