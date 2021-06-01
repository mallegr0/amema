package entidades;

import java.io.Serializable;

public class ParamUltNro implements Serializable {

	//VARIABLEs
	
	private static final long serialVersionUID = 1L;
	private String DescUltNro, letra;
	private int prefijo, ultimonro; 
	private String modulo; 
	
	//CONSTRUCTORES
	 public ParamUltNro() {}

	public ParamUltNro(String descUltNro, String letra, int prefijo, int ultimonro, String modulo) {
		DescUltNro = descUltNro;
		this.letra = letra;
		this.prefijo = prefijo;
		this.ultimonro = ultimonro;
		this.modulo = modulo;
	}
	
	
	//METODOS

	public String getDescUltNro() {
		return DescUltNro;
	}

	public void setDescUltNro(String descUltNro) {
		DescUltNro = descUltNro;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public int getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(int prefijo) {
		this.prefijo = prefijo;
	}

	public int getUltimonro() {
		return ultimonro;
	}

	public void setUltimonro(int ultimonro) {
		this.ultimonro = ultimonro;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
}
