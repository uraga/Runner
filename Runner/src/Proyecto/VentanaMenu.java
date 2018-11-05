package Proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("JUGAR");
		btnNewButton.setBounds(129, 66, 177, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OPCIONES");
		btnNewButton_1.setBounds(129, 121, 177, 51);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CREDITOS");
		btnNewButton_2.setBounds(129, 176, 177, 51);
		contentPane.add(btnNewButton_2);
		
		JButton button = new JButton("<--");
		button.setBounds(393, 6, 51, 29);
		contentPane.add(button);
		
		JLabel lblRunner = new JLabel("RUNNER");
		lblRunner.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblRunner.setBounds(181, 16, 103, 38);
		contentPane.add(lblRunner);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(393, 93, 51, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(393, 132, 51, 29);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(393, 173, 51, 29);
		contentPane.add(btnNewButton_5);
	}
}
