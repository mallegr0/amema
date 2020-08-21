package controladores;

import java.util.ArrayList;
import java.util.Date;

import data.DataCtactecliente;
import entidades.Ctactecliente;
import util.ApplicationException;

public class CtrlCtactecliente {
	/* CONSTRUCTOR */
	public CtrlCtactecliente() {}

	/* VARIABLES */
	DataCtactecliente dc = null;
	
	/* METODOS */
	public boolean altaCtaCte(Ctactecliente c) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.altaCtaCte(c);
	}
	
	public boolean bajaCtaCte(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.bajaCtaCte(cod, fec);
	}
	
	public boolean modificaCtaCte(Ctactecliente c) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.modificaCtaCte(c);
	}
	
	public Ctactecliente consultarCtaCte(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.consultarCtaCte(cod, fec);
	}
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocioYFecha(String cod, Date fec) throws ApplicationException {
		dc = new DataCtactecliente();
		return dc.listarCtaCtePorSocioYFec(cod, fec);
	}
}
