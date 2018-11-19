package Proyecto.ClasesBasicas;

import Proyecto.Datos.Usuario;

public class Partida {
	public Usuario usuario;
	public long fechaPartida;
	public int puntuacion;
	public int monedasConseguidas;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public long getFechaPartida() {
		return fechaPartida;
	}
	
	public void setFechaPartida(long fechaPartida) {
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
	
	public Partida(Usuario usuario, long fechaPartida, int puntuacion, 
			int monedasConseguidas) {
		super();
		this.usuario = usuario;
		this.fechaPartida = fechaPartida;
		this.puntuacion = puntuacion;
		this.monedasConseguidas = monedasConseguidas;
	}
	
	
}
