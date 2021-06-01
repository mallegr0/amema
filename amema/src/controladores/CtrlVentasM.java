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
	
	public boolean modificoVentaM(VentasM v) throws ApplicationException{
		dvm = new DataVentasM();
		return dvm.modificoVentaM(v);
	}
	
	public VentasM consultaVentasM(String comp) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.consultaVentaM(comp);
	}
	
	public VentasM consultaVentaPorTipoComp(String comprobante, String tcomprobante) throws ApplicationException {
		dvm = new DataVentasM(); 
		return dvm.consultaVentaPorTipoComp(comprobante, tcomprobante);
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
	
	public ArrayList<VentasM> listarVentasParatablaAux(String convenio, Date fecha) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarVentasParatablaAux(convenio, fecha);
	}
	
	public ArrayList<VentasM> listarDeudasPorSocio(String codcli) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarDeudasPorSocio(codcli);
	}
	
	public ArrayList<VentasM> listarAnalisisDeuda() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarAnalisisDeuda();
	}
	
	public ArrayList<VentasM> listarAnalisisDeudaConvenio(String convenio) throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.listarAnalisisDeudaConvenio(convenio);
	}
	
	public String ultimoID() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.ultimoID();
	}
	
	public int ultimoNroActualiz() throws ApplicationException {
		dvm = new DataVentasM();
		return dvm.ultimoNroActualiz();
	}

}
