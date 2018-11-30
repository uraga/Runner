package Proyecto.ClasesBasicas;

import java.awt.Point;

import java.io.Serializable;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.VentanaGrafica;


public class DeustoRunner extends ObjetoPantalla implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static double velHaciaArriba = 0D;
	protected boolean estoyMuerto = false;
	protected ObjetoGrafico og;
	int ancho = JuegoRunner.PX_ANCHO_R*2;
	int alto = JuegoRunner.PX_ALTO_R*2;
	int x1 = 0;
    int y1 = 0;
	private VentanaGrafica ventana;
    

	public DeustoRunner( int posX, int posY, VentanaGrafica ventana ) {
		super( posX, posY, JuegoRunner.PX_ANCHO_R*2, JuegoRunner.PX_ALTO_R*2, ventana );
		og = new ObjetoGrafico( "clipart2103535.png", true, JuegoRunner.PX_ANCHO_R*2, JuegoRunner.PX_ALTO_R*2);
		og.setName( "Dino" );
		og.setRectanguloDeChoque( JuegoRunner.PX_ANCHO_R/2, JuegoRunner.PX_ALTO_R/2, og.getAnchuraObjeto()-JuegoRunner.PX_ANCHO_R/2, og.getAlturaObjeto()-JuegoRunner.PX_ALTO_R/2 );
		ventana.addObjeto( og, new Point( posX, posY ) );
	}
	
	

	public ObjetoGrafico getOg() {
		return og;
	}
	
	public static void saltar() {
		 velHaciaArriba = JuegoRunner.VEL_SALTO;
	}

	@Override
	public void quitar() {
		muero();
		ventana.removeObjeto(og);
	}
	
	public boolean estoyMuerto() {
		return this.estoyMuerto;
	}
	
	public void muero() {
		estoyMuerto = true;
	}
	
	@Override
	public void mover() {
		if (!estoyMuerto) {
			long tiempoCambio = System.currentTimeMillis() - tiempoMovimiento;
			tiempoMovimiento = System.currentTimeMillis();
			velHaciaArriba = velHaciaArriba + JuegoRunner.ACEL_CAIDA * tiempoCambio / 1000D;
			posY = posY - tiempoCambio * velHaciaArriba / 1000D;
			if (posX + x1 > 0 && posX + x1 < ventana.getWidth() - ancho) 
				posX = posX + x1;
				if (posY > 350) {
					posY = 350;
				}
		}
	}
	
	public void parar() {
		velHaciaArriba = 0;
	}
	
	public void chocar( ObjetoPantalla of ) {
		if (of instanceof Obstaculo)
			((Obstaculo)of).tocar();
	}
	
	
}
