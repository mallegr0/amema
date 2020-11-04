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
	
	public boolean bajaFacturaPorComprobante(String comprobante) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.bajaFacturaPorComprobante(comprobante);
	}
	
	public FactRec consultarFactura(String recibo) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.consultarFactura(recibo);
	}
	
	public String consultaNroComprobante(String recibo) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.consultaNroComprobante(recibo);
	}
	
	public FactRec consultarFacturaPorNroComprobante(String comprobante) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.consultarFacturaPorNroComprobante(comprobante);
	}
}

