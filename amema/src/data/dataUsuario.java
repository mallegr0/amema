package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import data.Conector;
import entidades.Usuario;
import util.ApplicationException;


public class dataUsuario {
	
	/*	CONSTRUCTOR	*/
	public dataUsuario(){}
	
	/*  variables  */
	Conector conn = new Conector();
	
	/*  METODOS  */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs){
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
	public boolean altaUsuario(Usuario u){
		PreparedStatement stmt = null;
		String sql = 
		
	}



}
