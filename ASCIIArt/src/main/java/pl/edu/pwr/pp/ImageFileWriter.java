package pl.edu.pwr.pp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

public class ImageFileWriter {

	public void saveToTxtFile(char[][] ascii, String fileName) throws URISyntaxException, IOException {

		File file = new File(getPathToFile(fileName));
		file.createNewFile();

		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(file);
			for (char[] cs : ascii) {
				printWriter.println(cs);
			}
			printWriter.close();

		} catch (FileNotFoundException e) {
			System.err.println("Nie udalo sie utworzyc pliku " + fileName);
			e.printStackTrace();
		}

	}

	private String getPathToFile(String fileName) throws URISyntaxException {

		String path = null;
		if (fileName.contains("\\") || fileName.contains("/")) {
			path = fileName;
		} else {
			path = "src/main/resources/" + fileName;
		}
		return path;
	}

}
