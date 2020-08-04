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
		int nro = Integer.parseInt(dc.ultimoID());
		nro += 1;
		return Integer.toString(nro);
	}
	
	public String localidad(Integer codpos) throws ApplicationException{
		dc = new DataCliente();
		return dc.localidad(codpos);
	}
	
	public Cliente consultaClientePorDNI(String doc) throws ApplicationException{
		dc = new DataCliente();
		return dc.consultaClientePorDNI(doc);
	}
	
	public ArrayList<Cliente> listarClientePorNombre(String nombre) throws ApplicationException {
		dc = new DataCliente();
		return dc.buscarClientePorNombre(nombre);
	}
}
