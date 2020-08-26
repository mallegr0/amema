package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String MARCA, CODCLI, NOMCLI, DOMCLI, LOCCLI, TELCLI_1, TELCLI_2, FAX, CVTO, CCOND, ZONCLI, NVIAJ, PROVCLI;
	private String CUITCLI, IVACLI, REGCLI, DNRP, CTRANSP, COM_IND, CREDITO, CONTACTO, CONTACTO2, E_MAIL, MAKITA, COMISION, OBSCLI;
	private String COMI_DIFE, TIPO_DOC, CPCCP;
	private int CODPOS, LISTAPRE;
	private double PRETEN, SALCLI_1, SALCLID_1, SALCLI_2, SALCLID_2,A_CTA_1,A_CTA_2, A_CTAD_1, A_CTAD_2,CRED_MAX;
	private Date FSALCLI_1, FSALCLI_2, FECHA_NAC, FECHA_ING;
	
	
	/* CONSTRUCTORES */
	
	public Cliente() {}
	
	public Cliente(String MARCA, String CODCLI, String NOMCLI, String DOMCLI, int CODPOS, String LOCCLI, String TELCLI_1,
			String TELCLI_2, String FAX, String CVTO, String CCOND, String ZONCLI, String NVIAJ, String PROVCLI, String CUITCLI,
			String IVACLI, String REGCLI, double PRETEN, String DNRP, double SALCLI_1, double SALCLID_1, Date FSALCLI_1, double SALCLI_2, 
			double SALCLID_2, Date FSALCLI_2, double A_CTA_1, double A_CTA_2, double A_CTAD_1, double A_CTAD_2, String CTRANSP, String COM_IND, String CREDITO, double CRED_MAX,
			String CONTACTO, String CONTACTO2, int LISTAPRE, String E_MAIL, String MAKITA, String COMISION, String COMI_DIFE, String TIPO_DOC, 
			Date FECHA_NAC, Date FECHA_ING, String CPCCP, String OBSCLI) {
		this.MARCA = MARCA;
		this.CODCLI = CODCLI;
		this.NOMCLI = NOMCLI;
		this.DOMCLI = DOMCLI;
		this.CODPOS = CODPOS;
		this.LOCCLI = LOCCLI;
		this.TELCLI_1 = TELCLI_1;
		this.TELCLI_2 = TELCLI_2;
		this.FAX = FAX;
		this.CVTO = CVTO;
		this.CCOND = CCOND;
		this.ZONCLI = ZONCLI;
		this.NVIAJ = NVIAJ;
		this.PROVCLI = PROVCLI;
		this.CUITCLI = CUITCLI;
		this.IVACLI = IVACLI;
		this.REGCLI = REGCLI;
		this.PRETEN = PRETEN;
		this.DNRP = DNRP;
		this.SALCLI_1 = SALCLI_1;
		this.SALCLID_1 = SALCLID_1;
		this.FSALCLI_1 = FSALCLI_1;
		this.SALCLI_2 = SALCLI_2;
		this.SALCLID_2 = SALCLID_2;
		this.FSALCLI_2 = FSALCLI_2;
		this.A_CTA_1 = A_CTA_1;
		this.A_CTA_2 = A_CTA_2;
		this.A_CTAD_1 = A_CTAD_1;
		this.A_CTAD_2 = A_CTAD_2;
		this.CTRANSP = CTRANSP;
		this.COM_IND = COM_IND;
		this.CREDITO = CREDITO;
		this.CRED_MAX = CRED_MAX;
		this.CONTACTO = CONTACTO;
		this.CONTACTO2 = CONTACTO2;
		this.LISTAPRE = LISTAPRE;
		this.E_MAIL = E_MAIL;
		this.MAKITA = MAKITA;
		this.COMISION = COMISION;
		this.COMI_DIFE = COMI_DIFE; 
		this.TIPO_DOC = TIPO_DOC;
		this.FECHA_NAC = FECHA_NAC;
		this.FECHA_ING = FECHA_ING;
		this.CPCCP = CPCCP;
		this.OBSCLI = OBSCLI;
	}
	
	
	/* METODOS */

	public String getMARCA() {
		return MARCA;
	}

	public void setMARCA(String mARCA) {
		MARCA = mARCA;
	}

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

	public String getDOMCLI() {
		return DOMCLI;
	}

	public void setDOMCLI(String dOMCLI) {
		DOMCLI = dOMCLI;
	}

	public String getLOCCLI() {
		return LOCCLI;
	}

	public void setLOCCLI(String lOCCLI) {
		LOCCLI = lOCCLI;
	}

	public String getTELCLI_1() {
		return TELCLI_1;
	}

	public void setTELCLI_1(String tELCLI_1) {
		TELCLI_1 = tELCLI_1;
	}

	public String getTELCLI_2() {
		return TELCLI_2;
	}

	public void setTELCLI_2(String tELCLI_2) {
		TELCLI_2 = tELCLI_2;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}

	public String getCVTO() {
		return CVTO;
	}

	public void setCVTO(String cVTO) {
		CVTO = cVTO;
	}

	public String getCCOND() {
		return CCOND;
	}

	public void setCCOND(String cCOND) {
		CCOND = cCOND;
	}

	public String getZONCLI() {
		return ZONCLI;
	}

	public void setZONCLI(String zONCLI) {
		ZONCLI = zONCLI;
	}

	public String getNVIAJ() {
		return NVIAJ;
	}

	public void setNVIAJ(String nVIAJ) {
		NVIAJ = nVIAJ;
	}

	public String getPROVCLI() {
		return PROVCLI;
	}

	public void setPROVCLI(String pROVCLI) {
		PROVCLI = pROVCLI;
	}

	public String getCUITCLI() {
		return CUITCLI;
	}

	public void setCUITCLI(String cUITCLI) {
		CUITCLI = cUITCLI;
	}

	public String getIVACLI() {
		return IVACLI;
	}

	public void setIVACLI(String iVACLI) {
		IVACLI = iVACLI;
	}

	public String getREGCLI() {
		return REGCLI;
	}

	public void setREGCLI(String rEGCLI) {
		REGCLI = rEGCLI;
	}

	public String getDNRP() {
		return DNRP;
	}

	public void setDNRP(String dNRP) {
		DNRP = dNRP;
	}

	public String getCTRANSP() {
		return CTRANSP;
	}

	public void setCTRANSP(String cTRANSP) {
		CTRANSP = cTRANSP;
	}

	public String getCOM_IND() {
		return COM_IND;
	}

	public void setCOM_IND(String cOM_IND) {
		COM_IND = cOM_IND;
	}

	public String getCREDITO() {
		return CREDITO;
	}

	public void setCREDITO(String cREDITO) {
		CREDITO = cREDITO;
	}

	public String getCONTACTO() {
		return CONTACTO;
	}

	public void setCONTACTO(String cONTACTO) {
		CONTACTO = cONTACTO;
	}

	public String getCONTACTO2() {
		return CONTACTO2;
	}

	public void setCONTACTO2(String cONTACTO2) {
		CONTACTO2 = cONTACTO2;
	}

	public String getE_MAIL() {
		return E_MAIL;
	}

	public void setE_MAIL(String e_MAIL) {
		E_MAIL = e_MAIL;
	}

	public String getMAKITA() {
		return MAKITA;
	}

	public void setMAKITA(String mAKITA) {
		MAKITA = mAKITA;
	}

	public String getCOMISION() {
		return COMISION;
	}

	public void setCOMISION(String cOMISION) {
		COMISION = cOMISION;
	}

	public String getCOMI_DIFE() {
		return COMI_DIFE;
	}

	public void setCOMI_DIFE(String cOMI_DIFE) {
		COMI_DIFE = cOMI_DIFE;
	}

	public String getTIPO_DOC() {
		return TIPO_DOC;
	}

	public void setTIPO_DOC(String tIPO_DOC) {
		TIPO_DOC = tIPO_DOC;
	}

	public String getCPCCP() {
		return CPCCP;
	}

	public void setCPCCP(String cPCCP) {
		CPCCP = cPCCP;
	}

	public int getCODPOS() {
		return CODPOS;
	}

	public void setCODPOS(int cODPOS) {
		CODPOS = cODPOS;
	}

	public int getLISTAPRE() {
		return LISTAPRE;
	}

	public void setLISTAPRE(int lISTAPRE) {
		LISTAPRE = lISTAPRE;
	}

	public double getPRETEN() {
		return PRETEN;
	}

	public void setPRETEN(double pRETEN) {
		PRETEN = pRETEN;
	}

	public double getSALCLI_1() {
		return SALCLI_1;
	}

	public void setSALCLI_1(double sALCLI_1) {
		SALCLI_1 = sALCLI_1;
	}

	public double getSALCLID_1() {
		return SALCLID_1;
	}

	public void setSALCLID_1(double sALCLID_1) {
		SALCLID_1 = sALCLID_1;
	}

	public double getSALCLI_2() {
		return SALCLI_2;
	}

	public void setSALCLI_2(double sALCLI_2) {
		SALCLI_2 = sALCLI_2;
	}

	public double getSALCLID_2() {
		return SALCLID_2;
	}

	public void setSALCLID_2(double sALCLID_2) {
		SALCLID_2 = sALCLID_2;
	}

	public double getA_CTA_1() {
		return A_CTA_1;
	}

	public void setA_CTA_1(double a_CTA_1) {
		A_CTA_1 = a_CTA_1;
	}

	public double getA_CTA_2() {
		return A_CTA_2;
	}

	public void setA_CTA_2(double a_CTA_2) {
		A_CTA_2 = a_CTA_2;
	}

	public double getA_CTAD_1() {
		return A_CTAD_1;
	}

	public void setA_CTAD_1(double a_CTAD_1) {
		A_CTAD_1 = a_CTAD_1;
	}

	public double getA_CTAD_2() {
		return A_CTAD_2;
	}

	public void setA_CTAD_2(double a_CTAD_2) {
		A_CTAD_2 = a_CTAD_2;
	}

	public double getCRED_MAX() {
		return CRED_MAX;
	}

	public void setCRED_MAX(double cRED_MAX) {
		CRED_MAX = cRED_MAX;
	}

	public Date getFSALCLI_1() {
		return FSALCLI_1;
	}

	public void setFSALCLI_1(Date fSALCLI_1) {
		FSALCLI_1 = fSALCLI_1;
	}

	public Date getFSALCLI_2() {
		return FSALCLI_2;
	}

	public void setFSALCLI_2(Date fSALCLI_2) {
		FSALCLI_2 = fSALCLI_2;
	}

	public Date getFECHA_NAC() {
		return FECHA_NAC;
	}

	public void setFECHA_NAC(Date fECHA_NAC) {
		FECHA_NAC = fECHA_NAC;
	}

	public Date getFECHA_ING() {
		return FECHA_ING;
	}

	public void setFECHA_ING(Date fECHA_ING) {
		FECHA_ING = fECHA_ING;
	}
	
	public String getOBSCLI() {
		return OBSCLI;
	}
	
	public void setOBSCLI(String oBSCLI) {
		OBSCLI = oBSCLI;
	}
	
}
