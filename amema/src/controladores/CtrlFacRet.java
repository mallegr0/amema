package controladores;

import java.util.ArrayList;

import data.dataFacRet;
import entidades.FacturaAnalisisSaldo;
import util.ApplicationException;

public class CtrlFacRet {
	
	//VARIABLES
	dataFacRet dfr = null; 
	
	//CONSTRUCTOR
	public CtrlFacRet() {}
	
	//METODOS
	public ArrayList<FacturaAnalisisSaldo> listarRetencionesAnalisisSaldo() throws ApplicationException {
		dfr = new dataFacRet();
		return dfr.listarRetencionesAnalisisSaldo();
	}
	
	public ArrayList<FacturaAnalisisSaldo> listarRetencionesAnalisisSaldoConvenio(String convenio) throws ApplicationException {
		dfr = new dataFacRet();
		return dfr.listarRetencionesAnalisisSaldoConvenio(convenio);
	}
}
