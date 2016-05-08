package pl.edu.pwr.pp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

public class WczytajObrazDialog {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnZDysku;
	private JRadioButton rdbtnZAdresuUrl;
	private MainWindow mainWindow;

	/**
	 * Create the application.
	 */
	public WczytajObrazDialog(MainWindow mainWindow) {
		initialize();
		this.mainWindow = mainWindow;
	}

	public void showWindow() {
		frame.pack();
		
		frame.setSize(450, 280);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWczytajPlik = new JLabel("Wczytaj plik ...");
		lblWczytajPlik.setBounds(26, 11, 232, 14);
		frame.getContentPane().add(lblWczytajPlik);

		final JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 204));
		panel.setBounds(26, 32, 370, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		final JLabel lblSciekaDoPliku = new JLabel("");
		lblSciekaDoPliku.setBounds(30, 50, 300, 14);
		panel.add(lblSciekaDoPliku);

		final JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.setBounds(181, 189, 89, 23);
		btnOk.setEnabled(false);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = "";
				if (rdbtnZDysku.isSelected()) {
					path = lblSciekaDoPliku.getText();
				}
				if (rdbtnZAdresuUrl.isSelected()) {
					path = textField.getText();
				}
				mainWindow.loadImageToWindow(path);
				frame.setVisible(false);
			}
		});

		frame.getContentPane().add(btnOk);

		final JButton btnWybierzPlik = new JButton("Wybierz plik");
		btnWybierzPlik.setBackground(new Color(255, 255, 255));
		btnWybierzPlik.setEnabled(false);
		btnWybierzPlik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PGM JPG GIF PNG JPEG Images", "jpeg",
						"png", "pgm", "jpg", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					lblSciekaDoPliku.setText(chooser.getSelectedFile().getAbsolutePath());
					if (chooser.getSelectedFile().getAbsolutePath() != null) {
						btnOk.setEnabled(true);
					}
				}

			}

		});
		btnWybierzPlik.setBounds(200, 17, 150, 23);
		panel.add(btnWybierzPlik);

		rdbtnZDysku = new JRadioButton("Z dysku");
		buttonGroup.add(rdbtnZDysku);
		rdbtnZDysku.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnWybierzPlik.setEnabled(rdbtnZDysku.isSelected());
				btnOk.setEnabled(lblSciekaDoPliku.getText().length() > 0);

			}
		});

		rdbtnZDysku.setBackground(new Color(255, 153, 204));
		rdbtnZDysku.setBounds(20, 17, 109, 23);
		panel.add(rdbtnZDysku);

		textField = new JTextField();
		textField.setBounds(20, 107, 323, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				btnOk.setEnabled(textField.getText().length() > 0);
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		rdbtnZAdresuUrl = new JRadioButton("Z adresu URL");
		buttonGroup.add(rdbtnZAdresuUrl);
		rdbtnZAdresuUrl.setBackground(new Color(255, 153, 204));
		rdbtnZAdresuUrl.setBounds(20, 71, 109, 23);
		rdbtnZAdresuUrl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnWybierzPlik.setEnabled(!rdbtnZAdresuUrl.isSelected());
				btnOk.setEnabled(textField.getText().length() > 0);
			}
		});
		panel.add(rdbtnZAdresuUrl);

		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setBackground(new Color(255, 255, 153));
		label.setBounds(20, 300, 315, 14);
		panel.add(label);

		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBackground(new Color(255, 255, 255));
		btnAnuluj.setBounds(280, 189, 89, 23);
		btnAnuluj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		frame.getContentPane().add(btnAnuluj);
	}

}
