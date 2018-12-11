package com.andrewlevan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleTest {
	
	private Reader testReader;
	private Puzzle testPuzzle;
	
	@Before
	public void setup() throws IOException {
		File file = new File("test.csv");
		testReader = new Reader(file);
		testPuzzle = new Puzzle(testReader.readFile());
	}
	
	@Test
	public void verify_puzzle_contructor_splitting_list_correctly() {
		String[] puzzleTargetWords = testPuzzle.getTargetWords();
		List<String[]> puzzleArray = testPuzzle.getPuzzle();
		Assert.assertArrayEquals("Incorrect target words pulled from file", puzzleTargetWords, new String[] {"ABC","CFI","CEG","EI"});
		Assert.assertEquals("Incorrect puzzle size in Puzzle object", 3, puzzleArray.size());
	}
	
	@Test
	public void verify_return_all_rows_correctly() {
		assertPuzzleRowsEqual(testPuzzle.getPuzzleRows(false), false);
	}
	
	@Test
	public void verify_return_all_rows_reversed_correctly() {
		assertPuzzleRowsEqual(testPuzzle.getPuzzleRows(true), true);
	}
	
	@Test
	public void verify_return_all_columns_correctly() {
		assertPuzzleColumnsEqual(testPuzzle.getPuzzleColumns(false), false);
	}
	
	@Test
	public void verify_return_all_columns_reversed_correctly() {
		assertPuzzleColumnsEqual(testPuzzle.getPuzzleColumns(true), true);
	}
	
	@Test
	public void verify_return_all_diagonal_ascending_correctly() {
		assertPuzzleDiagonalAscendingEqual(testPuzzle.getPuzzleDiagAsc(false), false);
	}
	
	@Test
	public void verify_return_all_diagonal_ascending_reversed_correctly() {
		assertPuzzleDiagonalAscendingEqual(testPuzzle.getPuzzleDiagAsc(true), true);
	}
	
	@Test
	public void verify_return_all_diagonal_descending_correctly() {
		assertPuzzleDiagonalDescendingEqual(testPuzzle.getPuzzleDiagDesc(false), false);
	}
	
	@Test
	public void verify_return_all_diagonal_descending_reversed_correctly() {
		assertPuzzleDiagonalDescendingEqual(testPuzzle.getPuzzleDiagDesc(true), true);
	}
	
	private void assertPuzzleRowsEqual(String[] puzzleRows, boolean reverse) {
		if (!reverse) {
			Assert.assertEquals("Row one did not pull correctly", puzzleRows[0], "ABC");
			Assert.assertEquals("Row two did not pull correctly", puzzleRows[1], "DEF");
			Assert.assertEquals("Row three did not pull correctly", puzzleRows[2], "GHI");
		} else {
			Assert.assertEquals("Row one did not reverse correctly", puzzleRows[0], "CBA");
			Assert.assertEquals("Row two did not reverse correctly", puzzleRows[1], "FED");
			Assert.assertEquals("Row three did not reverse correctly", puzzleRows[2], "IHG");
		}
	}
	
	private void assertPuzzleColumnsEqual(String[] puzzleColumns, boolean reverse) {
		if (!reverse) {
			Assert.assertEquals("Column one did not pull correctly", puzzleColumns[0], "ADG");
			Assert.assertEquals("Column two did not pull correctly", puzzleColumns[1], "BEH");
			Assert.assertEquals("Column three did not pull correctly", puzzleColumns[2], "CFI");
		} else {
			Assert.assertEquals("Column one did not reverse correctly", puzzleColumns[0], "GDA");
			Assert.assertEquals("Column two did not reverse correctly", puzzleColumns[1], "HEB");
			Assert.assertEquals("Column three did not reverse correctly", puzzleColumns[2], "IFC");
		}
	}
	
	private void assertPuzzleDiagonalAscendingEqual(String[] puzzleDiagAsc, boolean reverse) {
		if (!reverse) {
			Assert.assertEquals("Diagonal one did not pull correctly", puzzleDiagAsc[0], "A");
			Assert.assertEquals("Diagonal two did not pull correctly", puzzleDiagAsc[1], "DB");
			Assert.assertEquals("Diagonal three did not pull correctly", puzzleDiagAsc[2], "GEC");
			Assert.assertEquals("Diagonal four did not pull correctly", puzzleDiagAsc[3], "HF");
			Assert.assertEquals("Diagonal five did not pull correctly", puzzleDiagAsc[4], "I");
		} else {
			Assert.assertEquals("Diagonal one did not reverse correctly", puzzleDiagAsc[0], "A");
			Assert.assertEquals("Diagonal two did not reverse correctly", puzzleDiagAsc[1], "BD");
			Assert.assertEquals("Diagonal three did not reverse correctly", puzzleDiagAsc[2], "CEG");
			Assert.assertEquals("Diagonal four did not reverse correctly", puzzleDiagAsc[3], "FH");
			Assert.assertEquals("Diagonal five did not reverse correctly", puzzleDiagAsc[4], "I");
		}
	}
	
	private void assertPuzzleDiagonalDescendingEqual(String[] puzzleDiagDesc, boolean reverse) {
		if (!reverse) {
			Assert.assertEquals("Diagonal one did not pull correctly", puzzleDiagDesc[0], "G");
			Assert.assertEquals("Diagonal two did not pull correctly", puzzleDiagDesc[1], "DH");
			Assert.assertEquals("Diagonal three did not pull correctly", puzzleDiagDesc[2], "AEI");
			Assert.assertEquals("Diagonal four did not pull correctly", puzzleDiagDesc[3], "BF");
			Assert.assertEquals("Diagonal five did not pull correctly", puzzleDiagDesc[4], "C");
		} else {
			Assert.assertEquals("Diagonal one did not reverse correctly", puzzleDiagDesc[0], "G");
			Assert.assertEquals("Diagonal two did not reverse correctly", puzzleDiagDesc[1], "HD");
			Assert.assertEquals("Diagonal three did not reverse correctly", puzzleDiagDesc[2], "IEA");
			Assert.assertEquals("Diagonal four did not reverse correctly", puzzleDiagDesc[3], "FB");
			Assert.assertEquals("Diagonal five did not reverse correctly", puzzleDiagDesc[4], "C");
		}
	}
	
}
