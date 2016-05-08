package pl.edu.pwr.pp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;

public class ErrorWindow {

	private JFrame frame;
	private String errorMessage;

	public void showWindow() {
		frame.pack();
		frame.setSize(300, 160);
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ErrorWindow(String errorMessage) {
		this.errorMessage = errorMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(221, 160, 221));
		frame.setBounds(100, 100, 300, 159);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblBd = new JLabel("B\u0142\u0105d");
		lblBd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBd.setBounds(132, 11, 30, 20);
		frame.getContentPane().add(lblBd);
		lblBd.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 284, 81);
		scrollPane.setBackground(new Color(221, 160, 221));
		scrollPane.getViewport().setBackground(new Color(221, 160, 221));
		frame.getContentPane().add(scrollPane);

		JLabel lblNieZnalez = new JLabel(errorMessage);
		lblNieZnalez.setBackground(new Color(221, 160, 221));
		scrollPane.setViewportView(lblNieZnalez);
		lblNieZnalez.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
