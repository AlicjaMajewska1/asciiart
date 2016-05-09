package pl.edu.pwr.pp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow {

	private JFrame frame;
	private ImageComponent imagePanel;
	private JButton btnZapiszDoPliku;
	private JPanel panel;
	private JButton btnWczytajObraz;
	private JLabel lblOpcja1;
	private JLabel lblOpcja2;
	private JButton btnFunkcja1;
	private JButton btnFunkcja2;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 153, 204));
		panel.setBounds(10, 11, 128, 250);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		btnWczytajObraz = new JButton("Wczytaj obraz");
		btnWczytajObraz.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnWczytajObraz.setBackground(new Color(255, 255, 255));
		final MainWindow mainWindow = this;
		btnWczytajObraz.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				WczytajObrazDialog wczytaj = new WczytajObrazDialog(mainWindow);
				wczytaj.showWindow();
			}
		});
		btnWczytajObraz.setBounds(10, 22, 101, 23);
		panel.add(btnWczytajObraz);

		lblOpcja1 = new JLabel("opcja1");
		lblOpcja1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblOpcja1.setBounds(39, 71, 32, 14);
		panel.add(lblOpcja1);

		lblOpcja2 = new JLabel("opcja2");
		lblOpcja2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblOpcja2.setBounds(39, 98, 32, 14);
		panel.add(lblOpcja2);

		btnZapiszDoPliku = new JButton("Zapisz do pliku");
		btnZapiszDoPliku.setBackground(new Color(255, 255, 255));
		btnZapiszDoPliku.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnZapiszDoPliku.setEnabled(false);
		btnZapiszDoPliku.setBounds(10, 138, 101, 23);
		btnZapiszDoPliku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ImageFileReader imageFileReader = new ImageFileReader();
					try {
						int[][] image = imageFileReader.readPgmFile(imagePanel.getImagePath());
						ImageFileWriter imageFileWriter = new ImageFileWriter();
						imageFileWriter.saveToTxtFile(ImageConverter.intensitiesToAscii(image), chooser.getSelectedFile().getAbsolutePath());
					} catch (URISyntaxException | IOException e) {
						ErrorWindow errorWindow = new ErrorWindow("Wybrano niepoprawn¹ scie¿kê do pliku.");
						errorWindow.showWindow();
					} catch (Exception e) {
						ErrorWindow errorWindow = new ErrorWindow(e.getMessage());
						errorWindow.showWindow();
					}
				}

			}
		});
		panel.add(btnZapiszDoPliku);

		btnFunkcja1 = new JButton("Funkcja 1");
		btnFunkcja1.setEnabled(false);
		btnFunkcja1.setBounds(10, 182, 101, 23);
		panel.add(btnFunkcja1);

		btnFunkcja2 = new JButton("Funkcja 2");
		btnFunkcja2.setEnabled(false);
		btnFunkcja2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFunkcja2.setBounds(10, 216, 101, 23);
		panel.add(btnFunkcja2);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(148, 11, 293, 248);
		frame.getContentPane().add(scrollPane);
		imagePanel = new ImageComponent();
		scrollPane.setViewportView(imagePanel);
		imagePanel.setBackground(new Color(255, 153, 204));
	}

	public void loadImageToWindow(String path) {
		imagePanel.loadImage(path);
		if (imagePanel.getImage() != null) {
			imagePanel.setSize(imagePanel.getImage().getWidth(), imagePanel.getImage().getHeight());
		}
		btnZapiszDoPliku.setEnabled(imagePanel.getImageName().toLowerCase().contains(".pgm"));
		imagePanel.repaint();
	}

}
