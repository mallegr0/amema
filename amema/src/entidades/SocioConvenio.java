package entidades;

import java.io.Serializable;

public class SocioConvenio implements Serializable{
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CODCLI, ConVta, Conc1, Conc2, Conc3, GenInt, CODARTINT;
	private double Tope1, Tope2, Tope3, TasaInt;
	
	/* CONSTRUCTORES */
	public SocioConvenio() {}
	
	public SocioConvenio(String cod, String concepto, String c1, double t1, String c2, double t2, String c3, double t3,
			String generaInt, double tasa, String codInt) {
		this.CODCLI = cod;
		this.ConVta = concepto;
		this.Conc1 = c1;
		this.Tope1 = t1;
		this.Conc2 = c2;
		this.Tope2 = t2;
		this.Conc3 = c3;
		this.Tope3 = t3;
		this.GenInt = generaInt;
		this.TasaInt = tasa;
		this.CODARTINT = codInt;
	}
	
	
	/* METODOS */
	
	public String getCODCLI() {
		return CODCLI;
	}

	public void setCODCLI(String cODCLI) {
		CODCLI = cODCLI;
	}

	public String getConVta() {
		return ConVta;
	}

	public void setConVta(String conVta) {
		ConVta = conVta;
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

	public double getTope1() {
		return Tope1;
	}

	public void setTope1(double tope1) {
		Tope1 = tope1;
	}

	public double getTope2() {
		return Tope2;
	}

	public void setTope2(double tope2) {
		Tope2 = tope2;
	}

	public double getTope3() {
		return Tope3;
	}

	public void setTope3(double tope3) {
		Tope3 = tope3;
	}

	public double getTasaInt() {
		return TasaInt;
	}

	public void setTasaInt(double tasaInt) {
		TasaInt = tasaInt;
	}
}
