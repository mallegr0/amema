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
	
	//baja
	
	//modificacion
	
	//consulta
	
	public ArrayList<Convenio> listarConvenio() throws ApplicationException {
		dc = new DataConvenio();
		return dc.listarConvenio();
	}

}
