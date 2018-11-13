package Proyecto;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String contraseña;
	private String email;
	private String nombre;
	private String apellidos;
	private long telefono;
	private int nivel;
	private TipoUsuario tipo;
	
	
	
	
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

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return usuario;
	}

	public void setUsername(String username) {
		this.usuario = username;
	}

	public String getPassword() {
		return contraseña;
	}

	public void setPassword(String password) {
		this.contraseña = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean probarPass() {
		usuario = VentanaUsuario.intrUsuario.getText();
		contraseña = VentanaUsuario.intrContrasena.getText();
		if(usuario.equals(usuario)&&contraseña.equals(contraseña)) {
			return true;
		}
		else {
			return false;
		}
	}

			
	}


