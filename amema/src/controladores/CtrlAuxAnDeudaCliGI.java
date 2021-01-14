package controladores;

import java.util.ArrayList;

import data.DataAuxAnDeudaCliGI;
import entidades.AuxAnDeudaCliGI;
import util.ApplicationException;

public class CtrlAuxAnDeudaCliGI {
	
	//Variables 
	DataAuxAnDeudaCliGI daux = null; 
	
	//Constructor
	public CtrlAuxAnDeudaCliGI() {}
	
	//Metodos
	
	public boolean altaInteres (AuxAnDeudaCliGI a) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI(); 
		return daux.altaInteres(a);
	}
	
	public boolean bajaInteres(String comprobante) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.bajaInteres(comprobante);
	}
	
	public boolean bajaInteresMasivoPeriodo(String convenio, String periodo) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.bajaInteresMasivoPeriodo(convenio, periodo);
	}
	
	public boolean modificaInteres (AuxAnDeudaCliGI a) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.modificaInteres(a);
	}
	
	public AuxAnDeudaCliGI consultaInteres(String comprobante) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.consultaInteres(comprobante);
	}
	
	public ArrayList<AuxAnDeudaCliGI> listarInteresPorPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.listarInteresPorPeriodoyConvenio(convenio, periodo);
	}
	
	public ArrayList<AuxAnDeudaCliGI> listarDeudaTotal(String convenio, String periodo) throws ApplicationException {
		daux = new DataAuxAnDeudaCliGI();
		return daux.listarDeudaTotal(convenio, periodo);
	}
}
