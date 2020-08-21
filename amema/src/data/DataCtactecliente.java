package data;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Ctactecliente;
import util.ApplicationException;

public class DataCtactecliente {
	
	/* CONSTRUCTOR */
	public DataCtactecliente() {}
	
	/* VARIABLES */
	Conector conn = new Conector();
	
	/* METODOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	public Boolean altaCtaCte(Ctactecliente c) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO CTACTECLI (CODCLI, FMOV, TMOV, LCOMP, PCOMP, TCOMP, NCOMP, FCOMPORIOG, LCOMPORIG, PCOMPORIG,"
				+ "NCOMPORIG, DEBE, HABER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, c.getCODCLI());
			stmt.setDate(2, (java.sql.Date) c.getFMOV());
			stmt.setString(3, c.getTMOV());
			stmt.setString(4, c.getLCOMP());
			stmt.setString(5, c.getPCOMP());
			stmt.setString(6, c.getTCOMP());
			stmt.setString(7, c.getNCOMP());
			stmt.setDate(8, (java.sql.Date) c.getFCOMPORIG());
			stmt.setString(9, c.getLCOMPORIG());
			stmt.setString(10, c.getPCOMPORIG());
			stmt.setString(11, c.getNCOMPORIG());
			stmt.setDouble(12, c.getDEBE());
			stmt.setDouble(13, c.getHABER());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }		
	}

	public Boolean bajaCtaCte(String cod, java.util.Date fec) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM CTACTECLI WHERE CODCLI = ? AND FMOV = ?";
		
		try {
			
			java.sql.Date fecha = new java.sql.Date(fec.getTime());
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, fecha);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	public Boolean modificaCtaCte(Ctactecliente c) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE CTACTECLI SET  TMOV = ?, LCOMP = ?, PCOMP = ?, TCOMP = ?, NCOMP = ?, FCOMPORIOG = ?,"
				+ " LCOMPORIG = ?, PCOMPORIG = ?, NCOMPORIG = ?, DEBE = ?, HABER = ? WHERE CODCLI = ?, FMOV = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, c.getTMOV());
			stmt.setString(2, c.getLCOMP());
			stmt.setString(3, c.getPCOMP());
			stmt.setString(4, c.getTCOMP());
			stmt.setString(5, c.getNCOMP());
			stmt.setDate(6, (java.sql.Date) c.getFCOMPORIG());
			stmt.setString(7, c.getLCOMPORIG());
			stmt.setString(8, c.getPCOMPORIG());
			stmt.setString(9, c.getNCOMPORIG());
			stmt.setDouble(10, c.getDEBE());
			stmt.setDouble(11, c.getHABER());
			stmt.setString(12, c.getCODCLI());
			stmt.setDate(13, (java.sql.Date) c.getFMOV());
			
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }			
	}

	public Ctactecliente consultarCtaCte(String cod, Date fec) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		String sql = "SELECT * FROM CTACTECLI WHERE CODCLI = ? AND FMOV = ?";
		
		try {
			java.sql.Date fecha = new java.sql.Date(fec.getTime());
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, fecha);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					c = new Ctactecliente();
					c.setCODCLI(rs.getString("CODCLI"));
					c.setFMOV(rs.getDate("FMOV"));
					c.setTMOV(rs.getString("TMOV"));
					c.setLCOMP(rs.getString("LCOMP"));
					c.setPCOMP(rs.getString("PCOMP"));
					c.setTCOMP(rs.getString("TCOMP"));
					c.setNCOMP(rs.getString("NCOMP"));
					c.setFCOMPORIG(rs.getDate("FCOMPORIG"));
					c.setLCOMPORIG(rs.getString("LCOMPORIG"));
					c.setPCOMPORIG(rs.getString("PCOMPORIG"));
					c.setNCOMPORIG(rs.getString("NCOMPORIG"));
					c.setDEBE(rs.getDouble("DEBE"));
					c.setHABER(rs.getDouble("HABER"));
				}
			}
		}
		catch(SQLException e) {e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return c;
	}
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocioYFec(String cod, Date fec) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CTACTECLI WHERE CODCLI = ? AND FMOV < ? ORDER BY FMOV";
		
		try {
			java.sql.Date fecha = new java.sql.Date(fec.getTime());
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, fecha);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					c = new Ctactecliente();
					c.setCODCLI(rs.getString("CODCLI"));
					c.setFMOV(rs.getDate("FMOV"));
					c.setTMOV(rs.getString("TMOV"));
					c.setLCOMP(rs.getString("LCOMP"));
					c.setPCOMP(rs.getString("PCOMP"));
					c.setTCOMP(rs.getString("TCOMP"));
					c.setNCOMP(rs.getString("NCOMP"));
					c.setFCOMPORIG(rs.getDate("FCOMPORIG"));
					c.setLCOMPORIG(rs.getString("LCOMPORIG"));
					c.setPCOMPORIG(rs.getString("PCOMPORIG"));
					c.setNCOMPORIG(rs.getString("NCOMPORIG"));
					c.setDEBE(rs.getDouble("DEBE"));
					c.setHABER(rs.getDouble("HABER"));
					lista.add(c);
				}
			}
		}
		catch(SQLException e) {e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	

}
