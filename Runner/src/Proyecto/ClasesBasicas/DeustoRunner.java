package Proyecto.ClasesBasicas;

import java.io.Serializable;

import Proyecto.VentanasYEventos.EventoVentana;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.TeclaPulsada;
import Proyecto.VentanasYEventos.VentanaGrafica;


public class DeustoRunner extends ObjetoPantalla implements Serializable {

public DeustoRunner(int posX, int posY, int ancho, int alto, VentanaGrafica ventana) {
		super(posX, posY, ancho, alto, ventana);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double VEL_SALTO = 300;
	protected double velHaciaArriba = 0D;
	protected boolean estoyMuerto = false;
	protected ObjetoGrafico og;
	
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
		ventana.remove(og);
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean estoyMuerto() {
		return this.estoyMuerto;
	}
	
	public void muero() {
		estoyMuerto = true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
