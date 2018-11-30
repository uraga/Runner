package Proyecto.ClasesBasicas;



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
	public static final double ACEL_CAIDA = -325.0;  
	public static final double VEL_SALTO = 150;            
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
					int puntuacion = 0;
					for(puntuacion=0; puntuacion >= 0; puntuacion++ ) {
						System.out.println("Puntuación: " + puntuacion);
						delayTiempo();
					}
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

	private static void delayTiempo() {
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			
		}
		
	}

}
