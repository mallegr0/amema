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
	public boolean altaReciboM(ReciboM rm) throws ApplicationException {
		drm = new DataReciboM();
		return drm.altaReciboM(rm);
	}
	
	public ArrayList<ReciboM> listarRecibosM() throws ApplicationException {
		drm = new DataReciboM();
		return drm.listarRecibosM();
	}
	
	public ArrayList<ReciboM> listarRecibosMSocio(String cod) throws ApplicationException {
		drm = new DataReciboM();
		return drm.listarRecibosMSocio(cod);
	}
	
	public ArrayList<ReciboM> listarRecibosMPendientes(String codcli) throws ApplicationException {
		drm = new DataReciboM();
		return drm.listarRecibosMPendientes(codcli);
	}
	public String ultimoID() throws ApplicationException {
		drm = new DataReciboM();  
		int nro = Integer.parseInt(drm.ultimoID());
		nro += 1;
		return Integer.toString(nro);
	}
}

