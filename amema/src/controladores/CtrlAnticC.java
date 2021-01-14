package controladores;

import data.DataAnticC;
import entidades.AnticC;
import util.ApplicationException;

public class CtrlAnticC {
	
	//variables
	DataAnticC dac = null; 
	
	//constructor
	public CtrlAnticC() {}
	
	//Metodos publicos
	public boolean bajaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		dac = new DataAnticC();
		return dac.bajaAnticipoPorNroRecibo(nrec);
	}
	
	public AnticC consultaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		dac = new DataAnticC();
		return dac.consultaAnticipoPorNroRecibo(nrec);
	}
}
