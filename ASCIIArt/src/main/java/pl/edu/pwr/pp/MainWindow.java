package pl.edu.pwr.pp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;

public class MainWindow {

	private JFrame frame;
	private ImageComponent imagePanel;
	private JButton btnZapiszDoPliku;
	private JPanel panel;
	private JButton btnWczytajObraz;
	private JLabel lblSzerokosc;
	private JButton btnFunkcja1;
	private JButton btnFunkcja2;
	private JScrollPane scrollPane;
	private JComboBox<QualityEnum> qualityComboBox;
	private JComboBox<ImageWidthEnum> widhtComboBox;
	private JLabel lblJakosc;

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
		initializeFrame();
		initializePanel();
		initializeLabels();
		initializeComboBoxes();
		initialiseScrollPaneForImage();

		btnWczytajObraz = new JButton("Wczytaj obraz");
		btnWczytajObraz.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnWczytajObraz.setBackground(new Color(255, 255, 255));
		btnWczytajObraz.setBounds(10, 22, 133, 23);
		final MainWindow mainWindow = this;
		btnWczytajObraz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WczytajObrazDialog wczytaj = new WczytajObrazDialog(mainWindow);
				wczytaj.showWindow();
			}
		});
		panel.add(btnWczytajObraz);

		btnZapiszDoPliku = new JButton("Zapisz do pliku");
		btnZapiszDoPliku.setBackground(new Color(255, 255, 255));
		btnZapiszDoPliku.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnZapiszDoPliku.setEnabled(false);
		btnZapiszDoPliku.setBounds(10, 169, 133, 23);
		btnZapiszDoPliku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					imagePanel.setWidthEnum((ImageWidthEnum) widhtComboBox.getSelectedItem());
					imagePanel.setQuality((QualityEnum) qualityComboBox.getSelectedItem());
					imagePanel.saveImage(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		panel.add(btnZapiszDoPliku);

		btnFunkcja1 = new JButton("Funkcja 1");
		btnFunkcja1.setEnabled(false);
		btnFunkcja1.setBounds(10, 211, 133, 23);
		panel.add(btnFunkcja1);

		btnFunkcja2 = new JButton("Funkcja 2");
		btnFunkcja2.setEnabled(false);
		btnFunkcja2.setBounds(10, 245, 133, 23);
		panel.add(btnFunkcja2);

	}

	private void initialiseScrollPaneForImage() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(173, 11, 268, 279);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
		imagePanel = new ImageComponent();
		scrollPane.setViewportView(imagePanel);
		imagePanel.setBackground(new Color(255, 153, 204));
		imagePanel.setLayout(null);
	}

	private void initializeLabels() {
		lblSzerokosc = new JLabel("Szeroko\u015B\u0107 obrazu");
		lblSzerokosc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSzerokosc.setBounds(10, 113, 91, 14);
		panel.add(lblSzerokosc);

		lblJakosc = new JLabel("Jako\u015B\u0107");
		lblJakosc.setBounds(10, 56, 46, 14);
		panel.add(lblJakosc);
	}

	public void loadImageToWindow(String path) {
		imagePanel.loadImage(path);
		if (imagePanel.getImage() != null) {
			imagePanel.setSize(imagePanel.getImage().getWidth(), imagePanel.getImage().getHeight());
		}
		btnZapiszDoPliku.setEnabled(imagePanel.getImage() != null);
		imagePanel.repaint();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void initializePanel() {
		panel = new JPanel();
		panel.setBounds(10, 11, 153, 279);
		panel.setBackground(new Color(255, 153, 204));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	}

	private void initializeComboBoxes() {
		qualityComboBox = new JComboBox<QualityEnum>();
		qualityComboBox.setBackground(Color.WHITE);
		qualityComboBox.setBounds(10, 81, 133, 20);
		for (QualityEnum qualityEnum : QualityEnum.values()) {
			qualityComboBox.addItem(qualityEnum);
		}
		qualityComboBox.setSelectedItem(QualityEnum.LOW);
		panel.add(qualityComboBox);

		widhtComboBox = new JComboBox<ImageWidthEnum>();
		widhtComboBox.setBackground(Color.WHITE);
		widhtComboBox.setBounds(10, 138, 133, 20);
		for (ImageWidthEnum imageWidthEnum : ImageWidthEnum.values()) {
			widhtComboBox.addItem(imageWidthEnum);
		}
		widhtComboBox.setSelectedItem(ImageWidthEnum.ORIGINAL);
		panel.add(widhtComboBox);
	}

}
