package entidades;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/* VARIABLES */
	
	private static final long serialVersionUID = 1L;
	private String NroUsuario, NomUs, LogIn, PassWord, DescOficina, DescFunc, Cperfil, Hab;
	
	
	public Usuario() {}
	
	public Usuario(String id, String nombre, String user, String pass, String oficina, String funcion, String perfil, String habilitado){
		this.NroUsuario = id;
		this.NomUs = nombre;
		this.LogIn = user;
		this.PassWord = pass;
		this.DescOficina = oficina;
		this.DescFunc = funcion;
		this.Cperfil = perfil;
		this.Hab = habilitado;
		}

	public String getNroUsuario() {
		return NroUsuario;
	}

	public void setNroUsuario(String nroUsuario) {
		NroUsuario = nroUsuario;
	}

	public String getNomUs() {
		return NomUs;
	}

	public void setNomUs(String nomUS) {
		NomUs = nomUS;
	}

	public String getLogIn() {
		return LogIn;
	}

	public void setLogIn(String logIn) {
		LogIn = logIn;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getDescOficina() {
		return DescOficina;
	}

	public void setDescOficina(String descOficina) {
		DescOficina = descOficina;
	}

	public String getDescFunc() {
		return DescFunc;
	}

	public void setDescFunc(String descFunc) {
		DescFunc = descFunc;
	}

	public String getCperfil() {
		return Cperfil;
	}

	public void setCperfil(String cperfil) {
		Cperfil = cperfil;
	}

	public String getHab() {
		return Hab;
	}

	public void setHab(String hab) {
		Hab = hab;
	}
	
}
