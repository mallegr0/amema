package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Ctactecliente implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CODCLI, TMOV, LCOMP, PCOMP, TCOMP, NCOMP, LCOMPORIG, PCOMPORIG, NCOMPORIG;
	private Date FMOV, FCOMPORIG;
	private Double DEBE, HABER;
	
	/* CONSTRUCTORES */
	public Ctactecliente() {}
	
	public Ctactecliente(String cod, Date fec, String tmov, String lcomp, String pcomp, String tcomp, String ncomp, Date forig,
			String lorig, String porig, String norig, Double debe, Double haber) {
		this.CODCLI = cod;
		this.FMOV = fec;
		this.TMOV = tmov;
		this.LCOMP = lcomp;
		this.PCOMP = pcomp;
		this.TCOMP = tcomp;
		this.NCOMP = ncomp;
		this.FCOMPORIG = forig;
		this.LCOMPORIG = lorig;
		this.PCOMPORIG = porig;
		this.NCOMPORIG = norig;
		this.DEBE = debe;
		this.HABER = haber;
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

	public String getLCOMP() {
		return LCOMP;
	}

	public void setLCOMP(String lCOMP) {
		LCOMP = lCOMP;
	}

	public String getPCOMP() {
		return PCOMP;
	}

	public void setPCOMP(String pCOMP) {
		PCOMP = pCOMP;
	}

	public String getTCOMP() {
		return TCOMP;
	}

	public void setTCOMP(String tCOMP) {
		TCOMP = tCOMP;
	}

	public String getNCOMP() {
		return NCOMP;
	}

	public void setNCOMP(String nCOMP) {
		NCOMP = nCOMP;
	}

	public String getLCOMPORIG() {
		return LCOMPORIG;
	}

	public void setLCOMPORIG(String lCOMPORIG) {
		LCOMPORIG = lCOMPORIG;
	}

	public String getPCOMPORIG() {
		return PCOMPORIG;
	}

	public void setPCOMPORIG(String pCOMPOTIG) {
		PCOMPORIG = pCOMPOTIG;
	}

	public String getNCOMPORIG() {
		return NCOMPORIG;
	}

	public void setNCOMPORIG(String nCOMPORIG) {
		NCOMPORIG = nCOMPORIG;
	}

	public Date getFMOV() {
		return FMOV;
	}

	public void setFMOV(Date fMOV) {
		FMOV = fMOV;
	}

	public Date getFCOMPORIG() {
		return FCOMPORIG;
	}

	public void setFCOMPORIG(Date fCOMPORIG) {
		FCOMPORIG = fCOMPORIG;
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
	
	
	
}
