package Proyecto.VentanasYEventos;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animacion {

	private List<BufferedImage> lista;
	private long deltaTime;
	private int currentFrame = 0;
	private long previousTime;

	public Animacion(int deltaTime) {
		this.deltaTime = deltaTime;
		lista = new ArrayList<BufferedImage>();
		previousTime = 0;
	}

	public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			currentFrame++;
			if (currentFrame >= lista.size()) {
				currentFrame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

	public void addFrame(BufferedImage image) {
		lista.add(image);
	}

	public BufferedImage getFrame() {
		return lista.get(currentFrame);
	}

}
