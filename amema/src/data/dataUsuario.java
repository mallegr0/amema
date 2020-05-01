package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import entidades.Usuario;
import util.ApplicationException;


public class dataUsuario {
	
	/*	CONSTRUCTOR	*/
	public dataUsuario(){}
	
	/*  variables  */
	Conector conn = new Conector();
	
	/*  METODOS  */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs){
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
	public boolean altaUsuario(Usuario u) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO usuarios (usuario, password, nombreyapellido) VALUES (?, ?, ?)";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getUsuario());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getNombreyapellido());
			
			return stmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			cerrar(stmt,null);
		}
	}
	
	public boolean bajaUsuario(String user) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "DELETE FROM usuarios WHERE usuario = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, user);
			
			return stmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			cerrar(stmt,null);
		}
	}

	public boolean modificaUsuario(Usuario u) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "UPDATE usuarios SET (password = ?, nombreyapellido = ? WHERE usuario = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getPassword());
			stmt.setString(2, u.getNombreyapellido());
			stmt.setString(3, u.getUsuario());
			
			return stmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			cerrar(stmt,null);
		}
	}

	public Usuario consultaUsuario(String user) throws ApplicationException {
		Usuario u = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT * FROM usuarios WHERE usuario = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, user);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					u= new Usuario();
					u.setUsuario(rs.getString(1));
					u.setPassword(rs.getString(2));
					u.setNombreyapellido(rs.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt,rs);}
		return u;
	}

	public ArrayList<Usuario> listarUsuarios() throws ApplicationException{
		Usuario u = null;
		ArrayList<Usuario> lista = new ArrayList<>();
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT * FROM usuarios ORDER BY usuario";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				rs.beforeFirst();
				while (rs.next()) {
					u = new Usuario();
					u.setUsuario(rs.getString(1));
					u.setPassword(rs.getString(2));
					u.setNombreyapellido(rs.getString(3));
					lista.add(u);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			cerrar(stmt,rs);
		}
		return lista;
	}
}
