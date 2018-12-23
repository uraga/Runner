package objetosJuego;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase para las llamadas de diferentes metodos para los obstaculos
 * @author JON URAGA, YERAY BELLANCO
 */
public abstract class Obstaculo {
	public abstract void actualizar(); //actualiza
	public abstract void draw(Graphics g); //pinta
	public abstract Rectangle getBound(); //rectangulo de choque
	public abstract boolean seSalePorIzquierda(); //de izq a derecha
}
