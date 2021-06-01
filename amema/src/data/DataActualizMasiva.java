package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.ActualizMasivas;
import util.ApplicationException;

public class DataActualizMasiva {
	
	// VARIABLES
	Connection conn = PoolConection.getInstance().abrirConexion(); 
	
	
	//CONSTRUCTOR
	public DataActualizMasiva() {}
	
	//METODOS PRIVADOS
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fec) throws ApplicationException {
		java.sql.Date fecha = new java.sql.Date(fec.getTime()); 
		return fecha; 
	}
	
	
	//METODOS PUBLICOS
	
	//Alta
	public boolean altaActualizMasiva(ActualizMasivas a) throws ApplicationException {
		PreparedStatement stmt = null; 
		boolean r; 
		String sql = "INSERT INTO actualizmasivas "
				+ "(nroactualiz, fechaact, fechadesde, fechahasta, anulada) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getNroActualiz());
			stmt.setDate(2, cambiaFecha(a.getFechaAct()));
			stmt.setDate(3, cambiaFecha(a.getFechaDesde()));
			stmt.setDate(4, cambiaFecha(a.getFechaHasta()));
			stmt.setString(5, a.getAnulado());
			
			if(stmt.executeUpdate() > 0) { r = true; }
			else { r = false; }
		}
		catch(SQLException e) { 
			e.printStackTrace(); 
			r = false; }
		finally { cerrar(stmt, null); }
		return r; 
	}
}
