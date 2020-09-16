package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.SocioConvenio;
import util.ApplicationException;

public class DataSocioConvenio {
	
	/* VARIABLES */
	
	Conector conn = new Conector();
	
	/* COSTRUCTOR */ 
	
	public DataSocioConvenio() {}
	
	/* METODOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	
	public boolean altaSocioConvenio(SocioConvenio s) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO SociosConv (CODCLI, ConVta, Conc1, Tope1, Conc2, Tope2, Conc3, Tope3, Genint, TasaInt, CODARTINT)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, s.getCODCLI());
			stmt.setString(2, s.getConVta());
			stmt.setString(3, s.getConc1());
			stmt.setDouble(4, s.getTope1());
			stmt.setString(5, s.getConc2());
			stmt.setDouble(6, s.getTope2());
			stmt.setString(7, s.getConc3());
			stmt.setDouble(8, s.getTope3());
			stmt.setString(9, s.getGenInt());
			stmt.setDouble(10, s.getTasaInt());
			stmt.setString(11, s.getCODARTINT());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null);}
	}
	
	public boolean bajaSocioConvenio(String cod, String cpto) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM SociosConv WHERE CODCLI = ? AND ConVta = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, cod);
			stmt.setString(2, cpto);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null);}
	}
	
	public boolean modificaSocioConvenio(SocioConvenio s) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "UPDATE SociosConv SET Conc1 = ?, Tope1 = ?, Conc2 = ?, Tope2 = ?, Conc3 = ?, Tope3 = ?,"
				+ " Genint = ?, TasaInt = ?, CODARTINT = ? WHERE CODCLI = ? AND ConVta = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, s.getConc1());
			stmt.setDouble(2, s.getTope1());
			stmt.setString(3, s.getConc2());
			stmt.setDouble(4, s.getTope2());
			stmt.setString(5, s.getConc3());
			stmt.setDouble(6, s.getTope3());
			stmt.setString(7, s.getGenInt());
			stmt.setDouble(8, s.getTasaInt());
			stmt.setString(9, s.getCODARTINT());
			stmt.setString(10, s.getCODCLI());
			stmt.setString(11, s.getConVta());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null);}
	}

	public SocioConvenio consultaSocioConvenio(String cod, String cpto) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SocioConvenio s = null;
		String sql = "SELECT * FROM SociosConv WHERE CODCLI =? AND ConVta = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, cod);
			stmt.setString(2, cpto);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					s = new SocioConvenio();
					s.setCODCLI(rs.getString("CODCLI"));
					s.setConVta(rs.getString("ConVta"));
					s.setConc1(rs.getString("Conc1"));
					s.setTope1(rs.getDouble("Tope1"));
					s.setConc2(rs.getString("Conc2"));
					s.setTope2(rs.getDouble("Tope2"));
					s.setConc3(rs.getString("Conc3"));
					s.setTope3(rs.getDouble("Tope3"));
					s.setGenInt(rs.getString("GenInt"));
					s.setTasaInt(rs.getDouble("TasaInt"));
					s.setCODARTINT(rs.getString("CODARTINT"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return s;
	}
}
