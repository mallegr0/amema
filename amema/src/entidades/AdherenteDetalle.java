package entidades;

import java.io.Serializable;
import java.util.Date;

public class AdherenteDetalle implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private  Date FECMOV; 
	private Double TOTAL, ACUENTA, ADEUDADO, TTOTAL, TACUENTA, TADEUDADO;
	private String NCOMPROBANTE;
	
	/* CONSTRUCTORES */
	
	public AdherenteDetalle() {}
	
	public AdherenteDetalle(Date fecha, String comprobante, double total, double acuenta, double adeudado) {
		this.FECMOV = fecha;
		this.NCOMPROBANTE = comprobante;
		this.TOTAL = total;
		this.ACUENTA = acuenta;
		this.ADEUDADO = adeudado;
		this.TTOTAL = 0.0;
		this.TACUENTA = 0.0;
		this.TADEUDADO = 0.0;
	}

	public Date getFECMOV() {
		return FECMOV;
	}

	public void setFECMOV(Date fECMOV) {
		FECMOV = fECMOV;
	}

	public Double getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(Double tOTAL) {
		TOTAL = tOTAL;
	}

	public Double getACUENTA() {
		return ACUENTA;
	}

	public void setACUENTA(Double aCUENTA) {
		ACUENTA = aCUENTA;
	}

	public Double getADEUDADO() {
		return ADEUDADO;
	}

	public void setADEUDADO(Double aDEUDADO) {
		ADEUDADO = aDEUDADO;
	}

	public Double getTTOTAL() {
		return TTOTAL;
	}

	public void setTTOTAL(Double tTOTAL) {
		TTOTAL = tTOTAL;
	}

	public Double getTACUENTA() {
		return TACUENTA;
	}

	public void setTACUENTA(Double tACUENTA) {
		TACUENTA = tACUENTA;
	}

	public Double getTADEUDADO() {
		return TADEUDADO;
	}

	public void setTADEUDADO(Double tADEUDADO) {
		TADEUDADO = tADEUDADO;
	}

	public String getNCOMPROBANTE() {
		return NCOMPROBANTE;
	}

	public void setNCOMPROBANTE(String nCOMPROBANTE) {
		NCOMPROBANTE = nCOMPROBANTE;
	}	
}
