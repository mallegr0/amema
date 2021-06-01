package controladores;

import data.DataParametros;
import util.ApplicationException;

public class CtrlParametros {
	
	// CONSTRUCTOR
	public CtrlParametros() {}
	
	// VARIABLRES
	DataParametros dp = null; 
	
	// METODOS
	public String consultaCompctaneto(String ultnrocompes) throws ApplicationException {
		dp = new DataParametros();
		return dp.consultaCompctaneto(ultnrocompes);
	}
	
	public double consultaCotDolar(String ultnrocompes) throws ApplicationException {
		dp = new DataParametros();
		return dp.consultaCotDolar(ultnrocompes);
	}
}
