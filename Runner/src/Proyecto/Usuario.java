package Proyecto;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String usuario;
	public String contrasena;
	public String email;
	public String nombre;
	public String apellidos;
	public long telefono;
	public int nivel;
	public TipoUsuario tipo;
	
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
		return contrasena;
	}

	public void setPassword(String password) {
		this.contrasena = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario(String usuario, String contrasena, String nombre,
			String apellidos, long telefono, TipoUsuario tipo,
			String email, int nivel) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.tipo = tipo;
		this.email = email;
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return "Usuario: " + usuario + "\nNivel: " + nivel + "\nNombre: " + nombre + " " + apellidos + 
			"\nTeléfono: " + telefono + "\nTipo de usuario: " + tipo +
			"\nEmail: " + email;
	}

	public boolean probarPass() {
		usuario = VentanaUsuario.intrUsuario.getText();
		contrasena = VentanaUsuario.intrContrasena.getText();
		if(usuario.equals(usuario)&&contrasena.equals(contrasena)) {
			return true;
		}
		else {
			return false;
		}
	}

			
	}


