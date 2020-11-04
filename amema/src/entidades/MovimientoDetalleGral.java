package entidades;

import java.io.Serializable;
import java.util.Date;

public class MovimientoDetalleGral implements Serializable{
	
	// VARIABLES 
	private static final long serialVersionUID = 1L;
	private int nroMov; 
	private Date fecMov;
	private String DescMov;
	private double saldoDebe, saldoPago;
	
	// COSNTRUCTORES 
	public MovimientoDetalleGral() {}
	
	public MovimientoDetalleGral(int nro, Date fec, String desc, double debe, double pago) {
		this.nroMov = nro;
		this.fecMov = fec;
		this.DescMov = desc;
		this.saldoDebe = debe;
		this.saldoPago = pago; 
	}
	
	
	// METODOS

	public int getNroMov() {
		return nroMov;
	}

	public void setNroMov(int nroMov) {
		this.nroMov = nroMov;
	}

	public Date getFecMov() {
		return fecMov;
	}

	public void setFecMov(Date fecMov) {
		this.fecMov = fecMov;
	}

	public String getDescMov() {
		return DescMov;
	}

	public void setDescMov(String descMov) {
		DescMov = descMov;
	}

	public double getSaldoDebe() {
		return saldoDebe;
	}

	public void setSaldoDebe(double saldoDebe) {
		this.saldoDebe = saldoDebe;
	}

	public double getSaldoPago() {
		return saldoPago;
	}

	public void setSaldoPago(double saldoPago) {
		this.saldoPago = saldoPago;
	}

	

}
