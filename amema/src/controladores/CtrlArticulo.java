package controladores;

import java.util.ArrayList;

import data.DataArticulo;
import entidades.Articulo;
import util.ApplicationException;

public class CtrlArticulo {
	
	/* VARIABLES */
	private DataArticulo da = null;
	
	/* CONSTRUCTOR */ 
	public CtrlArticulo() {}
	
	/* METODOS */
	
	public boolean actualizoStock(String cod, String scod, String art, double stock) throws ApplicationException {
		da = new DataArticulo(); 
		return da.actualizoStock(cod, scod, art, stock);
	}
	
	public Articulo consultarArticulo(String cod, String scod, String art) throws ApplicationException {
		da = new DataArticulo();
		return da.ConsultaArticulo(cod, scod, art);
	}
	
	public ArrayList<Articulo> listarArticulos() throws ApplicationException {
		da = new DataArticulo();
		return da.listarArticulos();
	}

}
