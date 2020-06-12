package pruebas;

import java.util.ArrayList;

import controladores.*;
import entidades.Usuario;
import util.ApplicationException;




public class Prueba {

	public static void main(String[] args) {
		CtrlUsuario cu = new CtrlUsuario();
		
		Usuario u = new Usuario();
		
		
		
		try {
			u = cu.consultaUsuario("mallegr0");
			System.out.println("usuario "+u.getLogIn());
			System.out.println("perfil "+cu.consultaPerfil(u.getCperfil()));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}
}