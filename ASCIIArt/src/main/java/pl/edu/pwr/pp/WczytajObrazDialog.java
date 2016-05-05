package pl.edu.pwr.pp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WczytajObrazDialog {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WczytajObrazDialog window = new WczytajObrazDialog();
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
	public WczytajObrazDialog() {
		initialize();
	}
	
	public void showWindow(){
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 204));
		panel.setBounds(26, 32, 370, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnZDysku = new JRadioButton("Z dysku");
		rdbtnZDysku.setBackground(new Color(255, 153, 204));
		rdbtnZDysku.setBounds(20, 17, 109, 23);
		panel.add(rdbtnZDysku);
		
		JRadioButton rdbtnZAdresuUrl = new JRadioButton("Z adresu URL");
		rdbtnZAdresuUrl.setBackground(new Color(255, 153, 204));
		rdbtnZAdresuUrl.setBounds(20, 71, 109, 23);
		panel.add(rdbtnZAdresuUrl);
		
		JLabel label = new JLabel("");
		label.setEnabled(false);
		label.setBackground(new Color(255, 255, 153));
		label.setBounds(20, 300, 315, 14);
		panel.add(label);
		
		JLabel lblSciekaDoPliku = new JLabel("Scie\u017Cka do pliku");
		lblSciekaDoPliku.setBounds(30, 50, 300, 14);
		panel.add(lblSciekaDoPliku);
		
		textField = new JTextField();
		textField.setBounds(20, 107, 323, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnWybierzPlik = new JButton("Wybierz plik");
		btnWybierzPlik.setBackground(new Color(255, 255, 255));
		btnWybierzPlik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "PGM Images", "pgm");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			    }
				
			}
		});
		btnWybierzPlik.setBounds(200, 17, 150, 23);
		panel.add(btnWybierzPlik);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.setBounds(181, 189, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBackground(new Color(255, 255, 255));
		btnAnuluj.setBounds(280, 189, 89, 23);
		frame.getContentPane().add(btnAnuluj);
	}
}
