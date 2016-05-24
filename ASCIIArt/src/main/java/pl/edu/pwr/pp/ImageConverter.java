package pl.edu.pwr.pp;

public class ImageConverter {

	/**
	 * Znaki odpowiadające kolejnym poziomom odcieni szarości - od czarnego
	 * (0) do białego (255).
	 */
	public static String INTENSITY_2_ASCII_QUALITY_LOW = "@%#*+=-:. ";
	public static String INTENSITY_2_ASCII_QUALITY_HIGH = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
	public static final int ZAKRES = 256;

	/**
	 * Metoda zwraca znak odpowiadający danemu odcieniowi szarości. Odcienie
	 * szarości mogą przyjmować wartości z zakresu [0,255]. Zakres jest
	 * dzielony na równe przedziały, liczba przedziałów jest równa ilości
	 * znaków w {@value #INTENSITY_2_ASCII_QUALITY_LOW}. Zwracany znak jest
	 * znakiem dla przedziału, do którego należy zadany odcień szarości.
	 * 
	 * 
	 * @param intensity
	 *            odcień szarości w zakresie od 0 do 255
	 * @return znak odpowiadający zadanemu odcieniowi szarości
	 */
	public static char intensityToAscii(int intensity, QualityEnum quality) {
		String conversionPattern = "";

		switch (quality) {
		case HIGH:
			conversionPattern = INTENSITY_2_ASCII_QUALITY_HIGH;
			break;
		case LOW:
		default:
			conversionPattern = INTENSITY_2_ASCII_QUALITY_LOW;
			break;
		}

		return conversionPattern.charAt(intensity * conversionPattern.length() / ZAKRES);
	}

	/**
	 * Metoda zwraca dwuwymiarową tablicę znaków ASCII mając dwuwymiarową
	 * tablicę odcieni szarości. Metoda iteruje po elementach tablicy odcieni
	 * szarości i dla każdego elementu wywołuje {@ref #intensityToAscii(int)}
	 * .
	 * 
	 * @param intensities
	 *            tablica odcieni szarości obrazu
	 * @param quality
	 * @return tablica znaków ASCII
	 */
	public static char[][] intensitiesToAscii(int[][] intensities, QualityEnum quality) {
		int columns = intensities[0].length;
		int rows = intensities.length;
		char[][] characters = new char[rows][columns];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				characters[i][j] = intensityToAscii(intensities[i][j], quality);
			}
		}
		return characters;
	}

}
