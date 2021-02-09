package controladores;

import java.util.ArrayList;

import data.DataCheque;
import entidades.Cheque;
import entidades.ChequeAnalisisSaldo;
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
	
	public ArrayList<ChequeAnalisisSaldo> listarChequesAnalisisSaldo() throws ApplicationException {
		dc = new DataCheque();
		return dc.listarChequesAnalisisSaldo();
	}
	
	public ArrayList<ChequeAnalisisSaldo> listarChequesAnalisisSaldoConvenio(String convenio) throws ApplicationException {
		dc = new DataCheque();
		return dc.listarChequesAnalisisSaldoConvenio(convenio);
	}
}
