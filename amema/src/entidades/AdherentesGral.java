package entidades;

import java.io.Serializable;

public class AdherentesGral implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private int NROMC, CUOTAS;
	private String PRIOR, CODMOV, REF, DESMOV, CANTMENS, MODO, ESTADO, FDESDE, FHASTA, NOMBRE;
	private double IMPORTE; 
	
	/* CONSTRUCTOR */
	public AdherentesGral() {}
	
	public AdherentesGral(int nro, String fdesde, String fhasta, String prioridad, String codMov, String referencia, 
			String descMov, double Importe, int cuotas, String mensual, String modo, String estado, String nombre) {
		
		this.NROMC = nro;
		this.FDESDE = fdesde;
		this.FHASTA =fhasta;
		this.PRIOR = prioridad;
		this.CODMOV = codMov;
		this.REF = referencia;
		this.DESMOV = descMov;
		this.IMPORTE = Importe;
		this.CUOTAS = cuotas;
		this.CANTMENS = mensual;
		this.MODO = modo;
		this.ESTADO = estado;
		this.NOMBRE	 = nombre;
	}
	
	/* METODOS */
	
	public int getNROMC() {
		return NROMC;
	}

	public void setNROMC(int nROMC) {
		NROMC = nROMC;
	}

	public String getPRIOR() {
		return PRIOR;
	}

	public void setPRIOR(String pRIOR) {
		PRIOR = pRIOR;
	}

	public String getCODMOV() {
		return CODMOV;
	}

	public void setCODMOV(String cODMOV) {
		CODMOV = cODMOV;
	}

	public String getREF() {
		return REF;
	}

	public void setREF(String rEF) {
		REF = rEF;
	}

	public String getDESMOV() {
		return DESMOV;
	}

	public void setDESMOV(String dESMOV) {
		DESMOV = dESMOV;
	}

	public String getCANTMENS() {
		return CANTMENS;
	}

	public void setCANTMENS(String cANTMENS) {
		CANTMENS = cANTMENS;
	}

	public String getMODO() {
		return MODO;
	}

	public void setMODO(String mODO) {
		MODO = mODO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getFDESDE() {
		return FDESDE;
	}

	public void setFDESDE(String fDESDE) {
		FDESDE = fDESDE;
	}

	public String getFHASTA() {
		return FHASTA;
	}

	public void setFHASTA(String fHASTA) {
		FHASTA = fHASTA;
	}

	public double getIMPORTE() {
		return IMPORTE;
	}

	public void setIMPORTE(double iMPORTE) {
		IMPORTE = iMPORTE;
	}

	public int getCUOTAS() {
		return CUOTAS;
	}

	public void setCUOTAS(int cUOTAS) {
		CUOTAS = cUOTAS;
	}
	
	public String getNOMBRE() {
		return NOMBRE;
	}
	
	public void SetNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
}
