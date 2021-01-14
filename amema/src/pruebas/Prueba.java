package pruebas;


import java.util.ArrayList;

import controladores.CtrlAuxAnDeudaCli;
import entidades.AuxAnDeudaCli;
import util.ApplicationException;


public class Prueba {

	public static void main(String[] args) {
		CtrlAuxAnDeudaCli a = new CtrlAuxAnDeudaCli();
		ArrayList<AuxAnDeudaCli> listado = new ArrayList<AuxAnDeudaCli>();
		
		try {
			listado = a.totalDeudaPeriodoyConvenio("20", "062020");
			for(AuxAnDeudaCli l: listado) {
				System.out.println(l.getCODCLI()+" - "+l.getIMPORTE());
			}
		} catch (ApplicationException e) { e.printStackTrace(); } 
	}
}