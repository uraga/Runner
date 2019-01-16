package Proyecto.VentanasYEventos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaClasif extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClasif frame = new VentanaClasif();
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
	public VentanaClasif() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 185);
		setTitle("Clasificación");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Object [][] datos = {
				{ "10", "Agapito99", "1020"},
				{ "43", "Bull", "840"},
				{ "32", "Brawler", "780"},
				{ "25", "Poco97", "640"},
				{ "7", "Leon32", "400"},
				{ "50", "DjMariio", "220"}
		};
		
		String [] columnas = { "Nivel", "Usuario", "Puntuacion" };
		
		DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
		JTable clasificacion = new JTable( modelo );
		getContentPane().add( new JScrollPane(clasificacion), BorderLayout.CENTER );
		
		JButton btnVolver = new JButton( "Volver" );
		btnVolver.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu vMenu = new VentanaMenu();
				vMenu.setVisible(true);
				dispose();
			}
		});
		
		JPanel panelBoton = new JPanel();
		panelBoton.add(btnVolver);
		add( panelBoton, BorderLayout.SOUTH );
		
	}

}
