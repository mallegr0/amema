package controladores;

import java.util.ArrayList;

import data.DataVenta;
import entidades.Venta;
import util.ApplicationException;

public class CtrlVenta {
	
	DataVenta dv = null;
	
	public CtrlVenta() {}
	
	public boolean altaVenta(Venta v) throws ApplicationException {
		dv = new DataVenta();
		return dv.altaVenta(v);
	}
	
	public boolean bajaVenta(String cod, int mov) throws ApplicationException {
		dv = new DataVenta();
		return dv.bajaVenta(cod, mov);
	}
	
	public boolean modificaVenta(Venta v) throws ApplicationException {
		dv = new DataVenta();
		return dv.modificaVenta(v);
	}
	
	public Venta ConsultaVentaPorNroMov(int nroMov) throws ApplicationException {
		dv = new DataVenta();
		return dv.ConsultaVentaPorNroMov(nroMov);
	}
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentaPorSocio(cod);
	}
	
	public int ultimoID() throws ApplicationException {
		dv = new DataVenta();
		return dv.ultimoID();
	}
}
