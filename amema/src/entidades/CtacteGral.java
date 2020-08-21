package entidades;

import java.io.Serializable;

public class CtacteGral implements Serializable{
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CODCLI, TMOV, NCOMP, FMOV;
	private Double DEBE, HABER, SALDO;
	
	/* CONSTRUCTORES */
	public CtacteGral() {}
	
	public CtacteGral(String cod, String fec, String tmov, String ncomp, Double debe, Double haber, Double saldo) {
		this.CODCLI = cod;
		this.FMOV = fec;
		this.TMOV = tmov;
		this.NCOMP = ncomp;
		this.DEBE = debe;
		this.HABER = haber;
		this.SALDO = saldo;
	}
	
	/* METODOS */

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getTMOV() {
		return TMOV;
	}

	public void setTMOV(String tMOV) {
		TMOV = tMOV;
	}

	public String getNCOMP() {
		return NCOMP;
	}

	public void setNCOMP(String nCOMP) {
		NCOMP = nCOMP;
	}

	public String getFMOV() {
		return FMOV;
	}

	public void setFMOV(String fMOV) {
		FMOV = fMOV;
	}

	public Double getDEBE() {
		return DEBE;
	}

	public void setDEBE(Double dEBE) {
		DEBE = dEBE;
	}

	public Double getHABER() {
		return HABER;
	}

	public void setHABER(Double hABER) {
		HABER = hABER;
	}

	public Double getSALDO() {
		return SALDO;
	}

	public void setSALDO(Double sALDO) {
		SALDO = sALDO;
	}
	
	
}
