package Proyecto.ClasesBasicas;

import java.awt.Point;
import java.io.Serializable;

import Proyecto.VentanasYEventos.EventoVentana;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.TeclaPulsada;
import Proyecto.VentanasYEventos.VentanaGrafica;



public class DeustoRunner extends ObjetoPantalla implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double VEL_SALTO = 300;
	protected double velHaciaArriba = 0D;
	protected boolean estoyMuerto = false;
	protected ObjetoGrafico og;

	public DeustoRunner( int posX, int posY, VentanaGrafica ventana ) {
		super( posX, posY, JuegoRunner.PX_ANCHO_UD*2, JuegoRunner.PX_ALTO_UD*2, ventana );
		og = new ObjetoGrafico("carrito.png", true, JuegoRunner.PX_ANCHO_UD*2, JuegoRunner.PX_ALTO_UD*2);
		og.setName( "Dino" );
		og.setRectanguloDeChoque( JuegoRunner.PX_ANCHO_UD/2, JuegoRunner.PX_ALTO_UD/2, og.getAnchuraObjeto()-JuegoRunner.PX_ANCHO_UD/2, og.getAlturaObjeto()-JuegoRunner.PX_ALTO_UD/2 );
		ventana.addObjeto( og, new Point( posX, posY ) );
	}
	
	public ObjetoGrafico getOg() {
		return og;
	}
	
	public void setOg(ObjetoGrafico og) {
		this.og = og;
	}

	public void saltar() {
		velHaciaArriba = VEL_SALTO;
	}
	
	private static void controlDeJugador (EventoVentana ev, DeustoRunner dr) {
		if (ev != null) {
			if (ev instanceof TeclaPulsada) {
				dr.saltar();
			}
		}
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
			velHaciaArriba = velHaciaArriba + JuegoRunner.ACEL_CAIDA * tiempoCambio / 1000D;  // cambio de velocidad con la aceleraci�n
			posY = posY - tiempoCambio * velHaciaArriba / 1000D;
			int posYNueva = (int) Math.round( posY );
//			if (og.getY() != posYNueva) {
//				if (posYNueva > JuegoRunner.PX_ALTO_VENT-(JuegoRunner.PX_ALTO_UD/2) ||
//					posYNueva < - 3*JuegoRunner.PX_ALTO_UD/2){  // Si se ha salido por abajo se quita (se muere)
//					muero();
//					quitar();
//				} else {  // Si no, se mueve
			og.setLocation( og.getX(), posYNueva );
//				}
//			}
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
