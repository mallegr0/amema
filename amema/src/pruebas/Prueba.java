package pruebas;



import controladores.ctrlUsuario;
import data.Conector;
import entidades.Usuario;
import util.ApplicationException;

public class Prueba {

	public static void main(String[] args) {
		
		Conector conn = new Conector();
		
		conn.abrirConn();
		System.out.println("se abrio la conexion");
		
		
	}
}
