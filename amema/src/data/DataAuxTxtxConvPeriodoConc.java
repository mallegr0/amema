package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AuxTxtxConvPeriodoConc;
import util.ApplicationException;

public class DataAuxTxtxConvPeriodoConc {
	
	//Variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Conector
	public DataAuxTxtxConvPeriodoConc() {}
	
	//Metodos Privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	//Metodos Públicos
	public boolean altaRegistroAux(AuxTxtxConvPeriodoConc a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO auxtxtxconvperiodoconc (periodo, convenio, concepto, empresa, legajo, codcli, importe) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getPeriodo());
			stmt.setString(2, a.getConvenio());
			stmt.setString(3, a.getConcepto());
			stmt.setString(4, a.getEmpresa());
			stmt.setString(5, a.getLegajo());
			stmt.setString(6, a.getCodcli());
			stmt.setDouble(7, a.getImporte());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; 
		}
		finally { cerrar( stmt, null); }
	}
		
	public boolean bajaRegistro(String codcli, String convenio, String periodo, String concepto) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM auxtxtxconvperiodoconc WHERE codcli = ? AND convenio = ? AND periodo = ? AND concepto = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, codcli);
			stmt.setString(2, convenio);
			stmt.setString(3, periodo);
			stmt.setString(4, concepto);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; 
			}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaPeriodo(String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM auxtxtxconvperiodoconc WHERE periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; 
			}
		finally { cerrar(stmt, null); }
	}
	
	public  ArrayList<AuxTxtxConvPeriodoConc> listarPeriodo(String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AuxTxtxConvPeriodoConc > lista = new ArrayList<>();
		AuxTxtxConvPeriodoConc a = null; 
		String sql = "SELECT * FROM auxtxtxconvperiodoconc WHERE periodo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxTxtxConvPeriodoConc();
					a.setPeriodo(rs.getString("periodo"));
					a.setConvenio(rs.getString("convenio"));
					a.setConcepto(rs.getString("concepto"));
					a.setEmpresa(rs.getString("empresa"));
					a.setLegajo(rs.getString("legajo"));
					a.setCodcli(rs.getString("codcli"));
					a.setImporte(rs.getDouble("importe"));
					lista.add(a);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public  ArrayList<AuxTxtxConvPeriodoConc> listarConceptoPeriodo(String concepto, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AuxTxtxConvPeriodoConc > lista = new ArrayList<>();
		AuxTxtxConvPeriodoConc a = null; 
		String sql = "SELECT * FROM auxtxtxconvperiodoconc WHERE periodo = ? AND concepto = ? ORDER BY empresa, legajo"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, concepto);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxTxtxConvPeriodoConc();
					a.setPeriodo(rs.getString("periodo"));
					a.setConvenio(rs.getString("convenio"));
					a.setConcepto(rs.getString("concepto"));
					a.setEmpresa(rs.getString("empresa"));
					a.setLegajo(rs.getString("legajo"));
					a.setCodcli(rs.getString("codcli"));
					a.setImporte(rs.getDouble("importe"));
					lista.add(a);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
}
