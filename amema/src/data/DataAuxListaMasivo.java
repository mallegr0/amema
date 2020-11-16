package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entidades.AuxListaMasivo;
import util.ApplicationException;

public class DataAuxListaMasivo {
	
	//Variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//constructor
	public DataAuxListaMasivo() {}
	
	//Metodos privados 
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	public ArrayList<AuxListaMasivo> listarPorPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		// Declaro las variables que voy a usar
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AuxListaMasivo> lista = new ArrayList<>();
		AuxListaMasivo alm = null;
		DecimalFormat df = new DecimalFormat("#0.00");
		
		//esta consulta la hago con un inner join para cruzar los datos de aux con cliente y asi ordenar como yo quiero
		String sql = "SELECT aux.periodo, aux.convenio, aux.codcli, cli.cpccp, cli.dnrp, aux.nomcli, aux.ref, aux.importepagado "
				+ "FROM auxandeudacli AS aux INNER JOIN clientes AS cli WHERE aux.codcli = cli.codcli AND aux.periodo = ? AND aux.convenio = ?"
				+ " AND aux.importepagado <> 0 ORDER BY cli.cpccp, aux.codcli, aux.analisis DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					alm = new AuxListaMasivo();
					alm.setPeriodo(rs.getString("aux.periodo"));
					alm.setConvenio(rs.getString("aux.convenio"));
					alm.setCodcli(rs.getString("aux.codcli"));
					alm.setEmpresa(rs.getString("cli.cpccp"));
					alm.setLegajo(rs.getString("cli.dnrp"));
					alm.setNombre(rs.getString("aux.nomcli"));
					alm.setConcepto(rs.getString("aux.ref"));
					alm.setImporte(df.format(rs.getDouble("aux.importepagado")));
					lista.add(alm);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<AuxListaMasivo> listarPorPeriodo(String periodo) throws ApplicationException {
		// Declaro las variables que voy a usar
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<AuxListaMasivo> lista = new ArrayList<>();
		AuxListaMasivo alm = null;
		DecimalFormat df = new DecimalFormat("#0.00");
		
		//esta consulta la hago con un inner join para cruzar los datos de aux con cliente y asi ordenar como yo quiero
		String sql = "SELECT aux.periodo, aux.convenio, aux.codcli, cli.cpccp, cli.dnrp, aux.nomcli, aux.ref, aux.importepagado "
				+ "FROM auxandeudacli AS aux INNER JOIN clientes AS cli WHERE aux.codcli = cli.codcli AND aux.periodo = ?"
				+ " AND aux.importepagado <> 0 ORDER BY cli.cpccp, aux.convenio, aux.codcli, aux.analisis DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					alm = new AuxListaMasivo();
					alm.setPeriodo(rs.getString("aux.periodo"));
					alm.setConvenio(rs.getString("aux.convenio"));
					alm.setCodcli(rs.getString("aux.codcli"));
					alm.setEmpresa(rs.getString("cli.cpccp"));
					alm.setLegajo(rs.getString("cli.dnrp"));
					alm.setNombre(rs.getString("aux.nomcli"));
					alm.setConcepto(rs.getString("aux.ref"));
					alm.setImporte(df.format(rs.getDouble("aux.importepagado")));
					lista.add(alm);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	

}
