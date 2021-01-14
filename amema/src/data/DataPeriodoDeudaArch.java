package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.PeriodoDeudaArch;
import util.ApplicationException;

public class DataPeriodoDeudaArch {
	
	//Variable
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	//Constructor
	public DataPeriodoDeudaArch() {}
	
	//Metodos privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	//Metodos publicos
	
	public boolean altaPeriodoDeudaArch(PeriodoDeudaArch p) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO periodosdeudaarch (nro_gen_deuda, nroconv, periodo, nomb_archivo) VALUES (?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getNro_gen_deuda());
			stmt.setString(2, p.getNroconv());
			stmt.setString(3, p.getPerido());
			stmt.setString(4, p.getNomb_archivo());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false;}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaPeriodoDeudaArch(String nombre) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM periodosdeudaarch WHERE nomb_archivo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nombre);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false;}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaPeriodoDeudaArch(PeriodoDeudaArch p) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE FROM periodosdeudaarch SET nro_gen_deuda = ?, nroconv = ?, periodo = ? WHERE nomb_archivo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getNro_gen_deuda());
			stmt.setString(2, p.getNroconv());
			stmt.setString(3, p.getPerido());
			stmt.setString(4, p.getNomb_archivo());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false;}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public PeriodoDeudaArch consultaPeriodoDeudaArchPorNombreArchivo(String nombre) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		PeriodoDeudaArch p = null;
		String sql = "SELECT * FROM periodosdeudaarch WHERE nomb_archivo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nombre);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					p = new PeriodoDeudaArch(rs.getInt("nro_gen_deuda"), rs.getString("nroconv"), rs.getString("periodo"), rs.getString("nomb_archivo"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return p;
	}
	
	public ArrayList<PeriodoDeudaArch> ListarDatosPorPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		PeriodoDeudaArch p = null;
		ArrayList<PeriodoDeudaArch> lista = new ArrayList<>();
		String sql = "SELECT * FROM periodosdeudaarch WHERE nroconv = ? AND periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					p = new PeriodoDeudaArch(rs.getInt("nro_gen_deuda"), rs.getString("nroconv"), rs.getString("periodo"), rs.getString("nomb_archivo"));
					lista.add(p);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
