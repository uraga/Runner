package Proyecto.VentanasYEventos;

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
					VentanaMenu vm = new VentanaMenu();
					vm.setVisible(true);
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
		
		//Elementos de la ventana
		JButton btnJugar = new JButton("JUGAR");
		JButton btnOpciones = new JButton("OPCIONES");
		JButton btnCreditos = new JButton("CREDITOS");
		JButton btnExit = new JButton("<--");
		JLabel lblRunner = new JLabel("RUNNER");
		JButton btnTrofeos = new JButton();
		JButton btnTienda = new JButton();
		JButton btnAjustes = new JButton();
		JButton btnIniciarSesion = new JButton();
		
		//Posición en la ventana
		btnJugar.setBounds(129, 66, 177, 51);
		contentPane.add(btnJugar);
		btnOpciones.setBounds(129, 121, 177, 51);
		contentPane.add(btnOpciones);
		btnCreditos.setBounds(129, 176, 177, 51);
		contentPane.add(btnCreditos);
		btnExit.setBounds(393, 6, 51, 29);
		contentPane.add(btnExit);
		lblRunner.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblRunner.setBounds(181, 16, 103, 38);
		contentPane.add(lblRunner);
		btnTrofeos.setBounds(393, 66, 51, 56);
		contentPane.add(btnTrofeos);
		btnTienda.setBounds(393, 121, 51, 56);
		contentPane.add(btnTienda);
		btnAjustes.setBounds(393, 176, 51, 56);
		contentPane.add(btnAjustes);
		btnIniciarSesion.setBounds(20, 220, 51, 56);
		contentPane.add(btnIniciarSesion);
	}
}
