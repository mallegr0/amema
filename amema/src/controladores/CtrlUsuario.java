package controladores;

import java.util.ArrayList;

import data.DataUsuario;
import entidades.Usuario;
import util.ApplicationException;

public class CtrlUsuario {
	
	/*CONSTRUCTOR*/
	
	public CtrlUsuario() {}
	
	/*VARIABLES*/
	
	DataUsuario du = new DataUsuario();
	
	/*METODOS*/
	
	public boolean altaUsuario(Usuario u){
		try {
			return du.altaUsuario(u);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bajaUsuario(String user){
		try {
			return du.bajaUsuario(user);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificaUsuario(Usuario u){
		try {
			return du.modificaUsuario(u);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Usuario consultaUsuario(Usuario u){
		try {
			return du.consultaUsuario(u);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Usuario> listaUsuarios(String orden){
		try {
			return du.listarUsuarios(orden);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return  null;
		}
	}
	
	public boolean cambiaPassword(String user, String pass){
		try {
			return du.cambiaPassword(user, pass);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validaUsuario(String user, String pass){
		
		try {
			Usuario u = new Usuario();
			u.setUser(user);
			u = du.consultaUsuario(u);
			if(u.getUser() == user && u.getPassword() == pass){
				return true;
			}
			else{
				return false;
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
}
