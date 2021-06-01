package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Mov_stoc;
import util.ApplicationException;

public class DataMov_Stoc {
	
	//Constructor
	public DataMov_Stoc() {}
	
	//Variable
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Metodos Privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambioFecha(java.util.Date fec) throws ApplicationException {
		java.sql.Date fecha = new java.sql.Date(fec.getTime()); 
		return fecha; 
	}
	
	//Metodos Publicos
	public boolean altaMovStoc(Mov_stoc m) throws ApplicationException {
		PreparedStatement stmt = null; 
		boolean rta = false; 
		String sql = "INSERT INTO mov_stoc (codart, cdep, fmov, cconcepto, cantidad, ncomp) VALUES (?,?,?,?,?,?)"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getCodart());
			stmt.setString(2, m.getCdep());
			stmt.setDate(3, cambioFecha(m.getFmov()));
			stmt.setString(4, m.getCconcepto());
			stmt.setDouble(5, m.getCantidad());
			stmt.setString(6, m.getNcomp());
			
			if(stmt.executeUpdate() > 0) { rta = true; }
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return rta; 
	}

}
