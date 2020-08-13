package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Articulo implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CGRUPO, CSUBF, NROART, DESART, ENVASE, CPROV, ANALISIS, UNIVTA, MARCA, CAJA, UBICACION, ADUANA;
	private String DESPACHO1, DESPACHO2, DESPACHO3, TIVA;
	private Double UNIDAD, TASA, PCOMPRA, PVENTA_1, PVENTA_2, PVENTA_3, PVENTA_4, STOCK_1, STOCK_2, MINIMO_1, MINIMO_2;
	private Double VTAPROM_1, HORI_1, HORI_2, PE, VTAPROM_2, PBONIF, PPPC, PPPV, BAK, PFISCAL;
	private Date FECAUM, DESDE_PPPC, HASTA_PPPC, DESDE_PPPV, HASTA_PPPV, FDESPACHO, ULT_CPRA;//44
	
	
	/* CONSTRUCTORES */
	public Articulo() {}
	
	public Articulo(String grupo, String subgrupo, String articulo, String descArt, Double unidad, String envase, 
			String codProv, String analisis, String tiva, Double tasa, Double pCompra, Double pvta1, Double pvta2,
			Double pvta3, Double pvta4, Date fecAum, Double stock, Double stock2, Double min1, Double min2, Double vtaProm,
			Double hori1, Double hori2, Double pe, Double vtaProm2, String univta, String marca, Double bonif, String caja, Double pppc,
			Date pppcDesde, Date pppcHasta, Double pppv, Date pppvDesde, Date pppvHasta, String ubicacion, String aduana,
			String despacho1, String despacho2, String despacho3, Date fecDespacho, Double bak, Double pFiscal, Date ultCpra) {
		
		this.CGRUPO = grupo;
		this.CSUBF = subgrupo;
		this.NROART = articulo;
		this.DESART = descArt;
		this.UNIDAD = unidad;
		this.ENVASE = envase;
		this.CPROV = codProv;
		this.ANALISIS = analisis;
		this.TIVA = tiva;
		this.TASA = tasa;
		this.PCOMPRA = pCompra;
		this.PVENTA_1 = pvta1;
		this.PVENTA_2 = pvta2;
		this.PVENTA_3 = pvta3;
		this.PVENTA_4 = pvta4;
		this.FECAUM = fecAum;
		this.STOCK_1 = stock;
		this.STOCK_2 = stock2;
		this.MINIMO_1 = min1;
		this.MINIMO_2 = min2;
		this.VTAPROM_1 = vtaProm;
		this.HORI_1 = hori1;
		this.HORI_2 = hori2;
		this.PE = pe;
		this.VTAPROM_2 = vtaProm2;
		this.UNIVTA = univta;
		this.MARCA = marca;
		this.PBONIF = bonif;
		this.CAJA = caja;
		this.PPPC = pppc;
		this.DESDE_PPPC = pppcDesde;
		this.HASTA_PPPC = pppcHasta;
		this.PPPV = pppv;
		this.DESDE_PPPV = pppvDesde;
		this.HASTA_PPPV = pppvHasta;
		this.UBICACION = ubicacion;
		this.ADUANA = aduana;
		this.DESPACHO1 = despacho1;
		this.DESPACHO2 = despacho2;
		this.DESPACHO3 = despacho3;
		this.FDESPACHO = fecDespacho;
		this.BAK = bak;
		this.PFISCAL = pFiscal;
		this.ULT_CPRA = ultCpra;	
	}

	
	
	
	/* METODOS */
	public String getCGRUPO() {
		return CGRUPO;
	}

	public void setCGRUPO(String cGRUPO) {
		CGRUPO = cGRUPO;
	}

	public String getCSUBF() {
		return CSUBF;
	}

	public void setCSUBF(String cSUBF) {
		CSUBF = cSUBF;
	}

	public String getNROART() {
		return NROART;
	}

	public void setNROART(String nROART) {
		NROART = nROART;
	}

	public String getDESART() {
		return DESART;
	}

	public void setDESART(String dESART) {
		DESART = dESART;
	}

	public String getENVASE() {
		return ENVASE;
	}

	public void setENVASE(String eNVASE) {
		ENVASE = eNVASE;
	}

	public String getCPROV() {
		return CPROV;
	}

	public void setCPROV(String cPROV) {
		CPROV = cPROV;
	}

	public String getANALISIS() {
		return ANALISIS;
	}

	public void setANALISIS(String aNALISIS) {
		ANALISIS = aNALISIS;
	}

	public String getUNIVTA() {
		return UNIVTA;
	}

	public void setUNIVTA(String uNIVTA) {
		UNIVTA = uNIVTA;
	}

	public String getMARCA() {
		return MARCA;
	}

	public void setMARCA(String mARCA) {
		MARCA = mARCA;
	}

	public String getCAJA() {
		return CAJA;
	}

	public void setCAJA(String cAJA) {
		CAJA = cAJA;
	}

	public String getUBICACION() {
		return UBICACION;
	}

	public void setUBICACION(String uBICACION) {
		UBICACION = uBICACION;
	}

	public String getADUANA() {
		return ADUANA;
	}

	public void setADUANA(String aDUANA) {
		ADUANA = aDUANA;
	}

	public String getDESPACHO1() {
		return DESPACHO1;
	}

	public void setDESPACHO1(String dESPACHO1) {
		DESPACHO1 = dESPACHO1;
	}

	public String getDESPACHO2() {
		return DESPACHO2;
	}

	public void setDESPACHO2(String dESPACHO2) {
		DESPACHO2 = dESPACHO2;
	}

	public String getDESPACHO3() {
		return DESPACHO3;
	}

	public void setDESPACHO3(String dESPACHO3) {
		DESPACHO3 = dESPACHO3;
	}

	public String getTIVA() {
		return TIVA;
	}

	public void setTIVA(String tIVA) {
		TIVA = tIVA;
	}

	public Double getUNIDAD() {
		return UNIDAD;
	}

	public void setUNIDAD(Double uNIDAD) {
		UNIDAD = uNIDAD;
	}

	public Double getTASA() {
		return TASA;
	}

	public void setTASA(Double tASA) {
		TASA = tASA;
	}

	public Double getPCOMPRA() {
		return PCOMPRA;
	}

	public void setPCOMPRA(Double pCOMPRA) {
		PCOMPRA = pCOMPRA;
	}

	public Double getPVENTA_1() {
		return PVENTA_1;
	}

	public void setPVENTA_1(Double pVENTA_1) {
		PVENTA_1 = pVENTA_1;
	}

	public Double getPVENTA_2() {
		return PVENTA_2;
	}

	public void setPVENTA_2(Double pVENTA_2) {
		PVENTA_2 = pVENTA_2;
	}

	public Double getPVENTA_3() {
		return PVENTA_3;
	}

	public void setPVENTA_3(Double pVENTA_3) {
		PVENTA_3 = pVENTA_3;
	}

	public Double getPVENTA_4() {
		return PVENTA_4;
	}

	public void setPVENTA_4(Double pVENTA_4) {
		PVENTA_4 = pVENTA_4;
	}

	public Double getSTOCK_1() {
		return STOCK_1;
	}

	public void setSTOCK_1(Double sTOCK_1) {
		STOCK_1 = sTOCK_1;
	}

	public Double getSTOCK_2() {
		return STOCK_2;
	}

	public void setSTOCK_2(Double sTOCK_2) {
		STOCK_2 = sTOCK_2;
	}

	public Double getMINIMO_1() {
		return MINIMO_1;
	}

	public void setMINIMO_1(Double mINIMO_1) {
		MINIMO_1 = mINIMO_1;
	}

	public Double getMINIMO_2() {
		return MINIMO_2;
	}

	public void setMINIMO_2(Double mINIMO_2) {
		MINIMO_2 = mINIMO_2;
	}

	public Double getVTAPROM_1() {
		return VTAPROM_1;
	}

	public void setVTAPROM_1(Double vTAPROM_1) {
		VTAPROM_1 = vTAPROM_1;
	}

	public Double getHORI_1() {
		return HORI_1;
	}

	public void setHORI_1(Double hORI_1) {
		HORI_1 = hORI_1;
	}

	public Double getHORI_2() {
		return HORI_2;
	}

	public void setHORI_2(Double hORI_2) {
		HORI_2 = hORI_2;
	}

	public Double getPE() {
		return PE;
	}

	public void setPE(Double pE) {
		PE = pE;
	}

	public Double getVTAPROM_2() {
		return VTAPROM_2;
	}

	public void setVTAPROM_2(Double vTAPROM_2) {
		VTAPROM_2 = vTAPROM_2;
	}

	public Double getPBONIF() {
		return PBONIF;
	}

	public void setPBONIF(Double pBONIF) {
		PBONIF = pBONIF;
	}

	public Double getPPPC() {
		return PPPC;
	}

	public void setPPPC(Double pPPC) {
		PPPC = pPPC;
	}

	public Double getPPPV() {
		return PPPV;
	}

	public void setPPPV(Double pPPV) {
		PPPV = pPPV;
	}

	public Double getBAK() {
		return BAK;
	}

	public void setBAK(Double bAK) {
		BAK = bAK;
	}

	public Double getPFISCAL() {
		return PFISCAL;
	}

	public void setPFISCAL(Double pFISCAL) {
		PFISCAL = pFISCAL;
	}

	public Date getFECAUM() {
		return FECAUM;
	}

	public void setFECAUM(Date fECAUM) {
		FECAUM = fECAUM;
	}

	public Date getDESDE_PPPC() {
		return DESDE_PPPC;
	}

	public void setDESDE_PPPC(Date dESDE_PPPC) {
		DESDE_PPPC = dESDE_PPPC;
	}

	public Date getHASTA_PPPC() {
		return HASTA_PPPC;
	}

	public void setHASTA_PPPC(Date hASTA_PPPC) {
		HASTA_PPPC = hASTA_PPPC;
	}

	public Date getDESDE_PPPV() {
		return DESDE_PPPV;
	}

	public void setDESDE_PPPV(Date dESDE_PPPV) {
		DESDE_PPPV = dESDE_PPPV;
	}

	public Date getHASTA_PPPV() {
		return HASTA_PPPV;
	}

	public void setHASTA_PPPV(Date hASTA_PPPV) {
		HASTA_PPPV = hASTA_PPPV;
	}

	public Date getFDESPACHO() {
		return FDESPACHO;
	}

	public void setFDESPACHO(Date fDESPACHO) {
		FDESPACHO = fDESPACHO;
	}

	public Date getULT_CPRA() {
		return ULT_CPRA;
	}

	public void setULT_CPRA(Date uLT_CPRA) {
		ULT_CPRA = uLT_CPRA;
	}
	
	
	
	
	
	
}
