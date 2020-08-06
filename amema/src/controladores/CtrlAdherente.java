package controladores;

import java.util.ArrayList;

import data.DataAdherente;
import entidades.Adherente;
import util.ApplicationException;

public class CtrlAdherente {
	
	public CtrlAdherente() {}
	
	DataAdherente da = null;
	
	public boolean altaAdherente(Adherente a) throws ApplicationException {
		da = new DataAdherente();
		return da.altaAdherente(a);
	}
	
	public boolean bajaAdherente(String cod, String nro) throws ApplicationException {
		da = new DataAdherente();
		return da.bajaAdherente(cod, nro);
	}
	
	public boolean modificaAdherente(Adherente a) throws ApplicationException {
		da = new DataAdherente();
		return da.modificaAdherente(a);
	}

	public Adherente consultaAdherente(String cod, String nro) throws ApplicationException {
		da = new DataAdherente();
		return da.consultaAdherente(cod, nro);
	}

	public ArrayList<Adherente> listarAdherentes() throws ApplicationException {
		da = new DataAdherente();
		return da.listarAdherentes();
	}
	
	public ArrayList<Adherente> listarAdherentesPorSocio(String cod) throws ApplicationException {
		da = new DataAdherente();
		return da.listarAdherentesPorSocio(cod);
	}

	public String ultimoID(String cod) throws ApplicationException {
		da = new DataAdherente();
		String nro =  da.ultimoID(cod);
		if(nro != null) { 
			int n = Integer.parseInt(nro);
			n =+ 1;
			if(n <10) {
				nro = "000"+Integer.toString(n);
			}
			if(n <100) {
				nro = "00"+Integer.toString(n);
			}
			if(n <1000) {
				nro = "0"+Integer.toString(n);
			}
			else {
				nro = Integer.toString(n);
			}	
		}
		else {
			nro = "0001";
		}
		return nro;
	}
}
