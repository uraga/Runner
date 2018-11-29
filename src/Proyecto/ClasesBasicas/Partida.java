package Proyecto.ClasesBasicas;

import java.util.Date;

import Proyecto.Datos.Usuario;

public class Partida {
	public Usuario usuario;
	public Date fechaPartida;
	public int puntuacion;
	public int monedasConseguidas;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getFechaPartida() {
		return fechaPartida;
	}
	
	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public int getMonedasConseguidas() {
		return monedasConseguidas;
	}
	
	public void setMonedasConseguidas(int monedasConseguidas) {
		this.monedasConseguidas = monedasConseguidas;
	}
	
	public Partida(Usuario usuario, Date fechaPartida, int puntuacion, int monedasConseguidas) {
		super();
		this.usuario = usuario;
		this.fechaPartida = fechaPartida;
		this.puntuacion = puntuacion;
		this.monedasConseguidas = monedasConseguidas;
	}
	
	
}
