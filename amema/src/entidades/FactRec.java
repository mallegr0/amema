package entidades;

import java.io.Serializable;
import java.util.Date;

public class FactRec implements Serializable {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String PREFIJO, NCOMP, TCOMP, LETRA, CIA, NRECIBO, NMOV;
	private Date FECREC;
	private double MONTO_A, MONTO_D, DESCUENT_A, DESCUENT_D, A_CTA;
	
	
	/* CONSTRUCTORES */
	public FactRec() {}
	
	public FactRec(String prefijo, String comprobante, String tpoComprobante, String letra, String cia, String recibo, 
			Date fecha, String movimiento, double montoa, double montod, double dctoa, double dctod, double acta) {
		this.PREFIJO = prefijo;
		this.NCOMP = comprobante;
		this.TCOMP = tpoComprobante;
		this.LETRA = letra;
		this.CIA = cia;
		this.NRECIBO = recibo;
		this.FECREC = fecha;
		this.NMOV = movimiento;
		this.MONTO_A = montoa;
		this.MONTO_D = montod;
		this.DESCUENT_A = dctoa;
		this.DESCUENT_D = dctod;
		this.A_CTA = acta;
	}
	
	/* METODOS */

	public String getPREFIJO() {
		return PREFIJO;
	}

	public void setPREFIJO(String pREFIJO) {
		PREFIJO = pREFIJO;
	}

	public String getNCOMP() {
		return NCOMP;
	}

	public void setNCOMP(String nCOMP) {
		NCOMP = nCOMP;
	}

	public String getTCOMP() {
		return TCOMP;
	}

	public void setTCOMP(String tCOMP) {
		TCOMP = tCOMP;
	}

	public String getLETRA() {
		return LETRA;
	}

	public void setLETRA(String lETRA) {
		LETRA = lETRA;
	}

	public String getCIA() {
		return CIA;
	}

	public void setCIA(String cIA) {
		CIA = cIA;
	}

	public String getNRECIBO() {
		return NRECIBO;
	}

	public void setNRECIBO(String nRECIBO) {
		NRECIBO = nRECIBO;
	}

	public String getNMOV() {
		return NMOV;
	}

	public void setNMOV(String nMOV) {
		NMOV = nMOV;
	}

	public Date getFECREC() {
		return FECREC;
	}

	public void setFECREC(Date fECREC) {
		FECREC = fECREC;
	}

	public double getMONTO_A() {
		return MONTO_A;
	}

	public void setMONTO_A(double mONTO_A) {
		MONTO_A = mONTO_A;
	}

	public double getMONTO_D() {
		return MONTO_D;
	}

	public void setMONTO_D(double mONTO_D) {
		MONTO_D = mONTO_D;
	}

	public double getDESCUENT_A() {
		return DESCUENT_A;
	}

	public void setDESCUENT_A(double dESCUENT_A) {
		DESCUENT_A = dESCUENT_A;
	}

	public double getDESCUENT_D() {
		return DESCUENT_D;
	}

	public void setDESCUENT_D(double dESCUENT_D) {
		DESCUENT_D = dESCUENT_D;
	}

	public double getA_CTA() {
		return A_CTA;
	}

	public void setA_CTA(double a_CTA) {
		A_CTA = a_CTA;
	}
	
	
}
