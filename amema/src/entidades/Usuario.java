package entidades;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/* VARIABLES */
	
	private static final long serialVersionUID = 1L;
	private String usuario, password, nombreyapellido;
	
	
	public Usuario() {}
	
	public Usuario(String usuario, String password, String nombreyapellido){
		this.usuario = usuario;
		this.password = password;
		this.nombreyapellido = nombreyapellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreyapellido() {
		return nombreyapellido;
	}

	public void setNombreyapellido(String nombreyapellido) {
		this.nombreyapellido = nombreyapellido;
	}
	
	
	
	
	
}
