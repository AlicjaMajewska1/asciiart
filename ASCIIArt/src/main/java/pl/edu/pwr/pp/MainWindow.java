package pl.edu.pwr.pp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;

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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 204));
		panel.setBounds(10, 11, 128, 250);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JButton btnWczytajObraz = new JButton("Wczytaj obraz");
		btnWczytajObraz.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnWczytajObraz.setBackground(new Color(255, 255, 255));
		btnWczytajObraz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WczytajObrazDialog wczytaj = new WczytajObrazDialog();
				wczytaj.showWindow();
			}
		});
		btnWczytajObraz.setBounds(10, 22, 101, 23);
		panel.add(btnWczytajObraz);
		
		JLabel lblOpcja = new JLabel("opcja1");
		lblOpcja.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblOpcja.setBounds(39, 71, 32, 14);
		panel.add(lblOpcja);
		
		JLabel lblOpcja_1 = new JLabel("opcja2");
		lblOpcja_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblOpcja_1.setBounds(39, 98, 32, 14);
		panel.add(lblOpcja_1);
		
		JButton btnZapiszDoPliku = new JButton("Zapisz do pliku");
		btnZapiszDoPliku.setBackground(new Color(255, 255, 255));
		btnZapiszDoPliku.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnZapiszDoPliku.setBounds(10, 138, 101, 23);
		panel.add(btnZapiszDoPliku);
		
		JButton btnFunkcja = new JButton("Funkcja 1");
		btnFunkcja.setEnabled(false);
		btnFunkcja.setBounds(10, 182, 101, 23);
		panel.add(btnFunkcja);
		
		JButton button = new JButton("Funkcja 2");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(10, 216, 101, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 204));
		panel_1.setBounds(148, 11, 295, 250);
		frame.getContentPane().add(panel_1);
	}
}
