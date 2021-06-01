package controladores;

import java.util.ArrayList;
import java.util.Date;

import data.DataVenta;
import entidades.Venta;
import entidades.VentaReporte;
import util.ApplicationException;

public class CtrlVenta {
	
	DataVenta dv = null;
	
	public CtrlVenta() {}
	
	public boolean altaVenta(Venta v) throws ApplicationException {
		dv = new DataVenta();
		return dv.altaVenta(v);
	}
	
	public boolean bajaVenta(int mov) throws ApplicationException {
		dv = new DataVenta();
		return dv.bajaVenta(mov);
	}
	
	public boolean modificaVenta(Venta v) throws ApplicationException {
		dv = new DataVenta();
		return dv.modificaVenta(v);
	}
	
	public Venta ConsultaVentaPorNroMov(int nroMov) throws ApplicationException {
		dv = new DataVenta();
		return dv.ConsultaVentaPorNroMov(nroMov);
	}
	
	public String consultarClientePorNroMov(int nro) throws ApplicationException {
		dv = new DataVenta();
		return dv.consultarClientePorNroMov(nro);
	}
	
	public Venta consultarVentaActMF(Venta vta) throws ApplicationException {
		dv = new DataVenta();
		return dv.consultarVentaActMF(vta); 
	}
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentaPorSocio(cod);
	}
	
	public ArrayList<Venta> listarVentasPorFechas(Date fecIni, Date fecFin) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentasPorFechas(fecIni, fecFin);
	}
	
	public ArrayList<Venta> listarVentasPorFechasyModo(Date fecIni, Date fecFin, String modo) throws ApplicationException {
		dv = new DataVenta();
		return dv.listarVentasPorFechasyModo(fecIni, fecFin, modo);
	}
	
	public ArrayList<VentaReporte> listarAmbosUnaFlia(Date fd, Date fh, String fam1) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarAmbosUnaFlia(fd, fh, fam1);
	}
	
	public ArrayList<VentaReporte> listarAmbosDosFlia(Date fd, Date fh, String fam1, String fam2) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarAmbosDosFlia(fd, fh, fam1, fam2);
	}
	
	public ArrayList<VentaReporte> listarAmbosTresFlia(Date fd, Date fh, String fam1, String fam2, String fam3) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarAmbosTresFlia(fd, fh, fam1, fam2, fam3);
	}
	
	public ArrayList<VentaReporte> listarAmbosCuatroFlia(Date fd, Date fh, String fam1, String fam2, String fam3, String fam4) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarAmbosCuatroFlia(fd, fh, fam1, fam2, fam3, fam4);
	}
	
	public ArrayList<VentaReporte> listarAmbosTodasFlia(Date fd, Date fh) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarAmbosTodasFlia(fd, fh);
	}
	
	public ArrayList<VentaReporte> listarEstadoUnaFlia(Date fd, Date fh,String estado, String fam1) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarEstadoUnaFlia(fd, fh, estado, fam1);
	}
	
	public ArrayList<VentaReporte> listarEstadoDosFlia(Date fd, Date fh,String estado, String fam1, String fam2) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarEstadoDosFlia(fd, fh, estado, fam1, fam2);
	}
	
	public ArrayList<VentaReporte> listarEstadoTresFlia(Date fd, Date fh,String estado, String fam1, String fam2, String fam3) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarEstadoTresFlia(fd, fh, estado, fam1, fam2, fam3);
	}
	
	public ArrayList<VentaReporte> listarEstadoCuatroFlia(Date fd, Date fh,String estado, String fam1, String fam2, String fam3, String fam4) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarEstadoCuatroFlia(fd, fh, estado, fam1, fam2, fam3, fam4);
	}
	
	public ArrayList<VentaReporte> listarEstadoTodasFlia(Date fd, Date fh,String estado) throws ApplicationException {
		dv = new DataVenta(); 
		return dv.listarEstadoTodasFlia(fd, fh, estado);
	}
	
	public ArrayList<Venta> listarVentasActualizaMF(Date fecIni, Date fecFin, String modo) throws ApplicationException{
		dv = new DataVenta();
		return dv.listarVentasActualizaMF(fecIni, fecFin, modo);
	}
	
	public int ultimoID() throws ApplicationException {
		dv = new DataVenta();
		return dv.ultimoID()+1;
	}
}
