package com.andrewlevan;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Readable {
	
	public List<String[]> readFile() throws IOException;
	
}
