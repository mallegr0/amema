package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.GaranteMovFijo;
import util.ApplicationException;

public class DataGaranteMovFijo {
	
	/* VARIABLES */
	Conector conn = new Conector();
	
	/* CONSTRUCTOR */ 
	public DataGaranteMovFijo() {}
	
	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException{
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public Boolean altaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO GarantesMovFijos (NroGarante, NroMovimFijo) SET (?, ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, g.getNroGarante());
			stmt.setInt(2, g.getNroMovimFijo());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public Boolean bajaGaranteMovFPorGarante(String g) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM GarantesMovFijos WHERE NroGarante = ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, g);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public Boolean bajaGaranteMovFPorMov(Integer m) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM GarantesMovFijos WHERE NroMovimFijo = ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, m);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	public Boolean modificaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "UPDATE GarantesMovFijos SET NroMovimFijo = ? WHERE NroGarante = ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, g.getNroMovimFijo());
			stmt.setString(2, g.getNroGarante());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	public GaranteMovFijo consultaGaranteMovF(GaranteMovFijo g) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		GaranteMovFijo rta = null; 
		String sql = "SELECT * FROM GarantesMovimF WHERE NroGarante = ? AND NroMovimFijo = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, g.getNroGarante());
			stmt.setInt(2, g.getNroMovimFijo());
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rta = new GaranteMovFijo(rs.getString("NroGarante"), rs.getInt("NroMovimFijo"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rta;
	}
	
	public String consultaGarantePorMovF(int movimiento) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String rta = ""; 
		String sql = "SELECT NroGarante FROM GarantesMovimF WHERE NroMovimFijo = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, movimiento);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rta = rs.getString("NroGarante");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rta;
	}

	public ArrayList<GaranteMovFijo> listarTodo() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GaranteMovFijo> lista = new ArrayList<>();
		GaranteMovFijo g = null; 
		String sql = "SELECT * FROM GarantesMovFijos ORDER BY NroGarante, NroMovimFijo";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					g = new GaranteMovFijo(rs.getString("NroGarante"), rs.getInt("NroMovimFijo"));
					lista.add(g);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<GaranteMovFijo> listarMovimientos(String g) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GaranteMovFijo> lista = new ArrayList<>();
		GaranteMovFijo garantes = null; 
		String sql = "SELECT * FROM GarantesMovFijos WHERE NroGarante = ? ORDER BY NroMovimFijo";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, g);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					garantes = new GaranteMovFijo(rs.getString("NroGarante"), rs.getInt("NroMovimFijo"));
					lista.add(garantes);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<GaranteMovFijo> listarGarantesPorMovimientos(int m) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GaranteMovFijo> lista = new ArrayList<>();
		GaranteMovFijo garantes = null; 
		String sql = "SELECT * FROM GarantesMovFijos WHERE NroMovimFijo = ? ORDER BY NroGarante";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setInt(1, m);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					garantes = new GaranteMovFijo(rs.getString("NroGarante"), rs.getInt("NroMovimFijo"));
					lista.add(garantes);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
