package entidades;

import java.io.Serializable;
import java.sql.Date;

public class AnticipoAnalisisSaldo implements Serializable{

	//VARIABLES
	private static final long serialVersionUID = 1L;
	private String codcli, nomcli, nrecibo;
	private Date frecibo; 
	private double monto_a, sumaApliacado_a; 
	private String nviaj; 
	
	//CONSTRUCTORES
	public AnticipoAnalisisSaldo() {}

	public AnticipoAnalisisSaldo(String codcli, String nomcli, String nrecibo, Date frecibo, double monto_a,
			double sumaApliacado_a, String nviaj) {
		this.codcli = codcli;
		this.nomcli = nomcli;
		this.nrecibo = nrecibo;
		this.frecibo = frecibo;
		this.monto_a = monto_a;
		this.sumaApliacado_a = sumaApliacado_a;
		this.nviaj = nviaj;
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

	public String getNrecibo() {
		return nrecibo;
	}

	public void setNrecibo(String nrecibo) {
		this.nrecibo = nrecibo;
	}

	public Date getFrecibo() {
		return frecibo;
	}

	public void setFrecibo(Date frecibo) {
		this.frecibo = frecibo;
	}

	public double getMonto_a() {
		return monto_a;
	}

	public void setMonto_a(double monto_a) {
		this.monto_a = monto_a;
	}

	public double getSumaApliacado_a() {
		return sumaApliacado_a;
	}

	public void setSumaApliacado_a(double sumaApliacado_a) {
		this.sumaApliacado_a = sumaApliacado_a;
	}

	public String getNviaj() {
		return nviaj;
	}

	public void setNviaj(String nviaj) {
		this.nviaj = nviaj;
	}
}
