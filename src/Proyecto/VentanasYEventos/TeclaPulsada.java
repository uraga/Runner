package Proyecto.VentanasYEventos;

import java.awt.event.KeyEvent;

public class TeclaPulsada implements EventoTeclado{
	
	private KeyEvent ke;
	public TeclaPulsada(KeyEvent ke) {
		this.ke = ke;
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return ke.getWhen();
	}

	@Override
	public int getCodigoTecla() {
		// TODO Auto-generated method stub
		return ke.getKeyCode();
	}

	@Override
	public char getCarTecla() {
		// TODO Auto-generated method stub
		return ke.getKeyChar();
	}

}
