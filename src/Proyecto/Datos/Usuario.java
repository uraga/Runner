package Proyecto.Datos;

import java.io.Serializable;

import Proyecto.VentanasYEventos.VentanaUsuario;
/**
 * GESTION BD CON USUARIO
 * @author JON URAGA, YERAY BELLANCO
 *
 */
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String usuario;
	public String contrasena;
	public String email;
	public String nombre;
	public String apellidos;
	public long telefono;
	public int nivel;
	public long record;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String username) {
		this.usuario = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String password) {
		this.contrasena = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getRecord() {
		return record;
	}
	
	public void setRecord( long record ) {
		this.record = record;
	}
	
	public Usuario(String usuario, String contrasena, String nombre, String apellidos, long telefono, TipoUsuario tipo, String email, int nivel) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.nivel = nivel;
	}
	
	public Usuario (String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public Usuario ( String usuario, int nivel, long record ) {
		this.usuario = usuario;
		this.nivel = nivel;
		this.record = record;
	}
	
	@Override
	public String toString() {
		return "Usuario: " + usuario + "\nNivel: " + nivel + "\nNombre: " + nombre + " " + apellidos + 
			"\nTeléfono: " + telefono + "\nTipo de usuario: " + 
			"\nEmail: " + email;
	}
			
	}


