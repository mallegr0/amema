package controladores;

import java.util.ArrayList;

import data.DataPeriodoDeudaArch;
import entidades.PeriodoDeudaArch;
import util.ApplicationException;

public class CtrlPeriodoDeudaArch {
	
	//Constructor
	public CtrlPeriodoDeudaArch() {}
	
	//Variable
	DataPeriodoDeudaArch dp = null; 
	
	//Metodos
	public boolean altaPeriodoDeudaArch(PeriodoDeudaArch p) throws ApplicationException {
		dp = new DataPeriodoDeudaArch();
		return dp.altaPeriodoDeudaArch(p);
	}
	
	public boolean bajaPeriodoDeudaArch(String nombre) throws ApplicationException {
		dp = new DataPeriodoDeudaArch();
		return dp.bajaPeriodoDeudaArch(nombre);
	}
	
	public boolean modificaPeriodoDeudaArch(PeriodoDeudaArch p) throws ApplicationException {
		dp = new DataPeriodoDeudaArch();
		return dp.modificaPeriodoDeudaArch(p);
	}
	
	public PeriodoDeudaArch consultaPeriodoDeudaArchPorNombreArchivo(String nombre) throws ApplicationException {
		dp = new DataPeriodoDeudaArch();
		return dp.consultaPeriodoDeudaArchPorNombreArchivo(nombre);
	}
	
	public ArrayList<PeriodoDeudaArch> ListarDatosPorPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		dp = new DataPeriodoDeudaArch();
		return dp.ListarDatosPorPeriodoyConvenio(convenio, periodo);
	}
	
}
