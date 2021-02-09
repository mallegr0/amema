package entidades;

import java.io.Serializable;
import java.sql.Date;

public class FacturaAnalisisSaldo implements Serializable {

	// VARIABLES
	
	private static final long serialVersionUID = 1L;
	private String codcli, nomcli, nviaj, constancia; 
	private Date fec_ret; 
	private String nret; 
	private double monto_a;
	
	//CONSTRUCTORES
	public FacturaAnalisisSaldo() {}

	public FacturaAnalisisSaldo(String codcli, String nomcli, String nviaj, String constancia, Date fec_ret,
			String nret, double monto_a) {
		this.codcli = codcli;
		this.nomcli = nomcli;
		this.nviaj = nviaj;
		this.constancia = constancia;
		this.fec_ret = fec_ret;
		this.nret = nret;
		this.monto_a = monto_a;
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

	public String getNviaj() {
		return nviaj;
	}

	public void setNviaj(String nviaj) {
		this.nviaj = nviaj;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public Date getFec_ret() {
		return fec_ret;
	}

	public void setFec_ret(Date fec_ret) {
		this.fec_ret = fec_ret;
	}

	public String getNret() {
		return nret;
	}

	public void setNret(String nret) {
		this.nret = nret;
	}

	public double getMonto_a() {
		return monto_a;
	}

	public void setMonto_a(double monto_a) {
		this.monto_a = monto_a;
	}
}
