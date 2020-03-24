package entidades;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/*VARIABLES*/
	
	private static final long serialVersionUID = 1L;
	private String user, password, nombre, mail;
	
	/*CONSTRUCTORES*/
	
	public Usuario() {}
	
	public Usuario(String u, String p, String n, String m){
		this.user = u;
		this.password = p;
		this.nombre = n;
		this.mail = m;
	}

	/*METODOS*/
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}