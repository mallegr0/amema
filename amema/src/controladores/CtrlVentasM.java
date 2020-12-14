package controladores;

import java.util.Date;
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
	public boolean modificaVentasMImporte(VentasM v) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.modificaVentasMImporte(v);
	}
	
	public VentasM consultaVentasM(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.consultaVentaM(comp);
	}
	
	public boolean modificoLetraPagado(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.modificoLetraPagado(comp);
	}
	
	public double obtengoImporte(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.obtengoImporte(comp);
	}
	
	public String obtengoCliente(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.obtengoCliente(comp);
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
	
	public ArrayList<VentasM> listarVentasMPendientes(String cod) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasMPendientes(cod);
	}
	
	public ArrayList<VentasM> listarVentasMporFecha(Date fecha) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasMporFecha(fecha);
	}
	
	public String ultimoID() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.ultimoID();
	}

}
