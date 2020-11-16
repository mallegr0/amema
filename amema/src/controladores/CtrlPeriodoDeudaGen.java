package controladores;

import java.util.ArrayList;

import data.DataPeriodoDeudaGen;
import entidades.PeriodoDeudaGen;
import util.ApplicationException;

public class CtrlPeriodoDeudaGen {
	
	//Variables 
	DataPeriodoDeudaGen dpg = null; 
	
	//constructor
	public CtrlPeriodoDeudaGen() {}
	
	//Metodos publicos
	public boolean altaPeriodoDeudaGen(PeriodoDeudaGen p) throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.altaPeriodoDeudaGen(p);
	}
		
	public boolean bajaPeriodoDeudaGen(String periodo, String convenio) throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.bajaPeriodoDeudaGen(periodo, convenio);
	}
		
	public boolean modificaPeriodoDeudaGen(PeriodoDeudaGen p) throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.modificaPeriodoDeudaGen(p);
	}
		
	public PeriodoDeudaGen consultaPeriodoDeudaGen(String periodo, String convenio) throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.consultaPeriodoDeudaGen(periodo, convenio);
	}
		
	public ArrayList<PeriodoDeudaGen> listarPeriodoDeudaGen() throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.listarPeriodoDeudaGen();
	}
	
	public ArrayList<String> listarPeriodos() throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.listarPeriodos();
	}
		
	public int ultimoID() throws ApplicationException {
		dpg = new DataPeriodoDeudaGen();
		return dpg.ultimoID();
	}

}
