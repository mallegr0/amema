package controladores;

import java.util.ArrayList;

import data.DataAuxAnDeudaSoc;
import entidades.AnalisisSaldoListado;
import entidades.AuxAnDeudaSoc;
import util.ApplicationException;

public class CtrlAuxAnDeudaSoc {
	
	//VARIABLES
	DataAuxAnDeudaSoc da = null; 
	
	//Constructor
	public CtrlAuxAnDeudaSoc() {}
	
	//Metodos
	public boolean altaSocio(AuxAnDeudaSoc a) throws ApplicationException {
		da = new DataAuxAnDeudaSoc();
		return da.altaSocio(a);
	}
	
	public boolean bajaTodo() throws ApplicationException {
		da = new DataAuxAnDeudaSoc();
		return da.bajaTodo();
	}
	
	public int cantidadRegistros() throws ApplicationException {
		da = new DataAuxAnDeudaSoc();
		return da.cantidadRegistros();
	}
	
	public ArrayList<AuxAnDeudaSoc> listarTodo() throws ApplicationException {
		da = new DataAuxAnDeudaSoc();
		return da.listarTodo();
	}
	
	public ArrayList<AnalisisSaldoListado> emitirListadoSaldos() throws ApplicationException {
		da = new DataAuxAnDeudaSoc();
		return da.emitirListadoSaldos();
	}
}
