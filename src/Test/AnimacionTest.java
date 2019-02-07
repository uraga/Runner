package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Proyecto.VentanasYEventos.Animacion;
import Proyecto.VentanasYEventos.Img;

/**
 * Pruebas unitarias para la clase animacion
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class AnimacionTest {

	//Atributos
	Animacion a;
	Animacion b;
	Animacion c;
	
	@Before
	public void setUp() throws Exception {
	
		//Tres objetos tipo de animacion para añadirles un frame
		a = new Animacion(5); 
		b = new Animacion(5); 
		c = new Animacion(5); 
		
		//añadimos a cada objeto una imagen 
		a.addFrame(Img.getResouceImage("utils/cactus1.png"));
		b.addFrame(Img.getResouceImage("utils/dino2.png"));
		c.addFrame(Img.getResouceImage("utils/dino6.png"));
		
	}

	
	/**
	 * Metodo que comprueba que los Frames se añaden correctamente
	 * @author JON URAGA, YERAY BELLANCO
	 */
	@Test
	public void testAddFrame() {	
		//cada imagen tiene asociado un modelo de muestra
		//comprobamos si el metodo que nos devuelve el frame, nos da la imagen que hemos añadido en el before
		
		assertEquals(a.getFrame().getSampleModel(), Img.getResouceImage("utils/cactus1.png").getSampleModel());
		assertEquals(b.getFrame().getSampleModel(), Img.getResouceImage("utils/dino2.png").getSampleModel());
		assertEquals(c.getFrame().getSampleModel(), Img.getResouceImage("utils/dino6.png").getSampleModel());
		
		
	}

}
