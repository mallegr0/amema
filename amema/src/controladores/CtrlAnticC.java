package controladores;

import java.util.ArrayList;

import data.DataAnticC;
import entidades.AnticC;
import entidades.AnticipoAnalisisSaldo;
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
	
	public ArrayList<AnticipoAnalisisSaldo> listarAnticiposParaAnalisisSaldo() throws ApplicationException {
		dac = new DataAnticC();
		return dac.listarAnticiposParaAnalisisSaldo();
	}
	
	public ArrayList<AnticipoAnalisisSaldo> listarAnticiposParaAnalisisSaldoConvenio(String convenio) throws ApplicationException {
		dac = new DataAnticC();
		return dac.listarAnticiposParaAnalisisSaldoConvenio(convenio);
	}
}
