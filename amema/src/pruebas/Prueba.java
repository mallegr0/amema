package pruebas;

import java.util.ArrayList;

import controladores.CtrlUsuario;
import util.ApplicationException;
import entidades.Usuario;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CtrlUsuario cu = new CtrlUsuario();
		ArrayList<Usuario> lista = new ArrayList<>();
		
		/*Metodos a probar*/
		
		if(cu.altaUsuario(u)== true){
			System.out.println("El usuario se creo!!");
		}
		else{
			System.out.println("El usuario no se creo!!!");
		}
		
		
		
		/*Listado de usuarios*/
		lista = cu.listaUsuarios("user");
		System.out.println("Usuarios");
		System.out.println("----");
		System.out.println();
		for (Usuario u : lista) {
			System.out.println("Usuario: "+u.getUser());
			System.out.println("Password: "+u.getPassword());
			System.out.println("Nombre y Apellido: "+u.getNombre() );
			System.out.println("E-mail: "+u.getMail());
			System.out.println();
		}
	}

}
