package controladores;

import data.DataArticulo;
import entidades.Articulo;
import util.ApplicationException;

public class CtrlArticulo {
	
	/* VARIABLES */
	private DataArticulo da = null;
	
	/* CONSTRUCTOR */ 
	public CtrlArticulo() {}
	
	/* METODOS */
	
	
	
	public Articulo consultarArticulo(String cod, String scod, String art) throws ApplicationException {
		da = new DataArticulo();
		return da.ConsultaArticulo(cod, scod, art);
	}

}
