package Proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

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
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setForeground(Color.BLACK);
		btnJugar.setBackground(Color.WHITE);
		btnJugar.setBounds(129, 66, 177, 51);
		contentPane.add(btnJugar);
		
		JButton btnOpciones = new JButton("OPCIONES");
		btnOpciones.setBounds(129, 121, 177, 51);
		contentPane.add(btnOpciones);
		
		JButton btnCreditos = new JButton("CREDITOS");
		btnCreditos.setBounds(129, 176, 177, 51);
		contentPane.add(btnCreditos);
		
		JButton btnExit = new JButton("<--");
		btnExit.setBounds(393, 6, 51, 29);
		contentPane.add(btnExit);
		
		JLabel lblRunner = new JLabel("RUNNER");
		lblRunner.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblRunner.setBounds(181, 16, 103, 38);
		contentPane.add(lblRunner);
		
		JButton btnTrofeos = new JButton();
		btnTrofeos.setBounds(393, 66, 51, 56);
		contentPane.add(btnTrofeos);
		
		JButton btnTienda = new JButton();
		btnTienda.setIcon(new ImageIcon(VentanaMenu.class.getResource("/Proyecto/carrito.png")));
		btnTienda.setBounds(393, 121, 51, 56);
		contentPane.add(btnTienda);
		
		JButton btnAjustes = new JButton();
		btnAjustes.setIcon(new ImageIcon(VentanaMenu.class.getResource("/Proyecto/ajustes.png")));
		btnAjustes.setBounds(393, 176, 51, 56);
		contentPane.add(btnAjustes);
	}
}
