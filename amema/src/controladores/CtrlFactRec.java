package controladores;

import data.DataFactRec;
import entidades.FactRec;
import util.ApplicationException;

public class CtrlFactRec {
	
	/* VARIABLES */
	DataFactRec dfr = null;
	
	
	/* CONSTRUCTOR */
	public CtrlFactRec() {}
	
	/* METODO */
	
	public FactRec consultarFactura(String recibo) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.consultarFactura(recibo);
	}
	
	public String consultaNroComprobante(String recibo) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.consultaNroComprobante(recibo);
	}
}

