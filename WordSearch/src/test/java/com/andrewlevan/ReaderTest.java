package com.andrewlevan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class ReaderTest {
	
	private Reader testReader;
	private List<String[]> testList;
	
	@Before
	public void setup() throws FileNotFoundException {
		File file = new File("test.csv");
		testReader = new Reader(file);
		testList = new ArrayList<String[]>() {
			{
				add(new String[] {"ABC","CFI","CEG","EI"});
				add(new String[] {"A","B","C"});
				add(new String[] {"D","E","F"});
				add(new String[] {"G","H","I"});
			}
		};
	}
	
	@Test
	public void verify_can_read_file() throws IOException {
		List<String[]> result = testReader.readFile();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void verify_outputs_correct_number_of_list_items() throws IOException {
		List<String[]> result = testReader.readFile();
		Assert.assertEquals("Did not return correct number of list items", 4, result.size());
	}
	
	@Test
	public void verify_arrays_contain_correct_number_of_items() throws IOException {
		List<String[]> result = testReader.readFile();
		String[] lineOne = result.get(0);
		Assert.assertEquals("Array in index 0 of list did not return correct number of items", 4, lineOne.length);
	}
	
	@Test
	public void verify_arrays_splitting_correctly() throws IOException {
		List<String[]> result = testReader.readFile();
		assertListsEqual(testList, result);
	}
	
	private void assertListsEqual(List<String[]> testList, List<String[]> readList) {
		Assert.assertArrayEquals("Line one did not match", testList.get(0), readList.get(0));
		Assert.assertArrayEquals("Line two did not match", testList.get(1), readList.get(1));
		Assert.assertArrayEquals("Line three did not match", testList.get(2), readList.get(2));
		Assert.assertArrayEquals("Line four did not match", testList.get(3), readList.get(3));
	}
	

}
