package pruebas;

import java.util.ArrayList;


import controladores.CtrlCtactecliente;
import entidades.Ctactecliente;
import util.ApplicationException;

public class Prueba {

	public static void main(String[] args) {

		CtrlCtactecliente cc = new CtrlCtactecliente();
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		
		String cod = "0002";
		java.util.Date fec = new java.util.Date();
		
		java.sql.Date fecsql = new java.sql.Date(fec.getTime());
		
		try {
			lista = cc.listarCtaCtePorSocioYFecha(cod,fecsql);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		if(lista != null) { System.out.println("trajo algo");}
		else { System.out.println("fallo");}
		
		for(Ctactecliente c : lista) {
			System.out.println(c.getCODCLI()+" - "+c.getFMOV());
		}
	}
}