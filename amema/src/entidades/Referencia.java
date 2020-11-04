package entidades;

import java.io.Serializable;

public class Referencia implements Serializable{
	
	/* variables */
	
	private static final long serialVersionUID = 1L;
	private String CREF, NREF;
	
	/* constructores */
	
	public Referencia() {}
	
	public Referencia(String cod, String nombre) {
		this.CREF = cod;
		this.NREF = nombre;
	}
	
	
	/* metodos */
	
	public String getCREF() {
		return CREF;
	}

	public void setCREF(String cREF) {
		CREF = cREF;
	}

	public String getNREF() {
		return NREF;
	}

	public void setNREF(String nREF) {
		NREF = nREF;
	}
	
	
}
