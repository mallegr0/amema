package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Cheque;
import util.ApplicationException;

public class DataCheque {
	//variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//constructor
	public DataCheque() {}
	
	//Metodos Privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	//Metodos publicos
	
	public boolean bajaChequePorNroRecibo(String nroRecibo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM cheques WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nroRecibo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public Cheque consultaChequePorNroRecibo(String nroRecibo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		Cheque cheque = null; 
		String sql = "SELECT * FROM cheques WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setNString(1, nroRecibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					cheque = new Cheque();
					cheque.setNcheque(rs.getDouble("ncheque"));
					cheque.setCia(rs.getString("cia"));
					cheque.setTcheque(rs.getString("tcheque"));
					cheque.setNctacte(rs.getString("nctacte"));
					cheque.setCconc(rs.getString("conc"));
					cheque.setBanco(rs.getString("banco"));
					cheque.setPlaza(rs.getString("plaza"));
					cheque.setFecche(rs.getDate("fecche"));
					cheque.setImpche(rs.getDouble("impche"));
					cheque.setDolar(rs.getDouble("dolar"));
					cheque.setHs_acredit(rs.getDouble("hs_acredit"));
					cheque.setProp_ter(rs.getString("prop_ter"));
					cheque.setCar_pas(rs.getString("cart_pas"));
					cheque.setEmitido(rs.getString("emitido"));
					cheque.setCodcli(rs.getString("codcli"));
					cheque.setFrecep(rs.getDate("frecep"));
					cheque.setPasado_a(rs.getString("pasado_a"));
					cheque.setFpasado_a(rs.getDate("fpasado"));
					cheque.setNcom_ent(rs.getString("ncom_ent"));
					cheque.setTcom_ent(rs.getString("tcom_ent"));
					cheque.setNcom_sal(rs.getString("ncom_sal"));
					cheque.setTcom_sal(rs.getString("tcom_sal"));
					cheque.setNmov(rs.getString("nmov"));
					cheque.setNrecibo(rs.getString("nrecibo"));
					cheque.setDel_client(rs.getString("del_client"));
					cheque.setNroop(rs.getString("nroop"));
					cheque.setUsado(rs.getDouble("usado"));
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return cheque; 
	}
}
