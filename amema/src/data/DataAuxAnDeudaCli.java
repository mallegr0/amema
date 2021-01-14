package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AuxAnDeudaCli;
import util.ApplicationException;

public class DataAuxAnDeudaCli {
	
	// Variables 
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	// Constructor
	public DataAuxAnDeudaCli() {}
	
	// Metodos privados
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec;
	}
	
	//Metodos publicos
	public boolean altaAuxAnDeudaCli(AuxAnDeudaCli a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO auxandeudacli (codcli, nomcli, tipomov, fcomp, ncomp, tcomp, importe, nviaj, ref,"
				+ "convenio, analisis, periodo, saldo, importepagado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getCODCLI());
			stmt.setString(2, a.getNOMCLI());
			stmt.setString(3, a.getTIPOMOV());
			stmt.setDate(4, cambiaFecha(a.getFCOMP()));
			stmt.setString(5, a.getNCOMP());
			stmt.setString(6, a.getTCOMP());
			stmt.setDouble(7, a.getIMPORTE());
			stmt.setString(8, a.getNVIAJ());
			stmt.setString(9, a.getREF());
			stmt.setString(10, a.getCONVENIO());
			stmt.setInt(11, a.getANALISIS());
			stmt.setString(12, a.getPERIODO());
			stmt.setString(13, a.getSALDO());
			stmt.setDouble(14, a.getIMPORTEPAGADO());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaAuxAnDeudaCli(String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM auxandeudacli WHERE periodo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, periodo);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean eliminaPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM auxandeudacli WHERE periodo = ? AND convenio = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaAuxAnDeudaCli(AuxAnDeudaCli a) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE auxandeudacli SET importepagado = ? WHERE ncomp = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getIMPORTEPAGADO());
			stmt.setString(2, a.getNCOMP());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean hayDatosEnPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND convenio = ?"; 
		boolean rta = false;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) { rta = true; }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rta;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCli(String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliVarios(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND ref <> 'C' AND convenio = ? ORDER BY codcli, analisis DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliCuotas(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND ref = 'C' AND convenio = ? ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliPeriodoyConvenio(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND convenio = ? AND importepagado > 0 ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliPeriodo(String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? ORDER BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarAuxAnDeudaCliJubilados(String periodo, String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND convenio = ? AND importepagado = 0 ORDER BY codcli, analisis DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> listarDeudoresPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT * FROM auxandeudacli WHERE periodo = ? AND convenio = ? AND importepagado = 0 ORDER BY codcli, analisis DESC";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, periodo);
			stmt.setString(2, convenio);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setNOMCLI(rs.getString("nomcli"));
					a.setTIPOMOV(rs.getString("tipomov"));
					a.setFCOMP(rs.getDate("fcomp"));
					a.setNCOMP(rs.getString("ncomp"));
					a.setTCOMP(rs.getString("tcomp"));
					a.setIMPORTE(rs.getDouble("importe"));
					a.setNVIAJ(rs.getString("nviaj"));
					a.setREF(rs.getString("ref"));
					a.setCONVENIO(rs.getString("convenio"));
					a.setANALISIS(rs.getInt("analisis"));
					a.setPERIODO(rs.getString("periodo"));
					a.setSALDO(rs.getString("saldo"));
					a.setIMPORTEPAGADO(rs.getDouble("importepagado"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<AuxAnDeudaCli> totalDeudaPeriodoyConvenio(String convenio, String periodo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		AuxAnDeudaCli a = null; 
		ArrayList<AuxAnDeudaCli> lista = new ArrayList<>();
		String sql = "SELECT codcli, SUM(importe) AS deuda FROM auxandeudacli WHERE convenio = ? AND periodo = ? AND importepagado = 0 GROUP BY codcli";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, convenio);
			stmt.setString(2, periodo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new AuxAnDeudaCli();
					a.setCODCLI(rs.getString("codcli"));
					a.setIMPORTE(rs.getDouble("deuda"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
}
