package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Parametros;
import util.ApplicationException;

public class DataParametros {
	
	// COSTRUCTOR
	public DataParametros() {}
	
	// VARIABLES
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	// METODOS PRIVADOS
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close(); 
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	// METODOS PUBLICOS
	
	public String consultaCompctaneto(String ultnrocompes) throws ApplicationException {
		// Variables 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String rta = null; 
		String sql = "SELECT compctaneto FROM parametros WHERE ultnrocompes = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ultnrocompes);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rta = rs.getString("compctaneto");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rta; 
	}
	
	public double consultaCotDolar(String ultnrocompes) throws ApplicationException {
		// Variables 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		double rta = 0; 
		String sql = "SELECT dolar FROM parametros WHERE ultnrocompes = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ultnrocompes);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rta = rs.getDouble("dolar");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rta; 
	}
	
}
