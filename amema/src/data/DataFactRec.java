package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import entidades.FactRec;
import util.ApplicationException;

public class DataFactRec {
	
	/* VARIABLES */
	private Conector conn = new Conector();
	
	/* CONSTRUCTOR */
	public DataFactRec() {}
	
	/* METODOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	
	public FactRec consultarFactura(String recibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FactRec f = null;
		String sql = "SELECT * FROM FACT_REC WHERE NRECIBO = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, recibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = new FactRec();
					f.setPREFIJO(rs.getString("PREFIJO"));
					f.setNCOMP(rs.getString("NCOMP"));
					f.setTCOMP(rs.getString("TCOMP"));
					f.setLETRA(rs.getString("LETRA"));
					f.setCIA(rs.getString("CIA"));
					f.setNRECIBO(rs.getString("NRECIBO"));
					f.setFECREC((Date) rs.getDate("FECREC"));
					f.setNMOV(rs.getString("NMOV"));
					f.setMONTO_A(rs.getDouble("MONTO_A"));
					f.setMONTO_D(rs.getDouble("MONTO_D"));
					f.setDESCUENT_A(rs.getDouble("DESCUENT_A"));
					f.setDESCUENT_D(rs.getDouble("DESCUENT_D"));
					f.setA_CTA(rs.getDouble("A_CTA"));
					
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return f;
	}
	
	public String consultaNroComprobante(String recibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String f ="";
		String sql = "SELECT NCOMP FROM FACT_REC WHERE NRECIBO = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, recibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					f = rs.getString("NCOMP");					
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return f;
	}

}
