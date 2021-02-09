package entidades;

import java.io.Serializable;
import java.util.Date;

public class AuxAnDeudaSoc implements Serializable {

	//VARIABLES
	private static final long serialVersionUID = 1L;
	private String codcli, nomcli, tipoMov;
	Date fcomp; 
	private String ncomp, tcomp;
	private double importe;
	private String nviaj, ref, convenio; 
	private int analisis;
	private String periodo, saldo;
	private double importePagado; 
	private String obsMov;
	
	//CONSTRUCTORES
	public AuxAnDeudaSoc() {}

	public AuxAnDeudaSoc(String codcli, String nomcli, String tipoMov, Date fcomp, String ncomp, String tcomp,
			double importe, String nviaj, String ref, String convenio, int analisis, String periodo, String saldo,
			double importePagado, String obsMov) {
		this.codcli = codcli;
		this.nomcli = nomcli;
		this.tipoMov = tipoMov;
		this.fcomp = fcomp;
		this.ncomp = ncomp;
		this.tcomp = tcomp;
		this.importe = importe;
		this.nviaj = nviaj;
		this.ref = ref;
		this.convenio = convenio;
		this.analisis = analisis;
		this.periodo = periodo;
		this.saldo = saldo;
		this.importePagado = importePagado;
		this.obsMov = obsMov;
	}
	
	//METODOS
	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public String getNomcli() {
		return nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}

	public Date getFcomp() {
		return fcomp;
	}

	public void setFcomp(Date fcomp) {
		this.fcomp = fcomp;
	}

	public String getNcomp() {
		return ncomp;
	}

	public void setNcomp(String ncomp) {
		this.ncomp = ncomp;
	}

	public String getTcomp() {
		return tcomp;
	}

	public void setTcomp(String tcomp) {
		this.tcomp = tcomp;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getNviaj() {
		return nviaj;
	}

	public void setNviaj(String nviaj) {
		this.nviaj = nviaj;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public int getAnalisis() {
		return analisis;
	}

	public void setAnalisis(int analisis) {
		this.analisis = analisis;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public double getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(double importePagado) {
		this.importePagado = importePagado;
	}

	public String getObsMov() {
		return obsMov;
	}

	public void setObsMov(String obsMov) {
		this.obsMov = obsMov;
	}
}
