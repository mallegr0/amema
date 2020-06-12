package controladores;

import entidades.Usuario;
import util.ApplicationException;

import java.util.ArrayList;

import data.DataUsuario;


public class CtrlUsuario {
	
	/* CONSTRUCTORES */
	public CtrlUsuario() {}
	
	/* METODOS */
	
	public boolean altaUsuario(Usuario u) throws ApplicationException{
		DataUsuario du = new DataUsuario();
		return du.altaUsuario(u);
	}
	
	public boolean bajaUsuario(String user) throws ApplicationException{
		DataUsuario du = new DataUsuario();
		return du.bajaUsuario(user);
	}
	
	public boolean modificaUsuario(Usuario u) throws ApplicationException{
		DataUsuario du = new DataUsuario();
		return du.modificaUsuario(u);
	}
	
	public Usuario consultaUsuario(String user) throws ApplicationException{
		DataUsuario du = new DataUsuario();
		return du.consultaUsuario(user);
	}
	
	public ArrayList<Usuario> listarUsuarios() throws ApplicationException{
		DataUsuario du = new DataUsuario();
		return du.listarUsuarios();
	}
	
	public boolean validaUsuario(String user, String pass) throws ApplicationException{
		Usuario u = new Usuario();
		DataUsuario du = new DataUsuario();
		u = du.consultaUsuario(user);
		
		if(u != null) {
			if(u.getLogIn().compareTo(user) == 0 && u.getPassWord().compareTo(pass)== 0) { return true; }
			else { return false; }
		}
		else { return false;}
	}
	
	public boolean cambiaPassword(String user, String pass) throws ApplicationException {
		DataUsuario du = new DataUsuario();
		return du.cambiaPassword(user, pass);
		
	}
	
	public String UltimoID() throws ApplicationException {
		DataUsuario du = new DataUsuario();
		String id = du.ultimoID();
		int lID = Integer.parseInt(id);
		lID += 1;
		if(lID < 10) {
			id = "00"+Integer.toString(lID);
		}
		if(lID < 100) {
			id = "0"+Integer.toString(lID);
		}
		else{
			id = Integer.toString(lID);
		}
		return id;
		
	}

	public String consultaPerfil(String nro) throws ApplicationException {
		DataUsuario du = new DataUsuario();
		return du.consultaPerfil(nro);
	}

}


