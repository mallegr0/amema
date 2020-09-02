package entidades;

import java.io.Serializable;
import java.util.Date;

public class VentasM implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String PREFIJO, NCOMP, TCOMP, LETRA, CIA, NFACC, CODCLI, CVTO, PAGADO, ANULADO, PORAJUSTE, RECHAZADO, PORDESCUEN;
	private String CERRADA, REFERENCIA, DIRECTA, COMI_DIFE, CCOND_1, CCOND_2, CCOND_3, CCOND_4, NVIAJ, LIQUIDA, NROPEDIDO;
	private String NROREMITO, NROPRESUP, OBSERVAC, DESPACHADO, TEXTLIB, VA_DTO, CUENTA, CPERS1, CPERS2, CPERS3, ANALISIS, OBSERV;
	private Date FMOV, FECVTO;
	private double TASA, PORDESCTO, PORBONIF, LIQ_VIA, LIQ_TOT, FLETE, SUBTOTAL, A_CUENTA, A_CUENTAD, IMPPAG, IMPPAGD, IMPDESCTO;
	private double IMPBONIF, IMPIVA_1, IMPIVA_2, IMPIVA_3, TDOLAR, DOLAR, TEXTO;
	private int NROMOVPLANIF, NROACTUALIZ;
	
	/* CONSTRUCTOR */ 
	public VentasM() {}
	
	public VentasM(String pref, String nrocomp, String tcomp, String letra, String cia, Date fecmov, String nfac, String cod, 
			String vto, double tasa, String pagado, String anulado, String ajuste, String rechazado, String desc, String cerrada,
			String ref, String dir, String comi, Date fevto, double pordto, double porbonif, String con1, String con2, String con3, 
			String con4, String viaj, String liq, double liqvia, double liqtot, String pedido, String remito, String presup, String obs1,
			String despachado, double flete, double stotal, double acuenta, double acuentad, double imppag, double imppagd, double impdto, double impbonif,
			double impiva1, double impiva2, double impiva3, double tdolar, double dolar, double txt, String txtlib, String vaDto, String cuenta,
			String per1, String per2, String per3, String analisis, int nromovplanif, String obs2, int nroact) {
		this.PREFIJO = pref;
		this.NCOMP = nrocomp;
		this.TCOMP = tcomp;
		this.LETRA = letra;
		this.CIA = cia;
		this.FMOV = fecmov;
		this.NFACC = nfac;
		this.CODCLI = cod;
		this.CVTO = vto;
		this.TASA = tasa;
		this.PAGADO  = pagado;
		this.ANULADO = anulado;
		this.PORAJUSTE = ajuste;
		this.RECHAZADO = rechazado;
		this.PORDESCUEN = desc;
		this.CERRADA = cerrada;
		this.REFERENCIA = ref;
		this.DIRECTA = dir;
		this.COMI_DIFE = comi;
		this.FECVTO = fevto;
		this.PORDESCTO = pordto;
		this.PORBONIF = porbonif;
		this.CCOND_1 = con1;
		this.CCOND_2 = con2;
		this.CCOND_3 = con3;
		this.CCOND_4 = con4;
		this.NVIAJ = viaj;
		this.LIQUIDA = liq;
		this.LIQ_VIA = liqvia;
		this.LIQ_TOT = liqtot;
		this.NROPEDIDO = pedido;
		this.NROREMITO = remito;
		this.NROPRESUP = presup;
		this.OBSERVAC = obs1;
		this.DESPACHADO = despachado;
		this.FLETE = flete;
		this.SUBTOTAL = stotal;
		this.A_CUENTA = acuenta;
		this.A_CUENTAD = acuentad;
		this.IMPPAG = imppag;
		this.IMPPAGD = imppagd;
		this.IMPDESCTO = impdto;
		this.IMPBONIF = impbonif;
		this.IMPIVA_1 = impiva1;
		this.IMPIVA_2 = impiva2;
		this.IMPIVA_3 = impiva3;
		this.TDOLAR = tdolar;
		this.DOLAR = dolar;
		this.TEXTO = txt;
		this.TEXTLIB = txtlib;
		this.VA_DTO = vaDto;
		this.CUENTA = cuenta;
		this.CPERS1 = per1;
		this.CPERS2 = per2;
		this.CPERS3 = per3;
		this.ANALISIS = analisis;
		this.NROMOVPLANIF = nromovplanif;
		this.OBSERV = obs2;
		this.NROACTUALIZ = nroact;
	}

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

	public String getCVTO() {
		return CVTO;
	}

	public void setCVTO(String cVTO) {
		CVTO = cVTO;
	}

	public String getPAGADO() {
		return PAGADO;
	}

	public void setPAGADO(String pAGADO) {
		PAGADO = pAGADO;
	}

	public String getANULADO() {
		return ANULADO;
	}

	public void setANULADO(String aNULADO) {
		ANULADO = aNULADO;
	}

	public String getPORAJUSTE() {
		return PORAJUSTE;
	}

	public void setPORAJUSTE(String pORAJUSTE) {
		PORAJUSTE = pORAJUSTE;
	}

	public String getRECHAZADO() {
		return RECHAZADO;
	}

	public void setRECHAZADO(String rECHAZADO) {
		RECHAZADO = rECHAZADO;
	}

	public String getPORDESCUEN() {
		return PORDESCUEN;
	}

	public void setPORDESCUEN(String pORDESCUEN) {
		PORDESCUEN = pORDESCUEN;
	}

	public String getCERRADA() {
		return CERRADA;
	}

	public void setCERRADA(String cERRADA) {
		CERRADA = cERRADA;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getDIRECTA() {
		return DIRECTA;
	}

	public void setDIRECTA(String dIRECTA) {
		DIRECTA = dIRECTA;
	}

	public String getCOMI_DIFE() {
		return COMI_DIFE;
	}

	public void setCOMI_DIFE(String cOMI_DIFE) {
		COMI_DIFE = cOMI_DIFE;
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

	public String getNVIAJ() {
		return NVIAJ;
	}

	public void setNVIAJ(String nVIAJ) {
		NVIAJ = nVIAJ;
	}

	public String getLIQUIDA() {
		return LIQUIDA;
	}

	public void setLIQUIDA(String lIQUIDA) {
		LIQUIDA = lIQUIDA;
	}

	public String getNROPEDIDO() {
		return NROPEDIDO;
	}

	public void setNROPEDIDO(String nROPEDIDO) {
		NROPEDIDO = nROPEDIDO;
	}

	public String getNROREMITO() {
		return NROREMITO;
	}

	public void setNROREMITO(String nROREMITO) {
		NROREMITO = nROREMITO;
	}

	public String getNROPRESUP() {
		return NROPRESUP;
	}

	public void setNROPRESUP(String nROPRESUP) {
		NROPRESUP = nROPRESUP;
	}

	public String getOBSERVAC() {
		return OBSERVAC;
	}

	public void setOBSERVAC(String oBSERVAC) {
		OBSERVAC = oBSERVAC;
	}

	public String getDESPACHADO() {
		return DESPACHADO;
	}

	public void setDESPACHADO(String dESPACHADO) {
		DESPACHADO = dESPACHADO;
	}

	public String getTEXTLIB() {
		return TEXTLIB;
	}

	public void setTEXTLIB(String tEXTLIB) {
		TEXTLIB = tEXTLIB;
	}

	public String getVA_DTO() {
		return VA_DTO;
	}

	public void setVA_DTO(String vA_DTO) {
		VA_DTO = vA_DTO;
	}

	public String getCUENTA() {
		return CUENTA;
	}

	public void setCUENTA(String cUENTA) {
		CUENTA = cUENTA;
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

	public String getANALISIS() {
		return ANALISIS;
	}

	public void setANALISIS(String aNALISIS) {
		ANALISIS = aNALISIS;
	}

	public String getOBSERV() {
		return OBSERV;
	}

	public void setOBSERV(String oBSERV) {
		OBSERV = oBSERV;
	}

	public Date getFMOV() {
		return FMOV;
	}

	public void setFMOV(Date fMOV) {
		FMOV = fMOV;
	}

	public Date getFECVTO() {
		return FECVTO;
	}

	public void setFECVTO(Date fECVTO) {
		FECVTO = fECVTO;
	}

	public double getTASA() {
		return TASA;
	}

	public void setTASA(double tASA) {
		TASA = tASA;
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

	public double getLIQ_VIA() {
		return LIQ_VIA;
	}

	public void setLIQ_VIA(double lIQ_VIA) {
		LIQ_VIA = lIQ_VIA;
	}

	public double getLIQ_TOT() {
		return LIQ_TOT;
	}

	public void setLIQ_TOT(double lIQ_TOT) {
		LIQ_TOT = lIQ_TOT;
	}

	public double getFLETE() {
		return FLETE;
	}

	public void setFLETE(double fLETE) {
		FLETE = fLETE;
	}

	public double getSUBTOTAL() {
		return SUBTOTAL;
	}

	public void setSUBTOTAL(double sUBTOTAL) {
		SUBTOTAL = sUBTOTAL;
	}

	public double getA_CUENTA() {
		return A_CUENTA;
	}

	public void setA_CUENTA(double a_CUENTA) {
		A_CUENTA = a_CUENTA;
	}

	public double getA_CUENTAD() {
		return A_CUENTAD;
	}

	public void setA_CUENTAD(double a_CUENTAD) {
		A_CUENTAD = a_CUENTAD;
	}

	public double getIMPPAG() {
		return IMPPAG;
	}

	public void setIMPPAG(double iMPPAG) {
		IMPPAG = iMPPAG;
	}

	public double getIMPPAGD() {
		return IMPPAGD;
	}

	public void setIMPPAGD(double iMPPAGD) {
		IMPPAGD = iMPPAGD;
	}

	public double getIMPDESCTO() {
		return IMPDESCTO;
	}

	public void setIMPDESCTO(double iMPDESCTO) {
		IMPDESCTO = iMPDESCTO;
	}

	public double getIMPBONIF() {
		return IMPBONIF;
	}

	public void setIMPBONIF(double iMPBONIF) {
		IMPBONIF = iMPBONIF;
	}

	public double getIMPIVA_1() {
		return IMPIVA_1;
	}

	public void setIMPIVA_1(double iMPIVA_1) {
		IMPIVA_1 = iMPIVA_1;
	}

	public double getIMPIVA_2() {
		return IMPIVA_2;
	}

	public void setIMPIVA_2(double iMPIVA_2) {
		IMPIVA_2 = iMPIVA_2;
	}

	public double getIMPIVA_3() {
		return IMPIVA_3;
	}

	public void setIMPIVA_3(double iMPIVA_3) {
		IMPIVA_3 = iMPIVA_3;
	}

	public double getTDOLAR() {
		return TDOLAR;
	}

	public void setTDOLAR(double tDOLAR) {
		TDOLAR = tDOLAR;
	}

	public double getDOLAR() {
		return DOLAR;
	}

	public void setDOLAR(double dOLAR) {
		DOLAR = dOLAR;
	}

	public double getTEXTO() {
		return TEXTO;
	}

	public void setTEXTO(double tEXTO) {
		TEXTO = tEXTO;
	}

	public int getNROMOVPLANIF() {
		return NROMOVPLANIF;
	}

	public void setNROMOVPLANIF(int nROMOVPLANIF) {
		NROMOVPLANIF = nROMOVPLANIF;
	}

	public int getNROACTUALIZ() {
		return NROACTUALIZ;
	}

	public void setNROACTUALIZ(int nROACTUALIZ) {
		NROACTUALIZ = nROACTUALIZ;
	}
	
	
	

}
