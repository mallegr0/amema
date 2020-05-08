package pruebas;



import controladores.ctrlUsuario;
import entidades.Usuario;
import util.ApplicationException;

public class Prueba {

	public static void main(String[] args) {
		
		ctrlUsuario cu = new ctrlUsuario();
		String user = "mallegr0";
		String pass = "tute2021";
		Usuario u = new Usuario(user,pass, "Matias Allegranza");
		
		
		try {
			if(cu.cambiaPassword(u) == true) {
				System.out.println("La contraseña modificada");
			}
			else {
				System.out.println("La contraseña no se modifico!!");
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Hubo un problema al cambiar la contraseña!!");
		}
		
	}
}
