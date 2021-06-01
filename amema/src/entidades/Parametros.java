package entidades;

import java.io.Serializable;

public class Parametros implements Serializable {

	// VARIABLES
	private static final long serialVersionUID = 1L;
	private String ultnrocompes;
	private double dolar, iva1, iva2;
	private String compctaneto, compctaiva; 
	
	// CONSTRUCTORES
	public Parametros() {}

	public Parametros(String ultnrocompes, double dolar, double iva1, double iva2, String compctaneto,
			String compctaiva) {
		this.ultnrocompes = ultnrocompes;
		this.dolar = dolar;
		this.iva1 = iva1;
		this.iva2 = iva2;
		this.compctaneto = compctaneto;
		this.compctaiva = compctaiva;
	}
	
	
	// METODOS
	public String getUltnrocompes() {
		return ultnrocompes;
	}

	public void setUltnrocompes(String ultnrocompes) {
		this.ultnrocompes = ultnrocompes;
	}

	public double getDolar() {
		return dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public double getIva1() {
		return iva1;
	}

	public void setIva1(double iva1) {
		this.iva1 = iva1;
	}

	public double getIva2() {
		return iva2;
	}

	public void setIva2(double iva2) {
		this.iva2 = iva2;
	}

	public String getCompctaneto() {
		return compctaneto;
	}

	public void setCompctaneto(String compctaneto) {
		this.compctaneto = compctaneto;
	}

	public String getCompctaiva() {
		return compctaiva;
	}

	public void setCompctaiva(String compctaiva) {
		this.compctaiva = compctaiva;
	}
}
