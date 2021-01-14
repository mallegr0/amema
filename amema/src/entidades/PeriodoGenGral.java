package entidades;

import java.io.Serializable;

public class PeriodoGenGral implements Serializable {
	
	//Variables
	private static final long serialVersionUID = 1L;
	private String nroGen, nroConv, Periodo, NombArchivo; 
	
	//Constructores
	public PeriodoGenGral() {}

	public PeriodoGenGral(String nroGen, String nroConv, String periodo, String nombArchivo) {
		this.nroGen = nroGen;
		this.nroConv = nroConv;
		Periodo = periodo;
		NombArchivo = nombArchivo;
	}
	
	
	//Metodos
	
	public String getNroGen() {
		return nroGen;
	}

	public void setNroGen(String nroGen) {
		this.nroGen = nroGen;
	}

	public String getNroConv() {
		return nroConv;
	}

	public void setNroConv(String nroConv) {
		this.nroConv = nroConv;
	}

	public String getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}

	public String getNombArchivo() {
		return NombArchivo;
	}

	public void setNombArchivo(String nombArchivo) {
		NombArchivo = nombArchivo;
	}
}
