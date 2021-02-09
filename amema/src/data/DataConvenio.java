package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Convenio;
import util.ApplicationException;

public class DataConvenio {
	
	/* CONSTRUCTOR */
	public DataConvenio() {}
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	
	/* METODOS */ 
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		} catch (Exception e) { e.printStackTrace();}
	}
	
	//METODOS PUBLICOS
	
	//alta
	
	public boolean altaConvenio(Convenio c) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO convta (ccond, descond, conc1, conc2, conc3, tope1, tope2, tope3, "
				+ "genint, tasaint, codartint, ingcobro) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getCCOND());
			stmt.setString(2, c.getDESCOND());
			stmt.setString(3, c.getConc1());
			stmt.setString(4, c.getConc2());
			stmt.setString(5, c.getConc3());
			stmt.setDouble(6, c.getTope1());
			stmt.setDouble(7, c.getTope2());
			stmt.setDouble(8, c.getTope3());
			stmt.setString(9, c.getGenInt());
			stmt.setDouble(10, c.getTasaInt());
			stmt.setString(11, c.getCODARTINT());
			stmt.setString(12, c.getIngCobro());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	//baja
	
	public boolean bajaConvenioPorCodigo(String codigo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM convta WHERE ccond = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, codigo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	
	//modificacion
	
	public boolean modificaConvenio(Convenio c) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE convta SET descond = ?, conc1 = ?, conc2 = ?, conc3 = ?, tope1 = ?, "
				+ "tope2 = ?, tope3 = ?, genint = ?, tasaint = ?, codartint = ?, ingcobro = ?"
				+ "WHERE ccond = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getDESCOND());
			stmt.setString(2, c.getConc1());
			stmt.setString(3, c.getConc2());
			stmt.setString(4, c.getConc3());
			stmt.setDouble(5, c.getTope1());
			stmt.setDouble(6, c.getTope2());
			stmt.setDouble(7, c.getTope3());
			stmt.setString(8, c.getGenInt());
			stmt.setDouble(9, c.getTasaInt());
			stmt.setString(10, c.getCODARTINT());
			stmt.setString(11, c.getIngCobro());
			stmt.setString(12, c.getCCOND());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }		
	}
	
	
	//consulta
	
	public String buscaDescripcion(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT DESCOND FROM CONVTA WHERE CCOND = ?";
		String r = "";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					r = rs.getString("DESCOND");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return r;
	}
	
	public Convenio consultaConvenio(String convenio) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Convenio c = null;
		String sql = "SELECT * FROM CONVTA WHERE ccond = ?";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					c = new Convenio();
					c.setCCOND(rs.getString("CCOND"));
					c.setDESCOND(rs.getString("DESCOND"));
					c.setTIPOVTO(rs.getString("TIPOVTO"));
					c.setDIAS(rs.getInt("DIAS"));
					c.setFECVTO(rs.getDate("FECVTO"));
					c.setPORBONIF(rs.getDouble("PORBONIF"));
					c.setConc1(rs.getString("Conc1"));
					c.setConc2(rs.getString("Conc2"));
					c.setConc3(rs.getString("Conc3"));
					c.setTope1(rs.getDouble("Tope1"));
					c.setTope2(rs.getDouble("Tope2"));
					c.setTope3(rs.getDouble("Tope3"));
					c.setGenInt(rs.getString("GenInt"));
					c.setTasaInt(rs.getDouble("TasaInt"));
					c.setCODARTINT(rs.getString("CODARTINT"));
					c.setIngCobro(rs.getString("IngCobro"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return c;
	}
	
	public ArrayList<Convenio> listarConvenio() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Convenio c = null;
		ArrayList<Convenio> lista = new ArrayList<>();
		String sql = "SELECT * FROM CONVTA ORDER BY CCOND";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					c = new Convenio();
					c.setCCOND(rs.getString("CCOND"));
					c.setDESCOND(rs.getString("DESCOND"));
					c.setTIPOVTO(rs.getString("TIPOVTO"));
					c.setDIAS(rs.getInt("DIAS"));
					c.setFECVTO(rs.getDate("FECVTO"));
					c.setPORBONIF(rs.getDouble("PORBONIF"));
					c.setConc1(rs.getString("Conc1"));
					c.setConc2(rs.getString("Conc2"));
					c.setConc3(rs.getString("Conc3"));
					c.setTope1(rs.getDouble("Tope1"));
					c.setTope2(rs.getDouble("Tope2"));
					c.setTope3(rs.getDouble("Tope3"));
					c.setGenInt(rs.getString("GenInt"));
					c.setTasaInt(rs.getDouble("TasaInt"));
					c.setCODARTINT(rs.getString("CODARTINT"));
					c.setIngCobro(rs.getString("IngCobro"));
					lista.add(c);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
