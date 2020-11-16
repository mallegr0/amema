package entidades;

import java.io.Serializable;
import java.util.Date;

public class AuxAnDeudaCli implements Serializable{
	
	//Variables
	private static final long serialVersionUID = 1L;
	private String CODCLI, NOMCLI, TIPOMOV, NCOMP, TCOMP, NVIAJ, REF, CONVENIO, PERIODO, SALDO;
	private double IMPORTE, IMPORTEPAGADO;
	private int ANALISIS;
	private Date FCOMP;
	
	//Constructores
	public AuxAnDeudaCli() {}
	
	public AuxAnDeudaCli(String cODCLI, String nOMCLI, String tIPOMOV, String nCOMP, String tCOMP, String nVIAJ,
			String rEF, String cONVENIO, String pERIODO, String sALDO, double iMPORTE, double iMPORTEPAGADO,
			int aNALISIS, Date fCOMP) {
		this.CODCLI = cODCLI;
		this.NOMCLI = nOMCLI;
		this.TIPOMOV = tIPOMOV;
		this.NCOMP = nCOMP;
		this.TCOMP = tCOMP;
		this.NVIAJ = nVIAJ;
		this.REF = rEF;
		this.CONVENIO = cONVENIO;
		this.PERIODO = pERIODO;
		this.SALDO = sALDO;
		this.IMPORTE = iMPORTE;
		this.IMPORTEPAGADO = iMPORTEPAGADO;
		this.ANALISIS = aNALISIS;
		this.FCOMP = fCOMP;
	}
	
	
	//Metodos

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getNOMCLI() {
		return NOMCLI;
	}

	public void setNOMCLI(String nOMCLI) {
		NOMCLI = nOMCLI;
	}

	public String getTIPOMOV() {
		return TIPOMOV;
	}

	public void setTIPOMOV(String tIPOMOV) {
		TIPOMOV = tIPOMOV;
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

	public String getNVIAJ() {
		return NVIAJ;
	}

	public void setNVIAJ(String nVIAJ) {
		NVIAJ = nVIAJ;
	}

	public String getREF() {
		return REF;
	}

	public void setREF(String rEF) {
		REF = rEF;
	}

	public String getCONVENIO() {
		return CONVENIO;
	}

	public void setCONVENIO(String cONVENIO) {
		CONVENIO = cONVENIO;
	}

	public String getPERIODO() {
		return PERIODO;
	}

	public void setPERIODO(String pERIODO) {
		PERIODO = pERIODO;
	}

	public String getSALDO() {
		return SALDO;
	}

	public void setSALDO(String sALDO) {
		SALDO = sALDO;
	}

	public double getIMPORTE() {
		return IMPORTE;
	}

	public void setIMPORTE(double iMPORTE) {
		IMPORTE = iMPORTE;
	}

	public double getIMPORTEPAGADO() {
		return IMPORTEPAGADO;
	}

	public void setIMPORTEPAGADO(double iMPORTEPAGADO) {
		IMPORTEPAGADO = iMPORTEPAGADO;
	}

	public int getANALISIS() {
		return ANALISIS;
	}

	public void setANALISIS(int aNALISIS) {
		ANALISIS = aNALISIS;
	}

	public Date getFCOMP() {
		return FCOMP;
	}

	public void setFCOMP(Date fCOMP) {
		FCOMP = fCOMP;
	}
}
