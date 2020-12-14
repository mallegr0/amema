package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.PeriodoDeudaGen;
import util.ApplicationException;

public class DataPeriodoDeudaGen {
	
	//variables
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//constructor
	public DataPeriodoDeudaGen() {}
	
	//metodo privado
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
	
	
	//Metodos publicos
	public boolean altaPeriodoDeudaGen(PeriodoDeudaGen p) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO periodosdeudagen (nro_gen_deuda, anulado, fecha_gen, nroconv, periodo, inform_ingresada,"
				+ "recibos_gen, fechahasta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, p.getNro_gen_deuda());
			stmt.setString(2, p.getAnulado());
			stmt.setDate(3, cambiaFecha(p.getFecha_gen()));
			stmt.setString(4, p.getNroconv());
			stmt.setString(5, p.getPeriodo());
			stmt.setString(6, p.getInform_ingresada());
			stmt.setString(7, p.getRecibos_gen());
			stmt.setDate(8, cambiaFecha(p.getFechaHasta()));
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
			
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false;}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaPeriodoDeudaGen(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE FROM periodosdeudagen SET anulado = 'S' WHERE nroconv = ? AND periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
			
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false;}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaPeriodoDeudaGen(PeriodoDeudaGen p) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE periodosdeudagen SET anulado = ?, fecha_gen = ?, nroconv = ?, periodo = ?, "
				+ "inform_ingresada = ?, recibos_gen = ?, fechahasta = ? WHERE nro_gen_deuda = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, p.getAnulado());
			stmt.setDate(2, cambiaFecha(p.getFecha_gen()));
			stmt.setString(3, p.getNroconv());
			stmt.setString(4, p.getPeriodo());
			stmt.setString(5, p.getInform_ingresada());
			stmt.setString(6, p.getRecibos_gen());
			stmt.setDate(7, cambiaFecha(p.getFechaHasta()));
			stmt.setInt(8, p.getNro_gen_deuda());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
			
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return false;}
		finally { cerrar(stmt, null); }
	}
	
	public int consultaNroDeudaPorPeriodoyConvenio(String periodo, String convenio, Date fecha) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT nro_gen_deuda FROM periodosdeudagen WHERE nroconv = ? AND periodo = ? AND fecha_gen <= ?";
		int nro = 0; 
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			stmt.setDate(3, cambiaFecha(fecha));
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					nro = rs.getInt("nro_gen_deuda");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return nro;
	}
	
	
	public PeriodoDeudaGen consultaPeriodoDeudaGen(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		//String sql = "SELECT * FROM periodosdeudagen WHERE nroconv = ? AND periodo = ?";
		String sql = "SELECT * FROM periodosdeudagen WHERE periodo = ? AND nroconv = ?";
		PeriodoDeudaGen p = null;
		
		try {
			stmt = conn.prepareStatement(sql);
		
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					p = new PeriodoDeudaGen();
					p.setNro_gen_deuda(rs.getInt("nro_gen_deuda"));
					p.setAnulado(rs.getString("anulado"));
					p.setFecha_gen(rs.getDate("fecha_gen"));
					p.setNroconv(rs.getString("nroconv"));
					p.setPeriodo(rs.getString("periodo"));
					p.setInform_ingresada(rs.getString("inform_ingresada"));
					p.setRecibos_gen(rs.getString("recibos_gen"));
					p.setFechaHasta(rs.getDate("fechahasta"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return p;
	}
	
	public ArrayList<String> listarPeriodos() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT DISTINCT periodo FROM periodosdeudagen ORDER BY fecha_gen desc";
		ArrayList<String> lista = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					lista.add(rs.getString("periodo"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return lista;
	}
	
	public ArrayList<PeriodoDeudaGen> listarPeriodoDeudaGen() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT * FROM periodosdeudagen WHERE anulado = 'N' ORDER BY nro_gen_deuda";
		PeriodoDeudaGen p = null;
		ArrayList<PeriodoDeudaGen> lista = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					p = new PeriodoDeudaGen();
					p.setNro_gen_deuda(rs.getInt("nro_gen_deuda"));
					p.setAnulado(rs.getString("anulado"));
					p.setFecha_gen(rs.getDate("fecha_gen"));
					p.setNroconv(rs.getString("nroconv"));
					p.setPeriodo(rs.getString("periodo"));
					p.setInform_ingresada(rs.getString("inform_ingresada"));
					p.setRecibos_gen(rs.getString("recibos_gen"));
					p.setFechaHasta(rs.getDate("fechahasta"));
					lista.add(p);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return lista;
	}
	
	
	
	public int ultimoID() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String sql = "SELECT MAX(nro_gen_deuda) FROM periodosdeudagen";
		int nro = 0;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					nro = rs.getInt("nro_gen_deuda");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return nro;
	}
}
