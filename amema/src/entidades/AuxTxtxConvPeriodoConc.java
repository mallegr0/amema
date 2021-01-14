package entidades;

import java.io.Serializable;

public class AuxTxtxConvPeriodoConc implements Serializable {

	//Variables
	private static final long serialVersionUID = 1L;
	private String periodo, convenio, concepto, empresa, legajo, codcli;
	private double importe; 
	
	//Constructores
	public AuxTxtxConvPeriodoConc() {}

	public AuxTxtxConvPeriodoConc(String periodo, String convenio, String concepto, String empresa, String legajo,
			String codcli, double importe) {
		this.periodo = periodo;
		this.convenio = convenio;
		this.concepto = concepto;
		this.empresa = empresa;
		this.legajo = legajo;
		this.codcli = codcli;
		this.importe = importe;
	}
	
	
	//Metodos
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

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

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
}
