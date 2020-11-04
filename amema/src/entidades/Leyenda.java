package entidades;

import java.io.Serializable;

public class Leyenda implements Serializable{

	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CLEY, NLEY, TLEY;
	
	/* CONSTRUCTORES */
	
	public Leyenda() {}
	
	public Leyenda(String cod, String nombre, String tipo) {
		this.CLEY = cod;
		this.NLEY = nombre;
		this.TLEY = tipo;
	}
	
	
	/* METODOS */

	public String getCLEY() {
		return CLEY;
	}

	public void setCLEY(String cLEY) {
		CLEY = cLEY;
	}

	public String getNLEY() {
		return NLEY;
	}

	public void setNLEY(String nLEY) {
		NLEY = nLEY;
	}

	public String getTLEY() {
		return TLEY;
	}

	public void setTLEY(String tLEY) {
		TLEY = tLEY;
	}
	
	
}
