package entidades;

import java.io.Serializable;

public class VentasGral implements Serializable {
	/* VARIABLES */
	private static final long serialVersionUID = 1L;
	private String cliente, nromov, articulo, cuotas, cantidad, importe, fecDesde, fecHasta, modoGeneracion, referencia, analisis;
	private String estado, convenio, nroCheque, bancoCheque, impCheque, cancelaDeuda, impCancelaDeuda, observaciones;
	
	
	/* CONSTRUCTORES */
	public VentasGral() {}
	
	public VentasGral(String cliente, String nromov, String articulo, String cuotas, String cantidad, String importe, String fecDesde, String fecHasta, String modoGeneracion, String  referencia, String analisis,
			String estado, String convenio, String nroCheque, String bancoCheque, String impCheque, String cancelaDeuda, String impCancelaDeuda, String observaciones) {
		this.cliente = cliente;
		this.nromov = nromov;
		this.articulo = articulo;
		this.cuotas = cuotas;
		this.cantidad = cantidad;
		this.importe = importe;
		this.fecDesde = fecDesde;
		this.fecHasta = fecHasta;
		this.modoGeneracion = modoGeneracion;
		this.referencia = referencia;
		this.analisis = analisis;
		this.estado = estado;
		this.convenio = convenio;
		this.nroCheque = nroCheque;
		this.bancoCheque = bancoCheque;
		this.impCheque = impCheque;
		this.cancelaDeuda = cancelaDeuda;
		this.impCancelaDeuda = impCancelaDeuda;
		this.observaciones = observaciones;
	}
	
	/* METODOS */

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getNromov() {
		return nromov;
	}

	public void setNromov(String nromov) {
		this.nromov = nromov;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(String fecDesde) {
		this.fecDesde = fecDesde;
	}

	public String getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(String fecHasta) {
		this.fecHasta = fecHasta;
	}

	public String getModoGeneracion() {
		return modoGeneracion;
	}

	public void setModoGeneracion(String modoGeneracion) {
		this.modoGeneracion = modoGeneracion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getAnalisis() {
		return analisis;
	}

	public void setAnalisis(String analisis) {
		this.analisis = analisis;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getNroCheque() {
		return nroCheque;
	}

	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	public String getBancoCheque() {
		return bancoCheque;
	}

	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	public String getImpCheque() {
		return impCheque;
	}

	public void setImpCheque(String impCheque) {
		this.impCheque = impCheque;
	}

	public String getCancelaDeuda() {
		return cancelaDeuda;
	}

	public void setCancelaDeuda(String cancelaDeuda) {
		this.cancelaDeuda = cancelaDeuda;
	}

	public String getImpCancelaDeuda() {
		return impCancelaDeuda;
	}

	public void setImpCancelaDeuda(String impCancelaDeuda) {
		this.impCancelaDeuda = impCancelaDeuda;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
