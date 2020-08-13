package controladores;

import java.util.ArrayList;

import data.DataVenta;
import entidades.Venta;
import util.ApplicationException;

public class CtrlVenta {
	
	DataVenta dv = null;
	
	public CtrlVenta() {}
	
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentaPorSocio(cod);
	}
}
