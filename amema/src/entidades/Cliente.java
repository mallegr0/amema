package entidades;

import java.io.Serializable;
import java.sql.Timestamp;

public class Cliente implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String MARCA, CODCLI, NOMCLI, DOMCLI, LOCCLI, TELCLI_1, TELCLI_2, FAX, CVTO, CCOND, ZONCLI, NVIAJ, PROVCLI;
	private String CUITCLI, IVACLI, REGCLI, DNRP, CTRANSP, COM_IND, CREDITO, CONTACTO, CONTACTO2, E_MAIL, MAKITA, COMISION, OBSCLI;
	private String COMI_DIFE, TIPO_DOC, CPCCP;
	private Integer CODPOS, LISTAPRE;
	private Double PRETEN, SALCLI_1, SALCLID_1, SALCLI_2, SALCLID_2,A_CTA_1,A_CTA_2, A_CTAD_1, A_CTAD_2,CRED_MAX;
	private Timestamp FSALCLI_1, FSALCLI_2, FECHA_NAC, FECHA_ING;
	
	
	/* CONSTRUCTORES */
	
	public Cliente() {}
	
	public Cliente(String MARCA, String CODCLI, String NOMCLI, String DOMCLI, Integer CODPOS, String LOCCLI, String TELCLI_1,
			String TELCLI_2, String FAX, String CVTO, String CCOND, String ZONCLI, String NVIAJ, String PROVCLI, String CUITCLI,
			String IVACLI, String REGCLI, Double PRETEN, String DNRP, Double SALCLI_1, Double SALCLID_1, Timestamp FSALCLI_1, Double SALCLI_2, 
			Double SALCLID_2, Timestamp FSALCLI_2, Double A_CTA_1, Double A_CTA_2, Double A_CTAD_1, Double A_CTAD_2, String CTRANSP, String COM_IND, String CREDITO, Double CRED_MAX,
			String CONTACTO, String CONTACTO2, Integer LISTAPRE, String E_MAIL, String MAKITA, String COMISION, String COMI_DIFE, String TIPO_DOC, 
			Timestamp FECHA_NAC, Timestamp FECHA_ING, String CPCCP, String OBSCLI) {
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

	public Integer getCODPOS() {
		return CODPOS;
	}

	public void setCODPOS(Integer cODPOS) {
		CODPOS = cODPOS;
	}

	public Integer getLISTAPRE() {
		return LISTAPRE;
	}

	public void setLISTAPRE(Integer lISTAPRE) {
		LISTAPRE = lISTAPRE;
	}

	public Double getPRETEN() {
		return PRETEN;
	}

	public void setPRETEN(Double pRETEN) {
		PRETEN = pRETEN;
	}

	public Double getSALCLI_1() {
		return SALCLI_1;
	}

	public void setSALCLI_1(Double sALCLI_1) {
		SALCLI_1 = sALCLI_1;
	}

	public Double getSALCLID_1() {
		return SALCLID_1;
	}

	public void setSALCLID_1(Double sALCLID_1) {
		SALCLID_1 = sALCLID_1;
	}

	public Double getSALCLI_2() {
		return SALCLI_2;
	}

	public void setSALCLI_2(Double sALCLI_2) {
		SALCLI_2 = sALCLI_2;
	}

	public Double getSALCLID_2() {
		return SALCLID_2;
	}

	public void setSALCLID_2(Double sALCLID_2) {
		SALCLID_2 = sALCLID_2;
	}

	public Double getA_CTA_1() {
		return A_CTA_1;
	}

	public void setA_CTA_1(Double a_CTA_1) {
		A_CTA_1 = a_CTA_1;
	}

	public Double getA_CTA_2() {
		return A_CTA_2;
	}

	public void setA_CTA_2(Double a_CTA_2) {
		A_CTA_2 = a_CTA_2;
	}

	public Double getA_CTAD_1() {
		return A_CTAD_1;
	}

	public void setA_CTAD_1(Double a_CTAD_1) {
		A_CTAD_1 = a_CTAD_1;
	}

	public Double getA_CTAD_2() {
		return A_CTAD_2;
	}

	public void setA_CTAD_2(Double a_CTAD_2) {
		A_CTAD_2 = a_CTAD_2;
	}

	public Double getCRED_MAX() {
		return CRED_MAX;
	}

	public void setCRED_MAX(Double cRED_MAX) {
		CRED_MAX = cRED_MAX;
	}

	public Timestamp getFSALCLI_1() {
		return FSALCLI_1;
	}

	public void setFSALCLI_1(Timestamp fSALCLI_1) {
		FSALCLI_1 = fSALCLI_1;
	}

	public Timestamp getFSALCLI_2() {
		return FSALCLI_2;
	}

	public void setFSALCLI_2(Timestamp fSALCLI_2) {
		FSALCLI_2 = fSALCLI_2;
	}

	public Timestamp getFECHA_NAC() {
		return FECHA_NAC;
	}

	public void setFECHA_NAC(Timestamp fECHA_NAC) {
		FECHA_NAC = fECHA_NAC;
	}

	public Timestamp getFECHA_ING() {
		return FECHA_ING;
	}

	public void setFECHA_ING(Timestamp fECHA_ING) {
		FECHA_ING = fECHA_ING;
	}
	
	public String getOBSCLI() {
		return OBSCLI;
	}
	
	public void setOBSCLI(String oBSCLI) {
		OBSCLI = oBSCLI;
	}
	
}
