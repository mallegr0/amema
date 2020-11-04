package controladores;

import data.DataVentasD;
import util.ApplicationException;
import entidades.VentasD;

public class CtrlVentasD {
	
	//Variables
	DataVentasD dvd = null; 
	
	//constructor
	public CtrlVentasD() {}
	
	// Metodos
	
	public boolean altaVentasD(VentasD v) throws ApplicationException {
		dvd = new DataVentasD();
		return dvd.altaVentasD(v);
	}
	
	public boolean bajaVentasD(String nro) throws ApplicationException {
		dvd = new DataVentasD();
		return dvd.bajaVentasD(nro);
	}
	
	public boolean mosificaVentasD(VentasD v) throws ApplicationException {
		dvd = new DataVentasD();
		return dvd.modificaVentasD(v);
	}
	
	public VentasD consultaVentasD(String nro) throws ApplicationException {
		dvd = new DataVentasD();
		return dvd.consultaVentasD(nro);
	}
}
