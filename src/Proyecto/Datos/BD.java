package Proyecto.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;




/**
 * Gestión BD del runner.
 *
 */
public class BD {
	
	/**
	 * Conexion base de datos con SQLite
	 * @param BDRUNNER.db
	 * 
	 */
	public static Connection conexionBD ( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + "/Users/yerayb/git/Runner/src/Proyecto/Datos/BDRUNNER.db" );			
		    return con;
		} catch (ClassNotFoundException | SQLException e) {	
			return null;
		}
	}
	
	/**
	 * Crear tablas en la BD
	 */
	public static Statement CrearTablas( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  
			try {
				statement.executeUpdate("create table usuario " +"(cod_usuario string" +", nick string" +", n_saltos integer"  +", mail string " +", distancia double" +", tiempo_juego double" +", password string" +")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table mejoras " + "(cod_mejoras string" + ")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table logros " + "(cod_logros string" +", requisitos string" + ")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table personajes " + "(cod_personaje string" +", nombre string" +", salto string" +", velocidad double" +", skin string" + ")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table configuracion " + "(cod_conf string" +", idioma string" +", tamaño_ventana double" +", volumen integer" + ")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table usuario_personajes " + "(cod_usuario string REFERENCES usuario(cod_usuario)" +", cod_personaje string REFERENCES usuario(cod_personajes)" +", tamaño_ventana double" +", volumen integer" + ")");
			} catch (SQLException e) {} 
			try {
				statement.executeUpdate("create table usuario_mejoras_logros " + "(cod_usuario string REFERENCES usuario(cod_usuario)" +", cod_mejora string REFERENCES mejoras(cod_mejora)" +", cod_logro string REFERENCES logros(cod_logro) " +", fecha_logro date" + ")");
			} catch (SQLException e) {}
			return statement;
		} catch (SQLException e) {
			return null;
		}
	}
	
	
	/**
	 * BORRA todas las tablas de la BD
	 */
	
	public static Statement reiniciarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			statement.executeUpdate("drop table if exists usuario");
			statement.executeUpdate("drop table if exists mejoras");
			statement.executeUpdate("drop table if exists logros");
			statement.executeUpdate("drop table if exists personajes");
			statement.executeUpdate("drop table if exists configuracion");
			statement.executeUpdate("drop table if exists usuario_personajes");
			statement.executeUpdate("drop table if exists usuario_mejoras_logros");
			return CrearTablas( con );
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * CERRAR CONEXION BD
	 */
	public static void cerrarBD( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
		} catch (SQLException e) {
		}
	}
	
	
	//Main de prueba
	public static void main(String[] args) {
		Connection conn = conexionBD("/Users/yerayb/git/Runner/src/Proyecto/Datos/BDRUNNER.db");
		Statement stat = CrearTablas(conn);
		if (stat==null) return;
	}

}
