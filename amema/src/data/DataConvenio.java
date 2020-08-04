package data;

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
	Conector conn = new Conector();
	
	
	/* METODOS */ 
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		} catch (Exception e) { e.printStackTrace();}
	}
	
	
	//alta
	
	//baja
	
	//modificacion
	
	//consulta
	
	public ArrayList<Convenio> listarConvenio() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Convenio c = null;
		ArrayList<Convenio> lista = new ArrayList<>();
		String sql = "SELECT * FROM CONVTA ORDER BY CCOND";
		
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
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