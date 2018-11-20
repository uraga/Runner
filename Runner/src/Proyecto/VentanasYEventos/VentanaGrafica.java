package Proyecto.VentanasYEventos;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
