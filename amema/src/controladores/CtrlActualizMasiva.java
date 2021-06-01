package controladores;

import data.DataActualizMasiva;
import entidades.ActualizMasivas;
import util.ApplicationException;

public class CtrlActualizMasiva {
	
	//CONSTRUCTOR
	public CtrlActualizMasiva() {}
	
	//VARIABLES
	DataActualizMasiva dam = null; 
	
	//METODOS
	public boolean altaActualizMasiva(ActualizMasivas a) throws ApplicationException {
		dam = new DataActualizMasiva();
		return dam.altaActualizMasiva(a);
	}
	
}
