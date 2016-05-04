package pl.edu.pwr.pp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ImageFileWriterTest {

	private ImageFileWriter imageFileWriter;

	@Before
	public void setUp() {
		imageFileWriter = new ImageFileWriter();
	}

	@Test
	public void shouldWrite2DArrayToFile() throws URISyntaxException, IOException {
		//given
		int columns = 10;
		int rows = 5;
		String contentLine = "1234567890";
		String fileName = "PlikTestowy.txt";
		char[][] ascii = new char[rows][columns];
		for (char[] cs : ascii) {
			for (int i = 0; i < cs.length; ++i) {
				cs[i] = contentLine.charAt(i);
			}
		}
		//when
		imageFileWriter.saveToTxtFile(ascii, fileName);
		String columnsAndRows = "";
		List<String> contentLines = new ArrayList<String>();
		File file = new File("src/main/resources/" + fileName);
		try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
			reader.readLine();
			reader.readLine();
			columnsAndRows = reader.readLine();
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				contentLines.add(line);
			}

		}
		//then
		assertThat(columnsAndRows, is(equalTo(columns + " " + rows)));
		for (String contentLineTest : contentLines) {
			assertThat(contentLineTest, is(equalTo(contentLine)));
		}
	}

}
