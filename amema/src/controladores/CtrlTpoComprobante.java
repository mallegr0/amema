package controladores;

import data.DataTpoComprobante;
import entidades.TpoComprobante;
import util.ApplicationException;

public class CtrlTpoComprobante {
	
	/* CONSTRUCTOR */ 
	public CtrlTpoComprobante() {}
	
	/* VARIABLES */
	DataTpoComprobante dtc = null;
	
	/* METODOS */
	public TpoComprobante consultaTComprobante(String cod) throws ApplicationException {
		dtc = new DataTpoComprobante();
		return dtc.consultaTComprobante(cod);
	}
}
