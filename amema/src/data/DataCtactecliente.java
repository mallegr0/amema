package data;

import java.util.Date;
import java.sql.Connection;
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
	
	public Boolean altaCtaCte(Ctactecliente c) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO CTACTECLI (CODCLI, FMOV, TMOV, LCOMP, PCOMP, TCOMP, NCOMP, FCOMPORIG, LCOMPORIG, PCOMPORIG,"
				+ "TCOMPORIG, NCOMPORIG, DEBE, HABER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getCODCLI());
			stmt.setDate(2, cambiaFecha(c.getFMOV()));
			stmt.setString(3, c.getTMOV());
			stmt.setString(4, c.getLCOMP());
			stmt.setString(5, c.getPCOMP());
			stmt.setString(6, c.getTCOMP());
			stmt.setString(7, c.getNCOMP());
			stmt.setDate(8, cambiaFecha(c.getFCOMPORIG()));
			stmt.setString(9, c.getLCOMPORIG());
			stmt.setString(10, c.getPCOMPORIG());
			stmt.setNString(11, c.getTCOMPORIG());
			stmt.setString(12, c.getNCOMPORIG());
			stmt.setDouble(13, c.getDEBE());
			stmt.setDouble(14, c.getHABER());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }		
	}

	public Boolean bajaCtaCtePorSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM CTACTECLI WHERE CODCLI = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public Boolean bajaCtaCte(String cod, Date fec) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM CTACTECLI WHERE CODCLI = ? AND FMOV = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, cambiaFecha(fec));
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public Boolean bajaCtaCtePorCompOrig(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM CTACTECLI WHERE NCOMPORIG = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprobante);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public Boolean bajaCtaCtePorComprobante(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM CTACTECLI WHERE NCOMP = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprobante);
			
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
				+ " LCOMPORIG = ?, PCOMPORIG = ?, TCOMPORIG = ?, NCOMPORIG = ?, DEBE = ?, HABER = ? WHERE CODCLI = ?, FMOV = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getTMOV());
			stmt.setString(2, c.getLCOMP());
			stmt.setString(3, c.getPCOMP());
			stmt.setString(4, c.getTCOMP());
			stmt.setString(5, c.getNCOMP());
			stmt.setDate(6, cambiaFecha(c.getFCOMPORIG()));
			stmt.setString(7, c.getLCOMPORIG());
			stmt.setString(8, c.getPCOMPORIG());
			stmt.setString(9, c.getTCOMPORIG());
			stmt.setString(10, c.getNCOMPORIG());
			stmt.setDouble(11, c.getDEBE());
			stmt.setDouble(12, c.getHABER());
			stmt.setString(13, c.getCODCLI());
			stmt.setDate(14, cambiaFecha(c.getFMOV()));
			
			
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
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, cambiaFecha(fec));
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public Ctactecliente consultarComprobanteCta(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		String sql = "SELECT * FROM CTACTECLI WHERE NCOMP = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public String consultarNroCompOriginal(String nroRecibo) {return ""; }
	
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocioYFec(String cod, Date fec) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CTACTECLI WHERE CODCLI = ? AND FMOV BETWEEN DATE_SUB(NOW(),INTERVAL 2 YEAR) AND ? ORDER BY FMOV, NCOMP";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setDate(2, cambiaFecha(fec));
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public ArrayList<Ctactecliente> listarAnteriorCtaCtePorSocioYFec(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CTACTECLI WHERE CODCLI = ? AND FMOV <= DATE_SUB(NOW(),INTERVAL 2 YEAR) ORDER BY FMOV, NCOMP";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public ArrayList<Ctactecliente> listarCtaCtePorSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CTACTECLI WHERE CODCLI = ? AND DATE_SUB(NOW(),INTERVAL 2 YEAR)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public ArrayList<Ctactecliente> listarCtaCte() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Ctactecliente c = null; 
		ArrayList<Ctactecliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM CTACTECLI ORDER BY CODCLI";
		
		try {
			stmt = conn.prepareStatement(sql);
			
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
					c.setTCOMPORIG(rs.getString("TCOMPORIG"));
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
	
	public boolean contarTabla() throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "SELECT COUNT(*) FROM CTACTECLI";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			if(stmt.executeUpdate() == 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; }
		finally { cerrar(stmt, null); }
	}
	
	public int contarCtaSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM CTACTECLI WHERE CODCLI = ?";
		int cont = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery(); 
			if(rs.next()) { cont = rs.getInt(1); }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }

		return cont;
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec;
	}
}
