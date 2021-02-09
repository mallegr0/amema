package controladores;

import java.util.ArrayList;
import java.util.Date;

import data.DataFactRec;
import entidades.DatosCobroFact;
import entidades.FactRec;
import util.ApplicationException;

public class CtrlFactRec {
	
	/* VARIABLES */
	DataFactRec dfr = null;
	
	
	/* CONSTRUCTOR */
	public CtrlFactRec() {}
	
	/* METODO */
	public boolean altaFactura(FactRec f) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.altaFactura(f);
	}
	
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
	
	public String devolverNroFactura(String nroComp) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.devolverNroFactura(nroComp);
	}
	
	public ArrayList<FactRec> listarFacturasPorFechaySocio(Date fecha, String codcli) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.listarFacturasPorFechaySocio(fecha, codcli);
	}
	
	public ArrayList<FactRec> listarFacturasPorNroRecibo(String nroRecibo) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.listarFacturasPorNroRecibo(nroRecibo);
	}
	
	public ArrayList<DatosCobroFact> listarFacturasPorNroReciboDCobro(String comprobante, Date fecha) throws ApplicationException {
		dfr = new DataFactRec();
		return dfr.listarFacturasPorNroReciboDCobro(comprobante, fecha);
	}
}

