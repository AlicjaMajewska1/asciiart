package pl.edu.pwr.pp;

import java.net.URISyntaxException;

public class AsciiArtApplication {

	public static void main(String[] args) throws Exception {
		
		String[] images = new String[]{"Marilyn_Monroe", "Mona_Lisa", "Sierpinski_Triangle"};
		String pgmExtension = ".pgm";
		String txtExtension = ".txt";
		
		ImageFileReader imageFileReader = new ImageFileReader(); 
		ImageFileWriter imageFileWriter = new ImageFileWriter();
		
		for (String imageName : images) {
			try {
				int[][] intensities = imageFileReader.readPgmFile(imageName + pgmExtension);
				char[][] ascii = ImageConverter.intensitiesToAscii(intensities, QualityEnum.LOW);
				imageFileWriter.saveToTxtFile(ascii, imageName + txtExtension);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} 
		}
	}
}
