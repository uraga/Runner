package Proyecto.VentanasYEventos;


public class Img {
	public static java.net.URL getURLRecurso( String nomRecImg ) {
		java.net.URL recurso = null;
		try {
			recurso = Img.class.getResource( nomRecImg ).toURI().toURL();
		} catch (Exception e) {
			System.err.println( "Recurso incorrecto: " + nomRecImg + " no existe en " + Img.class.getPackage() );
		}
		return recurso;
	}

}
