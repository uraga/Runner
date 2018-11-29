package Proyecto.ClasesBasicas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import Proyecto.VentanasYEventos.EventoTeclado;
import Proyecto.VentanasYEventos.EventoVentana;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.VentanaGrafica;

public class JuegoRunner {
	
	public static final int PX_ALTO_VENT = 550;
	public static final int PX_ANCHO_VENT = 1000;
	public static final int PX_ALTO_R = 81;
	public static final int PX_ANCHO_R = 60;
	public static final int PROTA_X = 50;  
	public static final double ACEL_CAIDA = -650.0;  
	public static final double VEL_SALTO = 300;    
	public static final int NUM_VIDAS_EXTRA = 0;   
	public static final float PROBABILIDAD_MONEDAS = 0.6F;   
	public static final int PUNTOS_POR_MONEDA = 5;  
	public static final long TIEMPO_AVANCE_NIVEL = 10000;
	public static final double VELOCIDAD_INICIAL = 180;  // pix/seg
	public static final int HUECO_ENTRE_OBSTACULOS = 250;  // pixels de alto de hueco
	public static final double INC_VELOCIDAD = 6;  // +pix/seg
	public static final double MAX_VELOCIDAD = 500;  // +pix/seg
	private static double velAvance = VELOCIDAD_INICIAL;
	private static int huecoEntreObstaculos = HUECO_ENTRE_OBSTACULOS;
	private static int numVidas;
	
	public static int gethuecoEntreObstaculos() {
		return huecoEntreObstaculos;
	}
	
	public static void sethuecoEntreObstaculos(int huecoEntreObstaculos) {
		JuegoRunner.huecoEntreObstaculos = huecoEntreObstaculos;
	}
	
	public static double getVelAvance() {
		return velAvance;
	}
	
	public static void setVelAvance( double nuevaVelAvance ) {
		velAvance = nuevaVelAvance;
	}
	
	private static void controlDeJugador( EventoVentana ev, DeustoRunner dr ) {
		if (ev != null) {
			if ( ev instanceof EventoTeclado) {
				dr.saltar();
			}
		}
	}
	
	
	private static boolean moverYQuitar( VentanaGrafica v, DeustoRunner dr, ArrayList<Obstaculo> cactus ) {
		dr.mover();
		for (int i=0; i<cactus.size(); i++) {
			cactus.get(i).mover();
		}
		if (!cactus.isEmpty() && 
				((Obstaculo)cactus.get(0)).estoyFuera()) {   // M�todo solo de columna
			cactus.remove(0).quitar();
			cactus.remove(0).quitar();
			return true;
		}
		return false;
	}
	
	private static int comprobarChoque( DeustoRunner dr, ArrayList<Obstaculo> obstaculos ) {
		for (int i=0; i<obstaculos.size(); i++) {
			Obstaculo c = (Obstaculo) obstaculos.get(i);
			for (int j=0; j<c.getObjetosGraficos().size(); j++) {
				ObjetoGrafico o = (ObjetoGrafico) c.getObjetosGraficos().get(j);
				if (dr.getOg().chocaCon(o, 5)) {  // 5 pxs de margen 
					dr.chocar( c );
				}
			}
		}
		return 0;
	}
	
	private static Monedas comprobarChoqueMonedasYQuitar( DeustoRunner dr, ArrayList<Monedas> monedas, ArrayList<Obstaculo> obstaculo ) {
		if (!monedas.isEmpty() && (monedas.get(0)).estoyFuera()) {
			monedas.remove(0).quitar();
		}
		Monedas ret = null;
		for (Monedas m : monedas) {
			ObjetoGrafico o = m.getObjetoGrafico();
			if (dr.getOg().chocaCon(o, 0)) { 
				ret = m;
			}
		}
		return ret;
	}
	
	@SuppressWarnings("unused")
	private static void Juego() {
		
		VentanaGrafica v = new VentanaGrafica(PX_ANCHO_VENT, PX_ALTO_VENT, true, false, "JuegoRunner" );
		v.setFondoAnimado(new ObjetoGrafico("dunas-del-desierto.jpg", true ), new ObjetoGrafico("dunas-del-desierto.jpg", true ), 1 );
		EventoVentana ev = null;
		int posY = 350;
		do {
			v.rodarFondoAnimado( true );
			ev = null;
			v.clearObjetos();
			DeustoRunner dr = new DeustoRunner( PROTA_X, posY, v );
			// Bucle principal de juego
				while (!v.isClosed() && !dr.estoyMuerto()) {
				ev = v.readEvento( 20 );  // Lee evento o espera 20 msg  (algo movi�ndose)
				controlDeJugador( ev, dr );
			}
			v.rodarFondoAnimado( false );
			ev = null; 
			ev = v.readEvento( 10000 );
		}while (ev != null && (ev instanceof EventoTeclado));
		v.finish();	
	}
	
	public static void main(String[] args) {
		Juego();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
