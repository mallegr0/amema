package pruebas;

import controladores.ctrlUsuario;
import entidades.Usuario;
import util.ApplicationException;

public class Prueba {

	public static void main(String[] args) {
		
		ctrlUsuario cu = new ctrlUsuario();
		
		Usuario u = new Usuario();
		
		u.setUsuario("ame");
		u.setPassword("ame");
		u.setNombreyapellido("ame");
		
		try {
			if(cu.altaUsuario(u) == true) {
				System.out.println("Usuario creado!!");
			}
			else {
				System.out.println("El usuario no se creo!!");
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Hubo un problema al crear el usuario!!");
		}
		
	}
}
