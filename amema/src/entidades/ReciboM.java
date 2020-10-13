package entidades;

import java.io.Serializable;
import java.util.Date;

public class ReciboM implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String NRECIBO, CIA, PREFIJO, TRECIBO, CODCLI, NVIAJ, LIQUIDA, CERRADA, COMI_DIFE, REFERENCIA;
	private String TCOMP01, NFACT01, TCOMP02, NFACT02, TCOMP03, NFACT03, TCOMP04, NFACT04, TCOMP05, NFACT05, TCOMP06, NFACT06;
	private String TCOMP07, NFACT07, TCOMP08, NFACT08, TCOMP09, NFACT09, TCOMP10, NFACT10, TCOMP11, NFACT11, TCOMP12, NFACT12;
	private String TCOMP13, NFACT13, TCOMP14, NFACT14, TCOMP15, NFACT15, TCOMP16, NFACT16, TCOMP17, NFACT17, TCOMP18, NFACT18;
	private String TCOMP19, NFACT19, TCOMP20, NFACT20;
	private Date FRECIBO;
	private double EFECTIVO, CHEQUE, A_CTA, IFACT01, IFACT02, IFACT03, IFACT04, IFACT05, IFACT06, IFACT07, IFACT08, IFACT09, IFACT10;
	private double IFACT11, IFACT12, IFACT13, IFACT14, IFACT15, IFACT16, IFACT17, IFACT18, IFACT19, IFACT20;
	private String NANTI01, NANTI02, NANTI03, NANTI04, NANTI05, NANTI06, NANTI07, NANTI08, NANTI09, NANTI10;
	private double IANTI01, IANTI02, IANTI03, IANTI04, IANTI05, IANTI06, IANTI07, IANTI08, IANTI09, IANTI10;
	private String NCRED01, NCRED02, NCRED03, NCRED04, NCRED05, NCRED06, NCRED07, NCRED08, NCRED09, NCRED10;
	private double ICRED01, ICRED02, ICRED03, ICRED04, ICRED05, ICRED06, ICRED07, ICRED08, ICRED09, ICRED10;
	private double DESCUENTOS, RETENCION, USADO, NROGENDEUDA, DIFDSPAGO;
	
	/* CONSTRUCTOR */
	public ReciboM() {}
	
	public ReciboM(String nroRec, String cia, String prefijo, Date fecha, String tpoRec, String socio, String nroViaj, 
			String liquida, double efectivo, double cheque, double aCta, String cerrada, String comDif, String ref,
			String tpoComp1, String nroFac1, String tpoComp2, String nroFac2, String tpoComp3, String nroFac3, 
			String tpoComp4, String nroFac4, String tpoComp5, String nroFac5, String tpoComp6, String nroFac6, 
			String tpoComp7, String nroFac7, String tpoComp8, String nroFac8, String tpoComp9, String nroFac9, 
			String tpoComp10, String nroFac10, String tpoComp11, String nroFac11, String tpoComp12, String nroFac12, 
			String tpoComp13, String nroFac13, String tpoComp14, String nroFac14, String tpoComp15, String nroFac15, 
			String tpoComp16, String nroFac16, String tpoComp17, String nroFac17, String tpoComp18, String nroFac18,
			String tpoComp19, String nroFac19, String tpoComp20, String nroFac20, double ifact1, double ifact2, double ifact3,
			double ifact4, double ifact5, double ifact6, double ifact7, double ifact8, double ifact9, double ifact10,
			double ifact11, double ifact12, double ifact13, double ifact14, double ifact15, double ifact16, double ifact17,
			double ifact18, double ifact19, double ifact20, String nroAnt1, String nroAnt2, String nroAnt3, String nroAnt4, 
			String nroAnt5, String nroAnt6, String nroAnt7, String nroAnt8, String nroAnt9, String nroAnt10, double iAnt1, 
			double iAnt2, double iAnt3, double iAnt4, double iAnt5, double iAnt6, double iAnt7, double iAnt8, double iAnt9, 
			double iAnt10, String nroCred1, String nroCred2, String nroCred3, String nroCred4, String nroCred5, String nroCred6, 
			String nroCred7, String nroCred8, String nroCred9, String nroCred10, double iCred1, double iCred2, double iCred3, 
			double iCred4, double iCred5, double iCred6, double iCred7, double iCred8, double iCred9, double iCred10, double dscto,
			double retencion, double usado, double nroDeuda, double difPago) {
		this.NRECIBO = nroRec;
		this.CIA = cia;
		this.PREFIJO = prefijo;
		this.FRECIBO = fecha;
		this.TRECIBO = tpoRec;
		this.CODCLI = socio;
		this.NVIAJ = nroViaj;
		this.LIQUIDA = liquida; 
		this.EFECTIVO = efectivo;
		this.CHEQUE = cheque;
		this.A_CTA = aCta;
		this.CERRADA = cerrada;
		this.COMI_DIFE = comDif;
		this.REFERENCIA = ref;
		this.TCOMP01 = tpoComp1; 
		this.NFACT01 = nroFac1; 
		this.TCOMP02 = tpoComp2; 
		this.NFACT02 = nroFac2;
		this.TCOMP03 = tpoComp3; 
		this.NFACT03 = nroFac3;
		this.TCOMP04 = tpoComp4; 
		this.NFACT04 = nroFac4;
		this.TCOMP05 = tpoComp5; 
		this.NFACT05 = nroFac5;
		this.TCOMP06 = tpoComp6; 
		this.NFACT06 = nroFac6;
		this.TCOMP07 = tpoComp7; 
		this.NFACT07 = nroFac7;
		this.TCOMP08 = tpoComp8; 
		this.NFACT08 = nroFac8;
		this.TCOMP09 = tpoComp9; 
		this.NFACT09 = nroFac9;
		this.TCOMP10 = tpoComp10; 
		this.NFACT10 = nroFac10;
		this.TCOMP11 = tpoComp11; 
		this.NFACT11 = nroFac11; 
		this.TCOMP12 = tpoComp12; 
		this.NFACT12 = nroFac12;
		this.TCOMP13 = tpoComp13; 
		this.NFACT13 = nroFac13;
		this.TCOMP14 = tpoComp14; 
		this.NFACT14 = nroFac14;
		this.TCOMP15 = tpoComp15; 
		this.NFACT15 = nroFac15;
		this.TCOMP16 = tpoComp16; 
		this.NFACT16 = nroFac16;
		this.TCOMP17 = tpoComp17; 
		this.NFACT17 = nroFac17;
		this.TCOMP18 = tpoComp18; 
		this.NFACT18 = nroFac18;
		this.TCOMP19 = tpoComp19; 
		this.NFACT19 = nroFac19;
		this.TCOMP20 = tpoComp20; 
		this.NFACT20 = nroFac20;
		this.IFACT01 = ifact1;
		this.IFACT02 = ifact2;
		this.IFACT03 = ifact3;
		this.IFACT04 = ifact4; 
		this.IFACT05 = ifact5; 
		this.IFACT06 = ifact6;
		this.IFACT07 = ifact7; 
		this.IFACT08 = ifact8; 
		this.IFACT09 = ifact9; 
		this.IFACT10 = ifact10;
		this.IFACT11 = ifact11; 
		this.IFACT12 = ifact12;
		this.IFACT13 = ifact13; 
		this.IFACT14 = ifact14; 
		this.IFACT15 = ifact15; 
		this.IFACT16 = ifact16; 
		this.IFACT17 = ifact17;
		this.IFACT18 = ifact18;
		this.IFACT19 = ifact19;
		this.IFACT20 = ifact20;
		this.NANTI01 = nroAnt1;
		this.NANTI02 = nroAnt2;
		this.NANTI03 = nroAnt3;
		this.NANTI04 = nroAnt4;
		this.NANTI05 = nroAnt5;
		this.NANTI06 = nroAnt6;
		this.NANTI07 = nroAnt7; 
		this.NANTI08 = nroAnt8; 
		this.NANTI09 = nroAnt9; 
		this.NANTI10 = nroAnt10;
		this.IANTI01 = iAnt1;
		this.IANTI02 = iAnt2; 
		this.IANTI03 = iAnt3;
		this.IANTI04 = iAnt4;
		this.IANTI05 = iAnt5; 
		this.IANTI06 = iAnt6; 
		this.IANTI07 = iAnt7;
		this.IANTI08 = iAnt8;
		this.IANTI09 = iAnt9;
		this.IANTI10 = iAnt10;
		this.NCRED01 = nroCred1;
		this.NCRED02 = nroCred2;
		this.NCRED03 = nroCred3;
		this.NCRED04 = nroCred4;
		this.NCRED05 = nroCred5; 
		this.NCRED06 = nroCred6;
		this.NCRED07 = nroCred7;
		this.NCRED08 = nroCred8; 
		this.NCRED09 = nroCred9;
		this.NCRED10 = nroCred10;
		this.ICRED01 = iCred1;
		this.ICRED02 = iCred2; 
		this.ICRED03 = iCred3; 
		this.ICRED04 = iCred4;
		this.ICRED05 = iCred5; 
		this.ICRED06 = iCred6;
		this.ICRED07 = iCred7;
		this.ICRED08 = iCred8;
		this.ICRED09 =iCred9;
		this.ICRED10 = iCred10;
		this.DESCUENTOS = dscto;
		this.RETENCION = retencion; 
		this.USADO = usado; 
		this.NROGENDEUDA = nroDeuda; 
		this.DIFDSPAGO = difPago;
	}
	
	/* METODOS */
	
	public String getNRECIBO() {
		return NRECIBO;
	}

	public void setNRECIBO(String nRECIBO) {
		NRECIBO = nRECIBO;
	}

	public String getCIA() {
		return CIA;
	}

	public void setCIA(String cIA) {
		CIA = cIA;
	}

	public String getPREFIJO() {
		return PREFIJO;
	}

	public void setPREFIJO(String pREFIJO) {
		PREFIJO = pREFIJO;
	}

	public String getTRECIBO() {
		return TRECIBO;
	}

	public void setTRECIBO(String tRECIBO) {
		TRECIBO = tRECIBO;
	}

	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
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

	public String getCERRADA() {
		return CERRADA;
	}

	public void setCERRADA(String cERRADA) {
		CERRADA = cERRADA;
	}

	public String getCOMI_DIFE() {
		return COMI_DIFE;
	}

	public void setCOMI_DIFE(String cOMI_DIFE) {
		COMI_DIFE = cOMI_DIFE;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public String getTCOMP01() {
		return TCOMP01;
	}

	public void setTCOMP01(String tCOMP01) {
		TCOMP01 = tCOMP01;
	}

	public String getNFACT01() {
		return NFACT01;
	}

	public void setNFACT01(String nFACT01) {
		NFACT01 = nFACT01;
	}

	public String getTCOMP02() {
		return TCOMP02;
	}

	public void setTCOMP02(String tCOMP02) {
		TCOMP02 = tCOMP02;
	}

	public String getNFACT02() {
		return NFACT02;
	}

	public void setNFACT02(String nFACT02) {
		NFACT02 = nFACT02;
	}

	public String getTCOMP03() {
		return TCOMP03;
	}

	public void setTCOMP03(String tCOMP03) {
		TCOMP03 = tCOMP03;
	}

	public String getNFACT03() {
		return NFACT03;
	}

	public void setNFACT03(String nFACT03) {
		NFACT03 = nFACT03;
	}

	public String getTCOMP04() {
		return TCOMP04;
	}

	public void setTCOMP04(String tCOMP04) {
		TCOMP04 = tCOMP04;
	}

	public String getNFACT04() {
		return NFACT04;
	}

	public void setNFACT04(String nFACT04) {
		NFACT04 = nFACT04;
	}

	public String getTCOMP05() {
		return TCOMP05;
	}

	public void setTCOMP05(String tCOMP05) {
		TCOMP05 = tCOMP05;
	}

	public String getNFACT05() {
		return NFACT05;
	}

	public void setNFACT05(String nFACT05) {
		NFACT05 = nFACT05;
	}

	public String getTCOMP06() {
		return TCOMP06;
	}

	public void setTCOMP06(String tCOMP06) {
		TCOMP06 = tCOMP06;
	}

	public String getNFACT06() {
		return NFACT06;
	}

	public void setNFACT06(String nFACT06) {
		NFACT06 = nFACT06;
	}

	public String getTCOMP07() {
		return TCOMP07;
	}

	public void setTCOMP07(String tCOMP07) {
		TCOMP07 = tCOMP07;
	}

	public String getNFACT07() {
		return NFACT07;
	}

	public void setNFACT07(String nFACT07) {
		NFACT07 = nFACT07;
	}

	public String getTCOMP08() {
		return TCOMP08;
	}

	public void setTCOMP08(String tCOMP08) {
		TCOMP08 = tCOMP08;
	}

	public String getNFACT08() {
		return NFACT08;
	}

	public void setNFACT08(String nFACT08) {
		NFACT08 = nFACT08;
	}

	public String getTCOMP09() {
		return TCOMP09;
	}

	public void setTCOMP09(String tCOMP09) {
		TCOMP09 = tCOMP09;
	}

	public String getNFACT09() {
		return NFACT09;
	}

	public void setNFACT09(String nFACT09) {
		NFACT09 = nFACT09;
	}

	public String getTCOMP10() {
		return TCOMP10;
	}

	public void setTCOMP10(String tCOMP10) {
		TCOMP10 = tCOMP10;
	}

	public String getNFACT10() {
		return NFACT10;
	}

	public void setNFACT10(String nFACT10) {
		NFACT10 = nFACT10;
	}

	public String getTCOMP11() {
		return TCOMP11;
	}

	public void setTCOMP11(String tCOMP11) {
		TCOMP11 = tCOMP11;
	}

	public String getNFACT11() {
		return NFACT11;
	}

	public void setNFACT11(String nFACT11) {
		NFACT11 = nFACT11;
	}

	public String getTCOMP12() {
		return TCOMP12;
	}

	public void setTCOMP12(String tCOMP12) {
		TCOMP12 = tCOMP12;
	}

	public String getNFACT12() {
		return NFACT12;
	}

	public void setNFACT12(String nFACT12) {
		NFACT12 = nFACT12;
	}

	public String getTCOMP13() {
		return TCOMP13;
	}

	public void setTCOMP13(String tCOMP13) {
		TCOMP13 = tCOMP13;
	}

	public String getNFACT13() {
		return NFACT13;
	}

	public void setNFACT13(String nFACT13) {
		NFACT13 = nFACT13;
	}

	public String getTCOMP14() {
		return TCOMP14;
	}

	public void setTCOMP14(String tCOMP14) {
		TCOMP14 = tCOMP14;
	}

	public String getNFACT14() {
		return NFACT14;
	}

	public void setNFACT14(String nFACT14) {
		NFACT14 = nFACT14;
	}

	public String getTCOMP15() {
		return TCOMP15;
	}

	public void setTCOMP15(String tCOMP15) {
		TCOMP15 = tCOMP15;
	}

	public String getNFACT15() {
		return NFACT15;
	}

	public void setNFACT15(String nFACT15) {
		NFACT15 = nFACT15;
	}

	public String getTCOMP16() {
		return TCOMP16;
	}

	public void setTCOMP16(String tCOMP16) {
		TCOMP16 = tCOMP16;
	}

	public String getNFACT16() {
		return NFACT16;
	}

	public void setNFACT16(String nFACT16) {
		NFACT16 = nFACT16;
	}

	public String getTCOMP17() {
		return TCOMP17;
	}

	public void setTCOMP17(String tCOMP17) {
		TCOMP17 = tCOMP17;
	}

	public String getNFACT17() {
		return NFACT17;
	}

	public void setNFACT17(String nFACT17) {
		NFACT17 = nFACT17;
	}

	public String getTCOMP18() {
		return TCOMP18;
	}

	public void setTCOMP18(String tCOMP18) {
		TCOMP18 = tCOMP18;
	}

	public String getNFACT18() {
		return NFACT18;
	}

	public void setNFACT18(String nFACT18) {
		NFACT18 = nFACT18;
	}

	public String getTCOMP19() {
		return TCOMP19;
	}

	public void setTCOMP19(String tCOMP19) {
		TCOMP19 = tCOMP19;
	}

	public String getNFACT19() {
		return NFACT19;
	}

	public void setNFACT19(String nFACT19) {
		NFACT19 = nFACT19;
	}

	public String getTCOMP20() {
		return TCOMP20;
	}

	public void setTCOMP20(String tCOMP20) {
		TCOMP20 = tCOMP20;
	}

	public String getNFACT20() {
		return NFACT20;
	}

	public void setNFACT20(String nFACT20) {
		NFACT20 = nFACT20;
	}

	public Date getFRECIBO() {
		return FRECIBO;
	}

	public void setFRECIBO(Date fRECIBO) {
		FRECIBO = fRECIBO;
	}

	public double getEFECTIVO() {
		return EFECTIVO;
	}

	public void setEFECTIVO(double eFECTIVO) {
		EFECTIVO = eFECTIVO;
	}

	public double getCHEQUE() {
		return CHEQUE;
	}

	public void setCHEQUE(double cHEQUE) {
		CHEQUE = cHEQUE;
	}

	public double getA_CTA() {
		return A_CTA;
	}

	public void setA_CTA(double a_CTA) {
		A_CTA = a_CTA;
	}

	public double getIFACT01() {
		return IFACT01;
	}

	public void setIFACT01(double iFACT01) {
		IFACT01 = iFACT01;
	}

	public double getIFACT02() {
		return IFACT02;
	}

	public void setIFACT02(double iFACT02) {
		IFACT02 = iFACT02;
	}

	public double getIFACT03() {
		return IFACT03;
	}

	public void setIFACT03(double iFACT03) {
		IFACT03 = iFACT03;
	}

	public double getIFACT04() {
		return IFACT04;
	}

	public void setIFACT04(double iFACT04) {
		IFACT04 = iFACT04;
	}

	public double getIFACT05() {
		return IFACT05;
	}

	public void setIFACT05(double iFACT05) {
		IFACT05 = iFACT05;
	}

	public double getIFACT06() {
		return IFACT06;
	}

	public void setIFACT06(double iFACT06) {
		IFACT06 = iFACT06;
	}

	public double getIFACT07() {
		return IFACT07;
	}

	public void setIFACT07(double iFACT07) {
		IFACT07 = iFACT07;
	}

	public double getIFACT08() {
		return IFACT08;
	}

	public void setIFACT08(double iFACT08) {
		IFACT08 = iFACT08;
	}

	public double getIFACT09() {
		return IFACT09;
	}

	public void setIFACT09(double iFACT09) {
		IFACT09 = iFACT09;
	}

	public double getIFACT10() {
		return IFACT10;
	}

	public void setIFACT10(double iFACT10) {
		IFACT10 = iFACT10;
	}

	public double getIFACT11() {
		return IFACT11;
	}

	public void setIFACT11(double iFACT11) {
		IFACT11 = iFACT11;
	}

	public double getIFACT12() {
		return IFACT12;
	}

	public void setIFACT12(double iFACT12) {
		IFACT12 = iFACT12;
	}

	public double getIFACT13() {
		return IFACT13;
	}

	public void setIFACT13(double iFACT13) {
		IFACT13 = iFACT13;
	}

	public double getIFACT14() {
		return IFACT14;
	}

	public void setIFACT14(double iFACT14) {
		IFACT14 = iFACT14;
	}

	public double getIFACT15() {
		return IFACT15;
	}

	public void setIFACT15(double iFACT15) {
		IFACT15 = iFACT15;
	}

	public double getIFACT16() {
		return IFACT16;
	}

	public void setIFACT16(double iFACT16) {
		IFACT16 = iFACT16;
	}

	public double getIFACT17() {
		return IFACT17;
	}

	public void setIFACT17(double iFACT17) {
		IFACT17 = iFACT17;
	}

	public double getIFACT18() {
		return IFACT18;
	}

	public void setIFACT18(double iFACT18) {
		IFACT18 = iFACT18;
	}

	public double getIFACT19() {
		return IFACT19;
	}

	public void setIFACT19(double iFACT19) {
		IFACT19 = iFACT19;
	}

	public double getIFACT20() {
		return IFACT20;
	}

	public void setIFACT20(double iFACT20) {
		IFACT20 = iFACT20;
	}

	public String getNANTI01() {
		return NANTI01;
	}

	public void setNANTI01(String nANTI01) {
		NANTI01 = nANTI01;
	}

	public String getNANTI02() {
		return NANTI02;
	}

	public void setNANTI02(String nANTI02) {
		NANTI02 = nANTI02;
	}

	public String getNANTI03() {
		return NANTI03;
	}

	public void setNANTI03(String nANTI03) {
		NANTI03 = nANTI03;
	}

	public String getNANTI04() {
		return NANTI04;
	}

	public void setNANTI04(String nANTI04) {
		NANTI04 = nANTI04;
	}

	public String getNANTI05() {
		return NANTI05;
	}

	public void setNANTI05(String nANTI05) {
		NANTI05 = nANTI05;
	}

	public String getNANTI06() {
		return NANTI06;
	}

	public void setNANTI06(String nANTI06) {
		NANTI06 = nANTI06;
	}

	public String getNANTI07() {
		return NANTI07;
	}

	public void setNANTI07(String nANTI07) {
		NANTI07 = nANTI07;
	}

	public String getNANTI08() {
		return NANTI08;
	}

	public void setNANTI08(String nANTI08) {
		NANTI08 = nANTI08;
	}

	public String getNANTI09() {
		return NANTI09;
	}

	public void setNANTI09(String nANTI09) {
		NANTI09 = nANTI09;
	}

	public String getNANTI10() {
		return NANTI10;
	}

	public void setNANTI10(String nANTI10) {
		NANTI10 = nANTI10;
	}

	public double getIANTI01() {
		return IANTI01;
	}

	public void setIANTI01(double iANTI01) {
		IANTI01 = iANTI01;
	}

	public double getIANTI02() {
		return IANTI02;
	}

	public void setIANTI02(double iANTI02) {
		IANTI02 = iANTI02;
	}

	public double getIANTI03() {
		return IANTI03;
	}

	public void setIANTI03(double iANTI03) {
		IANTI03 = iANTI03;
	}

	public double getIANTI04() {
		return IANTI04;
	}

	public void setIANTI04(double iANTI04) {
		IANTI04 = iANTI04;
	}

	public double getIANTI05() {
		return IANTI05;
	}

	public void setIANTI05(double iANTI05) {
		IANTI05 = iANTI05;
	}

	public double getIANTI06() {
		return IANTI06;
	}

	public void setIANTI06(double iANTI06) {
		IANTI06 = iANTI06;
	}

	public double getIANTI07() {
		return IANTI07;
	}

	public void setIANTI07(double iANTI07) {
		IANTI07 = iANTI07;
	}

	public double getIANTI08() {
		return IANTI08;
	}

	public void setIANTI08(double iANTI08) {
		IANTI08 = iANTI08;
	}

	public double getIANTI09() {
		return IANTI09;
	}

	public void setIANTI09(double iANTI09) {
		IANTI09 = iANTI09;
	}

	public double getIANTI10() {
		return IANTI10;
	}

	public void setIANTI10(double iANTI10) {
		IANTI10 = iANTI10;
	}

	public String getNCRED01() {
		return NCRED01;
	}

	public void setNCRED01(String nCRED01) {
		NCRED01 = nCRED01;
	}

	public String getNCRED02() {
		return NCRED02;
	}

	public void setNCRED02(String nCRED02) {
		NCRED02 = nCRED02;
	}

	public String getNCRED03() {
		return NCRED03;
	}

	public void setNCRED03(String nCRED03) {
		NCRED03 = nCRED03;
	}

	public String getNCRED04() {
		return NCRED04;
	}

	public void setNCRED04(String nCRED04) {
		NCRED04 = nCRED04;
	}

	public String getNCRED05() {
		return NCRED05;
	}

	public void setNCRED05(String nCRED05) {
		NCRED05 = nCRED05;
	}

	public String getNCRED06() {
		return NCRED06;
	}

	public void setNCRED06(String nCRED06) {
		NCRED06 = nCRED06;
	}

	public String getNCRED07() {
		return NCRED07;
	}

	public void setNCRED07(String nCRED07) {
		NCRED07 = nCRED07;
	}

	public String getNCRED08() {
		return NCRED08;
	}

	public void setNCRED08(String nCRED08) {
		NCRED08 = nCRED08;
	}

	public String getNCRED09() {
		return NCRED09;
	}

	public void setNCRED09(String nCRED09) {
		NCRED09 = nCRED09;
	}

	public String getNCRED10() {
		return NCRED10;
	}

	public void setNCRED10(String nCRED10) {
		NCRED10 = nCRED10;
	}

	public double getICRED01() {
		return ICRED01;
	}

	public void setICRED01(double iCRED01) {
		ICRED01 = iCRED01;
	}

	public double getICRED02() {
		return ICRED02;
	}

	public void setICRED02(double iCRED02) {
		ICRED02 = iCRED02;
	}

	public double getICRED03() {
		return ICRED03;
	}

	public void setICRED03(double iCRED03) {
		ICRED03 = iCRED03;
	}

	public double getICRED04() {
		return ICRED04;
	}

	public void setICRED04(double iCRED04) {
		ICRED04 = iCRED04;
	}

	public double getICRED05() {
		return ICRED05;
	}

	public void setICRED05(double iCRED05) {
		ICRED05 = iCRED05;
	}

	public double getICRED06() {
		return ICRED06;
	}

	public void setICRED06(double iCRED06) {
		ICRED06 = iCRED06;
	}

	public double getICRED07() {
		return ICRED07;
	}

	public void setICRED07(double iCRED07) {
		ICRED07 = iCRED07;
	}

	public double getICRED08() {
		return ICRED08;
	}

	public void setICRED08(double iCRED08) {
		ICRED08 = iCRED08;
	}

	public double getICRED09() {
		return ICRED09;
	}

	public void setICRED09(double iCRED09) {
		ICRED09 = iCRED09;
	}

	public double getICRED10() {
		return ICRED10;
	}

	public void setICRED10(double iCRED10) {
		ICRED10 = iCRED10;
	}

	public double getDESCUENTOS() {
		return DESCUENTOS;
	}

	public void setDESCUENTOS(double dESCUENTOS) {
		DESCUENTOS = dESCUENTOS;
	}

	public double getRETENCION() {
		return RETENCION;
	}

	public void setRETENCION(double rETENCION) {
		RETENCION = rETENCION;
	}

	public double getUSADO() {
		return USADO;
	}

	public void setUSADO(double uSADO) {
		USADO = uSADO;
	}

	public double getNROGENDEUDA() {
		return NROGENDEUDA;
	}

	public void setNROGENDEUDA(double nROGENDEUDA) {
		NROGENDEUDA = nROGENDEUDA;
	}

	public double getDIFDSPAGO() {
		return DIFDSPAGO;
	}

	public void setDIFDSPAGO(double dIFDSPAGO) {
		DIFDSPAGO = dIFDSPAGO;
	}
	
	
	
}
