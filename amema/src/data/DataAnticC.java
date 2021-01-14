package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.AnticC;
import util.ApplicationException;

public class DataAnticC {
	
	//variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Constructores
	public DataAnticC() { }
	
	//Metodos privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException{
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	
	//Metodos publicos
	public boolean bajaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM antic_c WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nrec);
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public AnticC consultaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		AnticC anticipo = null; 
		String sql = "SELECT * FROM antic_c WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nrec);

			rs = stmt.executeQuery(); 
			if(rs != null) {
				while(rs.next()){
					anticipo = new AnticC(); 
					anticipo.setNrecibo(rs.getString("nrecibo"));
					anticipo.setCia(rs.getString("cia"));
					anticipo.setCodcli(rs.getString("codcli"));
					anticipo.setNviaj(rs.getString("nviaj"));
					anticipo.setFrecibo(rs.getDate("frecibo"));
					anticipo.setMonto_a(rs.getDouble("monto_a"));
					anticipo.setMonto_d(rs.getDouble("monto_d"));
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, null); }
		return anticipo; 
	}
}
