package Proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;



public class Usuario {

	private static String username;
	private static String password;
	private static String email;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean probarPass() {
		username = VentanaUsuario.intrUsuario.getText();
		password = VentanaUsuario.intrContrasena.getText();
		if(username.equals(username)&&password.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

			
	}


