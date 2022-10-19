package telran.io;

import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class CopyFilesInputOutputStreams {
	public static void main(String[] args) {

		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			if (args.length < 2)
				throw new IllegalArgumentException("Missing required parameters");

			File fileIn = new File(args[0]);
			if (!isFileExists(fileIn)) {
				throw new IllegalArgumentException("Source file " + args[0] + " does not exist");
			}

			fileInputStream = new FileInputStream(fileIn);

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

			byte[] data = new byte[1024];
			try {
				int countBytes = 0, lengthBytes = 512;
				LocalDateTime oldlt = LocalDateTime.now();

				while (fileInputStream.read(data) > 0) {
					fileOutputStream.write(data, 0, lengthBytes);
					countBytes += lengthBytes;
				}
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
				if (fileInputStream != null)
					fileInputStream.close();
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
