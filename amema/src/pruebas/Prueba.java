package pruebas;

import controladores.*;
import util.ApplicationException;




public class Prueba {

	public static void main(String[] args) {
		String user ="aaaaaaa";
		String pass = "prueba21";
		
		ctrlUsuario cu = new ctrlUsuario();
		
		try {
			System.out.println(cu.cambiaPassword(user, pass));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
