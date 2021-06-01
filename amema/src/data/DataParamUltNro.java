package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.ParamUltNro;
import util.ApplicationException;

public class DataParamUltNro {
	
	//Variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Constructor
	public DataParamUltNro() {}
	
	//Metodos Privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch( SQLException e) { e.printStackTrace(); }
	}
	
	//Metodos publicos
	
	
	//ACTUALIZACIONES
	public boolean modificaParamUltNro(ParamUltNro p) throws ApplicationException {
		PreparedStatement stmt = null; 
		boolean r = false; 
		String sql = "UPDATE paramultnro SET descultnro = ?, letra = ?, ultimonro = ?, prefijo = ?, modulo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getDescUltNro());
			stmt.setString(2, p.getLetra());
			stmt.setInt(3, p.getUltimonro());
			stmt.setInt(4, p.getPrefijo());
			stmt.setString(5, p.getModulo());
			
			if(stmt.executeUpdate() > 0) { r = true; }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return r; 
	}
	
	public boolean modificaParamUltNroMF1(ParamUltNro p) throws ApplicationException {
		PreparedStatement stmt = null; 
		boolean r = false; 
		String sql = "UPDATE paramultnro SET ultimonro = ?, prefijo = ? WHERE descultnro = ? AND letra = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getUltimonro());
			stmt.setInt(2, p.getPrefijo());
			stmt.setString(3, p.getDescUltNro());
			stmt.setString(4, p.getLetra());
			
			if(stmt.executeUpdate() > 0) { r = true; }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return r; 
	}
	
	public boolean modificaParamUltNroMF2(ParamUltNro p) throws ApplicationException {
		PreparedStatement stmt = null; 
		boolean r = false; 
		String sql = "UPDATE paramultnro SET ultimonro = ?, prefijo = ? WHERE descultnro = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getUltimonro());
			stmt.setInt(2, p.getPrefijo());
			stmt.setString(3, p.getDescUltNro());
			
			if(stmt.executeUpdate() > 0) { r = true; }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return r; 
	}
	
	
	
	//CONSULTAS
	public ParamUltNro consultaUltimoNro(String desc, String letra) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ParamUltNro p = null; 
		String sql = "SELECT ultimonro, prefijo FROM paramultnro WHERE "
				+ "descultnro = ? AND letra = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, desc);
			stmt.setString(2, letra);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					p = new ParamUltNro();
					p.setUltimonro(rs.getInt("ultimonro"));
					p.setPrefijo(rs.getInt("prefijo"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return p;
	}
} 
