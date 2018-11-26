package Proyecto.ClasesBasicas;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import Proyecto.VentanasYEventos.EventoTeclado;
import Proyecto.VentanasYEventos.EventoVentana;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.TeclaPulsada;
import Proyecto.VentanasYEventos.VentanaGrafica;





public class JuegoRunner {
	
	public static final int PX_ALTO_VENT = 550;
	public static final int PX_ANCHO_VENT = 1000;
	public static final int PX_ALTO_R = 81;
	public static final int PX_ANCHO_R = 60;
	public static final int PX_PEGADOS_COLUMNA = 60;
	public static final int PROTA_X = 50;  
	public static final double ACEL_CAIDA = -650.0;  
	public static final double VEL_SALTO = 300;    
	public static final int NUM_VIDAS_EXTRA = 0;   
	public static final float PROBABILIDAD_MONEDAS = 0.6F;   
	public static final int PUNTOS_POR_MONEDA = 5;  
	public static final long TIEMPO_AVANCE_NIVEL = 10000;
	public static final double INI_VELOCIDAD_POR_NIVEL = 180;  // pix/seg
	public static final long INI_TIEMPO_OBS_POR_NIVEL = 2000;  // msg
	public static final int INI_HUECO_ENTRE_OBSTACULOS = 250;  // pixels de alto de hueco
	public static final double INC_VELOCIDAD_POR_NIVEL = 6;  // +pix/seg
	public static final long  INC_TIEMPO_COLS_POR_NIVEL = -33;  // msg
	public static final int INC_HUECO_ENTRE_OBSTACULOS = -4;  // +pixels de alto de hueco
	public static final double MAX_VELOCIDAD_POR_NIVEL = 360;  // +pix/seg
	public static final long MIN_TIEMPO_COLS_POR_NIVEL = 1000;  // msg
	public static final int MIN_HUECO_ENTRE_OBSTACULOS = 140;  // +pixels de alto de hueco
	private static double velAvance = INI_VELOCIDAD_POR_NIVEL;
	private static long tiempoEntreObstaculos = INI_TIEMPO_OBS_POR_NIVEL;
	private static int huecoEntreObstaculos = INI_HUECO_ENTRE_OBSTACULOS;
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
	
	public static long getTiempoEntreObs() {
		return tiempoEntreObstaculos;
	}
	
	public static void setTiempoEntreObs( long nuevoTiempo ) {
		tiempoEntreObstaculos = nuevoTiempo;
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
	
	private static Monedas comprobarChoqueBonusYQuitar( DeustoRunner dr, ArrayList<Monedas> monedas, ArrayList<Obstaculo> obstaculo ) {
		if (!monedas.isEmpty() && 
				(monedas.get(0)).estoyFuera()) {
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
	
	private static void crearNuevosObstaculos( VentanaGrafica v, int huecoEn, int numObstaculo, ArrayList<Obstaculo> obstaculo ) {
		Obstaculo ob = new Obstaculo( numObstaculo, huecoEn + gethuecoEntreObstaculos(), v.getAnchoPanelGrafico(), v );
		obstaculo.add( ob );
	}
	
	
	@SuppressWarnings("unused")
	private static void Juego() {
		VentanaGrafica v = new VentanaGrafica(PX_ANCHO_VENT, PX_ALTO_VENT, true, false, "JuegoRunner" );
		v.setFondoAnimado(new ObjetoGrafico("UD-roller.jpg", true ),
				new ObjetoGrafico("UD-roller.jpg", true ), 1 );
		int posY = (v.getAltoPanelGrafico() - PX_ALTO_R) / 2;   // C�lculo altura del prota en la mitad de la ventana
		Random r = new Random();
		EventoVentana ev = null;
		int nivel = 1;
		do {
			v.rodarFondoAnimado( true );
			numVidas = NUM_VIDAS_EXTRA;
			ev = null;
			v.clearObjetos();
			DeustoRunner dr = new DeustoRunner( PROTA_X, posY, v );
			ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
			ArrayList<Monedas> monedas = new ArrayList<Monedas>();
			boolean puedeHaberMonedas = false;  
			int puntosPorMonedas = 0;
			long tiempoJuego = System.currentTimeMillis();
			long tiempoUltimoObstaculo = tiempoJuego - tiempoEntreObstaculos;
			int numObstaculo = 0;
			int ObstaculosPasados = 0;
			long tiempoUltimoAvanceNivel = tiempoJuego;
			int ObstaculoChoque = -1;
			// Bucle principal de juego
			while (!v.isClosed() && !dr.estoyMuerto()) {
				System.out.println( System.currentTimeMillis() );
				// ============================
				// 1. Input
				// ============================
				long tiempoActual = System.currentTimeMillis();
				ev = v.readEvento( 20 );  // Lee evento o espera 20 msg  (algo movi�ndose)
				
				// ============================
				// 2. Update
				// ============================
					// 1. Chequear control del escudo
				controlDeJugador( ev, dr );
					// 2. Mover escudo y columnas y quitar columnas si se salen
				if (moverYQuitar( v, dr, obstaculos )) {
					ObstaculosPasados++;
				}
				for (Monedas m : monedas) m.mover();
					// 3. Ver si hay choque del escudo
				int chocan = comprobarChoque( dr, obstaculos );
				if (chocan == 1) {  // Choque vertical: se quitan vidas
					dr.parar();
					if (ObstaculoChoque != numObstaculo) {
						ObstaculoChoque = numObstaculo;
						numVidas--;
						if (numVidas<=1) {
							dr.muero();
						} 
					}
				} else if (chocan == 2) {  // Choque no vertical: muerte segura
					dr.muero();
				}
					// 4. Ver si hay captura de bonus
				Monedas choqueBonus = comprobarChoqueBonusYQuitar( dr, monedas, obstaculos );
				if (choqueBonus != null) {  // Choque con bonus
					monedas.remove( choqueBonus );
					choqueBonus.muero();
					choqueBonus.quitar();
					puntosPorMonedas += PUNTOS_POR_MONEDA;
				}
					// 5. Ver si salen nuevas columnas
				if (tiempoActual - tiempoUltimoObstaculo > getTiempoEntreObs()) {  // Crear nuevas columnas
					// Calcular sitio del hueco en random
					int huecoEn = r.nextInt( v.getAltoPanelGrafico() - gethuecoEntreObstaculos() - 20 ) + 10;
					numObstaculo++;
					tiempoUltimoObstaculo = tiempoActual;
					crearNuevosObstaculos( v, huecoEn, numObstaculo, obstaculos );
					puedeHaberMonedas = true;  // preparamos para siguiente bonus
				}
					// 6. Ver si salen nuevos bonus (aleatorios y entre columnas)
				if (puedeHaberMonedas) {
					if (tiempoActual - tiempoUltimoObstaculo > getTiempoEntreObs()/2 ) {  
						// En la mitad del tiempo entre columnas...
						// Ver si pasa el aleatorio (20%)
						if (r.nextFloat() < PROBABILIDAD_MONEDAS ) {
							monedas.add( new Monedas( v.getAnchoPanelGrafico(), r.nextInt( v.getAltoPanelGrafico() ), v) );
						}
					}
				}
					// 7. Posible avance nivel
//				if (tiempoActual - tiempoUltimoAvanceNivel > TIEMPO_AVANCE_NIVEL) {
//					tiempoUltimoAvanceNivel = tiempoActual;
//					incrementaNivel();
//					nivel++;
//					v.showMessage( "Avanzas de nivel!!! Nivel " + nivel );
//				}

					// 8. Rotaci�n bonus
//				for (Monedas b : monedas) b.getObjetoGrafico().incRotacion( 0.06 );
				
				// ============================
				// 3. Render
				// ============================
//				v.repaint();   // Realmente como est� implementado en swing no har�a falta porque se va actualizando directamente (JLabel, setLocation)
				
			}
			// ============================
			// 4.1. Cierre de cada gameplay
			// ============================
			v.rodarFondoAnimado( false );
			
//			v.showMessage( "Se acab�!!!!  Puntuaci�n = " + (columnasPasadas + puntosPorBonus) );
//			v.esperaUnRato( 2000 );
//			v.borraEventos();
//			v.showMessage( "<html>Pulsa antes de 10\" si quieres seguir jugando<br> (tu puntuaci�n empezar� en 0 de nuevo)</html>" );
//			ev = null; 
//			ev = v.readEvento( 10000 );
		}while (ev != null && (ev instanceof EventoTeclado));
		v.finish();	
	}
	
	public static void main(String[] args) {
		Juego();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
