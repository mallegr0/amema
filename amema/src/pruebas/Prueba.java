package pruebas;


import java.util.ArrayList;

import controladores.CtrlGaranteMovFijo;
import entidades.GaranteMovFijo;
import util.ApplicationException;

public class Prueba {

	public static void main(String[] args) {
		CtrlGaranteMovFijo cg = new CtrlGaranteMovFijo();
		
		try {
			ArrayList<GaranteMovFijo> l = cg.listarGarantesPorMovimientos(18037);
			
			if(l == null) { System.out.println("no hay nada"); }
			else {System.out.println("hay algo");}
		} catch (ApplicationException e) {e.printStackTrace();
		}
	}}