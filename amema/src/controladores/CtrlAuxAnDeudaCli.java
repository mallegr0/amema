package controladores;

import java.util.ArrayList;

import data.DataAuxAnDeudaCli;
import entidades.AuxAnDeudaCli;
import util.ApplicationException;

public class CtrlAuxAnDeudaCli {
	// Variables
	DataAuxAnDeudaCli da = null;
	
	// Constructor
	public CtrlAuxAnDeudaCli() {}
	
	//Metodos
	public boolean altaAuxAnDeudaCli(AuxAnDeudaCli a) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.altaAuxAnDeudaCli(a);
	}
	
	public boolean bajaAuxAnDeudaCli(String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.bajaAuxAnDeudaCli(periodo);
	}
	
	public boolean eliminaPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.eliminaPeriodoyConvenio(convenio, periodo);
	}
	
	public boolean modificaAuxAnDeudaCli(AuxAnDeudaCli a) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.modificaAuxAnDeudaCli(a);
	}
	
	public boolean hayDatosEnPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.hayDatosEnPeriodoyConvenio(periodo, convenio);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCli(String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCli(periodo);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliVarios(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCliVarios(periodo, convenio);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliCuotas(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCliCuotas(periodo, convenio);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCliPeriodoyConvenio(periodo, convenio);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliPeriodo(String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCliPeriodo(periodo);
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliJubilados(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarAuxAnDeudaCliJubilados(periodo, convenio);
	}
	
	public ArrayList<AuxAnDeudaCli> listarDeudoresPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.listarDeudoresPeriodoyConvenio(convenio, periodo);
	}
	
	public ArrayList<AuxAnDeudaCli> totalDeudaPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		da = new DataAuxAnDeudaCli();
		return da.totalDeudaPeriodoyConvenio(convenio, periodo);
	}
}
