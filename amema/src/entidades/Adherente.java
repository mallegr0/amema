package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Adherente implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CODCLI, NROADHERENTE, APEYNOM_AD, DOMIC_AD, TEL_AD, TIPODOC_AD, NRODOC_AD, PARENT;
	private Date FEC_NAC_AD;
	
	/* CONSTRUCTORES */
	
	public Adherente() {}
	
	public Adherente(String id, String nro, String nombre, String domicilio, String tpodoc, String nrodoc, Date fecha, String pariente) {
		this.CODCLI = id;
		this.NROADHERENTE = nro;
		this.APEYNOM_AD = nombre;
		this.DOMIC_AD = domicilio;
		this.TIPODOC_AD = tpodoc;
		this.NRODOC_AD = nrodoc;
		this.FEC_NAC_AD = fecha;
		this.PARENT = pariente;
	}

	
	/* METODOS */
	
	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getNROADHERENTE() {
		return NROADHERENTE;
	}

	public void setNROADHERENTE(String nROADHERENTE) {
		NROADHERENTE = nROADHERENTE;
	}

	public String getAPEYNOM_AD() {
		return APEYNOM_AD;
	}

	public void setAPEYNOM_AD(String aPEYNOM_AD) {
		APEYNOM_AD = aPEYNOM_AD;
	}

	public String getDOMIC_AD() {
		return DOMIC_AD;
	}

	public void setDOMIC_AD(String dOMIC_AD) {
		DOMIC_AD = dOMIC_AD;
	}

	public String getTEL_AD() {
		return TEL_AD;
	}

	public void setTEL_AD(String tEL_AD) {
		TEL_AD = tEL_AD;
	}

	public String getTIPODOC_AD() {
		return TIPODOC_AD;
	}

	public void setTIPODOC_AD(String tIPODOC_AD) {
		TIPODOC_AD = tIPODOC_AD;
	}

	public String getNRODOC_AD() {
		return NRODOC_AD;
	}

	public void setNRODOC_AD(String nRODOC_AD) {
		NRODOC_AD = nRODOC_AD;
	}

	public String getPARENT() {
		return PARENT;
	}

	public void setPARENT(String pARENT) {
		PARENT = pARENT;
	}

	public Date getFEC_NAC_AD() {
		return FEC_NAC_AD;
	}

	public void setFEC_NAC_AD(Date fEC_NAC_AD) {
		FEC_NAC_AD = fEC_NAC_AD;
	}
	
	
	
}
