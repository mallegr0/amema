package controladores;

import java.util.ArrayList;

import data.DataReciboM;
import entidades.ReciboM;
import util.ApplicationException;

public class CtrlReciboM {
	
	/* VARIABLES */
	DataReciboM drm;
	
	/* constructor */
	public CtrlReciboM() { }
	
	
	/* METODOS */
	public ArrayList<ReciboM> listarRecibosM() throws ApplicationException {
		drm = new DataReciboM();
		return drm.listarRecibosM();
	}
	
	public ArrayList<ReciboM> listarRecibosMSocio(String cod) throws ApplicationException {
		drm = new DataReciboM();
		return drm.listarRecibosMSocio(cod);
	}
}

