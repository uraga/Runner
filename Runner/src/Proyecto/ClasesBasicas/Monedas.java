package Proyecto.ClasesBasicas;

import java.awt.Point;
import java.util.Random;

import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.VentanaGrafica;




public class Monedas extends ObjetoPantalla{
	
	protected ObjetoGrafico og;
	protected boolean estoyMuerto = false;
	protected boolean parado = false;
	public static final int PX_ALTO_BONUS = 40;
	public static final int PX_ANCHO_BONUS = 30;
	private static Random r = new Random();

	public Monedas(int posX, int posY, VentanaGrafica ventana) {
		super(posX, posY, PX_ANCHO_BONUS*2, PX_ALTO_BONUS*2, ventana);
		og = new ObjetoGrafico( "Moneda.png" , true, PX_ANCHO_BONUS*2, PX_ALTO_BONUS*2);
		og.setName( "moneda" );
		og.setRectanguloDeChoque( PX_ANCHO_BONUS/2, PX_ALTO_BONUS/2, og.getAnchuraObjeto()-PX_ANCHO_BONUS/2, og.getAlturaObjeto()-PX_ALTO_BONUS/2 );
		ventana.addObjeto( og, new Point( posX, posY ) );
	}
	
	public ObjetoGrafico getObjetoGrafico() {
		return og;
	}

	@Override
	public void quitar() {
		muero();
		ventana.removeObjeto(og);
		
	}
	
	public void muero() {
		estoyMuerto = true;
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean estoyFuera() {
		return (posX < ancho/2);
	}
	
	


}
