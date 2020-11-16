package entidades;

import java.io.Serializable;

public class AuxListaMasivo implements Serializable {

	//Variables
	private static final long serialVersionUID = 1L;
	private String periodo, convenio, codcli, empresa, legajo, nombre, concepto, importe;
	
	//Constructores
	public AuxListaMasivo() {}

	public AuxListaMasivo(String periodo, String convenio, String codcli, String empresa, String legajo, String nombre,
			String concepto, String importe) {
		this.periodo = periodo;
		this.convenio = convenio;
		this.codcli = codcli;
		this.empresa = empresa;
		this.legajo = legajo;
		this.nombre = nombre;
		this.concepto = concepto;
		this.importe = importe;
	}
	
	
	//Metodos get y set
	
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

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

}
