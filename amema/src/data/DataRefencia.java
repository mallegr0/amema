package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ApplicationException;
import entidades.Referencia;

public class DataRefencia {
	
	/* variables */
	//Conector conn = new Conector();
		ConectorMySQL conn = new ConectorMySQL();
	
	/* constructor */
	public DataRefencia() {}
	
	/* metodos */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (SQLException e) { e.printStackTrace();}
	}
	
	// Consultas
	
	public Referencia consultaReferencia(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		Referencia r = null; 
		String sql = "SELECT * FROM REFERENCIA WHERE CREF = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setNString(1, cod);
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					r = new Referencia(rs.getString("CREF"), rs.getString("NREF"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return r;
	}
	
	
	// Listados
	
	public ArrayList<Referencia> listarReferencias() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<Referencia> lista = new ArrayList<>();
		Referencia r = null; 
		String sql = "SELECT * FROM REFERENCIA";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					r = new Referencia(rs.getString("CREF"), rs.getString("NREF"));
					lista.add(r);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return lista;
	}

}
