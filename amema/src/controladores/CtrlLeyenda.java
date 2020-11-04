package controladores;

import java.util.ArrayList;

import data.DataLeyenda;
import entidades.Leyenda;
import util.ApplicationException;

public class CtrlLeyenda {
	
	// Variables
	DataLeyenda dl = null;
	
	// Constructor
	public CtrlLeyenda() {}
	
	// Metodos 
	
	public ArrayList<Leyenda> listarLeyendas() throws ApplicationException {
		dl = new DataLeyenda();
		return dl.listarLeyendas();
	}
}
