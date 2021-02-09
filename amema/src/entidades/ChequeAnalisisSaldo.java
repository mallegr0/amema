package entidades;

import java.io.Serializable;

public class ChequeAnalisisSaldo implements Serializable {

	//VARIABLES
	private static final long serialVersionUID = 1L;
	private String codcli, nomcli, nviaj;
	private double monto; 
	
	//CONSTRUCTORES
	public ChequeAnalisisSaldo() {}

	public ChequeAnalisisSaldo(String codcli, String nomcli, String nviaj, double monto) {
		this.codcli = codcli;
		this.nomcli = nomcli;
		this.nviaj = nviaj;
		this.monto = monto;
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

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
}
