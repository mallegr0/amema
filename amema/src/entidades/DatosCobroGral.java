package entidades;

import java.io.Serializable;

public class DatosCobroGral implements Serializable  {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String nRecibo, fecha, importe; 
	
	/* CONSTRUCTORES */
	public DatosCobroGral() {}

	public DatosCobroGral(String nRecibo, String fecha, String importe) {
		this.nRecibo = nRecibo;
		this.fecha = fecha;
		this.importe = importe;
	}
	
	/* METODOS */ 
	public String getnRecibo() {
		return nRecibo;
	}

	public void setnRecibo(String nRecibo) {
		this.nRecibo = nRecibo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	
}
