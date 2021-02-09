package entidades;

import java.io.Serializable;
import java.util.Date;

public class AnalisisSaldoListado implements Serializable {
	
	//Variables
	private static final long serialVersionUID = 1L;
	private String nviaj, nomcli, codcli, tipomov;
	private Date fcomp;
	private String tcomp, ncomp;
	private double importe; 
	private String ref, saldo, dnrp, cpccp, desccond, ccond, obsmov;
	
	//Constructores
	public AnalisisSaldoListado() {}

	public AnalisisSaldoListado(String nviaj, String nomcli, String codcli, String tipomov, Date fcomp, String tcomp,
			String ncomp, double importe, String ref, String saldo, String dnrp, String cpccp, String desccond,
			String ccond, String obsmov) {
		this.nviaj = nviaj;
		this.nomcli = nomcli;
		this.codcli = codcli;
		this.tipomov = tipomov;
		this.fcomp = fcomp;
		this.tcomp = tcomp;
		this.ncomp = ncomp;
		this.importe = importe;
		this.ref = ref;
		this.saldo = saldo;
		this.dnrp = dnrp;
		this.cpccp = cpccp;
		this.desccond = desccond;
		this.ccond = ccond;
		this.obsmov = obsmov;
	}
	
	//Metodos
	
	public String getNviaj() {
		return nviaj;
	}

	public void setNviaj(String nviaj) {
		this.nviaj = nviaj;
	}

	public String getNomcli() {
		return nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public String getTipomov() {
		return tipomov;
	}

	public void setTipomov(String tipomov) {
		this.tipomov = tipomov;
	}

	public Date getFcomp() {
		return fcomp;
	}

	public void setFcomp(Date fcomp) {
		this.fcomp = fcomp;
	}

	public String getTcomp() {
		return tcomp;
	}

	public void setTcomp(String tcomp) {
		this.tcomp = tcomp;
	}

	public String getNcomp() {
		return ncomp;
	}

	public void setNcomp(String ncomp) {
		this.ncomp = ncomp;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getDnrp() {
		return dnrp;
	}

	public void setDnrp(String dnrp) {
		this.dnrp = dnrp;
	}

	public String getCpccp() {
		return cpccp;
	}

	public void setCpccp(String cpccp) {
		this.cpccp = cpccp;
	}

	public String getDesccond() {
		return desccond;
	}

	public void setDesccond(String desccond) {
		this.desccond = desccond;
	}

	public String getCcond() {
		return ccond;
	}

	public void setCcond(String ccond) {
		this.ccond = ccond;
	}

	public String getObsmov() {
		return obsmov;
	}

	public void setObsmov(String obsmov) {
		this.obsmov = obsmov;
	}
}
