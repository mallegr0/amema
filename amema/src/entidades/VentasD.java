package entidades;

import java.io.Serializable;

public class VentasD implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String PREFIJO, NCOMP, TCOMP, LETRA, CIA, CODART, DESPACHO, ADUANA;
	private double UNIDADES, PBONIF, PBONIF2, PVENTA, BONART;
	
	/* CONSTRUCTORES */
	public VentasD() {}
	
	public VentasD(String prefijo, String nrocomp, String tpocomp, String letra, String cia, String codart, double unidades,
			double pbonif, double pbonif2, double pventa, String despacho, String aduana, double bonart) {
		this.PREFIJO = prefijo;
		this.NCOMP = nrocomp;
		this.TCOMP = tpocomp;
		this.LETRA = letra;
		this.CIA = cia;
		this.CODART = codart;
		this.UNIDADES = unidades;
		this.PBONIF = pbonif;
		this.PBONIF2 = pbonif2;
		this.PVENTA = pventa;
		this.DESPACHO = despacho;
		this.ADUANA = aduana;
		this.BONART = bonart;
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

	public String getCODART() {
		return CODART;
	}

	public void setCODART(String cODART) {
		CODART = cODART;
	}

	public String getDESPACHO() {
		return DESPACHO;
	}

	public void setDESPACHO(String dESPACHO) {
		DESPACHO = dESPACHO;
	}

	public String getADUANA() {
		return ADUANA;
	}

	public void setADUANA(String aDUANA) {
		ADUANA = aDUANA;
	}

	public double getUNIDADES() {
		return UNIDADES;
	}

	public void setUNIDADES(double uNIDADES) {
		UNIDADES = uNIDADES;
	}

	public double getPBONIF() {
		return PBONIF;
	}

	public void setPBONIF(double pBONIF) {
		PBONIF = pBONIF;
	}

	public double getPBONIF2() {
		return PBONIF2;
	}

	public void setPBONIF2(double pBONIF2) {
		PBONIF2 = pBONIF2;
	}

	public double getPVENTA() {
		return PVENTA;
	}

	public void setPVENTA(double pVENTA) {
		PVENTA = pVENTA;
	}

	public double getBONART() {
		return BONART;
	}

	public void setBONART(double bONART) {
		BONART = bONART;
	}
	
	
}
