package entidades;

import java.io.Serializable;

public class GaranteMovFijo implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String NroGarante;
	private int NroMovimFijo;
	
	/* CONSTRUCTORES */
	public GaranteMovFijo() {}
	
	public GaranteMovFijo(String garante, int movimiento) {
		this.NroGarante = garante;
		this.NroMovimFijo = movimiento;
	}
	
	/* METODOS */

	public String getNroGarante() {
		return NroGarante;
	}

	public void setNroGarante(String nroGarante) {
		NroGarante = nroGarante;
	}

	public int getNroMovimFijo() {
		return NroMovimFijo;
	}

	public void setNroMovimFijo(int nroMovimF) {
		NroMovimFijo = nroMovimF;
	}
}
