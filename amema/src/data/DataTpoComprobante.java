package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.TpoComprobante;
import util.ApplicationException;

public class DataTpoComprobante {
	
	/* CONSTRUCTOR */
	public DataTpoComprobante() {}
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	
	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	// alta
	//baja
	//modifica

	public TpoComprobante consultaTComprobante(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		TpoComprobante r = null;
		String sql = "SELECT * FROM TIPOCOMPROB WHERE CTIPO = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					r = new TpoComprobante();
					r.setCTIPO(rs.getString("CTIPO"));
					r.setDESCTIPO(rs.getString("DESCTIPO"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return r;
	} 
	//listar
	
	
}
