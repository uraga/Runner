package Proyecto.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestión BD
 * 
 * @author JON URAGA, YERAY BELLANCO CONEXION ESTABLECIA Y TABLAS CREADAS
 *         PENDIENTE DE FUNCIONAMIENTO
 *
 */
public class BD {

	/**
	 * Conexion base de datos con SQLite
	 * 
	 * @param BDRUNNER.db
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public static Connection conexionBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection( "jdbc:sqlite:" + "/Users/yerayb/git/Runner/src/Proyecto/Datos/BDRUNNER.db" );
			log( Level.INFO, "Conectada base de datos", null );
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			log( Level.SEVERE, "Error en conexión a base de datos", null );
			return null;
		}
	}

	

	/**
	 * Crear tablas en la BD
	 * 
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public static Statement CrearTablas(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);
			try {
				statement.executeUpdate( "create table usuario " + "(cod_usuario string" + ", usuario string" + ", nombre string" + ", apellido string" 
						+ ", email string " + ", telefono integer" + "nivel integer" + ", distancia_max double" + ", password string" + ")" );
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate("create table mejoras " + "(cod_mejoras string" + ")");
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate("create table logros " + "(cod_logros string" + ", requisitos string" + ")");
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate("create table personajes " + "(cod_personaje string" + ", nombre string"
						+ ", salto string" + ", velocidad double" + ", skin string" + ")");
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate("create table configuracion " + "(cod_conf string" + ", idioma string"
						+ ", tamaño_ventana double" + ", volumen integer" + ")");
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate(
						"create table usuario_personajes " + "(cod_usuario string REFERENCES usuario(cod_usuario)"
								+ ", cod_personaje string REFERENCES usuario(cod_personajes)"
								+ ", tamaño_ventana double" + ", volumen integer" + ")");
			} catch (SQLException e) {
			}
			try {
				statement.executeUpdate(
						"create table usuario_mejoras_logros " + "(cod_usuario string REFERENCES usuario(cod_usuario)"
								+ ", cod_mejora string REFERENCES mejoras(cod_mejora)"
								+ ", cod_logro string REFERENCES logros(cod_logro) " + ", fecha_logro date" + ")");
			} catch (SQLException e) {
			}
			return statement;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static boolean introducirUsuario ( Statement stat, String nombre, String apellido, String usuario, String password, String email, long telefono, int nivel ) {
		String sentSQL = "";
		try {
			sentSQL = " insert into usuario values( " + "'" + nombre + "', " + "'" + apellido + "', " + "'" + usuario + "', " + "'" + password + "', " +  "'" + email + "', " + telefono + ", " + nivel;
			stat.executeUpdate(sentSQL);
			return true;
		} catch ( SQLException e ) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean login ( Statement stat, Usuario user) {
		String sentSQL = "";
		try {
			sentSQL = " select * from usuario where usuario= '" + user.getUsuario() + "' and password= '" + user.getContrasena() + "'";
			ResultSet rs = stat.executeQuery(sentSQL);
			if ( rs.next() ) {
				if ( user.getContrasena().equals( rs.getString("password") ) ) {
					return true;
				} else {
					return false;
				}
			}
			rs.close();
			log( Level.INFO, "BD\t" + sentSQL, null );
			return false;
		} catch ( Exception e) {
			log( Level.SEVERE, "usuario no encontrado" , e );
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * BORRA todas las tablas de la BD
	 * 
	 * @author JON URAGA, YERAY BELLANCO
	 */

	public static Statement reiniciarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // poner timeout 30 msg
			statement.executeUpdate("drop table if exists usuario");
			statement.executeUpdate("drop table if exists mejoras");
			statement.executeUpdate("drop table if exists logros");
			statement.executeUpdate("drop table if exists personajes");
			statement.executeUpdate("drop table if exists configuracion");
			statement.executeUpdate("drop table if exists usuario_personajes");
			statement.executeUpdate("drop table if exists usuario_mejoras_logros");
			return CrearTablas(con);
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * CERRAR CONEXION BD
	 * 
	 * @author JON URAGA, YERAY BELLANCO
	 */
	public static void cerrarBD(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}
	}
	
	
	private static Logger logger = null;
	
	private static void log(Level level, String msg, Throwable excepcion) {
		if ( logger == null ) {
			logger = Logger.getLogger( BD.class.getName() );
			logger.setLevel( Level.ALL );
		}
		if ( excepcion == null ) {
			logger.log( level, msg );
		} else {
			logger.log( level, msg, excepcion );
		}
	}

	// Main de prueba
	public static void main(String[] args) {
		Connection conn = conexionBD();
		Statement stat = CrearTablas(conn);
		if (stat == null)
			return;
	}

}
