package Proyecto.ClasesBasicas;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstaculo {
	public abstract void actualizar();
	public abstract void dibujar(Graphics g);
	public abstract Rectangle getBorde();
	public abstract boolean salePorIzquierda();
}
