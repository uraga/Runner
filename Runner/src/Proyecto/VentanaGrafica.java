package Proyecto;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;



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

}
