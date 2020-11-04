package controladores;

import java.util.ArrayList;

import data.DataRefencia;
import entidades.Referencia;
import util.ApplicationException;

public class CtrlReferencia {
	/* variables */
	
	DataRefencia dr = null; 
	
	/* constructor */
	public CtrlReferencia() {}
	
	
	/* metodos */
	
	public Referencia consultaReferencia(String cod) throws ApplicationException {
		dr = new DataRefencia();
		return dr.consultaReferencia(cod);
	}
	
	public ArrayList<Referencia> listarReferencias() throws ApplicationException {
		dr = new DataRefencia();
		return dr.listarReferencias();
	}
	

}
