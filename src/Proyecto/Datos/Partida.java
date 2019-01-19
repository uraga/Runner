package Proyecto.Datos;

import java.util.Date;

import objetosJuego.Personaje;

public class Partida {
	
	private Personaje personaje;
	private int puntuacion;
	private Usuario usuario;
	private Date fecha;
	
	public Partida(int puntuacion) {
		this.puntuacion = personaje.puntuacion;
		// TODO usuario = usuario actual que haya jugado
		// TODO fecha = dd/mm/aaaa en la que se ha jugado
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void doblarPuntuacion() {
		this.puntuacion = puntuacion * 2;
	}
	
	public void triplicarPuntuacion() {
		this.puntuacion = puntuacion * 3;
	}
	
	

}
