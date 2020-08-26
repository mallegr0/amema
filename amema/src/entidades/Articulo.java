package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Articulo implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CGRUPO, CSUBF, NROART, DESART, ENVASE, CPROV, ANALISIS, UNIVTA, MARCA, CAJA, UBICACION, ADUANA;
	private String DESPACHO1, DESPACHO2, DESPACHO3, TIVA;
	private double UNIDAD, TASA, PCOMPRA, PVENTA_1, PVENTA_2, PVENTA_3, PVENTA_4, STOCK_1, STOCK_2, MINIMO_1, MINIMO_2;
	private double VTAPROM_1, HORI_1, HORI_2, PE, VTAPROM_2, PBONIF, PPPC, PPPV, BAK, PFISCAL;
	private Date FECAUM, DESDE_PPPC, HASTA_PPPC, DESDE_PPPV, HASTA_PPPV, FDESPACHO, ULT_CPRA;//44
	
	
	/* CONSTRUCTORES */
	public Articulo() {}
	
	public Articulo(String grupo, String subgrupo, String articulo, String descArt, double unidad, String envase, 
			String codProv, String analisis, String tiva, double tasa, double pCompra, double pvta1, double pvta2,
			double pvta3, double pvta4, Date fecAum, double stock, double stock2, double min1, double min2, double vtaProm,
			double hori1, double hori2, double pe, double vtaProm2, String univta, String marca, double bonif, String caja, double pppc,
			Date pppcDesde, Date pppcHasta, double pppv, Date pppvDesde, Date pppvHasta, String ubicacion, String aduana,
			String despacho1, String despacho2, String despacho3, Date fecDespacho, double bak, double pFiscal, Date ultCpra) {
		
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

	public double getUNIDAD() {
		return UNIDAD;
	}

	public void setUNIDAD(double uNIDAD) {
		UNIDAD = uNIDAD;
	}

	public double getTASA() {
		return TASA;
	}

	public void setTASA(double tASA) {
		TASA = tASA;
	}

	public double getPCOMPRA() {
		return PCOMPRA;
	}

	public void setPCOMPRA(double pCOMPRA) {
		PCOMPRA = pCOMPRA;
	}

	public double getPVENTA_1() {
		return PVENTA_1;
	}

	public void setPVENTA_1(double pVENTA_1) {
		PVENTA_1 = pVENTA_1;
	}

	public double getPVENTA_2() {
		return PVENTA_2;
	}

	public void setPVENTA_2(double pVENTA_2) {
		PVENTA_2 = pVENTA_2;
	}

	public double getPVENTA_3() {
		return PVENTA_3;
	}

	public void setPVENTA_3(double pVENTA_3) {
		PVENTA_3 = pVENTA_3;
	}

	public double getPVENTA_4() {
		return PVENTA_4;
	}

	public void setPVENTA_4(double pVENTA_4) {
		PVENTA_4 = pVENTA_4;
	}

	public double getSTOCK_1() {
		return STOCK_1;
	}

	public void setSTOCK_1(double sTOCK_1) {
		STOCK_1 = sTOCK_1;
	}

	public double getSTOCK_2() {
		return STOCK_2;
	}

	public void setSTOCK_2(double sTOCK_2) {
		STOCK_2 = sTOCK_2;
	}

	public double getMINIMO_1() {
		return MINIMO_1;
	}

	public void setMINIMO_1(double mINIMO_1) {
		MINIMO_1 = mINIMO_1;
	}

	public double getMINIMO_2() {
		return MINIMO_2;
	}

	public void setMINIMO_2(double mINIMO_2) {
		MINIMO_2 = mINIMO_2;
	}

	public double getVTAPROM_1() {
		return VTAPROM_1;
	}

	public void setVTAPROM_1(double vTAPROM_1) {
		VTAPROM_1 = vTAPROM_1;
	}

	public double getHORI_1() {
		return HORI_1;
	}

	public void setHORI_1(double hORI_1) {
		HORI_1 = hORI_1;
	}

	public double getHORI_2() {
		return HORI_2;
	}

	public void setHORI_2(double hORI_2) {
		HORI_2 = hORI_2;
	}

	public double getPE() {
		return PE;
	}

	public void setPE(double pE) {
		PE = pE;
	}

	public double getVTAPROM_2() {
		return VTAPROM_2;
	}

	public void setVTAPROM_2(double vTAPROM_2) {
		VTAPROM_2 = vTAPROM_2;
	}

	public double getPBONIF() {
		return PBONIF;
	}

	public void setPBONIF(double pBONIF) {
		PBONIF = pBONIF;
	}

	public double getPPPC() {
		return PPPC;
	}

	public void setPPPC(double pPPC) {
		PPPC = pPPC;
	}

	public double getPPPV() {
		return PPPV;
	}

	public void setPPPV(double pPPV) {
		PPPV = pPPV;
	}

	public double getBAK() {
		return BAK;
	}

	public void setBAK(double bAK) {
		BAK = bAK;
	}

	public double getPFISCAL() {
		return PFISCAL;
	}

	public void setPFISCAL(double pFISCAL) {
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
