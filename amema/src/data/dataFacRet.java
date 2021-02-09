package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.FacturaAnalisisSaldo;
import util.ApplicationException;

public class dataFacRet {
	
	//VARIABLES
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//CONSTRUCTORES
	public dataFacRet() {}
	
	//METODOS PRIVADOS
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	//METODOS PUBLICOS
	
	public ArrayList<FacturaAnalisisSaldo> listarRetencionesAnalisisSaldo() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		FacturaAnalisisSaldo f = null; 
		ArrayList<FacturaAnalisisSaldo> lista = new ArrayList<>();
		String sql = "SELECT FACT_RET.CODCLI AS 'codcli', CLIENTES.NOMCLI AS 'nomcli', CLIENTES.NVIAJ AS 'nviaj', "
				+ "FACT_RET.CONSTANCIA AS 'constancia', FACT_RET.FEC_RET AS 'fecha', RETENCIO.NRET AS 'nret', "
				+ "FACT_RET.MONTO_A AS 'monto_a' FROM (FACT_RET LEFT JOIN RETENCIO ON FACT_RET.CRET = RETENCIO.CRET) "
				+ "LEFT JOIN CLIENTES ON FACT_RET.CODCLI = CLIENTES.CODCLI Where (((FACT_RET.NCOMP) Is Null)) "
				+ "GROUP BY FACT_RET.CODCLI, CLIENTES.NOMCLI, CLIENTES.NVIAJ, FACT_RET.CONSTANCIA, "
				+ "FACT_RET.FEC_RET, RETENCIO.NRET, FACT_RET.MONTO_A";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = new FacturaAnalisisSaldo();
					f.setCodcli(rs.getString("codcli"));
					f.setNomcli(rs.getString("nomcli"));
					f.setNviaj(rs.getString("nviaj"));
					f.setConstancia(rs.getString("constancia"));
					f.setFec_ret(rs.getDate("fecha"));
					f.setNret(rs.getString("nret"));
					f.setMonto_a(rs.getDouble("monto_a"));
					lista.add(f);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<FacturaAnalisisSaldo> listarRetencionesAnalisisSaldoConvenio(String convenio) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		FacturaAnalisisSaldo f = null; 
		ArrayList<FacturaAnalisisSaldo> lista = new ArrayList<>();
		String sql = "SELECT FACT_RET.CODCLI AS 'codcli', CLIENTES.NOMCLI AS 'nomcli', CLIENTES.NVIAJ AS 'nviaj', "
				+ "FACT_RET.CONSTANCIA AS 'constancia', FACT_RET.FEC_RET AS 'fecha', RETENCIO.NRET AS 'nret', "
				+ "FACT_RET.MONTO_A AS 'monto_a' FROM (FACT_RET LEFT JOIN RETENCIO ON FACT_RET.CRET = RETENCIO.CRET) "
				+ "LEFT JOIN CLIENTES ON FACT_RET.CODCLI = CLIENTES.CODCLI Where (((FACT_RET.NCOMP) Is Null)) AND CLIENTES.CCOND = ? "
				+ "GROUP BY FACT_RET.CODCLI, CLIENTES.NOMCLI, CLIENTES.NVIAJ, FACT_RET.CONSTANCIA, "
				+ "FACT_RET.FEC_RET, RETENCIO.NRET, FACT_RET.MONTO_A";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = new FacturaAnalisisSaldo();
					f.setCodcli(rs.getString("codcli"));
					f.setNomcli(rs.getString("nomcli"));
					f.setNviaj(rs.getString("nviaj"));
					f.setConstancia(rs.getString("constancia"));
					f.setFec_ret(rs.getDate("fecha"));
					f.setNret(rs.getString("nret"));
					f.setMonto_a(rs.getDouble("monto_a"));
					lista.add(f);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
}
