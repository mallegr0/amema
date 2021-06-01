package entidades;

import java.io.Serializable;
import java.util.Date;

public class Mov_stoc implements Serializable {

	// Variables
	private static final long serialVersionUID = 1L;
	private String codart, cdep; 
	private Date fmov; 
	private String cconcepto; 
	private double cantidad; 
	private String ncomp; 
	
	// Cosntructores
	public Mov_stoc() {}

	public Mov_stoc(String codart, String cdep, Date fmov, String cconcepto, double cantidad, String ncomp) {
		this.codart = codart;
		this.cdep = cdep;
		this.fmov = fmov;
		this.cconcepto = cconcepto;
		this.cantidad = cantidad;
		this.ncomp = ncomp;
	}

	
	// Metodos
	
	public String getCodart() {
		return codart;
	}

	public void setCodart(String codart) {
		this.codart = codart;
	}

	public String getCdep() {
		return cdep;
	}

	public void setCdep(String cdep) {
		this.cdep = cdep;
	}

	public Date getFmov() {
		return fmov;
	}

	public void setFmov(Date fmov) {
		this.fmov = fmov;
	}

	public String getCconcepto() {
		return cconcepto;
	}

	public void setCconcepto(String cconcepto) {
		this.cconcepto = cconcepto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getNcomp() {
		return ncomp;
	}

	public void setNcomp(String ncomp) {
		this.ncomp = ncomp;
	}
}
