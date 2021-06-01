package entidades;

import java.io.Serializable;
import java.util.Date;

public class ActualizMasivas implements Serializable{

	// 	VARIABLES
	
	private static final long serialVersionUID = 1L;
	private int NroActualiz; 
	private Date FechaAct, FechaDesde, FechaHasta; 
	private String anulado; 
	
	//  CONSTRUCTORES
	public ActualizMasivas() {}

	public ActualizMasivas(int nroActualiz, Date fechaAct, Date fechaDesde, Date fechaHasta, String anulado) {
		NroActualiz = nroActualiz;
		FechaAct = fechaAct;
		FechaDesde = fechaDesde;
		FechaHasta = fechaHasta;
		this.anulado = anulado;
	}
	
	
	//   METODOS

	public int getNroActualiz() {
		return NroActualiz;
	}

	public void setNroActualiz(int nroActualiz) {
		NroActualiz = nroActualiz;
	}

	public Date getFechaAct() {
		return FechaAct;
	}

	public void setFechaAct(Date fechaAct) {
		FechaAct = fechaAct;
	}

	public Date getFechaDesde() {
		return FechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		FechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return FechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		FechaHasta = fechaHasta;
	}

	public String getAnulado() {
		return anulado;
	}

	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}
}
