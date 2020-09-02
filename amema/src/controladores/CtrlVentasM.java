package controladores;

import java.util.ArrayList;

import data.DataVentasM;
import entidades.VentasM;
import util.ApplicationException;

public class CtrlVentasM {
	
	/* VARIABLES */
	DataVentasM dvm = null;
	
	/* CONSTRUCTOR */
	public CtrlVentasM() {}
	
	/* METODOS */
	
	//alta
	//baja
	//modificacion
	
	public VentasM consultaVentasM(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.consultaVentaM(comp);
	}
	
	public ArrayList<VentasM> listarVentasMPorNroMov(int mov) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasMPorNroMov(mov);
	}
	

}
