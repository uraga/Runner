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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana menu principal del juego
 * 
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class VentanaMenu extends JFrame {

	// ATRIBUTOS//
	private JPanel contentPane;
	

	// CONSTRUCTOR
	public VentanaMenu(String nombreU) {
		
		setTitle("Menu principal " + " - " + nombreU );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocation(400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Elementos de la ventana
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaJuego vj = new VentanaJuego( nombreU );
				vj.setVisible(true);
				vj.inicioJuego();
				dispose();
			}
		});
		JButton btnRanking = new JButton("RANKINGS");
		btnRanking.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasif vc = new VentanaClasif(nombreU);
				vc.setVisible(true);
				dispose();
			}
		});
		JButton btnCreditos = new JButton("CREDITOS");
		btnCreditos.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCreditos vc = new VentanaCreditos(nombreU);
				vc.setVisible(true);
				dispose();
			}
		});
		JButton btnExit = new JButton("<--");
		btnExit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaUsuario vu = new VentanaUsuario();
				vu.setVisible(true);
				dispose();
			}
		});
		JLabel lblRunner = new JLabel("RUNNER");
		JButton btnTrofeos = new JButton();
		JButton btnTienda = new JButton();
		JButton btnAjustes = new JButton();

		// Posición en la ventana
		btnJugar.setBounds(129, 66, 177, 51);
		contentPane.add(btnJugar);
		btnRanking.setBounds(129, 121, 177, 51);
		contentPane.add(btnRanking);
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
		
	}

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaMenu vm = new VentanaMenu();
//					vm.setVisible(true); // Ventana visible
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
