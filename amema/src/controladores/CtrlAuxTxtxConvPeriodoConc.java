package controladores;

import java.util.ArrayList;

import data.DataAuxTxtxConvPeriodoConc;
import entidades.AuxTxtxConvPeriodoConc;
import util.ApplicationException;

public class CtrlAuxTxtxConvPeriodoConc {
	
	//Variables 
	DataAuxTxtxConvPeriodoConc daux = null; 
	
	//Constructor
	public CtrlAuxTxtxConvPeriodoConc() {}
	
	//Metodos
	
	public boolean altaRegistro(AuxTxtxConvPeriodoConc a) throws ApplicationException {
		daux = new DataAuxTxtxConvPeriodoConc();
		return daux.altaRegistroAux(a);
	}
	
	public boolean bajaRegistro(String codcli, String convenio, String periodo, String concepto) throws ApplicationException {
		daux = new DataAuxTxtxConvPeriodoConc();
		return daux.bajaRegistro(codcli, convenio, periodo, concepto);
	}
	
	public boolean bajaPeriodo(String periodo) throws ApplicationException {
		daux = new DataAuxTxtxConvPeriodoConc();
		return daux.bajaPeriodo(periodo);
	}
	
	public ArrayList<AuxTxtxConvPeriodoConc> listarPeriodo(String periodo) throws ApplicationException {
		daux = new DataAuxTxtxConvPeriodoConc();
		return daux.listarPeriodo(periodo);
	}
	
	public ArrayList<AuxTxtxConvPeriodoConc> listarConceptoPeriodo(String concepto, String periodo) throws ApplicationException {
		daux = new DataAuxTxtxConvPeriodoConc();
		return daux.listarConceptoPeriodo(concepto, periodo);
	}
	

}
