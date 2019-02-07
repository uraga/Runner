package Proyecto.VentanasYEventos;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Gestion utils
 * @author JON URAGA, YERAY BELLANCO
 *
 */

public class Img {
	
	public static BufferedImage getResouceImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
}
