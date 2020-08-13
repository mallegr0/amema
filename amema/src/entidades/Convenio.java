package entidades;

import java.io.Serializable;
import java.sql.Date;

public class Convenio implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CCOND, DESCOND, TIPOVTO, Conc1, Conc2, Conc3, GenInt, CODARTINT, IngCobro;
	private Integer DIAS;
	private Date FECVTO;
	private Double PORBONIF, Tope1, Tope2, Tope3, TasaInt;
	
	/* CONSTRUCTORES */
	public Convenio() {}
	
	public Convenio(String id, String nombre, String vto, Integer dias, Date fec, Double bonif, String cpto1, String cpto2,
			String cpto3, Double t1, Double t2, double t3, String intereses, Double tasa, String codigo,String cobro) {
		
		this.CCOND = id;
		this.DESCOND = nombre;
		this.TIPOVTO = vto;
		this.DIAS = dias;
		this.FECVTO = fec;
		this.PORBONIF = bonif;
		this.Conc1 = cpto1;
		this.Conc2 = cpto2;
		this.Conc3 = cpto3;
		this.Tope1 = t1;
		this.Tope2= t2;
		this.Tope3 = t3;
		this.GenInt = intereses;
		this.TasaInt = tasa;
		this.CODARTINT = codigo;
		this.IngCobro = cobro;
	}

	
	/* METODOS */
	
	
	public String getCCOND() {
		return CCOND;
	}

	public void setCCOND(String cCOND) {
		CCOND = cCOND;
	}

	public String getDESCOND() {
		return DESCOND;
	}

	public void setDESCOND(String dESCOND) {
		DESCOND = dESCOND;
	}

	public String getTIPOVTO() {
		return TIPOVTO;
	}

	public void setTIPOVTO(String tIPOVTO) {
		TIPOVTO = tIPOVTO;
	}

	public String getConc1() {
		return Conc1;
	}

	public void setConc1(String conc1) {
		Conc1 = conc1;
	}

	public String getConc2() {
		return Conc2;
	}

	public void setConc2(String conc2) {
		Conc2 = conc2;
	}

	public String getConc3() {
		return Conc3;
	}

	public void setConc3(String conc3) {
		Conc3 = conc3;
	}

	public String getGenInt() {
		return GenInt;
	}

	public void setGenInt(String genInt) {
		GenInt = genInt;
	}

	public String getCODARTINT() {
		return CODARTINT;
	}

	public void setCODARTINT(String cODARTINT) {
		CODARTINT = cODARTINT;
	}

	public String getIngCobro() {
		return IngCobro;
	}

	public void setIngCobro(String ingCobro) {
		IngCobro = ingCobro;
	}

	public Integer getDIAS() {
		return DIAS;
	}

	public void setDIAS(Integer dIAS) {
		DIAS = dIAS;
	}

	public Date getFECVTO() {
		return FECVTO;
	}

	public void setFECVTO(Date fECVTO) {
		FECVTO = fECVTO;
	}

	public Double getPORBONIF() {
		return PORBONIF;
	}

	public void setPORBONIF(Double pORBONIF) {
		PORBONIF = pORBONIF;
	}

	public Double getTope1() {
		return Tope1;
	}

	public void setTope1(Double tope1) {
		Tope1 = tope1;
	}

	public Double getTope2() {
		return Tope2;
	}

	public void setTope2(Double tope2) {
		Tope2 = tope2;
	}

	public Double getTope3() {
		return Tope3;
	}

	public void setTope3(Double tope3) {
		Tope3 = tope3;
	}

	public Double getTasaInt() {
		return TasaInt;
	}

	public void setTasaInt(Double tasaInt) {
		TasaInt = tasaInt;
	}
	
	

	
	
}
