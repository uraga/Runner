package Proyecto.ClasesBasicas;

import java.util.ArrayList;

import javax.swing.Icon;

import Proyecto.VentanasYEventos.Img;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.VentanaGrafica;





public class Obstaculo extends ObjetoPantalla{
	
	protected ArrayList<ObjetoGrafico> listaOG = new ArrayList<ObjetoGrafico>();
	protected ObjetoGrafico cactus;
	protected Icon estaEnRojo = null;   
	protected boolean fuera = false;
	

	public Obstaculo( int numCol, int posX, VentanaGrafica ventana ) {
		super( posX, 0, JuegoRunner.PX_ANCHO_UD, 0, ventana );
		cactus = new ObjetoGrafico( "ladrillo.png",
				true, JuegoRunner.PX_ANCHO_UD, JuegoRunner.PX_ALTO_UD );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void quitar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean estoyFuera() {
		return fuera;
	}

	
	
	
	

}
