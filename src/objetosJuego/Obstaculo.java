package objetosJuego;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstaculo {
	public abstract void actualizar();
	public abstract void draw(Graphics g);
	public abstract Rectangle getBound();
	public abstract boolean seSalePorIzquierda();
}
