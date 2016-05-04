package pl.edu.pwr.pp;

public class ImageConverter {

	/**
	 * Znaki odpowiadające kolejnym poziomom odcieni szarości - od czarnego
	 * (0) do białego (255).
	 */
	public static String INTENSITY_2_ASCII = "@%#*+=-:. ";
	public static final int ZAKRES = 256;

	/**
	 * Metoda zwraca znak odpowiadający danemu odcieniowi szarości. Odcienie
	 * szarości mogą przyjmować wartości z zakresu [0,255]. Zakres jest
	 * dzielony na równe przedziały, liczba przedziałów jest równa ilości
	 * znaków w {@value #INTENSITY_2_ASCII}. Zwracany znak jest znakiem dla
	 * przedziału, do którego należy zadany odcień szarości.
	 * 
	 * 
	 * @param intensity
	 *            odcień szarości w zakresie od 0 do 255
	 * @return znak odpowiadający zadanemu odcieniowi szarości
	 */
	public static char intensityToAscii(int intensity) {

		return INTENSITY_2_ASCII.charAt(intensity * INTENSITY_2_ASCII.length() / ZAKRES);
	}

	/**
	 * Metoda zwraca dwuwymiarową tablicę znaków ASCII mając dwuwymiarową
	 * tablicę odcieni szarości. Metoda iteruje po elementach tablicy odcieni
	 * szarości i dla każdego elementu wywołuje {@ref #intensityToAscii(int)}
	 * .
	 * 
	 * @param intensities
	 *            tablica odcieni szarości obrazu
	 * @return tablica znaków ASCII
	 */
	public static char[][] intensitiesToAscii(int[][] intensities) {
		int columns = intensities[0].length;
		int rows = intensities.length;
		char[][] characters = new char[rows][columns];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				characters[i][j] = intensityToAscii(intensities[i][j]);
			}
		}
		return characters;
	}

}
