package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import entidades.Adherente;
import util.ApplicationException;

public class DataAdherente {
	
	/* VARIABLES */
	Conector conn = new Conector();
	
	/* CONSTRUCTOR */
	public DataAdherente() {}
	
	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	public boolean altaAdherente(Adherente a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO ADHERENTE (CODCLI, NROADHERENTE, APEYNOM_AD, DOMIC_AD, TEL_AD, TIPODOC_AD, NRODOC_AD, FEC_NAC_AD, PARENT) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, a.getCODCLI());
			stmt.setString(2, a.getNROADHERENTE());
			stmt.setString(3, a.getAPEYNOM_AD());
			stmt.setString(4, a.getDOMIC_AD());
			stmt.setString(5, a.getTEL_AD());
			stmt.setString(6, a.getTIPODOC_AD());
			stmt.setString(7, a.getNRODOC_AD());
			stmt.setDate(8,(Date) a.getFEC_NAC_AD());
			stmt.setString(9, a.getPARENT());
			
			if(stmt.executeUpdate() > 0 ) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false;
			}
		finally { cerrar(stmt, null); }
	}

	public boolean bajaAdherente(String cod, String nro) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM ADHERENTE WHERE CODCLI = ? AND NROADHERENTE = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, cod);
			stmt.setString(2, nro);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false;
			}
		finally { cerrar(stmt, null); }
	}

	public boolean modificaAdherente(Adherente a) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "UPDATE ADHERENTES SET APEYNOM_AD = ?, DOMIC_AD = ?, TEL_AD = ?, TIPODOC_AD = ?, NRODOC_AD = ?, FEC_NAC_AD = ?, PARENT = ? "
				+ "WHERE CODCLI = ? AND NROADHERENTE = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, a.getAPEYNOM_AD());
			stmt.setString(2, a.getDOMIC_AD());
			stmt.setString(3, a.getTEL_AD());
			stmt.setString(4, a.getTIPODOC_AD());
			stmt.setString(5, a.getNRODOC_AD());
			stmt.setDate(6, (Date) a.getFEC_NAC_AD());
			stmt.setString(7, a.getPARENT());
			stmt.setString(8, a.getCODCLI());
			stmt.setString(9, a.getNROADHERENTE());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; 
			}
		finally { cerrar(stmt, null); }
	}
	
	public Adherente consultaAdherente(String cod, String nro) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Adherente a = null;
		String sql = "SELECT * FROM ADHERENTES WHERE CODCLI = ? AND NROADHERENTE = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setString(2, nro);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new Adherente();
					a.setCODCLI(rs.getString("CODCLI"));
					a.setNROADHERENTE(rs.getString("NROADHERENTE"));
					a.setAPEYNOM_AD(rs.getString("APEYNOM_AD"));
					a.setDOMIC_AD(rs.getString("DOMIC_AD"));
					a.setTEL_AD(rs.getString("TEL_AD"));
					a.setTIPODOC_AD(rs.getString("TIPODOC_AD"));
					a.setNRODOC_AD(rs.getString("NRODOC_AD"));
					a.setFEC_NAC_AD(rs.getDate("FEC_NAC_AD"));
					a.setPARENT(rs.getString("PARENT"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return a;
	}

	public ArrayList<Adherente> listarAdherentes() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Adherente a = null;
		ArrayList<Adherente> lista = new ArrayList<>();
		String sql = "SELECT * FROM ADHERENTES ORDER BY CODCLI, NROADHERENTE";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new Adherente();
					a.setCODCLI(rs.getString("CODCLI"));
					a.setNROADHERENTE(rs.getString("NROADHERENTE"));
					a.setAPEYNOM_AD(rs.getString("APEYNOM_AD"));
					a.setDOMIC_AD(rs.getString("DOMIC_AD"));
					a.setTEL_AD(rs.getString("TEL_AD"));
					a.setTIPODOC_AD(rs.getString("TIPODOC_AD"));
					a.setNRODOC_AD(rs.getString("NRODOC_AD"));
					a.setFEC_NAC_AD(rs.getDate("FEC_NAC_AD"));
					a.setPARENT(rs.getString("PARENT"));
					lista.add(a);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<Adherente> listarAdherentesPorSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		Adherente a = null;
		ArrayList<Adherente> lista = new ArrayList<>();
		String sql = "SELECT * FROM ADHERENTES WHERE CODCLI = ? ORDER BY CODCLI, NROADHERENTE";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new Adherente();
					a.setCODCLI(rs.getString("CODCLI"));
					a.setNROADHERENTE(rs.getString("NROADHERENTE"));
					a.setAPEYNOM_AD(rs.getString("APEYNOM_AD"));
					a.setDOMIC_AD(rs.getString("DOMIC_AD"));
					a.setTEL_AD(rs.getString("TEL_AD"));
					a.setTIPODOC_AD(rs.getString("TIPODOC_AD"));
					a.setNRODOC_AD(rs.getString("NRODOC_AD"));
					a.setFEC_NAC_AD(rs.getDate("FEC_NAC_AD"));
					a.setPARENT(rs.getString("PARENT"));
					lista.add(a);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}

	public String ultimoID(String cod) throws ApplicationException {
		String id = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT TOP 1 NROADHERENTE FROM ADHERENTES WHERE CODCLI = ? ORDER BY NROADHERENTE DESC ";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					id = rs.getString("NROADHERENTE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, rs);	}
		return id;
	}
	
}
