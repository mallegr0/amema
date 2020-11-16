package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

import entidades.Venta;
import util.ApplicationException;

public class DataVenta {
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	/* CONSTRUCTOR */
	public DataVenta() {}
	
	
	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	public boolean altaVenta(Venta v) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO VENTAS (NCOMP, TCOMP, LETRA, CIA, FCOMP, NFACC, FVTO, CODCLI, REGCLI, OBSERV, CPERS1,"
				+ "CPERS2, CPERS3, CVTO, NROREMITO, NROPEDIDO, NROPRESUP, NVIAJ, DIRECTA, REFERENCIA, LIQUIDA, COMI_DIFE, "
				+ "INCCTACTE, DESPACHA, TEXLIB, TEXTO, FLETE, CCOND_1, CCOND_2, CCOND_3, CCOND_4, PORDESCTO, PORBONIF, VA_DTO, CODART, "
				+ "TASA, DESPACHO, TIVA, BONART, BONART2, PRECIO, UNIDADES, UBICAC1, UBICAC2, UBICAC3, ANALISIS, FEC_DESDE, NROMOV, IMPCH,"
				+ "CANCDEUANT, IMPCANCDEUANT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, v.getNCOMP());
			stmt.setString(2, v.getTCOMP());
			stmt.setString(3, v.getLETRA());
			stmt.setString(4, v.getCIA());
			stmt.setDate(5, cambiaFecha(v.getFCOMP()));
			stmt.setString(6, v.getNFACC());
			stmt.setDate(7, cambiaFecha(v.getFVTO()));
			stmt.setString(8, v.getCODCLI());
			stmt.setString(9, v.getREGCLI());
			stmt.setString(10, v.getOBSERV());
			stmt.setString(11, v.getCPERS1());
			stmt.setString(12, v.getCPERS2());
			stmt.setString(13, v.getCPERS3());
			stmt.setString(14, v.getCVTO());
			stmt.setString(15, v.getNROREMITO());
			stmt.setString(16, v.getNROPEDIDO());
			stmt.setString(17, v.getNROPRESUP());
			stmt.setString(18, v.getNVIAJ());
			stmt.setString(19, v.getDIRECTA());
			stmt.setString(20, v.getREFERENCIA());
			stmt.setString(21, v.getLIQUIDA());
			stmt.setString(22, v.getCOMI_DIFE());
			stmt.setString(23, v.getINNCTACTE());
			stmt.setString(24, v.getDESPACHA());
			stmt.setString(25, v.getTEXTLIB());
			stmt.setDouble(26, v.getTEXTO());
			stmt.setDouble(27, v.getFLETE());
			stmt.setString(28, v.getCCOND_1());
			stmt.setString(29, v.getCCOND_2());
			stmt.setString(30, v.getCCOND_3());
			stmt.setString(31, v.getCCOND_4());
			stmt.setDouble(32, v.getPORDESCTO());
			stmt.setDouble(33, v.getPORBONIF());
			stmt.setString(34, v.getVA_DTO());
			stmt.setString(35, v.getCODART());
			stmt.setDouble(36, v.getTASA());
			stmt.setString(37, v.getDESPACHO());
			stmt.setString(38, v.getTIVA());
			stmt.setDouble(39, v.getBONART());
			stmt.setDouble(40, v.getBONART2());
			stmt.setDouble(41, v.getPRECIO());
			stmt.setDouble(42, v.getUNIDADES());
			stmt.setString(43, v.getUBICAC1());
			stmt.setString(44, v.getUBICAC2());
			stmt.setString(45, v.getUBICAC3());
			stmt.setString(46, v.getANALISIS());
			stmt.setDate(47,cambiaFecha(v.getFEC_DESDE()));
			stmt.setInt(48, v.getNROMOV());
			stmt.setDouble(49, v.getIMPCH());
			stmt.setString(50, v.getCANCDEUANT());
			stmt.setDouble(51, v.getIMPCANCDEUANT());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean bajaVenta(int mov) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM VENTAS WHERE NROMOV = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, mov);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificaVenta(Venta v) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE VENTAS SET NCOMP = ?, TCOMP = ?, LETRA = ?, CIA = ?, FCOMP = ?, NFACC = ?, FVTO = ?, REGCLI = ?, OBSERV = ?, CPERS1 = ?,"
				+ "CPERS2 = ?, CPERS3 = ?, CVTO = ?, NROREMITO = ?, NROPEDIDO = ?, NROPRESUP = ?, NVIAJ = ?, DIRECTA = ?, REFERENCIA = ?, LIQUIDA = ?,"
				+ "COMI_DIFE = ?, INCCTACTE = ?, DESPACHA = ?, TEXLIB = ?, TEXTO = ?, FLETE = ?, CCOND_1 = ?, CCOND_2 = ?, CCOND_3 = ?, CCOND_4 = ?, "
				+ "PORDESCTO = ?, PORBONIF = ?, VA_DTO = ?, CODART = ?, TASA = ?, DESPACHO = ?, TIVA = ?, BONART = ?, BONART2 = ?, PRECIO = ?, UNIDADES = ?, UBICAC1 = ?, "
				+ "UBICAC2 = ?, UBICAC3 = ?, ANALISIS = ?, FEC_DESDE = ?, IMPCH = ?, CANCDEUANT = ?, IMPCANCDEUANT = ? WHERE CODCLI = ? AND NROMOV = ?";
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, v.getNCOMP());
			stmt.setString(2, v.getTCOMP());
			stmt.setString(3, v.getLETRA());
			stmt.setString(4, v.getCIA());
			stmt.setDate(5, cambiaFecha(v.getFCOMP()));
			stmt.setString(6, v.getNFACC());
			stmt.setDate(7, cambiaFecha(v.getFVTO()));
			stmt.setString(8, v.getREGCLI());
			stmt.setString(9, v.getOBSERV());
			stmt.setString(10, v.getCPERS1());
			stmt.setString(11, v.getCPERS2());
			stmt.setString(12, v.getCPERS3());
			stmt.setString(13, v.getCVTO());
			stmt.setString(14, v.getNROREMITO());
			stmt.setString(15, v.getNROPEDIDO());
			stmt.setString(16, v.getNROPRESUP());
			stmt.setString(17, v.getNVIAJ());
			stmt.setString(18, v.getDIRECTA());
			stmt.setString(19, v.getREFERENCIA());
			stmt.setString(20, v.getLIQUIDA());
			stmt.setString(21, v.getCOMI_DIFE());
			stmt.setString(22, v.getINNCTACTE());
			stmt.setString(23, v.getDESPACHA());
			stmt.setString(24, v.getTEXTLIB());
			stmt.setDouble(25, v.getTEXTO());
			stmt.setDouble(26, v.getFLETE());
			stmt.setString(27, v.getCCOND_1());
			stmt.setString(28, v.getCCOND_2());
			stmt.setString(29, v.getCCOND_3());
			stmt.setString(30, v.getCCOND_4());
			stmt.setDouble(31, v.getPORDESCTO());
			stmt.setDouble(32, v.getPORBONIF());
			stmt.setString(33, v.getVA_DTO());
			stmt.setString(34, v.getCODART());
			stmt.setDouble(35, v.getTASA());
			stmt.setString(36, v.getDESPACHO());
			stmt.setString(37, v.getTIVA());
			stmt.setDouble(38, v.getBONART());
			stmt.setDouble(39, v.getBONART2());
			stmt.setDouble(40, v.getPRECIO());
			stmt.setDouble(41, v.getUNIDADES());
			stmt.setString(42, v.getUBICAC1());
			stmt.setString(43, v.getUBICAC2());
			stmt.setString(44, v.getUBICAC3());
			stmt.setString(45, v.getANALISIS());
			stmt.setDate(46,cambiaFecha(v.getFEC_DESDE()));
			stmt.setDouble(47, v.getIMPCH());
			stmt.setString(48, v.getCANCDEUANT());
			stmt.setDouble(49, v.getIMPCANCDEUANT());
			stmt.setString(50, v.getCODCLI());
			stmt.setInt(51, v.getNROMOV());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}

	
	public Venta ConsultaVentaPorNroMov(int nroMov) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		Venta v = null;
		String sql = "SELECT * FROM VENTAS WHERE NROMOV = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, nroMov);
			
			rs = stmt.executeQuery();
			
			if (rs != null) {
				while(rs.next()) {
					v = new Venta();
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFCOMP((Date) rs.getDate("FCOMP"));
					v.setNFACC(rs.getString("NFACC"));
					v.setFVTO((Date) rs.getDate("FVTO"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setREGCLI(rs.getString("REGCLI"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setCVTO(rs.getString("CVTO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setINNCTACTE(rs.getString("INCCTACTE"));
					v.setDESPACHA(rs.getString("DESPACHA"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCODART(rs.getString("CODART"));
					v.setTASA(rs.getDouble("TASA"));
					v.setDESPACHO(rs.getString("DESPACHO"));
					v.setTIVA(rs.getString("TIVA"));
					v.setBONART(rs.getDouble("BONART"));
					v.setBONART2(rs.getDouble("BONART2"));
					v.setPRECIO(rs.getDouble("PRECIO"));
					v.setUNIDADES(rs.getDouble("UNIDADES"));
					v.setUBICAC1(rs.getString("UBICAC1"));
					v.setUBICAC2(rs.getString("UBICAC2"));
					v.setUBICAC3(rs.getString("UBICAC3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setFEC_DESDE((Date) rs.getDate("FEC_DESDE"));
					v.setNROMOV(rs.getInt("NROMOV"));
					v.setIMPCH(rs.getDouble("IMPCH"));
					v.setCANCDEUANT(rs.getString("CANCDEUANT"));
					v.setIMPCANCDEUANT(rs.getDouble("IMPCANCDEUANT"));
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally {
			cerrar(stmt, rs);
		}
		return v;
	}
	
	public String consultarClientePorNroMov(int nro) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String codcli = "";
		String sql ="SELECT * FROM VENTAS WHERE NROMOV = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nro);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					codcli = rs.getString("CODCLI");
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return codcli;
	}
	
	//metodo listar 
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ArrayList<Venta> lista = new ArrayList<>();
		Venta v = null;
		String sql = "SELECT * FROM VENTAS WHERE CODCLI = ? ORDER BY VA_DTO DESC, NROMOV ASC";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					v = new Venta();
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFCOMP((Date) rs.getDate("FCOMP"));
					v.setNFACC(rs.getString("NFACC"));
					v.setFVTO((Date) rs.getDate("FVTO"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setREGCLI(rs.getString("REGCLI"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setCVTO(rs.getString("CVTO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setINNCTACTE(rs.getString("INCCTACTE"));
					v.setDESPACHA(rs.getString("DESPACHA"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCODART(rs.getString("CODART"));
					v.setTASA(rs.getDouble("TASA"));
					v.setDESPACHO(rs.getString("DESPACHO"));
					v.setTIVA(rs.getString("TIVA"));
					v.setBONART(rs.getDouble("BONART"));
					v.setBONART2(rs.getDouble("BONART2"));
					v.setPRECIO(rs.getDouble("PRECIO"));
					v.setUNIDADES(rs.getDouble("UNIDADES"));
					v.setUBICAC1(rs.getString("UBICAC1"));
					v.setUBICAC2(rs.getString("UBICAC2"));
					v.setUBICAC3(rs.getString("UBICAC3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setFEC_DESDE((Date) rs.getDate("FEC_DESDE"));
					v.setNROMOV(rs.getInt("NROMOV"));
					v.setIMPCH(rs.getDouble("IMPCH"));
					v.setCANCDEUANT(rs.getString("CANCDEUANT"));
					v.setIMPCANCDEUANT(rs.getDouble("IMPCANCDEUANT"));
					lista.add(v);
				}
			}
		}
		catch( SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<Venta> listarVentasPorFechas(java.util.Date fecIni, java.util.Date fecFin) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ArrayList<Venta> lista = new ArrayList<>();
		Venta v = null;
		String sql = "SELECT * FROM VENTAS WHERE FCOMP BETWEEN ? AND ? ORDER NCOMP";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1, cambiaFecha(fecIni));
			stmt.setDate(2, cambiaFecha(fecFin));
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					v = new Venta();
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFCOMP((Date) rs.getDate("FCOMP"));
					v.setNFACC(rs.getString("NFACC"));
					v.setFVTO((Date) rs.getDate("FVTO"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setREGCLI(rs.getString("REGCLI"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setCVTO(rs.getString("CVTO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setINNCTACTE(rs.getString("INCCTACTE"));
					v.setDESPACHA(rs.getString("DESPACHA"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCODART(rs.getString("CODART"));
					v.setTASA(rs.getDouble("TASA"));
					v.setDESPACHO(rs.getString("DESPACHO"));
					v.setTIVA(rs.getString("TIVA"));
					v.setBONART(rs.getDouble("BONART"));
					v.setBONART2(rs.getDouble("BONART2"));
					v.setPRECIO(rs.getDouble("PRECIO"));
					v.setUNIDADES(rs.getDouble("UNIDADES"));
					v.setUBICAC1(rs.getString("UBICAC1"));
					v.setUBICAC2(rs.getString("UBICAC2"));
					v.setUBICAC3(rs.getString("UBICAC3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setFEC_DESDE((Date) rs.getDate("FEC_DESDE"));
					v.setNROMOV(rs.getInt("NROMOV"));
					v.setIMPCH(rs.getDouble("IMPCH"));
					v.setCANCDEUANT(rs.getString("CANCDEUANT"));
					v.setIMPCANCDEUANT(rs.getDouble("IMPCANCDEUANT"));
					lista.add(v);
				}
			}
		}
		catch( SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<Venta> listarVentasPorFechasyModo(java.util.Date fecIni, java.util.Date fecFin, String modo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ArrayList<Venta> lista = new ArrayList<>();
		Venta v = null;
		String sql = "SELECT * FROM VENTAS WHERE FCOMP BETWEEN ? AND ? AND INCCTACTE = ? ORDER BY NCOMP";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1, cambiaFecha(fecIni));
			stmt.setDate(2, cambiaFecha(fecFin));
			stmt.setString(3, modo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					v = new Venta();
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFCOMP((Date) rs.getDate("FCOMP"));
					v.setNFACC(rs.getString("NFACC"));
					v.setFVTO((Date) rs.getDate("FVTO"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setREGCLI(rs.getString("REGCLI"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setCVTO(rs.getString("CVTO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setINNCTACTE(rs.getString("INCCTACTE"));
					v.setDESPACHA(rs.getString("DESPACHA"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCODART(rs.getString("CODART"));
					v.setTASA(rs.getDouble("TASA"));
					v.setDESPACHO(rs.getString("DESPACHO"));
					v.setTIVA(rs.getString("TIVA"));
					v.setBONART(rs.getDouble("BONART"));
					v.setBONART2(rs.getDouble("BONART2"));
					v.setPRECIO(rs.getDouble("PRECIO"));
					v.setUNIDADES(rs.getDouble("UNIDADES"));
					v.setUBICAC1(rs.getString("UBICAC1"));
					v.setUBICAC2(rs.getString("UBICAC2"));
					v.setUBICAC3(rs.getString("UBICAC3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setFEC_DESDE((Date) rs.getDate("FEC_DESDE"));
					v.setNROMOV(rs.getInt("NROMOV"));
					v.setIMPCH(rs.getDouble("IMPCH"));
					v.setCANCDEUANT(rs.getString("CANCDEUANT"));
					v.setIMPCANCDEUANT(rs.getDouble("IMPCANCDEUANT"));
					lista.add(v);
				}
			}
		}
		catch( SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public int ultimoID() throws ApplicationException {
		int id = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT NROMOV FROM VENTAS ORDER BY NROMOV DESC LIMIT 1";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					id = rs.getInt("NROMOV");
					return id;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally { cerrar(stmt, rs);	}
		return id;
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec;
	}


}
