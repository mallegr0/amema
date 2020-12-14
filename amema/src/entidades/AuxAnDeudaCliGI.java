package entidades;

import java.io.Serializable;
import java.util.Date;

public class AuxAnDeudaCliGI implements Serializable {

	// Variables
	private static final long serialVersionUID = 1L;
	private String codcli, nomcli, tipomov, ncomp, tcomp, nviaj, ref, convenio, periodo, saldo;
	private Date fcomp; 
	private double importe, importepagado; 
	private int analisis; 
	
	//Constructores
	public AuxAnDeudaCliGI() { }

	public AuxAnDeudaCliGI(String codcli, String nomcli, String tipomov, String ncomp, String tcomp, String nviaj,
			String ref, String convenio, String periodo, String saldo, Date fcomp, double importe, double importepagado,
			int analisis) {
		this.codcli = codcli;
		this.nomcli = nomcli;
		this.tipomov = tipomov;
		this.ncomp = ncomp;
		this.tcomp = tcomp;
		this.nviaj = nviaj;
		this.ref = ref;
		this.convenio = convenio;
		this.periodo = periodo;
		this.saldo = saldo;
		this.fcomp = fcomp;
		this.importe = importe;
		this.importepagado = importepagado;
		this.analisis = analisis;
	}
	
	//Metodos

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

	public String getTipomov() {
		return tipomov;
	}

	public void setTipomov(String tipomov) {
		this.tipomov = tipomov;
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

	public Date getFcomp() {
		return fcomp;
	}

	public void setFcomp(Date fcomp) {
		this.fcomp = fcomp;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImportepagado() {
		return importepagado;
	}

	public void setImportepagado(double importepagado) {
		this.importepagado = importepagado;
	}

	public int getAnalisis() {
		return analisis;
	}

	public void setAnalisis(int analisis) {
		this.analisis = analisis;
	}
}
