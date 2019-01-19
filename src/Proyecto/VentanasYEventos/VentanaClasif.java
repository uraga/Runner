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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

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
		
		Object [][] datos = {
				{"1º", "10", "Agapito99", "1020"},
				{"2º",  "43", "Bull", "840"},
				{"3º", "32", "Brawler", "780"},
				{"4º", "25", "Poco97", "640"},
				{"5º", "7", "Leon32", "400"},
				{"6º", "50", "DjMariio", "220"},
				{"7º", "3", "Hamza67", "160"},
				{"8º",  "1", "Alexby", "40"}
		};
		
		String [] columnas = { "Posición", "Nivel", "Usuario", "Puntuacion" };
		
		DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
		JTable clasificacion = new JTable( modelo );
//		MiRenderer renderer = new MiRenderer();
//		clasificacion.setDefaultRenderer(null, renderer);
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
	
	public class MiRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if ( column == 0 ) {
				this.setForeground( Color.BLUE );
			} 
			return this;
		} 
	}
	
	public class MiModelo implements TableModel {

		@Override
		public int getRowCount() {
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Posicion";
			case 1:
				return "Nivel";
			case 2:
				return "Usuario";
			case 3:
				return "Puntuacion";
			default:
				return null;
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if ( columnIndex == 0 || columnIndex == 1 || columnIndex == 3) {
				return Integer.class;
			} else {
				return String.class;
			}
			
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
