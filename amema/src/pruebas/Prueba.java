package pruebas;

import java.util.ArrayList;

import controladores.CtrlFamilia;
import entidades.Familia;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CtrlFamilia cf = new CtrlFamilia();
		ArrayList<Familia> lista = new ArrayList<>();
		
		/*Metodos a probar*/
		
		Familia flia = new Familia();
		
		flia=cf.consultaFamilia(3);
		
		System.out.println(flia.getCodigo());
		System.out.println(flia.getDescripcion());
		System.out.println(flia.getBonificacion());
		
		/*Listado de familias*/
		lista = cf.listaFamilias("codigo");
		System.out.println("Familias");
		System.out.println("----");
		System.out.println();
		for (Familia fl : lista) {
			System.out.println("Codigo: "+fl.getCodigo());
			System.out.println("Descripcion: "+fl.getDescripcion());
			System.out.println("Bonificacion: "+fl.getBonificacion() );
			System.out.println();
		}
	}

}
