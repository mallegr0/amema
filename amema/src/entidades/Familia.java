package entidades;

import java.io.Serializable;

public class Familia implements Serializable{
	
	/*VARIABLES*/
	
	private static final long serialVersionUID = 1L;
	private int codigo, bonificacion;
	private String descripcion;
	
	/*CONSTRUCTORES*/
	
	public Familia() {}
	
	public Familia(int c, String d, int b){
		this.codigo = c;
		this.descripcion = d;
		this.bonificacion = b;
	}
	
	/*METODOS*/

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
}
