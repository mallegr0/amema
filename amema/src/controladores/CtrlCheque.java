package controladores;

import data.DataCheque;
import entidades.Cheque;
import util.ApplicationException;

public class CtrlCheque {
	//Variables
	DataCheque dc = null; 
	
	//constructor
	public CtrlCheque() {}
	
	//Metodos publicos
	public boolean bajaChequePorNroRecibo(String nroRecibo) throws ApplicationException {
		dc = new DataCheque();
		return dc.bajaChequePorNroRecibo(nroRecibo);
	}
	
	public Cheque consultaChequePorNroRecibo(String nroRecibo) throws ApplicationException {
		dc = new DataCheque();
		return dc.consultaChequePorNroRecibo(nroRecibo);
	}
}
