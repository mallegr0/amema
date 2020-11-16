package entidades;

import java.io.Serializable;

public class Entrada implements Serializable {

	//Variables
	private static final long serialVersionUID = 1L;
	private String empresa, legajo, fecha, concepto, descConcepto, importe;
	
	//Constructor
	public Entrada() {}

	public Entrada(String empresa, String legajo, String fecha, String concepto, String descConcepto, String importe) {
		this.empresa = empresa;
		this.legajo = legajo;
		this.fecha = fecha;
		this.concepto = concepto;
		this.descConcepto = descConcepto;
		this.importe = importe;
	}
	
	
	//Metodos
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDescConcepto() {
		return descConcepto;
	}

	public void setDescConcepto(String descConcepto) {
		this.descConcepto = descConcepto;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}
}
