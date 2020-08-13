package entidades;

import java.io.Serializable;

public class GaranteMovFijo implements Serializable {
	
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String NroGarante;
	private Integer NroMovimFijo;
	
	/* CONSTRUCTORES */
	public GaranteMovFijo() {}
	
	public GaranteMovFijo(String garante, Integer movimiento) {
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

	public Integer getNroMovimFijo() {
		return NroMovimFijo;
	}

	public void setNroMovimFijo(Integer nroMovimF) {
		NroMovimFijo = nroMovimF;
	}
}
