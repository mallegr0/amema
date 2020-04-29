package pruebas;

import java.util.ArrayList;


import util.ApplicationException;
import data.Conector;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Conector conn = new Conector();
		
		conn.abrirConn();
		System.out.println("Se abrio la conexion!!");
		
		try {
			conn.cerrarConn();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Se cerro la conexión!!");

		
	}

}
