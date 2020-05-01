package controladores;

import entidades.Usuario;
import util.ApplicationException;

import java.util.ArrayList;

import data.dataUsuario;


public class ctrlUsuario {
	
	/* CONSTRUCTORES */
	public ctrlUsuario() {}
	
	/* METODOES */
	
	public boolean altaUsuario(Usuario u) throws ApplicationException{
		dataUsuario du = new dataUsuario();
		return du.altaUsuario(u);
	}
	
	public boolean bajaUsuario(String user) throws ApplicationException{
		dataUsuario du = new dataUsuario();
		return du.bajaUsuario(user);
	}
	
	public boolean modificaUsuario(Usuario u) throws ApplicationException{
		dataUsuario du = new dataUsuario();
		return du.modificaUsuario(u);
	}
	
	public Usuario consultaUsuario(String user) throws ApplicationException{
		dataUsuario du = new dataUsuario();
		return du.consultaUsuario(user);
	}
	
	public ArrayList<Usuario> listarUsuarios() throws ApplicationException{
		dataUsuario du = new dataUsuario();
		return du.listarUsuarios();
	}
	
	public boolean validaUsuario(String user, String pass) throws ApplicationException{
		Usuario u = new Usuario();
		dataUsuario du = new dataUsuario();
		u = du.consultaUsuario(user);
		
		if(u != null) {
			if(u.getUsuario()==user && u.getPassword()== pass) {
				return true;
			}
			else {return false;}
		}
		else { return false;}
	}
	
	public boolean cambiaPassword(Usuario u) throws ApplicationException {
		dataUsuario du = new dataUsuario();
		return du.modificaUsuario(u); 
	}
	
}


