package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AnalisisSaldoListado;
import entidades.AuxAnDeudaSoc;
import util.ApplicationException;

public class DataAuxAnDeudaSoc {
	
	//VARIABLES 
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//CONSTRUCTOR
	public DataAuxAnDeudaSoc() {}
	
	//METODOS PRIVADOS
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec;
	}
	
	//METODOS PUBLICOS
	public boolean altaSocio(AuxAnDeudaSoc a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO auxandeudasoc (codcli, nomcli, tipomov, fcomp, ncomp, tcomp, importe, nviaj,"
				+ "ref, convenio, analisis, periodo, saldo, importepagado, obsmov) VALUES (?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getCodcli());
			stmt.setString(2, a.getNomcli());
			stmt.setString(3, a.getTipoMov());
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
			stmt.setDouble(14, a.getImportePagado());
			stmt.setString(15, a.getObsMov());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}

	public boolean bajaTodo() throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM auxandeudasoc";
		
		try {
			stmt = conn.prepareStatement(sql);
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public int cantidadRegistros() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		int cant = 0; 
		String sql = "SELECT COUNT(*) AS 'cant' FROM auxandeudasoc"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					cant = rs.getInt("cant");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return cant;
	}
	
	public ArrayList<AuxAnDeudaSoc> listarTodo() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AuxAnDeudaSoc> lista = new ArrayList<>();
		AuxAnDeudaSoc a = null; 
		String sql = "SELECT * FROM auxandeudasoc ORDER BY codcli. fcomp"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaSoc();
					a.setCodcli(rs.getString("codcli"));
					a.setNomcli(rs.getString("nomcli"));
					a.setTipoMov(rs.getString("tipomov"));
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
					a.setImportePagado(rs.getDouble("importepagado"));
					a.setObsMov(rs.getString("obsmov"));
					lista.add(a);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<AnalisisSaldoListado> emitirListadoSaldos() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<AnalisisSaldoListado> lista = new ArrayList<>();
		AnalisisSaldoListado a = null; 
		String sql = "SELECT Auxandeudasoc.NVIAJ, Auxandeudasoc.NOMCLI, Auxandeudasoc.CODCLI, "
				+ "Auxandeudasoc.TIPOMOV, Auxandeudasoc.FCOMP, Auxandeudasoc.TCOMP, Auxandeudasoc.NCOMP, "
				+ "Auxandeudasoc.IMPORTE, Auxandeudasoc.ref, Auxandeudasoc.saldo, CLIENTES.DNRP, CLIENTES.CPCCP, "
				+ "CONVTA.DESCOND, CONVTA.CCOND , Auxandeudasoc.obsmov "
				+ "FROM (Auxandeudasoc LEFT JOIN CLIENTES ON Auxandeudasoc.CODCLI = CLIENTES.CODCLI) "
				+ "LEFT JOIN CONVTA ON CLIENTES.CCOND = CONVTA.CCOND "
				+ "WHERE CLIENTES.COM_IND = 'A' "
				+ "ORDER BY CONVTA.CCOND, CLIENTES.CPCCP, Auxandeudasoc.NOMCLI, "
				+ "Auxandeudasoc.TIPOMOV, Auxandeudasoc.FCOMP";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AnalisisSaldoListado(); 
					a.setNviaj(rs.getString("Auxandeudasoc.NVIAJ"));
					a.setNomcli(rs.getString("Auxandeudasoc.NOMCLI"));
					a.setCodcli(rs.getString("Auxandeudasoc.CODCLI"));
					a.setTipomov(rs.getString("Auxandeudasoc.TIPOMOV"));
					a.setFcomp(rs.getDate("Auxandeudasoc.FCOMP"));
					a.setTcomp(rs.getString("Auxandeudasoc.TCOMP"));
					a.setNcomp(rs.getString("Auxandeudasoc.NCOMP"));
					a.setImporte(rs.getDouble("Auxandeudasoc.IMPORTE"));
					a.setRef(rs.getString("Auxandeudasoc.ref"));
					a.setSaldo(rs.getString("Auxandeudasoc.saldo"));
					a.setDnrp(rs.getString("CLIENTES.DNRP"));
					a.setCpccp(rs.getString("CLIENTES.CPCCP"));
					a.setDesccond(rs.getString("CONVTA.DESCOND"));
					a.setCcond(rs.getString("CONVTA.CCOND"));
					a.setObsmov(rs.getString("Auxandeudasoc.obsmov"));
					
					lista.add(a);
				}
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
