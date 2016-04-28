package pl.edu.pwr.pp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
			Path path = this.getPathToFile(fileName);

			try (BufferedReader reader = Files.newBufferedReader(path)) {

				String p2Line = reader.readLine();
				if (!"P2".equals(p2Line)) {
					throw new Exception("Incorrect file, can't continue");
				}
				String commentLine = reader.readLine();
				String columnsRows = reader.readLine();
				String[] columnAndRow = columnsRows.split(" ");
				columns = Integer.parseInt(columnAndRow[0]);
				rows = Integer.parseInt(columnAndRow[1]);
				Integer maxIntensity = Integer.parseInt(reader.readLine());
				intensities = new int[rows][];
				for (int i = 0; i < rows; i++) {
					intensities[i] = new int[columns];
				}
				String line = null;
				int currentRow = 0;
				int currentColumn = 0;
				while ((line = reader.readLine()) != null) {
					String[] elements = line.split(" ");
					for (int i = 0; i < elements.length; i++) {
						intensities[currentRow][currentColumn] = Integer
								.parseInt(elements[i]);
						currentColumn++;
						if (currentColumn == columns) {
							currentColumn = 0;
							currentRow++;
						}
					}
				}
			} catch (URISyntaxException e) {
				throw e;
			}
		} catch (IOException e) {
			throw e;
		}

		return intensities;
	}

	private Path getPathToFile(String fileName) throws URISyntaxException {
		URI uri = ClassLoader.getSystemResource(fileName).toURI();
		return Paths.get(uri);
	}

}
