package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Conector;
import util.ApplicationException;
import entidades.Usuario;

public class DataUsuario {
	
	/*CONSTRUCTOR*/
	
	public DataUsuario() {}
	
	/*VARIABLES*/
	
	Conector conn = new Conector();
	
	/*METODOS*/
	
	private void cerrar(PreparedStatement stmt, ResultSet rs){
		try{
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(ApplicationException | SQLException e){ e.printStackTrace();	}
	}

	public boolean altaUsuario(Usuario u) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="INSERT INTO usuarios SET user = ?, password = ?, nombre = ?, mail = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getUser());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getNombre());
			stmt.setString(4, u.getMail());
			
			return stmt.execute();
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}

	public boolean bajaUsuario(String id) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="DELETE FROM usuarios WHERE user = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, id);
			
			return stmt.execute();
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}
	
	public boolean modificaUsuario(Usuario u) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="UPDATE usuarios SET password = ?, nombre = ?, mail = ? WHERE user = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getPassword());
			stmt.setString(2, u.getNombre());
			stmt.setString(3, u.getMail());
			stmt.setString(4, u.getUser());
			
			return stmt.execute();
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}
	
	public Usuario consultaUsuario(Usuario u) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM usuarios WHERE user = ?";
		Usuario users = null;
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getUser());
			
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()){
				users = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		}
		catch( SQLException e){
			e.printStackTrace();
		}
		finally{
			cerrar(stmt, rs);
		}
		return users;
	}
	
	public ArrayList<Usuario> listarUsuarios(String orden) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM usuarios ORDER BY ?";
		Usuario users = null;
		ArrayList<Usuario> lista = new ArrayList<>();
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, orden);
			
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					users = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
					lista.add(users);
				}
			}
		}
		catch( SQLException e){	e.printStackTrace(); }
		finally{
			cerrar(stmt, rs);
		}
		return lista;
	}
	
	public boolean cambiaPassword(String user, String pass) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="UPDATE usuarios SET password = ? WHERE user = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, pass);
			stmt.setString(2, user);
			
			return stmt.execute();
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}
}
