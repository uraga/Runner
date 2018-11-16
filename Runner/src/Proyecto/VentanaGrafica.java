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
	
	
	public VentanaGrafica(int anchuraVent, int alturaVent, boolean cerrable, boolean tamFijo, String titulo) {
		setSize(anchuraVent, alturaVent);
		setTitle(titulo);
		setResizable(!tamFijo);
		if (cerrable)
			setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		else
			setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		
	}
	
	private class HiloAnimacion extends Thread {
		
	}

}
