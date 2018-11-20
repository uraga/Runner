package Proyecto.ClasesBasicas;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;

import Proyecto.VentanasYEventos.Img;
import Proyecto.VentanasYEventos.ObjetoGrafico;
import Proyecto.VentanasYEventos.VentanaGrafica;







public class Obstaculo extends ObjetoPantalla{
	
	protected ArrayList<ObjetoGrafico> listaOG = new ArrayList<ObjetoGrafico>();
	protected ObjetoGrafico cactus;
	protected long msgRojo;
	protected long msgUltRojo;
	protected Icon estaEnRojo = null;   
	protected boolean fuera = false;
	protected static long MSG_ROJO = 500L;
	

	public Obstaculo( int numCol, int posX, int posY, VentanaGrafica ventana ) {
		super( posX, 0, JuegoRunner.PX_ANCHO_UD, 0, ventana );
		cactus = new ObjetoGrafico( "ladrillo.png", true, JuegoRunner.PX_ANCHO_UD, JuegoRunner.PX_ALTO_UD );
		int alto = 100;
		do {
			ObjetoGrafico og = new ObjetoGrafico( "ladrillo.png" , true, JuegoRunner.PX_ANCHO_UD, JuegoRunner.PX_ALTO_UD );
			ventana.addObjeto( og, new Point( posX, posY));
			listaOG.add( og );
			og.setName( "Abajo-" + numCol + "->" + posY );
			posY += JuegoRunner.PX_PEGADOS_COLUMNA;
		} while (posY < ventana.getAltoPanelGrafico());
	}
	
	public ArrayList<ObjetoGrafico> getObjetosGraficos() {
		return listaOG;
	}
	
	

	@Override
	public void quitar() {
		for (int i=0; i<listaOG.size(); i++) {
			ObjetoGrafico og = (ObjetoGrafico) listaOG.get(i);
			ventana.removeObjeto(og);
			fuera = true;
		}
		if (estaEnRojo != null) {
			estaEnRojo = null;
		}
	}

	@Override
	public void mover() {
		long tiempoCambio = System.currentTimeMillis() - tiempoMovimiento;
		tiempoMovimiento = System.currentTimeMillis();
		posX = posX - tiempoCambio * JuegoRunner.getVelAvance() / 1000D;
		int posXNueva = (int) Math.round( posX );
		if (((ObjetoGrafico)listaOG.get(0)).getX() != posXNueva) {
			if (posXNueva < -JuegoRunner.PX_ANCHO_UD){ 
				quitar();
			} else {  // Si no, se mueve
				for (int i=0; i<listaOG.size(); i++) {
					ObjetoGrafico og = (ObjetoGrafico) listaOG.get(i);
					og.setLocation( posXNueva, og.getY() );
				}
			}
		}
		if (estaEnRojo != null) {
			long msg = System.currentTimeMillis() - msgUltRojo;
			msgUltRojo = System.currentTimeMillis();
			msgRojo -= msg;
			if (msgRojo <= 0) {
				((ObjetoGrafico) listaOG.get(0)).setIcon( estaEnRojo );
				estaEnRojo = null;
			}
		}
	}
	public void tocar() {
		if (estaEnRojo == null) {
			estaEnRojo = ((ObjetoGrafico) listaOG.get(0)).getIcon(); 
			((ObjetoGrafico) listaOG.get(0)).setIcon( cactus.getIcon() );
			msgRojo = MSG_ROJO;
			msgUltRojo = System.currentTimeMillis();
		} else { 
			msgRojo = MSG_ROJO;
			msgUltRojo = System.currentTimeMillis();
		}
	}
	
	public boolean estoyFuera() {
		return fuera;
	}

	
	
	
	

}
