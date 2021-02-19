package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ApplicationException;
import entidades.Cliente;

public class DataCliente {
	
	/*VARIABLES*/
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	/*CONSTRUCTOR*/
	public DataCliente() {}
	
	/*METODOS*/
	private void cerrar(PreparedStatement stmt, ResultSet rs) {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (Exception e) { e.printStackTrace();}
	}
	
	public boolean altaCliente(Cliente c) throws ApplicationException{
		PreparedStatement stmt = null;
		String sql = "INSERT INTO CLIENTES (MARCA, CODCLI, NOMCLI, DOMCLI, CODPOS, LOCCLI, TELCLI_1, TELCLI_2, FAX, CVTO,"
				+ "CCOND, ZONCLI, NVIAJ, PROVCLI, CUITCLI, IVACLI, REGCLI, PRETEN, DNRP, SALCLI_1, SALCLID_1, FSALCLI_1,"
				+ "SALCLI_2, SALCLID_2, FSALCLI_2, A_CTA_1, A_CTA_2, A_CTAD_1, A_CTAD_2, CTRANSP, COM_IND, CREDITO, CRED_MAX, "
				+ "CONTACTO, CONTACTO2, LISTAPRE, E_MAIL, MAKITA, COMISION, COMI_DIFE, TIPO_DOC, FECHA_NAC, FECHA_ING, CPCCP, OBSCLI) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			stmt = conn.prepareStatement(sql);
				
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
			stmt.setString(11, c.getCCOND());
			stmt.setString(12, c.getZONCLI());
			stmt.setString(13, c.getNVIAJ());
			stmt.setString(14, c.getPROVCLI());
			stmt.setString(15, c.getCUITCLI());
			stmt.setString(16, c.getIVACLI());
			stmt.setString(17, c.getREGCLI());
			stmt.setDouble(18, c.getPRETEN());
			stmt.setString(19, c.getDNRP());
			stmt.setDouble(20, c.getSALCLI_1());
			stmt.setDouble(21, c.getSALCLID_1());
			stmt.setDate(22, c.getFSALCLI_1());
			stmt.setDouble(23, c.getSALCLI_2());
			stmt.setDouble(24, c.getSALCLID_2());
			stmt.setDate(25, c.getFSALCLI_2());
			stmt.setDouble(26, c.getA_CTA_1());
			stmt.setDouble(27, c.getA_CTA_2());
			stmt.setDouble(28, c.getA_CTAD_1());
			stmt.setDouble(29, c.getA_CTAD_2());
			stmt.setString(30, c.getCTRANSP());
			stmt.setString(31, c.getCOM_IND());
			stmt.setString(32, c.getCREDITO());
			stmt.setDouble(33, c.getCRED_MAX());
			stmt.setString(34, c.getCONTACTO());
			stmt.setString(35, c.getCONTACTO2());
			stmt.setInt(36, c.getLISTAPRE());
			stmt.setString(37, c.getE_MAIL());
			stmt.setString(38, c.getMAKITA());
			stmt.setString(39, c.getCOMISION());
			stmt.setString(40, c.getCOMI_DIFE());
			stmt.setString(41, c.getTIPO_DOC());
			stmt.setDate(42, c.getFECHA_NAC());
			stmt.setDate(43, c.getFECHA_ING());
			stmt.setString(44, c.getCPCCP());
			stmt.setString(45, c.getOBSCLI());
			
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
			stmt = conn.prepareStatement(sql);
			
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
				+ "CONTACTO = ?, CONTACTO2 = ?, LISTAPRE = ?, E_MAIL = ?, MAKITA = ?, COMISION = ?, COMI_DIFE = ?, TIPO_DOC = ?, FECHA_NAC = ?, FECHA_ING = ?, CPCCP = ?, OBSCLI = ? "
				+ "WHERE CODCLI = ?";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, c.getMARCA());
			stmt.setString(2, c.getNOMCLI());
			stmt.setString(3, c.getDOMCLI());
			stmt.setInt(4, c.getCODPOS());
			stmt.setString(5, c.getLOCCLI());
			stmt.setString(6, c.getTELCLI_1());
			stmt.setString(7, c.getTELCLI_2());
			stmt.setString(8, c.getFAX());
			stmt.setString(9, c.getCVTO());
			stmt.setString(10, c.getCCOND());
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
			stmt.setDate(21, c.getFSALCLI_1());
			stmt.setDouble(22, c.getSALCLI_2());
			stmt.setDouble(23, c.getSALCLID_2());
			stmt.setDate(24, c.getFSALCLI_2());
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
			stmt.setDate(41, c.getFECHA_NAC());
			stmt.setDate(42, c.getFECHA_ING());
			stmt.setString(43, c.getCPCCP());
			stmt.setString(44, c.getOBSCLI());
			stmt.setString(45, c.getCODCLI());
			
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
			
			stmt = conn.prepareStatement(sql);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return c;
	}
	
	public String buscoCodigo(String legajo) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String codcli = "";
		String sql = "SELECT * FROM clientes WHERE DNRP = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, legajo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					codcli = rs.getString("CODCLI");
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return codcli;
	}
	
	public Cliente consultaClientePorDNI(String doc) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE CUITCLI = ?";
		Cliente c = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, doc);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
		String sql ="SELECT * FROM CLIENTES ORDER BY NOMCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
	
	public ArrayList<Cliente> listarClienteActivoConvenio(String estado, String conv) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE COM_IND = ? AND CCOND = ? ORDER BY CODCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, estado);
			stmt.setString(2, conv);
			
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
	
	public ArrayList<Cliente> listarClienteConvenio(String conv) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE CCOND = ? ORDER BY CODCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, conv);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
	
	public ArrayList<Cliente> listarClienteEstado(String estado) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE COM_IND = ? ORDER BY CODCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, estado);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
	
	public ArrayList<Cliente> buscarClientePorNombre(String nombre) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM CLIENTES WHERE NOMCLI LIKE CONCAT('%',?,'%') ORDER BY NOMCLI";
		Cliente c = null;
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nombre);
			
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
					c.setTELCLI_2(rs.getString("TELCLI_2"));
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
					c.setFSALCLI_1(rs.getDate("FSALCLI_1"));
					c.setSALCLI_2(rs.getDouble("SALCLI_2"));
					c.setSALCLID_2(rs.getDouble("SALCLID_2"));
					c.setFSALCLI_2(rs.getDate("FSALCLI_2"));
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
					c.setFECHA_NAC(rs.getDate("FECHA_NAC"));
					c.setFECHA_ING(rs.getDate("FECHA_ING"));
					c.setCPCCP(rs.getString("CPCCP"));
					c.setOBSCLI(rs.getString("OBSCLI"));
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
		String sql = "SELECT CODCLI FROM CLIENTES ORDER BY CODCLI DESC LIMIT 1";
		
		try {
			stmt = conn.prepareStatement(sql);
			
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
	
	public String localidad(Integer id) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String r = null;
		String sql = "SELECT LOCALIDAD FROM LOCALIDADES WHERE CODPOS = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs != null) { while(rs.next()) {  r = rs.getString("LOCALIDAD"); } }
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return r;
	}
}
