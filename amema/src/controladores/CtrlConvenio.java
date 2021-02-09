package controladores;

import java.util.ArrayList;

import data.DataConvenio;
import entidades.Convenio;
import util.ApplicationException;

public class CtrlConvenio {
	
	/* CONSTRUCTOR */
	public CtrlConvenio() {}
	
	/* VARIABLES*/
	DataConvenio dc = null;
	
	
	//alta
	public boolean altaConvenio(Convenio c) throws ApplicationException {
		dc = new DataConvenio();
		return dc.altaConvenio(c);
	}
	
	//baja
	public boolean bajaConvenioPorCodigo(String codigo) throws ApplicationException {
		dc = new DataConvenio();
		return dc.bajaConvenioPorCodigo(codigo);
	}
	
	//modificacion
	public boolean modificaConvenio(Convenio c) throws ApplicationException {
		dc = new DataConvenio(); 
		return dc.modificaConvenio(c);
	}
	
	
	//consulta
	
	public String buscaDescripcion(String cod) throws ApplicationException{
		dc = new DataConvenio();
		return dc.buscaDescripcion(cod);
	}
	
	public Convenio consultaConvenio(String convenio) throws ApplicationException {
		dc = new DataConvenio();
		return dc.consultaConvenio(convenio);
	}
	
	public ArrayList<Convenio> listarConvenio() throws ApplicationException {
		dc = new DataConvenio();
		return dc.listarConvenio();
	}

}
