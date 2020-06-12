package entidades;

import java.io.Serializable;

public class Familia implements Serializable{
	
	/* VARIABLES */
	
	private static final long serialVersionUID = 1L;
	private  String CFAMI, NFAMI;
	private  float BFAMI;
	
	/* CONSTRUCTORES */
	
	public Familia() {}
	
	public Familia(String id, String nombre, float bonif) {
		this.CFAMI = id;
		this.NFAMI = nombre;
		this.BFAMI = bonif;
	}
	
	
	/* METODOS */
	
	public String getCFAMI() {
		return CFAMI;
	}

	public void setCFAMI(String cFAMI) {
		CFAMI = cFAMI;
	}

	public String getNFAMI() {
		return NFAMI;
	}

	public void setNFAMI(String nFAMI) {
		NFAMI = nFAMI;
	}

	public float getBFAMI() {
		return BFAMI;
	}

	public void setBFAMI(float bFAMI) {
		BFAMI = bFAMI;
	}
	
		
}
