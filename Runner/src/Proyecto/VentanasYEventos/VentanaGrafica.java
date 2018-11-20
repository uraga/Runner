package Proyecto.VentanasYEventos;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;







@SuppressWarnings("serial")
public class VentanaGrafica extends JFrame {
	
	private JPanel pCristal = new JPanel();
	private ArrayList<EventoVentana> eventosVentana;
	private long tiempoAnim = 500L;
	private long tiempoFrameAnim = tiempoAnim/40L;
	private HiloAnimacion hilo = null;
	private ArrayList<Animacion> animacionesPendientes = new ArrayList<Animacion>();
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
	
	private class HiloAnimacion extends Thread {
		
	}
	
	private static class Animacion {
		
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
