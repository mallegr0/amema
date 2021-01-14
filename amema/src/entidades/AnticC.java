package entidades;

import java.io.Serializable;
import java.util.Date;

public class AnticC implements Serializable{

	//Variables
	private static final long serialVersionUID = 1L;
	private String nrecibo, cia, codcli, nviaj; 
	private Date frecibo; 
	private double monto_a, monto_d; 
	
	//constructores 
	public AnticC() {}

	public AnticC(String nrecibo, String cia, String codcli, String nviaj, Date frecibo, double monto_a,
			double monto_d) {
		this.nrecibo = nrecibo;
		this.cia = cia;
		this.codcli = codcli;
		this.nviaj = nviaj;
		this.frecibo = frecibo;
		this.monto_a = monto_a;
		this.monto_d = monto_d;
	}
	
	//Metodos
	public String getNrecibo() {
		return nrecibo;
	}

	public void setNrecibo(String nrecibo) {
		this.nrecibo = nrecibo;
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

	public String getCodcli() {
		return codcli;
	}

	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}

	public String getNviaj() {
		return nviaj;
	}

	public void setNviaj(String nviaj) {
		this.nviaj = nviaj;
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

	public double getMonto_d() {
		return monto_d;
	}

	public void setMonto_d(double monto_d) {
		this.monto_d = monto_d;
	}
}
