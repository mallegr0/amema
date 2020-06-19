package controladores;

import java.util.ArrayList;

import data.DataCliente;
import entidades.Cliente;
import util.ApplicationException;

public class CtrlCliente {

	/*CONSTRUCTOR*/
	public CtrlCliente() {}
	
	/*VARIABLES*/
	DataCliente dc = null;
	
	/*METODOS*/
	public boolean altaCliente(Cliente c) throws ApplicationException{
		dc = new DataCliente();
		return dc.altaCliente(c);
	}
	
	public boolean bajaCliente(String id) throws ApplicationException{
		dc = new DataCliente();
		return dc.bajaCliente(id);
	}
	
	public boolean modificaCliente(Cliente c) throws ApplicationException{
		dc = new DataCliente();
		return dc.modificaCliente(c);
	}
	
	public Cliente consultaCliente(String id) throws ApplicationException{
		dc = new DataCliente();
		return dc.consultaCliente(id);
	}
	
	public ArrayList<Cliente> listarCliente() throws ApplicationException{
		dc = new DataCliente();
		return dc.listarCliente();
	}
	
	public String ultimoID() throws ApplicationException{
		dc = new DataCliente();
		return dc.ultimoID();
	}
	
}
