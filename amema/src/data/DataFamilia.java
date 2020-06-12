package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Familia;
import util.ApplicationException;

public class DataFamilia {
	
	/* CONSTRUCTOR */
	
	public DataFamilia() {}
		
	/* VARIABLES*/
	Conector conn = new Conector();
	
	
	/* METODOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public boolean altaFamilia(Familia f) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO FAMILIAS (CFAMI, NFAMI, BFAMI) VALUES(?, ?, ?)";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, f.getCFAMI());
			stmt.setString(2, f.getNFAMI());
			stmt.setFloat(3, f.getBFAMI());
			
			if(stmt.executeUpdate() > 0) {return true; }
			else { return false; }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaFamilia(String id) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM FAMILIAS WHERE CFAMI = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, id);
			
			if(stmt.executeUpdate() > 0) {return true; }
			else { return false; }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaFamilia(Familia f) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "UPDATE FAMILIAS SET NFAMI = ?, BFAMI = ? WHERE CFAMI = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, f.getNFAMI());
			stmt.setFloat(2, f.getBFAMI());
			stmt.setString(3, f.getCFAMI());
			
			
			if(stmt.executeUpdate() > 0) {return true; }
			else { return false; }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	public Familia consultaFamilia(String id) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT * FROM FAMILIAS WHERE CFAMI = ?";
		Familia f = null;
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, id);
			
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = new Familia();
					f.setCFAMI(rs.getString("CFAMI"));
					f.setNFAMI(rs.getString("NFAMI"));
					f.setBFAMI(rs.getFloat("BFAMI"));
				}
			}
			return f;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return f;
		}
		finally { cerrar(stmt, rs);}
	}
	
	public ArrayList<Familia> listarFamilia() throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Familia> lista = new ArrayList<>();
		Familia f = null;
		String sql = "SELECT * FROM FAMILIAS ORDER BY CFAMI";
		
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = new Familia();
					f.setCFAMI(rs.getString("CFAMI"));
					f.setNFAMI(rs.getString("NFAMI"));
					f.setBFAMI(rs.getFloat("BFAMI"));
					lista.add(f);
				}
			}
			return lista;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally { cerrar(stmt, rs); }
	}
	
	
}
