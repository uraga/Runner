package Proyecto.ClasesBasicas;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import Proyecto.VentanasYEventos.EventoVentana;
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
	int x = JuegoRunner.PROTA_X;
    int y = 350;
	int x1 = 0;
    int y1 = 0;
	private VentanaGrafica ventana;
    boolean saltando = false;
    boolean sube = false;
    boolean baja = false;

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
			if (x + x1 > 0 && x + x1 < ventana.getWidth() - ancho)
				x = x + x1;
	                if(saltando)
	                {
	                    if(y == 350) //el personaje esta en el suelo
	                    		{sube = true;
	                        y1 = -10;
	                        baja = false;}
	                    if(y == 200) //el personale llego al limite del salto
	                        {baja = true;
	                        y1 = 10;
	                        sube = false;
	                        }
	                    if(sube)
	                        {
	                            y = y + y1;
	                        }
	                    if(baja)
	                        {
	                            y = y + y1;
	                            if(y == 350)
	                            {
	                                saltando = false;
	                            }
	                        }
	                }
	        }
		}
	
	public void keyPressed(KeyEvent e) {
		
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                saltando=true;
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
