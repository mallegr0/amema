package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AuxAnDeudaCliGI;
import util.ApplicationException;

public class DataAuxAnDeudaCliGI {
	
	//Variable
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Constructor
	public DataAuxAnDeudaCliGI() { }
	
	// Metodos Privados
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) stmt.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fec) throws ApplicationException {
		java.sql.Date fecha = new java.sql.Date(fec.getTime()); 
		return fecha; 
	}
	
	// Metodos publicos
	
	public boolean altaInteres (AuxAnDeudaCliGI a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql ="INSERT INTO auxandeudacligi (codcli, nomcli, tipomov, fcomp, ncomp, tcomp, importe, nviaj, "
				+ "ref, convenio, analisis, periodo, saldo, importepagado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getCodcli());
			stmt.setString(2, a.getNomcli());
			stmt.setString(3, a.getTipomov());
			stmt.setDate(4, cambiaFecha(a.getFcomp()));
			stmt.setString(5, a.getNcomp());
			stmt.setString(6, a.getTcomp());
			stmt.setDouble(7, a.getImporte());
			stmt.setString(8, a.getNviaj());
			stmt.setString(9, a.getRef());
			stmt.setString(10, a.getConvenio());
			stmt.setInt(11, a.getAnalisis());
			stmt.setString(12, a.getPeriodo());
			stmt.setString(13, a.getSaldo());
			stmt.setDouble(14, a.getImportepagado());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaInteres(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql ="DELETE FROM auxandeudacligi WHERE ncomp = ?";
		
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
	
	public boolean bajaInteresMasivoPeriodo(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql ="DELETE FROM auxandeudacligi WHERE convenio = ? AND periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaInteres (AuxAnDeudaCliGI a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql ="UPDATE auxandeudacligi SET nomcli = ?, tipomov = ?, fcomp = ?, ncomp = ?, importe = ?, nviaj = ?,"
				+ "ref = ?, analisis = ?, saldo = ?, importepagado = ? WHERE codcli = ? AND convenio = ? AND periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getNomcli());
			stmt.setString(2, a.getTipomov());
			stmt.setDate(3, cambiaFecha(a.getFcomp()));
			stmt.setString(4, a.getNcomp());
			stmt.setString(5, a.getTcomp());
			stmt.setDouble(6, a.getImporte());
			stmt.setString(7, a.getNviaj());
			stmt.setString(8, a.getRef());
			stmt.setInt(9, a.getAnalisis());
			stmt.setString(10, a.getSaldo());
			stmt.setDouble(11, a.getImportepagado());
			stmt.setString(12, a.getCodcli());
			stmt.setString(13, a.getConvenio());
			stmt.setString(14, a.getPeriodo());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public AuxAnDeudaCliGI consultaInteres(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		AuxAnDeudaCliGI a = null; 
		String sql = "SELECT * FROM auxandeudacligi WHERE ncomp = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprobante);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCliGI();
					a.setCodcli(rs.getString("codcli"));
					a.setNomcli(rs.getString("nomcli"));
					a.setTipomov(rs.getString("tipomov"));
					a.setFcomp(rs.getDate("fcomp"));
					a.setNcomp(rs.getString("ncomp"));
					a.setTcomp(rs.getString("tcomp"));
					a.setImporte(rs.getDouble("importe"));
					a.setNviaj(rs.getString("nviaj"));
					a.setRef(rs.getString("ref"));
					a.setConvenio(rs.getString("convenio"));
					a.setAnalisis(rs.getInt("analisis"));
					a.setPeriodo(rs.getString("periodo"));
					a.setSaldo(rs.getString("saldo"));
					a.setImportepagado(rs.getDouble("importepagado"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return a; 
	}
	
	public ArrayList<AuxAnDeudaCliGI> listarInteresPorPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		AuxAnDeudaCliGI a = null; 
		ArrayList<AuxAnDeudaCliGI> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacligi WHERE convenio = ? AND periodo = ? ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCliGI();
					a.setCodcli(rs.getString("codcli"));
					a.setNomcli(rs.getString("nomcli"));
					a.setTipomov(rs.getString("tipomov"));
					a.setFcomp(rs.getDate("fcomp"));
					a.setNcomp(rs.getString("ncomp"));
					a.setTcomp(rs.getString("tcomp"));
					a.setImporte(rs.getDouble("importe"));
					a.setNviaj(rs.getString("nviaj"));
					a.setRef(rs.getString("ref"));
					a.setConvenio(rs.getString("convenio"));
					a.setAnalisis(rs.getInt("analisis"));
					a.setPeriodo(rs.getString("periodo"));
					a.setSaldo(rs.getString("saldo"));
					a.setImportepagado(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<AuxAnDeudaCliGI> listarDeudaToal(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		AuxAnDeudaCliGI a = null; 
		ArrayList<AuxAnDeudaCliGI> lista = new ArrayList<>();
		String sql = "SELECT codcli, SUM(importe) AS 'saldo' FROM auxandeudacligi WHERE convenio = ? AND periodo = ? "
				+ "GROUP BY codcli ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCliGI();
					a.setCodcli(rs.getString("codcli"));
					a.setImporte(rs.getDouble("saldo"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
}
