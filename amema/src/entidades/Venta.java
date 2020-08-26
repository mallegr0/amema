package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Venta implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String NCOMP, TCOMP, LETRA, CIA, NFACC, CODCLI, REGCLI, OBSERV, CPERS1, CPERS2, CPERS3, CVTO, NROREMITO;
	private String NROPEDIDO, NROPRESUP, NVIAJ, DIRECTA, REFERENCIA, LIQUIDA, COMI_DIFE, INNCTACTE, DESPACHA, TEXTLIB;
	private String CCOND_1, CCOND_2, CCOND_3, CCOND_4, VA_DTO, CODART, DESPACHO, TIVA, UBICAC1, UBICAC2, UBICAC3, ANALISIS, CANCDEUANT;
	private Date FCOMP, FVTO, FEC_DESDE;
	private double TEXTO, FLETE, PORDESCTO, PORBONIF, TASA, BONART, BONART2, PRECIO, UNIDADES, IMPCH, IMPCANCDEUANT;
	private int NROMOV;
	
	
	/* CONSTRUCTORES */
	public Venta() {}
	
	public Venta(String nro, String tpo, String letra, String cia, Date fecCom, String nroFac, Date fecVto, String socio, String reg, 
			String obs, String cper1, String cper2, String cper3, String vto, String nroRem, String nroPed, String nroPre, String nroViaj, String dir, 
			String ref, String liq, String comDif, String cta, String despacha, String txtLib, double txt, double flete, String cod1, String cod2, 
			String cod3, String cod4, double dto, double bonif, String vaDto, String codart, double tasa, String despacho, String tiva, double bonart, 
			double bonart2, double precio, double unidades, String ubic1, String ubic2, String ubic3, String analisis, Date fecDesde, int nromov, double impch, 
			String cancelaD, double impCancela) {
		
		this.NCOMP = nro;
		this.TCOMP = tpo;
		this.LETRA = letra;
		this.CIA = cia;
		this.FCOMP = fecCom;
		this.NFACC = nroFac;
		this.FVTO = fecVto;
		this.CODCLI = socio;
		this.REGCLI = reg;
		this.OBSERV =obs;
		this.CPERS1 = cper1;
		this.CPERS2 = cper2;
		this.CPERS3 = cper3;
		this.CVTO = vto;
		this.NROREMITO = nroRem;
		this.NROPEDIDO = nroPed;
		this.NROPRESUP = nroPre;
		this.NVIAJ = nroViaj;
		this.DIRECTA = dir;
		this.REFERENCIA = ref;
		this.LIQUIDA = liq;
		this.COMI_DIFE = comDif;
		this.INNCTACTE = cta;
		this.DESPACHA = despacha;
		this.TEXTLIB = txtLib;
		this.TEXTO = txt;
		this.FLETE = flete;
		this.CCOND_1 = cod1;
		this.CCOND_2 = cod2;
		this.CCOND_3 = cod3;
		this.CCOND_4 = cod4;
		this.PORDESCTO = dto;
		this.PORBONIF = bonif;
		this.VA_DTO = vaDto;
		this.CODART = codart;
		this.TASA = tasa;
		this.DESPACHO = despacho;
		this.TIVA = tiva;
		this.BONART = bonart;
		this.BONART2 = bonart2;
		this.PRECIO = precio;
		this.UNIDADES = unidades;
		this.UBICAC1 = ubic1;
		this.UBICAC2 = ubic2;
		this.UBICAC3 = ubic3;
		this.ANALISIS = analisis;
		this.FEC_DESDE = fecDesde;
		this.NROMOV = nromov;
		this.IMPCH = impch;
		this.CANCDEUANT = cancelaD;
		this.IMPCANCDEUANT = impCancela;
	}
	
	
	/* METODOS */

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

	public String getNFACC() {
		return NFACC;
	}

	public void setNFACC(String nFACC) {
		NFACC = nFACC;
	}

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getREGCLI() {
		return REGCLI;
	}

	public void setREGCLI(String rEGCLI) {
		REGCLI = rEGCLI;
	}

	public String getOBSERV() {
		return OBSERV;
	}

	public void setOBSERV(String oBSERV) {
		OBSERV = oBSERV;
	}

	public String getCPERS1() {
		return CPERS1;
	}

	public void setCPERS1(String cPERS1) {
		CPERS1 = cPERS1;
	}

	public String getCPERS2() {
		return CPERS2;
	}

	public void setCPERS2(String cPERS2) {
		CPERS2 = cPERS2;
	}

	public String getCPERS3() {
		return CPERS3;
	}

	public void setCPERS3(String cPERS3) {
		CPERS3 = cPERS3;
	}

	public String getCVTO() {
		return CVTO;
	}

	public void setCVTO(String cVTO) {
		CVTO = cVTO;
	}

	public String getNROREMITO() {
		return NROREMITO;
	}

	public void setNROREMITO(String nROREMITO) {
		NROREMITO = nROREMITO;
	}

	public String getNROPEDIDO() {
		return NROPEDIDO;
	}

	public void setNROPEDIDO(String nROPEDIDO) {
		NROPEDIDO = nROPEDIDO;
	}

	public String getNROPRESUP() {
		return NROPRESUP;
	}

	public void setNROPRESUP(String nROPRESUP) {
		NROPRESUP = nROPRESUP;
	}

	public String getNVIAJ() {
		return NVIAJ;
	}

	public void setNVIAJ(String nVIAJ) {
		NVIAJ = nVIAJ;
	}

	public String getDIRECTA() {
		return DIRECTA;
	}

	public void setDIRECTA(String dIRECTA) {
		DIRECTA = dIRECTA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getLIQUIDA() {
		return LIQUIDA;
	}

	public void setLIQUIDA(String lIQUIDA) {
		LIQUIDA = lIQUIDA;
	}

	public String getCOMI_DIFE() {
		return COMI_DIFE;
	}

	public void setCOMI_DIFE(String cOMI_DIFE) {
		COMI_DIFE = cOMI_DIFE;
	}

	public String getINNCTACTE() {
		return INNCTACTE;
	}

	public void setINNCTACTE(String iNNCTACTE) {
		INNCTACTE = iNNCTACTE;
	}

	public String getDESPACHA() {
		return DESPACHA;
	}

	public void setDESPACHA(String dESPACHA) {
		DESPACHA = dESPACHA;
	}

	public String getTEXTLIB() {
		return TEXTLIB;
	}

	public void setTEXTLIB(String tEXTLIB) {
		TEXTLIB = tEXTLIB;
	}

	public String getCCOND_1() {
		return CCOND_1;
	}

	public void setCCOND_1(String cCOND_1) {
		CCOND_1 = cCOND_1;
	}

	public String getCCOND_2() {
		return CCOND_2;
	}

	public void setCCOND_2(String cCOND_2) {
		CCOND_2 = cCOND_2;
	}

	public String getCCOND_3() {
		return CCOND_3;
	}

	public void setCCOND_3(String cCOND_3) {
		CCOND_3 = cCOND_3;
	}

	public String getCCOND_4() {
		return CCOND_4;
	}

	public void setCCOND_4(String cCOND_4) {
		CCOND_4 = cCOND_4;
	}

	public String getVA_DTO() {
		return VA_DTO;
	}

	public void setVA_DTO(String vA_DTO) {
		VA_DTO = vA_DTO;
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

	public String getTIVA() {
		return TIVA;
	}

	public void setTIVA(String tIVA) {
		TIVA = tIVA;
	}

	public String getUBICAC1() {
		return UBICAC1;
	}

	public void setUBICAC1(String uBICAC1) {
		UBICAC1 = uBICAC1;
	}

	public String getUBICAC2() {
		return UBICAC2;
	}

	public void setUBICAC2(String uBICAC2) {
		UBICAC2 = uBICAC2;
	}

	public String getUBICAC3() {
		return UBICAC3;
	}

	public void setUBICAC3(String uBICAC3) {
		UBICAC3 = uBICAC3;
	}

	public String getANALISIS() {
		return ANALISIS;
	}

	public void setANALISIS(String aNALISIS) {
		ANALISIS = aNALISIS;
	}

	public String getCANCDEUANT() {
		return CANCDEUANT;
	}

	public void setCANCDEUANT(String cANCDEUANT) {
		CANCDEUANT = cANCDEUANT;
	}

	public Date getFCOMP() {
		return FCOMP;
	}

	public void setFCOMP(Date fCOMP) {
		FCOMP = fCOMP;
	}

	public Date getFVTO() {
		return FVTO;
	}

	public void setFVTO(Date fVTO) {
		FVTO = fVTO;
	}

	public Date getFEC_DESDE() {
		return FEC_DESDE;
	}

	public void setFEC_DESDE(Date fEC_DESDE) {
		FEC_DESDE = fEC_DESDE;
	}

	public double getTEXTO() {
		return TEXTO;
	}

	public void setTEXTO(double tEXTO) {
		TEXTO = tEXTO;
	}

	public double getFLETE() {
		return FLETE;
	}

	public void setFLETE(double fLETE) {
		FLETE = fLETE;
	}

	public double getPORDESCTO() {
		return PORDESCTO;
	}

	public void setPORDESCTO(double pORDESCTO) {
		PORDESCTO = pORDESCTO;
	}

	public double getPORBONIF() {
		return PORBONIF;
	}

	public void setPORBONIF(double pORBONIF) {
		PORBONIF = pORBONIF;
	}

	public double getTASA() {
		return TASA;
	}

	public void setTASA(double tASA) {
		TASA = tASA;
	}

	public double getBONART() {
		return BONART;
	}

	public void setBONART(double bONART) {
		BONART = bONART;
	}

	public double getBONART2() {
		return BONART2;
	}

	public void setBONART2(double bONART2) {
		BONART2 = bONART2;
	}

	public double getPRECIO() {
		return PRECIO;
	}

	public void setPRECIO(double pRECIO) {
		PRECIO = pRECIO;
	}

	public double getUNIDADES() {
		return UNIDADES;
	}

	public void setUNIDADES(double uNIDADES) {
		UNIDADES = uNIDADES;
	}

	public double getIMPCH() {
		return IMPCH;
	}

	public void setIMPCH(double iMPCH) {
		IMPCH = iMPCH;
	}

	public double getIMPCANCDEUANT() {
		return IMPCANCDEUANT;
	}

	public void setIMPCANCDEUANT(double iMPCANCDEUANT) {
		IMPCANCDEUANT = iMPCANCDEUANT;
	}

	public int getNROMOV() {
		return NROMOV;
	}

	public void setNROMOV(int nROMOV) {
		NROMOV = nROMOV;
	}
	
	
	
}
