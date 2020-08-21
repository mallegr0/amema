package entidades;

import java.io.Serializable;

public class TpoComprobante implements Serializable{
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String CTIPO, DESCTIPO;

	
	/* CONSTRUCTORES */
	public TpoComprobante() {}
	
	public TpoComprobante(String cod, String desc) {
		this.CTIPO = cod;
		this.DESCTIPO = desc;
	}
	
	
	/* METODOS */
	
	public String getCTIPO() {
		return CTIPO;
	}

	public void setCTIPO(String cTIPO) {
		CTIPO = cTIPO;
	}

	public String getDESCTIPO() {
		return DESCTIPO;
	}

	public void setDESCTIPO(String dESCTIPO) {
		DESCTIPO = dESCTIPO;
	}
	


	
}
