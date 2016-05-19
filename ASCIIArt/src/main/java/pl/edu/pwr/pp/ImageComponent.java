package pl.edu.pwr.pp;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageComponent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private File file;
	private String path = "";
	private QualityEnum quality;

	public ImageComponent() {

	}

	public void loadImage(String path) {
		try {
			this.path = path;
			file = new File(path);
			FileInputStream fis = new FileInputStream(file);

			image = ImageIO.read(fis);

		} catch (IOException ex) {

			String errorMessage = "Nie znaleziono pliku " + path;
			ErrorWindow errorWindow = new ErrorWindow(errorMessage);
			errorWindow.showWindow();
		}
	}

	public void saveImage(String fileToSavePath) {
		ImageFileReader imageFileReader = new ImageFileReader();
		try {
			int[][] pixels = null;
			if (getImageName().toLowerCase().contains(".pgm")) {
				pixels = imageFileReader.readPgmFile(getImagePath());
			} else {
				pixels = convertToGray();
			}
			ImageFileWriter imageFileWriter = new ImageFileWriter();
			imageFileWriter.saveToTxtFile(ImageConverter.intensitiesToAscii(pixels, quality), fileToSavePath);
		} catch (URISyntaxException | IOException e) {
			ErrorWindow errorWindow = new ErrorWindow("Wybrano niepoprawn¹ scie¿kê do pliku.");
			errorWindow.showWindow();
		} catch (Exception e) {
			ErrorWindow errorWindow = new ErrorWindow(e.getMessage());
			errorWindow.showWindow();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(), image.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public int[][] convertToGray() {

		int[][] grayIntensities = new int[image.getHeight()][];
		for (int i = 0; i < image.getHeight(); i++) {
			grayIntensities[i] = new int[image.getWidth()];
		}
		if (image.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			for (int y = 0; y < image.getHeight(); ++y) {
				for (int x = 0; x < image.getWidth(); ++x) {
					Color color = new Color(image.getRGB(x, y));
					grayIntensities[y][x] = (int) (0.2989 * color.getRed() + 0.5870 * color.getGreen()
							+ 0.1140 * color.getBlue());
				}
			}
		}
		return grayIntensities;
	}

	public String getImagePath() {
		return path;

	}

	public String getImageName() {
		return file == null ? "" : file.getName();
	}

	public BufferedImage getImage() {
		return image;
	}

	public QualityEnum getQuality() {
		return quality;
	}

	public void setQuality(QualityEnum quality) {
		this.quality = quality;
	}

}