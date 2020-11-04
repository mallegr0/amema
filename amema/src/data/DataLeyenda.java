package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Leyenda;
import util.ApplicationException;

public class DataLeyenda {
	
	/* variables */
	//Conector conn = new Conector();
		ConectorMySQL conn = new ConectorMySQL();
	
	/* constructor */
	public DataLeyenda() {}
	
	
	/* metodos */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	
	public ArrayList<Leyenda> listarLeyendas() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ArrayList<Leyenda> lista = new ArrayList<>();
		Leyenda l = null;
		String sql = "SELECT * FROM LEYENDAS";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					l = new Leyenda(rs.getString("CLEY"), rs.getString("NLEY"), rs.getString("TLEY"));
					lista.add(l);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
