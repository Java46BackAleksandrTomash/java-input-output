package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
class FileTests {
	File file;
	@BeforeEach
	void setUp() {
		file = new File("dir1/dir2");
		file.delete();
	}

	@Test
	void newDirectoryTest() {
		
		assertFalse(file.exists());
		file.mkdirs();
		assertTrue(file.exists());
	}
	@Test
	void printDirectoryContent() {
		printDirectory("..", 2);
	}

	/**
	 * 
	 * @param pathName - name of path to initial directory
	 * @param level - level of sub-directories printing
	 * example, level = 1 printing only first level of the initial directory content
	 *          level = 2 content + sub-directories content
	 *          ''''''''
	 *          level = -1 printing all levels
	 */
	private void printDirectory(String pathName, int level) {
		
		
		/*
		   <dir> type=dir
		       <dir> type=dir
		           <file> type=file
		           <dir> type=dir
		              ...
		 */
		
	}

}