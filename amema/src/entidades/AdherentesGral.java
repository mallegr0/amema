package entidades;

import java.io.Serializable;

public class AdherentesGral implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private Integer NROMC, CUOTAS;
	private String PRIOR, CODMOV, REF, DESMOV, CANTMENS, MODO, ESTADO, FDESDE, FHASTA;
	private Double IMPORTE; 
	
	/* CONSTRUCTOR */
	public AdherentesGral() {}
	
	public AdherentesGral(Integer nro, String fdesde, String fhasta, String prioridad, String codMov, String referencia, 
			String descMov, Double Importe, Integer cuotas, String mensual, String modo, String estado) {
		
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
		
	}
	
	/* METODOS */
	public Integer getNROMC() {
		return NROMC;
	}

	public void setNROMC(Integer nROMC) {
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

	public Double getIMPORTE() {
		return IMPORTE;
	}

	public void setIMPORTE(Double iMPORTE) {
		IMPORTE = iMPORTE;
	}

	public Integer getCUOTAS() {
		return CUOTAS;
	}

	public void setCUOTAS(Integer cUOTAS) {
		CUOTAS = cUOTAS;
	}
	
	
}
