package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ApplicationException;
import entidades.VentasD;

public class DataVentasD {

	// Variables
	//Conector conn = new Conector();
		ConectorMySQL conn = new ConectorMySQL();

	// constructor
	public DataVentasD() {
	}

	// Metodos

	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			conn.cerrarConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean altaVentasD(VentasD v) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO VENTASD (PREFIJO, NCOMP, TCOMP, LETRA, CIA, CODART, UNIDADES, PBONIF, "
				+ "PBONIF2, PVENTA, DESPACHO, ADUANA, BONART) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = conn.abrirConn().prepareStatement(sql);

			stmt.setString(1, v.getPREFIJO());
			stmt.setString(2, v.getNCOMP());
			stmt.setString(3, v.getTCOMP());
			stmt.setString(4, v.getLETRA());
			stmt.setString(5, v.getCIA());
			stmt.setString(6, v.getCODART());
			stmt.setDouble(7, v.getUNIDADES());
			stmt.setDouble(8, v.getPBONIF());
			stmt.setDouble(9, v.getPBONIF2());
			stmt.setDouble(10, v.getPVENTA());
			stmt.setString(11, v.getDESPACHO());
			stmt.setString(12, v.getADUANA());
			stmt.setDouble(13, v.getBONART());

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrar(stmt, null);
		}
	}

	public boolean bajaVentasD(String nro) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM VENTASD WHERE NCOMP = ?";

		try {
			stmt = conn.abrirConn().prepareStatement(sql);

			stmt.setString(1, nro);

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrar(stmt, null);
		}
	}

	public boolean modificaVentasD(VentasD v) throws ApplicationException {
		PreparedStatement stmt = null;
		String sql = "UPDATE FROM VENTASD SET (PREFIJO = ?, TCOMP = ?, LETRA = ?, CIA = ?, CODART = ?, UNIDADES = ?, "
				+ "PBONIF = ?, PBONIF2 = ?, PVENTA = ?, DESPACHO = ?, ADUANA = ?, BONART = ?) WHERE NCOMP = ?";

		try {
			stmt = conn.abrirConn().prepareStatement(sql);

			stmt.setString(1, v.getPREFIJO());
			stmt.setString(2, v.getTCOMP());
			stmt.setString(3, v.getLETRA());
			stmt.setString(4, v.getCIA());
			stmt.setString(5, v.getCODART());
			stmt.setDouble(6, v.getUNIDADES());
			stmt.setDouble(7, v.getPBONIF());
			stmt.setDouble(8, v.getPBONIF2());
			stmt.setDouble(9, v.getPVENTA());
			stmt.setString(10, v.getDESPACHO());
			stmt.setString(11, v.getADUANA());
			stmt.setDouble(12, v.getBONART());
			stmt.setString(13, v.getNCOMP());

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrar(stmt, null);
		}
	}

	public VentasD consultaVentasD(String nro) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasD v = null;
		String sql = "SELECT * FROM VENTASD WHERE NCOMP = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, nro);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					v = new VentasD();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setCODART(rs.getString("CODART"));
					v.setUNIDADES(rs.getDouble("UNIDADES"));
					v.setPBONIF(rs.getDouble("PBONIF"));
					v.setPBONIF2(rs.getDouble("PBONIF2"));
					v.setPVENTA(rs.getDouble("PVENTA"));
					v.setDESPACHO(rs.getString("DESPACHO"));
					v.setADUANA(rs.getString("ADUANA"));
					v.setBONART(rs.getDouble("BONART"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, null); }
		return v;
	}
}
