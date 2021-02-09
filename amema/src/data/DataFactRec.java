package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.DatosCobroFact;
import entidades.FactRec;
import util.ApplicationException;

public class DataFactRec {
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	/* CONSTRUCTOR */
	public DataFactRec() {}
	
	/* METODOS PRIVADOS */
	
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
	
	
	/* METODOS PUBLICOS */
	public boolean altaFactura(FactRec f) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO fact_rec (prefijo, ncomp, tcomp, letra, cia, nrecibo, fecreb, monto_a, monto_d, "
				+ "descuent_a) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, f.getPREFIJO());
			stmt.setString(2, f.getNCOMP());
			stmt.setString(3, f.getTCOMP());
			stmt.setString(4, f.getLETRA());
			stmt.setString(5, f.getCIA());
			stmt.setString(6, f.getNRECIBO());
			stmt.setDate(7, cambiaFecha(f.getFECREC()));
			stmt.setDouble(8, f.getMONTO_A());
			stmt.setDouble(9, f.getMONTO_D());
			stmt.setDouble(10, f.getDESCUENT_A());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaFacturaPorComprobante(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM FACT_REC WHERE NCOMP = ?";
		
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
	
	public FactRec consultarFactura(String recibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FactRec f = null;
		String sql = "SELECT * FROM FACT_REC WHERE NRECIBO = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
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
					f.setFECREC((Date) rs.getDate("FECREB"));
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
			stmt = conn.prepareStatement(sql);
			
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
	
	public FactRec consultarFacturaPorNroComprobante(String comprobante) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FactRec f = null;
		String sql = "SELECT * FROM FACT_REC WHERE NCOMP = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, comprobante);
			
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
					f.setFECREC((Date) rs.getDate("FECREB"));
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
	
	public String devolverNroFactura(String nroComp) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String factura ="";
		String sql = "SELECT nrecibo FROM fact_rec WHERE ncomp = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nroComp);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					factura = rs.getString("nrecibo");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return factura; 
	}
	
	public ArrayList<FactRec> listarFacturasPorFechaySocio(Date fecha, String codcli) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<FactRec> lista = new ArrayList<>();
		FactRec f = null; 
		String sql = "SELECT * FROM fact_rec WHERE codcli = ? AND fmov = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, codcli);
			stmt.setDate(2, cambiaFecha(fecha));
			
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
					f.setFECREC((Date) rs.getDate("FECREB"));
					f.setNMOV(rs.getString("NMOV"));
					f.setMONTO_A(rs.getDouble("MONTO_A"));
					f.setMONTO_D(rs.getDouble("MONTO_D"));
					f.setDESCUENT_A(rs.getDouble("DESCUENT_A"));
					f.setDESCUENT_D(rs.getDouble("DESCUENT_D"));
					f.setA_CTA(rs.getDouble("A_CTA"));
					lista.add(f);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<FactRec> listarFacturasPorNroRecibo(String nroRecibo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<FactRec> lista = new ArrayList<>();
		FactRec f = null; 
		String sql = "SELECT * FROM fact_rec WHERE nrecibo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nroRecibo);
			
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
					f.setFECREC((Date) rs.getDate("FECREB"));
					f.setNMOV(rs.getString("NMOV"));
					f.setMONTO_A(rs.getDouble("MONTO_A"));
					f.setMONTO_D(rs.getDouble("MONTO_D"));
					f.setDESCUENT_A(rs.getDouble("DESCUENT_A"));
					f.setDESCUENT_D(rs.getDouble("DESCUENT_D"));
					f.setA_CTA(rs.getDouble("A_CTA"));
					lista.add(f);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<DatosCobroFact> listarFacturasPorNroReciboDCobro(String comprobante, Date fecha) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<DatosCobroFact> lista = new ArrayList<>();
		DatosCobroFact f = null; 
		String sql = "SELECT f.tcomp AS 'tcomp', f.letra AS 'letra', f.prefijo AS 'prefijo', "
				+ "f.ncomp AS 'ncomp', v.fmov AS 'fmov', f.monto_a AS 'monto_a', "
				+ "f.descuent_a AS 'descuent_a', v.tasa AS 'tasa', v.observ AS 'observ' "
				+ "FROM fact_rec AS f INNER JOIN ventasm AS v "
				+ "ON f.letra = v.letra AND f.tcomp = v.tcomp AND f.ncomp = v.ncomp AND"
				+ " f.prefijo = v.prefijo WHERE f.nrecibo = ? AND f.fecreb = ? "
				+ "ORDER BY f.tcomp, f.letra, f.prefijo, f.ncomp;";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprobante);
			stmt.setDate(2, cambiaFecha(fecha));
			
			rs = stmt.executeQuery();
			
			if(rs != null) { 
				while(rs.next()) {
					f = new DatosCobroFact();
					f.setTcomp(rs.getString("tcomp"));
					f.setLetra(rs.getString("letra"));
					f.setPrefijo(rs.getString("prefijo"));
					f.setNcomp(rs.getString("ncomp"));
					f.setFecha(rs.getDate("fmov"));
					f.setImppagado(rs.getDouble("monto_a"));
					f.setDescuento(rs.getDouble("descuent_a"));
					f.setTasa(rs.getDouble("tasa"));
					f.setObs(rs.getString("observ"));
					lista.add(f);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
}
