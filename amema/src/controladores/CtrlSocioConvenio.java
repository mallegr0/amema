package controladores;

import data.DataSocioConvenio;
import entidades.SocioConvenio;
import util.ApplicationException;

public class CtrlSocioConvenio {
	/* VARIABLES */
	DataSocioConvenio sc = null; 
	
	/* CONSTRUCTOR */
	public CtrlSocioConvenio() {}
	
	/* METODOS */
	public boolean altaSocioConvenio(SocioConvenio s) throws ApplicationException {
		sc = new DataSocioConvenio();
		return sc.altaSocioConvenio(s);
	}
	
	public boolean bajaSocioConvenio(String cod, String cpto) throws ApplicationException {
		sc = new DataSocioConvenio();
		return sc.bajaSocioConvenio(cod, cpto);
	}
	
	public boolean modificaSocioConvenio(SocioConvenio s) throws ApplicationException {
		sc = new DataSocioConvenio();
		return sc.modificaSocioConvenio(s);
	}
	
	public SocioConvenio consultaSocioConvenio(String cod, String cpto) throws ApplicationException {
		sc = new DataSocioConvenio();
		return sc.consultaSocioConvenio(cod, cpto);
	}
}
