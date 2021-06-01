package controladores;

import data.DataMov_Stoc;
import entidades.Mov_stoc;
import util.ApplicationException;

public class CtrlMov_Stoc {
	
	//Constructor
	public CtrlMov_Stoc() {}
	
	//Variable
	DataMov_Stoc dms = null; 
	
	//Metodos
	public boolean altaMovStoc(Mov_stoc m) throws ApplicationException {
		dms = new DataMov_Stoc();
		return dms.altaMovStoc(m);
	}

}
