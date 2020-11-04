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
	public boolean altaVentasM(VentasM vm) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.altaVentasM(vm);
	}
	//baja
	public boolean bajaVentasMPorMovimiento(int nro) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.bajaVentasMPorMovimiento(nro);
	}
	//modificacion
	
	public VentasM consultaVentasM(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.consultaVentaM(comp);
	}
	
	public ArrayList<VentasM> listarVentasMPorNroMov(int mov) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasMPorNroMov(mov);
	}
	
	public ArrayList<VentasM> listarVentasM() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasM();
	}
	
	public ArrayList<VentasM> listarVentasMSocio(String cod) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasMSocio(cod);
	}
	
	public String ultimoID() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.ultimoID();
	}

}
