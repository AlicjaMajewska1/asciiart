package pl.edu.pwr.pp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class ImageFileReader {

	/**
	 * Metoda czyta plik pgm i zwraca tablicę odcieni szarości.
	 * 
	 * @param fileName
	 *            nazwa pliku pgm
	 * @return tablica odcieni szarości odczytanych z pliku
	 * @throws Exception
	 */
	public int[][] readPgmFile(String fileName) throws Exception {
		int columns = 0;
		int rows = 0;
		int[][] intensities = null;
		try {
			File file = new File(getPathStringToFile(fileName));
			file.toPath();
			// Path path = this.getPathToFile(fileName);

			try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {

				String[] columnAndRow = readHeader(reader);
				columns = Integer.parseInt(columnAndRow[0]);
				rows = Integer.parseInt(columnAndRow[1]);
				intensities = createIntensitiesTable(columns, rows, reader);
				fillIntensitiesTable(columns, intensities, reader);

			} catch (URISyntaxException e) {
				throw e;
			}
		} catch (IOException e) {
			throw e;
		}

		return intensities;
	}

	private void fillIntensitiesTable(int columns, int[][] intensities, BufferedReader reader) throws IOException {
		String line = null;
		int currentRow = 0;
		int currentColumn = 0;
		while ((line = reader.readLine()) != null) {
			String[] elements = line.split(" ");
			for (int i = 0; i < elements.length; i++) {
				intensities[currentRow][currentColumn] = Integer.parseInt(elements[i]);
				currentColumn++;
				if (currentColumn == columns) {
					currentColumn = 0;
					currentRow++;
				}
			}
		}
	}

	private int[][] createIntensitiesTable(int columns, int rows, BufferedReader reader) throws IOException {
		int[][] intensities;
		Integer maxIntensity = Integer.parseInt(reader.readLine());
		intensities = new int[rows][];
		for (int i = 0; i < rows; i++) {
			intensities[i] = new int[columns];
		}
		return intensities;
	}

	private String[] readHeader(BufferedReader reader) throws IOException, Exception {
		String p2Line = reader.readLine();
		if (!"P2".equals(p2Line)) {
			throw new Exception("Incorrect file, can't continue");
		}
		String commentLine = reader.readLine();
		String columnsRows = reader.readLine();
		String[] columnAndRow = columnsRows.split(" ");
		return columnAndRow;
	}


	private String getPathStringToFile(String fileName) throws URISyntaxException {
		if (fileName.contains("\\") || fileName.contains("/")) {
			return fileName;
		}
		URI uri = ClassLoader.getSystemResource(fileName).toURI();
		return uri.getPath();
	}
	
}
