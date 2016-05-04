package pl.edu.pwr.pp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.time.LocalTime;

public class ImageFileWriter {

	public void saveToTxtFile(char[][] ascii, String fileName) throws URISyntaxException, IOException {

		File file = new File("src/main/resources/" + fileName);
		file.createNewFile();

		int columns = ascii[0].length;
		int rows = ascii.length;

		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(file);

			printWriter.println("P2");
			printWriter.println("# Converted by Ala Asia at " + LocalTime.now().toString());
			printWriter.println(columns + " " + rows);
			printWriter.println("255");
			for (char[] cs : ascii) {
				printWriter.println(cs);
			}
			printWriter.close();

		} catch (FileNotFoundException e) {
			System.err.println("Nie udalo sie utworzyc pliku " + fileName);
			e.printStackTrace();
		}

	}

}
