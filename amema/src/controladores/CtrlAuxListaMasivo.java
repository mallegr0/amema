package controladores;

import java.util.ArrayList;

import data.DataAuxListaMasivo;
import entidades.AuxListaMasivo;
import util.ApplicationException;

public class CtrlAuxListaMasivo {
	
	// Variables
	DataAuxListaMasivo da = null; 
	
	// Constructor
	public CtrlAuxListaMasivo() { }
	
	
	//Metodos privados
	
	private ArrayList<AuxListaMasivo> cambioReferencia(ArrayList<AuxListaMasivo> lista) throws ApplicationException {
		for(AuxListaMasivo aux : lista) {
			if(aux.getConcepto().equals("C")) { aux.setConcepto("2110"); }
			else { aux.setConcepto("2207"); }
		}
		return lista;
	}
	
	
	//Metodos publicos 
	public ArrayList<AuxListaMasivo> listarPorPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		da = new DataAuxListaMasivo();
		return cambioReferencia(da.listarPorPeriodoyConvenio(periodo, convenio));
	}
	
	public ArrayList<AuxListaMasivo> listarPorPeriodo(String periodo) throws ApplicationException {
		da = new DataAuxListaMasivo();
		return cambioReferencia(da.listarPorPeriodo(periodo));
	}
}
