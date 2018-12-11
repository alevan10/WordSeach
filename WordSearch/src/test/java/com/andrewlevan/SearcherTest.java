package com.andrewlevan;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class SearcherTest {
	
	Searcher search;
	Puzzle testPuzzle;
	List<String[]> testList;
	
	@Before
	public void setup() {
		testList = new ArrayList<String[]>() {
			{
				add(new String[] {"ABC","CFI","CEG","EI"});
				add(new String[] {"A","B","C"});
				add(new String[] {"D","E","F"});
				add(new String[] {"G","H","I"});
			}
		};
		testPuzzle = new Puzzle(testList);
		search = new Searcher(testPuzzle);
	}
	
	@Test
	public void verify_can_find_words_in_rows() {
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_find_words_in_reversed_rows() {
		testPuzzle.setTargetWords(new String[] {"CBA"});
		search = new Searcher(testPuzzle);
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_format_found_words_in_rows_correctly() {
		testPuzzle.setTargetWords(new String[] {"AB", "DE", "HI"});
		search = new Searcher(testPuzzle);
		String foundAB = search.search().get(0);
		String foundDE = search.search().get(1);
		String foundHI = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for AB", "AB: (0,0),(1,0)", foundAB);
		Assert.assertEquals("Did not return correct found word formatting for DE", "DE: (0,1),(1,1)", foundDE);
		Assert.assertEquals("Did not return correct found word formatting for HI", "HI: (1,2),(2,2)", foundHI);
	}
	
	@Test
	public void verify_can_format_found_words_in_reversed_rows_correctly() {
		testPuzzle.setTargetWords(new String[] {"CB", "FE", "HG"});
		search = new Searcher(testPuzzle);
		String foundCB = search.search().get(0);
		String foundFE = search.search().get(1);
		String foundHG = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for CB", "CB: (2,0),(1,0)", foundCB);
		Assert.assertEquals("Did not return correct found word formatting for FE", "FE: (2,1),(1,1)", foundFE);
		Assert.assertEquals("Did not return correct found word formatting for HG", "HG: (1,2),(0,2)", foundHG);
	}
	
	@Test
	public void verify_can_find_word_in_columns() {
		String foundWord = search.search().get(1);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_find_word_in_reversed_columns() {
		testPuzzle.setTargetWords(new String[] {"HEB"});
		search = new Searcher(testPuzzle);
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_format_found_words_in_columns_correctly() {
		testPuzzle.setTargetWords(new String[] {"AD", "EH", "CF"});
		search = new Searcher(testPuzzle);
		String foundAD = search.search().get(0);
		String foundEH = search.search().get(1);
		String foundCF = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for AD", "AD: (0,0),(0,1)", foundAD);
		Assert.assertEquals("Did not return correct found word formatting for EH", "EH: (1,1),(1,2)", foundEH);
		Assert.assertEquals("Did not return correct found word formatting for CF", "CF: (2,0),(2,1)", foundCF);
	}
	
	@Test
	public void verify_can_format_found_words_in_reversed_columns_correctly() {
		testPuzzle.setTargetWords(new String[] {"GD", "EB", "IF"});
		search = new Searcher(testPuzzle);
		String foundGD = search.search().get(0);
		String foundEB = search.search().get(1);
		String foundIF = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for GD", "GD: (0,2),(0,1)", foundGD);
		Assert.assertEquals("Did not return correct found word formatting for EB", "EB: (1,1),(1,0)", foundEB);
		Assert.assertEquals("Did not return correct found word formatting for IF", "IF: (2,2),(2,1)", foundIF);
	}
	
	@Test
	public void verify_can_find_words_in_diagonal_ascending_lines() {
		testPuzzle.setTargetWords(new String[] {"GEC"});
		search = new Searcher(testPuzzle);
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_find_words_in_diagonal_ascending_reversed_lines() {
		String foundWord = search.search().get(2);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_format_found_words_in_diagonal_ascending_lines_correctly() {
		testPuzzle.setTargetWords(new String[] {"A", "DB", "HF"});
		search = new Searcher(testPuzzle);
		String foundA = search.search().get(0);
		String foundDB = search.search().get(1);
		String foundHF = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for A", "A: (0,0)", foundA);
		Assert.assertEquals("Did not return correct found word formatting for DB", "DB: (0,1),(1,0)", foundDB);
		Assert.assertEquals("Did not return correct found word formatting for HF", "HF: (1,2),(2,1)", foundHF);
	}
	
	@Test
	public void verify_can_format_found_words_in_diagonal_ascending_reversed_lines_correctly() {
		testPuzzle.setTargetWords(new String[] {"I", "CEG", "BD"});
		search = new Searcher(testPuzzle);
		String foundI = search.search().get(0);
		String foundCEG = search.search().get(1);
		String foundBD = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for I", "I: (2,2)", foundI);
		Assert.assertEquals("Did not return correct found word formatting for CEG", "CEG: (2,0),(1,1),(0,2)", foundCEG);
		Assert.assertEquals("Did not return correct found word formatting for BD", "BD: (1,0),(0,1)", foundBD);
	}
	
	@Test
	public void verify_can_find_words_in_diagonal_descending_lines() {
		testPuzzle.setTargetWords(new String[] {"AEI"});
		search = new Searcher(testPuzzle);
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_find_words_in_diagonal_descending_reversed_lines() {
		testPuzzle.setTargetWords(new String[] {"IEA"});
		search = new Searcher(testPuzzle);
		String foundWord = search.search().get(0);
		Assert.assertTrue(!foundWord.isEmpty());
	}
	
	@Test
	public void verify_can_format_found_words_in_diagonal_descending_lines_correctly() {
		testPuzzle.setTargetWords(new String[] {"G", "DH", "BF"});
		search = new Searcher(testPuzzle);
		String foundG = search.search().get(0);
		String foundDH = search.search().get(1);
		String foundBF = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for G", "G: (0,2)", foundG);
		Assert.assertEquals("Did not return correct found word formatting for DH", "DH: (0,1),(1,2)", foundDH);
		Assert.assertEquals("Did not return correct found word formatting for BF", "BF: (1,0),(2,1)", foundBF);
	}
	
	@Test
	public void verify_can_format_found_words_in_diagonal_descending_reversed_lines_correctly() {
		testPuzzle.setTargetWords(new String[] {"C", "FB", "IEA"});
		search = new Searcher(testPuzzle);
		String foundC = search.search().get(0);
		String foundFB = search.search().get(1);
		String foundIEA = search.search().get(2);
		Assert.assertEquals("Did not return correct found word formatting for C", "C: (2,0)", foundC);
		Assert.assertEquals("Did not return correct found word formatting for FB", "FB: (2,1),(1,0)", foundFB);
		Assert.assertEquals("Did not return correct found word formatting for IEA", "IEA: (2,2),(1,1),(0,0)", foundIEA);
	}
	
}
