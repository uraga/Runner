package Proyecto.VentanasYEventos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class VentanaGrafica extends JFrame {
	private JPanel pCristal = new JPanel();
	private JPanel pAreaControl = new JPanel();
	private ArrayList<EventoVentana> eventosVentana;
	private long tiempoAnim = 500L;
	private long tiempoFrameAnim = tiempoAnim/40L;
	private HiloAnimacion hilo = null;
	private ArrayList<Animacion> animacionesPendientes = new ArrayList<Animacion>();
	private long tiempoAnimMsg = 500L;
	private long tiempoFrameAnimMsg = tiempoAnimMsg/40L;
	private boolean generarClicksYDrags;
	private boolean todosLosEventosDeRaton; 
	private JLayeredPane layeredPane = new JLayeredPane();
	private static final Integer CAPA_FONDO = new Integer(-100);
	private static final Integer CAPA_DIBUJO = new Integer(-50); 
	private static final int PX_SOLAPE_FONDOS = 0;
	
	
	public VentanaGrafica(int anchuraVent, int alturaVent, boolean cerrable, boolean tamFijo, String titulo) {
		setSize(anchuraVent, alturaVent);
		setTitle(titulo);
		setResizable(!tamFijo);
		if (cerrable)
			setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		else
			setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		try {
			SwingUtilities.invokeAndWait( new Runnable() {
				@Override
				public void run() {
					eventosVentana = new ArrayList<EventoVentana>();
					setLocationRelativeTo( null );
					setLayeredPane( layeredPane );
					pCristal.setLayout( null );
					setGlassPane( pCristal );
					pCristal.setOpaque( false );
					pCristal.setVisible( true );
					pAreaControl.setOpaque( false );
					layeredPane.add( pAreaControl, JLayeredPane.PALETTE_LAYER );
					pAreaControl.setLayout( null );  // layout de posicionamiento absoluto
					layeredPane.setFocusable( true );
					layeredPane.requestFocus();
					setVisible( true );
				pCristal.setBounds( 0, 0, getContentPane().getWidth(), getContentPane().getHeight() );
					layeredPane.addFocusListener( new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent arg0) {
						layeredPane.requestFocus();
					}
				});
					layeredPane.addKeyListener(  new EventoTeclado() );
				}
			} );
		} catch (Exception e) {
			
		}
			
	}
			
	private synchronized void addEvento( EventoVentana ev ) {
		eventosVentana.add( ev );
	}
	private synchronized EventoVentana remEvento( int index ) {
		return eventosVentana.remove( index );
	}
	private synchronized boolean remEvento( EventoVentana ev ) {
		return eventosVentana.remove( ev );
	}
	private synchronized EventoVentana getEvento( int index ) {
		return eventosVentana.get ( index );
	}
	
	public void esperaAFinAnimaciones() {
		do {
			try {
				Thread.sleep( tiempoFrameAnimMsg );
			} catch (InterruptedException e) {
			}
		} while (!animacionesPendientes.isEmpty());
	}
	
	private class HiloAnimacion extends Thread {
		@Override
		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep( tiempoFrameAnimMsg );
				} catch (InterruptedException e) {
					break;  // No har�a falta, el while se interrumpe en cualquier caso y se acaba el hilo
				}
				for (int i=animacionesPendientes.size()-1; i>=0; i--) {  // Al rev�s porque puede haber que quitar animaciones si se acaban
					Animacion a = animacionesPendientes.get(i);
					if (a.oj != null) a.oj.setLocation( 
						a.calcNextFrame( tiempoFrameAnimMsg ) );  // Actualizar animaci�n
					if (a.finAnimacion()) animacionesPendientes.remove(i);  // Quitar si se acaba
				}
				if (fondoAnimado && fondoRodando) {
					coorX1 -= pixDespAIzqda;
					coorX2 -= pixDespAIzqda;
					int x1 = (int) Math.round( coorX1 );
					int x2 = (int) Math.round( coorX2 );
					if (x1 < -fondo1.getWidth()) {  // Se sale fondo1 por la izqda
						coorX1 = coorX2 + fondo2.getWidth() - PX_SOLAPE_FONDOS;  // solapa pixels
						x1 = (int) Math.round( coorX1 );
					} else if (x2 < -fondo2.getWidth()) {  // Se sale fondo2 por la izqda
						coorX2 = coorX1 + fondo1.getWidth() - PX_SOLAPE_FONDOS;  // solapa pixels
						x2 = (int) Math.round( coorX2 );
					}
					if (x1<x2) { // muevo primero el de m�s a la derecha
						fondo1.setLocation( x2, 0 );
						fondo2.setLocation( x1, 0 );
					} else {
						fondo1.setLocation( x1, 0 );
						fondo2.setLocation( x2, 0 );
					}
					layeredPane.repaint();
				}
			}
		}
	}
		
	private static class Animacion {
		double xDesde;    // Desde qu� x
		double xHasta;    // hasta qu� x
		double yDesde;    // Desde qu� y
		double yHasta;    // hasta qu� y
		long msFaltan;    // en cu�ntos msg
		ObjetoGrafico oj; // objeto a animar
		public Animacion(double xDesde, double xHasta, double yDesde,
				double yHasta, long msFaltan, ObjetoGrafico oj) {
			this.xDesde = xDesde;
			this.xHasta = xHasta;
			this.yDesde = yDesde;
			this.yHasta = yHasta;
			this.msFaltan = msFaltan;
			this.oj = oj;
		}
		Point calcNextFrame( long msPasados ) {
			if (msFaltan <= msPasados) {  // Llegar al final
				msFaltan = 0;
				return new Point( (int)Math.round(xHasta), (int)Math.round(yHasta) );
			} else if (msPasados <= 0) {  // No se ha movido
				return new Point( (int)Math.round(xDesde), (int)Math.round(yDesde) );
			} else {  // Movimiento normal
				xDesde = xDesde + (xHasta-xDesde)/msFaltan*msPasados;
				yDesde = yDesde + (yHasta-yDesde)/msFaltan*msPasados;
				msFaltan -= msPasados;
				return new Point( (int)Math.round(xDesde), (int)Math.round(yDesde) );
			}
		}
		boolean finAnimacion() {
			return (msFaltan <= 0);
		}
		// equals para buscar varias animaciones del mismo objeto
		// (se compara solo el oj)
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Animacion)) return false;
			return (oj == ((Animacion)obj).oj);
		}
		@Override
		public String toString() {
			return "Animacion (" + xDesde + "," + yDesde + ") -> ("
					+ xHasta + "," + yHasta + ") msg: " + msFaltan;
		}
	
		
	}
	
	public EventoVentana readEvento(long maxEspera) {
		long esperaHasta = System.currentTimeMillis() + maxEspera;
		boolean sigoEsperando = true;
		while(sigoEsperando && System.currentTimeMillis() < esperaHasta) {
			sigoEsperando = eventosVentana.isEmpty() && isVisible();
			if(!sigoEsperando && generarClicksYDrags && !todosLosEventosDeRaton) {
				if(hayClickODragAMedias())
					sigoEsperando = true;
			}
			if(sigoEsperando)
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
		}
		if(!isVisible()) return null;
		if(sigoEsperando) 
			return null;
		else 
			return remEvento(0);
	}

	private boolean hayClickODragAMedias() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addObjeto( final ObjetoGrafico oj ) {
		try {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					layeredPane.add( oj, new Integer( JLayeredPane.DEFAULT_LAYER ) );
					layeredPane.repaint( oj.getX(), oj.getY(), oj.getAnchuraObjeto(), oj.getAlturaObjeto() );
//					pAreaJuego.add( oj );
//					pAreaJuego.repaint( oj.getX(), oj.getY(), oj.getAnchuraObjeto(), oj.getAlturaObjeto() );
				}
			});
		} catch (Exception e) {
		}
	}
	
	public void addObjeto( final ObjetoGrafico oj, Point p ) {
		oj.setLocation(p);
		addObjeto(oj);
	}
	
	
	public void removeObjeto( final ObjetoGrafico oj ) {
		try {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					layeredPane.remove( oj );
					layeredPane.repaint( oj.getX(), oj.getY(), oj.getAnchuraObjeto(), oj.getAlturaObjeto() );
					layeredPane.validate();  
				}
			});
		} catch (Exception e) {
		}
	}
	
	public int getAltoPanelGrafico() {
		return layeredPane.getHeight();
	}
	
	public int getAnchoPanelGrafico() {
		return layeredPane.getWidth();
	}
	
	public void setFondoAnimado( ObjetoGrafico og1, ObjetoGrafico og2, double pixDespAIzqda ) {
		// Quitar posibles fondos anteriores
		for (Component c : layeredPane.getComponentsInLayer( CAPA_FONDO )) {
			layeredPane.remove( c );
		}
		fondo1 = og1;
		fondo2 = og2;
		og1.setLocation( 0, 0 );
		og2.setLocation( og1.getWidth() - PX_SOLAPE_FONDOS, 0 ); 
		coorX1 = 0;
		coorX2 = og1.getWidth() - PX_SOLAPE_FONDOS;
		layeredPane.add( og1, CAPA_FONDO );
		layeredPane.add( og2, CAPA_FONDO );
		layeredPane.repaint();
		fondoAnimado = true;
		fondoRodando = false;
		this.pixDespAIzqda = pixDespAIzqda;
		if (hilo==null) { hilo = new HiloAnimacion(); hilo.start(); }
	}
		// Atributos de animaci�n de fondo:
		private boolean fondoAnimado = false;
		private boolean fondoRodando = true;
		private double pixDespAIzqda = 0D;
		private double coorX1 = 0D;
		private double coorX2 = 0D;
		private ObjetoGrafico fondo1 = null;
		private ObjetoGrafico fondo2 = null;
		
	public void rodarFondoAnimado( boolean seguir ) {
		fondoRodando = seguir;
	}
	
	public void finish() {
		if (hilo!=null) { hilo.interrupt(); }
		dispose();
	}
	
	public void borraEventos() {
		eventosVentana.clear();
	}
	
	public boolean isClosed() {
		return !isVisible();
	}
	
	public void clearObjetos() {
		try {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					for (Component c : layeredPane.getComponentsInLayer( JLayeredPane.DEFAULT_LAYER )) {
						layeredPane.remove( c );
					}
					layeredPane.repaint();
				}
			});
		} catch (Exception e) {
		}
	}
}
	

	
	
	



	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


