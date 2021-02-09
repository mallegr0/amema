package entidades;

import java.io.Serializable;
import java.util.Date;

public class DatosCobroFact implements Serializable {

	//VARIABLES 
	private static final long serialVersionUID = 1L;
	private String tcomp, letra, prefijo, ncomp, obs; 
	private double imppagado, descuento, tasa; 
	private Date fecha; 
	
	//CONSTRUCTORES
	public DatosCobroFact() {}

	public DatosCobroFact(String tcomp, String letra, String prefijo, String ncomp, String obs, double imppagado,
			double descuento, double tasa, Date fecha) {
		this.tcomp = tcomp;
		this.letra = letra;
		this.prefijo = prefijo;
		this.ncomp = ncomp;
		this.obs = obs;
		this.imppagado = imppagado;
		this.descuento = descuento;
		this.tasa = tasa;
		this.fecha = fecha;
	}
	
	//METODOS 
	
	public String getTcomp() {
		return tcomp;
	}

	public void setTcomp(String tcomp) {
		this.tcomp = tcomp;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getNcomp() {
		return ncomp;
	}

	public void setNcomp(String ncomp) {
		this.ncomp = ncomp;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getImppagado() {
		return imppagado;
	}

	public void setImppagado(double imppagado) {
		this.imppagado = imppagado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
