package Proyecto.ClasesBasicas;

import java.util.ArrayList;

import Proyecto.VentanasYEventos.EventoVentana;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.TeclaPulsada;
import Proyecto.VentanasYEventos.VentanaGrafica;


public class JuegoRunner {
	
	public static final int PX_ALTO_VENT = 550;
	public static final int PX_ANCHO_VENT = 1000;
	public static final int PX_ALTO_UD = 81;
	public static final int PX_ANCHO_UD = 60;
	public static final int PX_PEGADOS_COLUMNA = 60;
	public static final int PROTA_X = 50;  
	public static final double ACEL_CAIDA = -650.0;  
	public static final double VEL_SALTO = 300;    
	public static final int NUM_VIDAS_EXTRA = 3;   
	public static final float PROBABILIDAD_BONUS = 0.2F;   
	public static final int PUNTOS_POR_BONUS = 5;  
	public static final long TIEMPO_AVANCE_NIVEL = 10000;
	public static final double INI_VELOCIDAD_POR_NIVEL = 180;  // pix/seg
	public static final long INI_TIEMPO_COLS_POR_NIVEL = 2000;  // msg
	public static final int INI_HUECO_ENTRE_COLUMNAS = 250;  // pixels de alto de hueco
	public static final double INC_VELOCIDAD_POR_NIVEL = 6;  // +pix/seg
	public static final long  INC_TIEMPO_COLS_POR_NIVEL = -33;  // msg
	public static final int INC_HUECO_ENTRE_COLUMNAS = -4;  // +pixels de alto de hueco
	public static final double MAX_VELOCIDAD_POR_NIVEL = 360;  // +pix/seg
	public static final long MIN_TIEMPO_COLS_POR_NIVEL = 1000;  // msg
	public static final int MIN_HUECO_ENTRE_COLUMNAS = 140;  // +pixels de alto de hueco
	private static double velAvance = INI_VELOCIDAD_POR_NIVEL;
	private static long tiempoEntreColumnas = INI_TIEMPO_COLS_POR_NIVEL;
	private static int huecoEntreColumnas = INI_HUECO_ENTRE_COLUMNAS;
	private static int numVidas;
	
	public static int getHuecoEntreColumnas() {
		return huecoEntreColumnas;
	}
	
	public static void setHuecoEntreColumnas(int huecoEntreColumnas) {
		JuegoRunner.huecoEntreColumnas = huecoEntreColumnas;
	}
	
	public static double getVelAvance() {
		return velAvance;
	}
	
	public static void setVelAvance( double nuevaVelAvance ) {
		velAvance = nuevaVelAvance;
	}
	
	public static long getTiempoEntreCols() {
		return tiempoEntreColumnas;
	}
	
	public static void setTiempoEntreCols( long nuevoTiempo ) {
		tiempoEntreColumnas = nuevoTiempo;
	}
	
	private static void controlDeJugador( EventoVentana ev, DeustoRunner dr ) {
		if (ev != null) {
			if ( ev instanceof TeclaPulsada) {
				dr.saltar();
			}
		}
	}
	
	
	private static boolean moverYQuitar( VentanaGrafica v, DeustoRunner ud, ArrayList<Obstaculo> cactus ) {
		ud.mover();
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
	
	private static int comprobarChoque( DeustoRunner dr, ArrayList<Obstaculo> columnas ) {
		for (int i=0; i<columnas.size(); i++) {
			Obstaculo c = (Obstaculo) columnas.get(i);
			for (int j=0; j<c.getObjetosGraficos().size(); j++) {
				ObjetoGrafico o = (ObjetoGrafico) c.getObjetosGraficos().get(j);
				if (dr.getOg().chocaCon(o, 5)) {  // 5 pxs de margen 
					// System.out.println( "CHOQUE!!!");
					dr.chocar( c );
					
				}
			}
		}
		return 0;
	}

}
