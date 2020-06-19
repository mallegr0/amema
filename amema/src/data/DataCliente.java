package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ApplicationException;
import entidades.Cliente;

public class DataCliente {
	
	/*VARIABLES*/
	Conector conn = new Conector();
	
	/*CONSTRUCTOR*/
	public DataCliente() {}
	
	/*METODOS*/
	private void cerrar(PreparedStatement stmt, ResultSet rs) {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (Exception e) { e.printStackTrace();}
	}
	
	public boolean altaCliente(Cliente c) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO CLIENTES (MARCA, CODCLI, NOMCLI, DOMCLI, CODPOS, LOCCLI, TELCLI_1, TELCLI_2, FAX, CVTO,"
				+ "CCOND, ZONCLI, NVIAJ, PROVCLI, CUITCLI, IVACLI, REGCLI, PRETEN, DNRP, SALCLI_1, SALCLID_1, FSALCLI_1,"
				+ "SALCLI_2, SALCLID_2, FSALCLI_2, A_CTA_1, A_CTA_2, A_CTAD_1, A_CTAD_2, CTRANSP, COM_IND, CREDITO, CRED_MAX, "
				+ "CONTACTO, CONTACTO2, LISTAPRE, E_MAIL, MAKITA, COMISION, COMI_DIFE, TIPO_DOC, FECHA_NAC, FECHA_ING, CPCCP) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, c.getMARCA());
			stmt.setString(2, c.getCODCLI());
			stmt.setString(3, c.getNOMCLI());
			stmt.setString(4, c.getDOMCLI());
			stmt.setInt(5, c.getCODPOS());
			stmt.setString(6, c.getLOCCLI());
			stmt.setString(7, c.getTELCLI_1());
			stmt.setString(8, c.getTELCLI_2());
			stmt.setString(9, c.getFAX());
			stmt.setString(10, c.getCVTO());
			stmt.setString(11, c.getZONCLI());
			stmt.setString(12, c.getNVIAJ());
			stmt.setString(13, c.getPROVCLI());
			stmt.setString(14, c.getCUITCLI());
			stmt.setString(15, c.getIVACLI());
			stmt.setString(16, c.getREGCLI());
			stmt.setDouble(17, c.getPRETEN());
			stmt.setString(18, c.getDNRP());
			stmt.setDouble(19, c.getSALCLI_1());
			stmt.setDouble(20, c.getSALCLID_1());
			stmt.setTimestamp(21, c.getFSALCLI_1());
			stmt.setDouble(22, c.getSALCLI_2());
			stmt.setDouble(23, c.getSALCLID_2());
			stmt.setTimestamp(24, c.getFSALCLI_2());
			stmt.setDouble(25, c.getA_CTA_1());
			stmt.setDouble(26, c.getA_CTA_2());
			stmt.setDouble(27, c.getA_CTAD_1());
			stmt.setDouble(28, c.getA_CTAD_2());
			stmt.setString(29, c.getCTRANSP());
			stmt.setString(30, c.getCOM_IND());
			stmt.setString(31, c.getCREDITO());
			stmt.setDouble(32, c.getCRED_MAX());
			stmt.setString(33, c.getCONTACTO());
			stmt.setString(34, c.getCONTACTO2());
			stmt.setInt(35, c.getLISTAPRE());
			stmt.setString(36, c.getE_MAIL());
			stmt.setString(37, c.getMAKITA());
			stmt.setString(38, c.getCOMISION());
			stmt.setString(39, c.getCOMI_DIFE());
			stmt.setString(40, c.getTIPO_DOC());
			stmt.setTimestamp(41, c.getFECHA_NAC());
			stmt.setTimestamp(42, c.getFECHA_ING());
			stmt.setString(43, c.getCPCCP());
			
			if(stmt.executeUpdate() >0 ) { return true;}
			else { return false;}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null);}
		
		
		
	}

	public boolean bajaCliente(String id) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "DELETE FROM CLIENTES WHERE CODCLI = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, id);
			
			if(stmt.executeUpdate() > 0) { return true;}
			else {return false;}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	public boolean modificaCliente(Cliente c) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "UPDATE CLIENTES SET MARCA = ?, NOMCLI = ?, DOMCLI = ?, CODPOS = ?, LOCCLI = ?, TELCLI_1 = ?, TELCLI_2 = ?, FAX = ?, CVTO = ?,"
				+ "CCOND = ?, ZONCLI = ?, NVIAJ = ?, PROVCLI = ?, CUITCLI = ?, IVACLI = ?, REGCLI = ?, PRETEN = ?, DNRP = ?, SALCLI_1 = ?, SALCLID_1 = ?, FSALCLI_1 = ?,"
				+ "SALCLI_2 = ?, SALCLID_2 = ?, FSALCLI_2 = ?, A_CTA_1 = ?, A_CTA_2 = ?, A_CTAD_1 = ?, A_CTAD_2 = ?, CTRANSP = ?, COM_IND = ?, CREDITO = ?, CRED_MAX = ?, "
				+ "CONTACTO = ?, CONTACTO2 = ?, LISTAPRE = ?, E_MAIL = ?, MAKITA = ?, COMISION = ?, COMI_DIFE = ?, TIPO_DOC = ?, FECHA_NAC = ?, FECHA_ING = ?, CPCCP = ? "
				+ "WHERE CODCLI = ?";
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, c.getMARCA());
			stmt.setString(2, c.getNOMCLI());
			stmt.setString(3, c.getDOMCLI());
			stmt.setInt(4, c.getCODPOS());
			stmt.setString(5, c.getLOCCLI());
			stmt.setString(6, c.getTELCLI_1());
			stmt.setString(7, c.getTELCLI_2());
			stmt.setString(8, c.getFAX());
			stmt.setString(9, c.getCVTO());
			stmt.setString(10, c.getZONCLI());
			stmt.setString(11, c.getNVIAJ());
			stmt.setString(12, c.getPROVCLI());
			stmt.setString(13, c.getCUITCLI());
			stmt.setString(14, c.getIVACLI());
			stmt.setString(15, c.getREGCLI());
			stmt.setDouble(16, c.getPRETEN());
			stmt.setString(17, c.getDNRP());
			stmt.setDouble(18, c.getSALCLI_1());
			stmt.setDouble(19, c.getSALCLID_1());
			stmt.setTimestamp(20, c.getFSALCLI_1());
			stmt.setDouble(21, c.getSALCLI_2());
			stmt.setDouble(22, c.getSALCLID_2());
			stmt.setTimestamp(23, c.getFSALCLI_2());
			stmt.setDouble(24, c.getA_CTA_1());
			stmt.setDouble(25, c.getA_CTA_2());
			stmt.setDouble(26, c.getA_CTAD_1());
			stmt.setDouble(27, c.getA_CTAD_2());
			stmt.setString(28, c.getCTRANSP());
			stmt.setString(29, c.getCOM_IND());
			stmt.setString(30, c.getCREDITO());
			stmt.setDouble(31, c.getCRED_MAX());
			stmt.setString(32, c.getCONTACTO());
			stmt.setString(33, c.getCONTACTO2());
			stmt.setInt(34, c.getLISTAPRE());
			stmt.setString(35, c.getE_MAIL());
			stmt.setString(36, c.getMAKITA());
			stmt.setString(37, c.getCOMISION());
			stmt.setString(38, c.getCOMI_DIFE());
			stmt.setString(39, c.getTIPO_DOC());
			stmt.setTimestamp(40, c.getFECHA_NAC());
			stmt.setTimestamp(41, c.getFECHA_ING());
			stmt.setString(42, c.getCPCCP());
			stmt.setString(43, c.getCODCLI());
			
			if(stmt.executeUpdate() >0 ) { return true;}
			else { return false;}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null);}
	}

	public Cliente consultaCliente(String id) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE CODCLI = ?";
		Cliente c = null;
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					c = new Cliente();
					c.setMARCA(rs.getString("MARCA"));
					c.setCODCLI(rs.getString("CODCLI"));
					c.setNOMCLI(rs.getString("NOMCLI"));
					c.setDOMCLI(rs.getString("DOMCLI"));
					c.setCODPOS(rs.getInt("CODPOS"));
					c.setLOCCLI(rs.getString("LOCCLI"));
					c.setTELCLI_1(rs.getString("TELCLI_1"));
					c.setTELCLI_2(rs.getString("TECCLI_2"));
					c.setFAX(rs.getString("FAX"));
					c.setCVTO(rs.getString("CVTO"));
					c.setCCOND(rs.getString("CCOND"));
					c.setZONCLI(rs.getString("ZONCLI"));
					c.setNVIAJ(rs.getString("NVIAJ"));
					c.setPROVCLI(rs.getString("PROVCLI"));
					c.setCUITCLI(rs.getString("CUITCLI"));
					c.setIVACLI(rs.getString("IVACLI"));
					c.setREGCLI(rs.getString("REGCLI"));
					c.setPRETEN(rs.getDouble("PRETEN"));
					c.setDNRP(rs.getString("DNRP"));
					c.setSALCLI_1(rs.getDouble("SALCLI_1"));
					c.setSALCLID_1(rs.getDouble("SALCLID_1"));
					c.setFSALCLI_1(rs.getTimestamp("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getTimestamp("FSALCLI_2"));
					c.setA_CTA_1(rs.getDouble("A_CTA_1"));
					c.setA_CTA_2(rs.getDouble("A_CTA_2"));
					c.setA_CTAD_1(rs.getDouble("A_CTAD_1"));
					c.setA_CTAD_2(rs.getDouble("A_CTAD_2"));
					c.setCTRANSP(rs.getString("CTRANSP"));
					c.setCOM_IND(rs.getString("COM_IND"));
					c.setCREDITO(rs.getString("CREDITO"));
					c.setCRED_MAX(rs.getDouble("CRED_MAX"));
					c.setCONTACTO(rs.getString("CONTACTO"));
					c.setCONTACTO2(rs.getString("CONTACTO2"));
					c.setLISTAPRE(rs.getInt("LISTAPRE"));
					c.setE_MAIL(rs.getString("E_MAIL"));
					c.setMAKITA(rs.getString("MAKITA"));
					c.setCOMISION(rs.getString("COMISION"));
					c.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					c.setTIPO_DOC(rs.getString("TIPO_DOC"));
					c.setFECHA_NAC(rs.getTimestamp("FECHA_NAC"));
					c.setFECHA_ING(rs.getTimestamp("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return c;
	}
	
	public ArrayList<Cliente> listarCliente() throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES ORDER BY CODCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					c = new Cliente();
					c.setMARCA(rs.getString("MARCA"));
					c.setCODCLI(rs.getString("CODCLI"));
					c.setNOMCLI(rs.getString("NOMCLI"));
					c.setDOMCLI(rs.getString("DOMCLI"));
					c.setCODPOS(rs.getInt("CODPOS"));
					c.setLOCCLI(rs.getString("LOCCLI"));
					c.setTELCLI_1(rs.getString("TELCLI_1"));
					c.setTELCLI_2(rs.getString("TECCLI_2"));
					c.setFAX(rs.getString("FAX"));
					c.setCVTO(rs.getString("CVTO"));
					c.setCCOND(rs.getString("CCOND"));
					c.setZONCLI(rs.getString("ZONCLI"));
					c.setNVIAJ(rs.getString("NVIAJ"));
					c.setPROVCLI(rs.getString("PROVCLI"));
					c.setCUITCLI(rs.getString("CUITCLI"));
					c.setIVACLI(rs.getString("IVACLI"));
					c.setREGCLI(rs.getString("REGCLI"));
					c.setPRETEN(rs.getDouble("PRETEN"));
					c.setDNRP(rs.getString("DNRP"));
					c.setSALCLI_1(rs.getDouble("SALCLI_1"));
					c.setSALCLID_1(rs.getDouble("SALCLID_1"));
					c.setFSALCLI_1(rs.getTimestamp("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getTimestamp("FSALCLI_2"));
					c.setA_CTA_1(rs.getDouble("A_CTA_1"));
					c.setA_CTA_2(rs.getDouble("A_CTA_2"));
					c.setA_CTAD_1(rs.getDouble("A_CTAD_1"));
					c.setA_CTAD_2(rs.getDouble("A_CTAD_2"));
					c.setCTRANSP(rs.getString("CTRANSP"));
					c.setCOM_IND(rs.getString("COM_IND"));
					c.setCREDITO(rs.getString("CREDITO"));
					c.setCRED_MAX(rs.getDouble("CRED_MAX"));
					c.setCONTACTO(rs.getString("CONTACTO"));
					c.setCONTACTO2(rs.getString("CONTACTO2"));
					c.setLISTAPRE(rs.getInt("LISTAPRE"));
					c.setE_MAIL(rs.getString("E_MAIL"));
					c.setMAKITA(rs.getString("MAKITA"));
					c.setCOMISION(rs.getString("COMISION"));
					c.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					c.setTIPO_DOC(rs.getString("TIPO_DOC"));
					c.setFECHA_NAC(rs.getTimestamp("FECHA_NAC"));
					c.setFECHA_ING(rs.getTimestamp("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					lista.add(c);
				}
			}
			return lista;
		}
		catch(SQLException e) { 
			e.printStackTrace();
			return null;}
		finally { cerrar(stmt, rs); }
	}
	
	public String ultimoID() {
		String id = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT TOP 1 CODCLI FROM CLIENTES ORDER BY CODCLI DESC ";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					id = rs.getString("CODCLI");
					return id;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, rs);	}
		return id;
	}
	
}
