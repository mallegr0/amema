package controladores;

import java.util.ArrayList;
import java.util.Date;

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
	
	public boolean bajaVenta(int mov) throws ApplicationException {
		dv = new DataVenta();
		return dv.bajaVenta(mov);
	}
	
	public boolean modificaVenta(Venta v) throws ApplicationException {
		dv = new DataVenta();
		return dv.modificaVenta(v);
	}
	
	public Venta ConsultaVentaPorNroMov(int nroMov) throws ApplicationException {
		dv = new DataVenta();
		return dv.ConsultaVentaPorNroMov(nroMov);
	}
	
	public String consultarClientePorNroMov(int nro) throws ApplicationException {
		dv = new DataVenta();
		return dv.consultarClientePorNroMov(nro);
	}
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentaPorSocio(cod);
	}
	
	public ArrayList<Venta> listarVentasPorFechas(Date fecIni, Date fecFin) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentasPorFechas(fecIni, fecFin);
	}
	
	public ArrayList<Venta> listarVentasPorFechasyModo(Date fecIni, Date fecFin, String modo) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentasPorFechasyModo(fecIni, fecFin, modo);
	}
	
	public int ultimoID() throws ApplicationException {
		dv = new DataVenta();
		return dv.ultimoID()+1;
	}
}
