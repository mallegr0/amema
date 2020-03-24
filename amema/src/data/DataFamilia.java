package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Familia;
import util.ApplicationException;

public class DataFamilia {
	
	/*CONSTRUCTOR*/
	
	public DataFamilia() {}
	
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

	public boolean altaFamilia(Familia f) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="INSERT INTO familias SET codigo = ?, descripcion = ?, bonificacion = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, f.getCodigo());
			stmt.setString(2, f.getDescripcion());
			stmt.setInt(3, f.getBonificacion());
			
			if(stmt.executeUpdate() > 0){
				return true;
			}
			else{ return false;}
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}

	public boolean bajaFamilia(int id) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="DELETE FROM familias WHERE codigo = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			if(stmt.executeUpdate() > 0){
				return true;
			}
			else{ return false;}
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}
	
	public boolean modificaFamilia(Familia f) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="UPDATE familias SET descripcion = ?, bonificacion = ? WHERE codigo = ?";
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, f.getDescripcion());
			stmt.setInt(2, f.getBonificacion());
			stmt.setInt(3, f.getCodigo());
			
			if(stmt.executeUpdate() > 0){
				return true;
			}
			else{
				return false;
			}
		}
		catch( SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			cerrar(stmt, rs);
		}
	}
	
	public Familia consultaFamilia(int id) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM familias WHERE codigo = ?";
		Familia flia = null;
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()){
				flia = new Familia(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		}
		catch( SQLException e){
			e.printStackTrace();
		}
		finally{
			cerrar(stmt, rs);
		}
		return flia;
	}
	
	public ArrayList<Familia> listarFamilias(String orden) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM familias ORDER BY ?";
		Familia flia = null;
		ArrayList<Familia> lista = new ArrayList<>();
		
		try{
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, orden);
			
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					flia = new Familia(rs.getInt(1), rs.getString(2), rs.getInt(3));
					lista.add(flia);
				}
			}
		}
		catch( SQLException e){	e.printStackTrace(); }
		finally{
			cerrar(stmt, rs);
		}
		return lista;
	}
}
