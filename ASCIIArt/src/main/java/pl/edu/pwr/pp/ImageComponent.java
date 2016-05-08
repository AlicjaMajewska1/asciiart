package pl.edu.pwr.pp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageComponent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private File file;

	public ImageComponent() {

	}

	public void loadImage(String path) {
		try {
			file = new File(path);
			FileInputStream fis = new FileInputStream(file);

			image = ImageIO.read(fis);

		} catch (IOException ex) {

			String errorMessage = "Nie znaleziono pliku " + path;
			ErrorWindow errorWindow = new ErrorWindow(errorMessage);
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

	public String getImageName() {
		return file == null ? "" : file.getName();
	}

	public BufferedImage getImage() {
		return image;
	}

}