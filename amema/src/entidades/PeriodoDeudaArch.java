package entidades;

import java.io.Serializable;

public class PeriodoDeudaArch implements Serializable{

	//Variables
	private static final long serialVersionUID = 1L;
	private int nro_gen_deuda;
	private String nroconv, perido, nomb_archivo;
	
	
	//Constructores
	public PeriodoDeudaArch() {}

	public PeriodoDeudaArch(int nro_gen_deuda, String nroconv, String perido, String nomb_archivo) {
		this.nro_gen_deuda = nro_gen_deuda;
		this.nroconv = nroconv;
		this.perido = perido;
		this.nomb_archivo = nomb_archivo;
	}
	
	
	//Metodos
	public int getNro_gen_deuda() {
		return nro_gen_deuda;
	}

	public void setNro_gen_deuda(int nro_gen_deuda) {
		this.nro_gen_deuda = nro_gen_deuda;
	}

	public String getNroconv() {
		return nroconv;
	}

	public void setNroconv(String nroconv) {
		this.nroconv = nroconv;
	}

	public String getPerido() {
		return perido;
	}

	public void setPerido(String perido) {
		this.perido = perido;
	}

	public String getNomb_archivo() {
		return nomb_archivo;
	}

	public void setNomb_archivo(String nomb_archivo) {
		this.nomb_archivo = nomb_archivo;
	}

}
