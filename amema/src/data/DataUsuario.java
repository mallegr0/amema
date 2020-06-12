package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import entidades.Usuario;
import util.ApplicationException;


public class DataUsuario {
	
	/*	CONSTRUCTOR	*/
	public DataUsuario(){}
	
	/*  variables  */
	ConectorSeguridad conn = new ConectorSeguridad();
	
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
		String sql = "INSERT INTO Usuarios (NroUsuario, NomUs, LogIn, PassWord, DescOficina, DescFunc, Cperfil, Hab) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getNroUsuario());
			stmt.setString(2, u.getNomUs());
			stmt.setString(3, u.getLogIn());
			stmt.setString(4, u.getPassWord());
			stmt.setString(5, u.getDescOficina());
			stmt.setString(6, u.getDescFunc());
			stmt.setString(7, u.getCperfil());
			stmt.setString(8, u.getHab());
			
			
			if(stmt.executeUpdate() > 0) { return true; }
			else {return false; }
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
		String sql = "DELETE FROM Usuarios WHERE LogIn = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, user);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else {return false; }
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
		String sql = "UPDATE Usuarios SET NroUsuario = ?, NomUs = ?, PassWord = ?, DescOficina = ?, DescFunc = ?, cPerfil = ?, hab = ?"
				+ "WHERE LogIn = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, u.getNroUsuario());
			stmt.setString(2, u.getNomUs());
			stmt.setString(3, u.getPassWord());
			stmt.setString(4, u.getDescOficina());
			stmt.setString(5, u.getDescFunc());
			stmt.setString(6, u.getCperfil());
			stmt.setString(7, u.getHab());
			stmt.setString(8, u.getLogIn());
			
			
			if(stmt.executeUpdate() > 0) { return true; }
			else {return false; }
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
		String sql = "SELECT * FROM Usuarios WHERE LogIn = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, user);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					u= new Usuario();
					u.setNroUsuario(rs.getString("NroUsuario"));
					u.setNomUs(rs.getString("nomUs"));
					u.setLogIn(rs.getString("LogIn"));
					u.setPassWord(rs.getString("PassWord"));
					u.setDescOficina(rs.getString("descOficina"));
					u.setDescFunc(rs.getString("descFunc"));
					u.setCperfil(rs.getString("cPerfil"));
					u.setHab(rs.getString("Hab"));	
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
		String sql = "SELECT * FROM Usuarios ORDER BY NroUsuario";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					u= new Usuario();
					u.setNroUsuario(rs.getString("NroUsuario"));
					u.setNomUs(rs.getString("nomUs"));
					u.setLogIn(rs.getString("LogIn"));
					u.setPassWord(rs.getString("PassWord"));
					u.setDescOficina(rs.getString("descOficina"));
					u.setDescFunc(rs.getString("descFunc"));
					u.setCperfil(rs.getString("cPerfil"));
					u.setHab(rs.getString("Hab"));	
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
	
	public boolean cambiaPassword(String user, String pass) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "UPDATE Usuarios SET PassWord = ? WHERE LogIn = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, pass);
			stmt.setString(2, user);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else {return false; }
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			cerrar(stmt,null);
		}
	}
	
	public String ultimoID() {
		String id = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT TOP 1 NroUsuario FROM Usuarios ORDER BY NroUsuario DESC ";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					id = rs.getString("NroUsuario");
					return id;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, rs);	}
		return id;
	}

	public String consultaPerfil(String nro) {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String r = "";
		String sql = "SELECT DescPerfil FROM PerfilUs WHERE cPerfil = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, nro);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					r = rs.getString("DescPerfil"); 
				}
			}
		} catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return r;
	}

}
