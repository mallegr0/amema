package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AnticC;
import entidades.AnticipoAnalisisSaldo;
import util.ApplicationException;

public class DataAnticC {
	
	//variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Constructores
	public DataAnticC() { }
	
	//Metodos privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException{
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	
	//Metodos publicos
	public boolean bajaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM antic_c WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nrec);
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public AnticC consultaAnticipoPorNroRecibo(String nrec) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		AnticC anticipo = null; 
		String sql = "SELECT * FROM antic_c WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nrec);

			rs = stmt.executeQuery(); 
			if(rs != null) {
				while(rs.next()){
					anticipo = new AnticC(); 
					anticipo.setNrecibo(rs.getString("nrecibo"));
					anticipo.setCia(rs.getString("cia"));
					anticipo.setCodcli(rs.getString("codcli"));
					anticipo.setNviaj(rs.getString("nviaj"));
					anticipo.setFrecibo(rs.getDate("frecibo"));
					anticipo.setMonto_a(rs.getDouble("monto_a"));
					anticipo.setMonto_d(rs.getDouble("monto_d"));
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, null); }
		return anticipo; 
	}
	
	public ArrayList<AnticipoAnalisisSaldo> listarAnticiposParaAnalisisSaldo() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AnticipoAnalisisSaldo> lista = new ArrayList<>();
		AnticipoAnalisisSaldo a = null; 
		String sql = "SELECT ANTIC_c.CODCLI as 'codcli', CLIENTES.NOMCLI as 'nomcli', ANTIC_c.NRECIBO as 'nrecibo',"
				+ " ANTIC_c.FRECIBO as 'frecibo', ANTIC_c.MONTO_A as 'monto_a', Sum(APLICA_c.APLICADO_A) AS 'SumaDeAPLICADO_A', "
				+ "CLIENTES.NVIAJ as 'nviaj' FROM CLIENTES RIGHT JOIN (ANTIC_c LEFT JOIN APLICA_c ON ANTIC_c.NRECIBO = APLICA_c.NRECIBO) "
				+ "ON CLIENTES.CODCLI = ANTIC_c.CODCLI GROUP BY ANTIC_c.CODCLI, CLIENTES.NOMCLI, ANTIC_c.NRECIBO,"
				+ " ANTIC_c.FRECIBO, ANTIC_c.MONTO_A, CLIENTES.NVIAJ";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()){
					a = new AnticipoAnalisisSaldo();
					a.setCodcli(rs.getString("codcli"));
					a.setNomcli(rs.getString("nomcli"));
					a.setNrecibo(rs.getString("nrecibo"));
					a.setFrecibo(rs.getDate("frecibo"));
					a.setMonto_a(rs.getDouble("monto_a"));
					a.setSumaApliacado_a(rs.getDouble("SumaDeAPLICADO_A"));
					a.setNviaj(rs.getString("nviaj"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<AnticipoAnalisisSaldo> listarAnticiposParaAnalisisSaldoConvenio(String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AnticipoAnalisisSaldo> lista = new ArrayList<>();
		AnticipoAnalisisSaldo a = null; 
		String sql = "SELECT ANTIC_c.CODCLI as 'codcli', CLIENTES.NOMCLI as 'nomcli', ANTIC_c.NRECIBO as 'nrecibo',"
				+ " ANTIC_c.FRECIBO as 'frecibo', ANTIC_c.MONTO_A as 'monto_a', Sum(APLICA_c.APLICADO_A) AS 'SumaDeAPLICADO_A', "
				+ "CLIENTES.NVIAJ as 'nviaj' FROM CLIENTES RIGHT JOIN (ANTIC_c LEFT JOIN APLICA_c ON ANTIC_c.NRECIBO = APLICA_c.NRECIBO) "
				+ "ON CLIENTES.CODCLI = ANTIC_c.CODCLI WHERE CLIENTES.CCOND = ? GROUP BY ANTIC_c.CODCLI, CLIENTES.NOMCLI, ANTIC_c.NRECIBO,"
				+ " ANTIC_c.FRECIBO, ANTIC_c.MONTO_A, CLIENTES.NVIAJ";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()){
					a = new AnticipoAnalisisSaldo();
					a.setCodcli(rs.getString("codcli"));
					a.setNomcli(rs.getString("nomcli"));
					a.setNrecibo(rs.getString("nrecibo"));
					a.setFrecibo(rs.getDate("frecibo"));
					a.setMonto_a(rs.getDouble("monto_a"));
					a.setSumaApliacado_a(rs.getDouble("SumaDeAPLICADO_A"));
					a.setNviaj(rs.getString("nviaj"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
}
