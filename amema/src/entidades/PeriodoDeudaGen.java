package entidades;

import java.io.Serializable;
import java.util.Date;

public class PeriodoDeudaGen implements Serializable{

	//Variables
	private static final long serialVersionUID = 1L;
	private int nro_gen_deuda;
	private String Anulado, nroconv, periodo, inform_ingresada, recibos_gen;
	private Date fecha_gen, FechaHasta;
	
	//Constructores
	public PeriodoDeudaGen() {}

	public PeriodoDeudaGen(int nro_gen_deuda, String anulado, String nroconv, String periodo, String inform_ingresada,
			String recibos_gen, Date fecha_gen, Date fechaHasta) {
		this.nro_gen_deuda = nro_gen_deuda;
		this.Anulado = anulado;
		this.nroconv = nroconv;
		this.periodo = periodo;
		this.inform_ingresada = inform_ingresada;
		this.recibos_gen = recibos_gen;
		this.fecha_gen = fecha_gen;
		this.FechaHasta = fechaHasta;
	}
	
	//Metodos
	
	public int getNro_gen_deuda() {
		return nro_gen_deuda;
	}

	public void setNro_gen_deuda(int nro_gen_deuda) {
		this.nro_gen_deuda = nro_gen_deuda;
	}

	public String getAnulado() {
		return Anulado;
	}

	public void setAnulado(String anulado) {
		Anulado = anulado;
	}

	public String getNroconv() {
		return nroconv;
	}

	public void setNroconv(String nroconv) {
		this.nroconv = nroconv;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getInform_ingresada() {
		return inform_ingresada;
	}

	public void setInform_ingresada(String inform_ingresada) {
		this.inform_ingresada = inform_ingresada;
	}

	public String getRecibos_gen() {
		return recibos_gen;
	}

	public void setRecibos_gen(String recibos_gen) {
		this.recibos_gen = recibos_gen;
	}

	public Date getFecha_gen() {
		return fecha_gen;
	}

	public void setFecha_gen(Date fecha_gen) {
		this.fecha_gen = fecha_gen;
	}

	public Date getFechaHasta() {
		return FechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		FechaHasta = fechaHasta;
	}

}
