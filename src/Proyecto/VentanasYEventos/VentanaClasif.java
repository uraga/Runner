package Proyecto.VentanasYEventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Proyecto.Datos.Usuario;

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
		ModeloTabla modelo = new ModeloTabla();
		JTable clasificacion = new JTable( modelo );
		clasificacion.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
				Component comp = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
				if ( value instanceof Integer ) {
					if (column == 2 || column == 4 ) {
						((JLabel)comp).setHorizontalAlignment( JLabel.CENTER );
					} else if ( column == 1 ) {
						((JLabel)comp).setHorizontalAlignment( JLabel.LEFT );
						comp.setBackground( Color.BLUE );
					}
				}
				return comp;
			}
		});
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
		
		TableColumn columna = null;
		for ( int i=0 ; i < 4; i++) {
			columna = clasificacion.getColumnModel().getColumn(i);
			if ( i == 0) {
				columna.setPreferredWidth(20);
			} else if ( i == 1) {
				columna.setPreferredWidth(20);
			} 
		}
	}
@SuppressWarnings("serial")
class ModeloTabla extends AbstractTableModel {
	String [] columnas = { "Posicion", "Nivel", "Usuario", "Puntuación" };
	Class<?> clasesDeColumnas [] = { Integer.class, Integer.class, String.class, Integer.class};
	Object [][] datos = {
			{"1", "10", "Agapito99", "1020"},
			{"2",  "43", "Bull", "840"},
			{"3", "32", "Brawler", "780"},
			{"4", "25", "Poco97", "640"},
			{"5", "7", "Leon32", "400"},
			{"6", "50", "DjMariio", "220"},
			{"7", "3", "Hamza67", "160"},
			{"8",  "1", "Alexby", "40"}
	};

	@Override
	public int getRowCount() {
		return datos.length;
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	
	public Class<?> getColumnClass(int column) {
		return clasesDeColumnas[column];
	}
	
	public String getColumnName(int column) {
		return columnas[column];
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return datos[row][column];
	}
	
	public void setValueAt(Object value, int row, int column) {
		datos[row][column] = value;
	}
	
}

}
