package telran.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Application for copying files based on static method copy of class Files
 * files may be very big (several Gbytes ) args[0] - source file path args[1] -
 * destination file path args[2] - if exists "overwritten" then destination may
 * be overwritten otherwise may not be Output one of the following: Files have
 * been copied (<amount of bytes> <time of copying>) Source file doesn't exist
 * Destination can not be overwritten
 *
 */
public class CopyFilesStandard {

	public static void main(String[] args) {

		OutputStream fileOutputStream = null;

		try {
			if (args.length < 2)
				throw new IllegalArgumentException("Missing required parameters");

			;
			File fileIn = new File(args[0]);
			if (!isFileExists(fileIn)) {
				throw new IllegalArgumentException("Source file " + args[0] + " does not exist");
			}
			Path source = null;
			try {
				source = Path.of(args[0]);
			} catch (Exception e1) {

				throw new IllegalArgumentException("Source file " + args[0] + " does not exist");
			}

			File fileOut = new File(args[1]);
			if (isFileExists(fileOut)) {
				if (args.length < 3 || !args[2].equals("overwritten")) { //
					throw new IllegalArgumentException("Destination " + args[1] + " cannot be overwritten");
				}
			}

			try {

				fileOutputStream = new FileOutputStream(fileOut);

			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException(
						"Destination " + args[1] + " has non-existed directory in the path.");
			}

			try {
				long countBytes = 0;

				LocalDateTime oldlt = LocalDateTime.now();
				countBytes = Files.copy(source, fileOutputStream);

				LocalDateTime newlt = LocalDateTime.now();

				System.out.println("Copied " + countBytes + " bytes for " + ChronoUnit.MILLIS.between(oldlt, newlt)
						+ " milliseconds");
			} catch (Exception e) {
				throw new IllegalArgumentException("Unknown Error" + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (fileOutputStream != null)
					fileOutputStream.close();

			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	public static boolean isFileExists(File file) {
		return file.exists() && !file.isDirectory();
	}
}
