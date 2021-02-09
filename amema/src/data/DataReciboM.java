package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ReciboM;
import util.ApplicationException;

public class DataReciboM {
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	/* CONSTRUCTOR */
	public DataReciboM() {}
	
	/* METODOS PRIVADOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec;
	}
	
	
	/* METODOS PUBLICOS */
	//alta
	public boolean altaReciboM(ReciboM rm) throws ApplicationException{
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO recibosm (nrecibo, cia, prefijo, frecibo, trecibo, codcli, nviaj, "
				+ "liquida, efectivo, cheques, a_cta, cerrada, comi_dife, referencia, tcomp01, tcomp02, tcomp03, "
				+ "tcomp04, tcomp05, tcomp06, tcomp07, tcomp08, tcomp09, tcomp10, tcomp11, tcomp12, tcomp13, tcomp14, "
				+ "tcomp15, tcomp16, tcomp17, tcomp18, tcomp19, tcomp20, nfact01, nfact02, nfact03, nfact04, nfact05, "
				+ "nfact06, nfact07, nfact08, nfact09, nfact10, nfact11, nfact12, nfact13, nfact14, nfact15, nfact16, "
				+ "nfact17, nfact18, nfact19, nfact20, ifact01, ifact02, ifact03, ifact04, ifact05, ifact06, ifact07, "
				+ "ifact08, ifact09, ifact10, ifact11, ifact12, ifact13, ifact14, ifact15, ifact16, ifact17, ifact18, "
				+ "ifact19, ifact20, nanti01, nanti02, nanti03, nanti04, nanti05, nanti06, nanti07, nanti08, nanti09, "
				+ "nanti10, ianti01, ianti02, ianti03, ianti04, ianti05, ianti06, ianti07, ianti08, ianti09, ianti10, "
				+ "ncred01, ncred02, ncred03, ncred04, ncred05, ncred06, ncred07, ncred08, ncred09, ncred10, icred01, "
				+ "icred02, icred03, icred04, icred05, icred06, icred07, icred08, icred09, icred10, descuentos, retencion, "
				+ "usado, nrogendeuda, difdspago) values (?, ?, ?, ?, ?, ?, ? ,? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, rm.getNRECIBO());
			stmt.setString(2, rm.getCIA());
			stmt.setString(3, rm.getPREFIJO());
			stmt.setDate(4, cambiaFecha(rm.getFRECIBO()));
			stmt.setString(5, rm.getTRECIBO());
			stmt.setString(6, rm.getCODCLI());
			stmt.setString(7, rm.getNVIAJ());
			stmt.setString(8, rm.getLIQUIDA());
			stmt.setDouble(9, rm.getEFECTIVO());
			stmt.setDouble(10, rm.getCHEQUE());
			stmt.setDouble(11, rm.getA_CTA());
			stmt.setString(12, rm.getCERRADA());
			stmt.setString(13, rm.getCOMI_DIFE());
			stmt.setString(14, rm.getREFERENCIA());
			stmt.setString(15, rm.getTCOMP01());
			stmt.setString(16, rm.getTCOMP02());
			stmt.setString(17, rm.getTCOMP03());
			stmt.setString(18, rm.getTCOMP04());
			stmt.setString(19, rm.getTCOMP05());
			stmt.setString(20, rm.getTCOMP06());
			stmt.setString(21, rm.getTCOMP07());
			stmt.setString(22, rm.getTCOMP08());
			stmt.setString(23, rm.getTCOMP09());
			stmt.setString(24, rm.getTCOMP10());
			stmt.setString(25, rm.getTCOMP11());
			stmt.setString(26, rm.getTCOMP12());
			stmt.setString(27, rm.getTCOMP13());
			stmt.setString(28, rm.getTCOMP14());
			stmt.setString(29, rm.getTCOMP15());
			stmt.setString(30, rm.getTCOMP16());
			stmt.setString(31, rm.getTCOMP17());
			stmt.setString(32, rm.getTCOMP18());
			stmt.setString(33, rm.getTCOMP19());
			stmt.setString(34, rm.getTCOMP20());
			stmt.setString(35, rm.getNFACT01());
			stmt.setString(36, rm.getNFACT02());
			stmt.setString(37, rm.getNFACT03());
			stmt.setString(38, rm.getNFACT04());
			stmt.setString(39, rm.getNFACT05());
			stmt.setString(40, rm.getNFACT06());
			stmt.setString(41, rm.getNFACT07());
			stmt.setString(42, rm.getNFACT08());
			stmt.setString(43, rm.getNFACT09());
			stmt.setString(44, rm.getNFACT10());
			stmt.setString(45, rm.getNFACT11());
			stmt.setString(46, rm.getNFACT12());
			stmt.setString(47, rm.getNFACT13());
			stmt.setString(48, rm.getNFACT14());
			stmt.setString(49, rm.getNFACT15());
			stmt.setString(50, rm.getNFACT16());
			stmt.setString(51, rm.getNFACT17());
			stmt.setString(52, rm.getNFACT18());
			stmt.setString(53, rm.getNFACT19());
			stmt.setString(54, rm.getNFACT20());
			stmt.setDouble(55, rm.getIFACT01());
			stmt.setDouble(56, rm.getIFACT02());
			stmt.setDouble(57, rm.getIFACT03());
			stmt.setDouble(58, rm.getIFACT04());
			stmt.setDouble(59, rm.getIFACT05());
			stmt.setDouble(60, rm.getIFACT06());
			stmt.setDouble(61, rm.getIFACT07());
			stmt.setDouble(62, rm.getIFACT08());
			stmt.setDouble(63, rm.getIFACT09());
			stmt.setDouble(64, rm.getIFACT10());
			stmt.setDouble(65, rm.getIFACT11());
			stmt.setDouble(66, rm.getIFACT12());
			stmt.setDouble(67, rm.getIFACT13());
			stmt.setDouble(68, rm.getIFACT14());
			stmt.setDouble(69, rm.getIFACT15());
			stmt.setDouble(70, rm.getIFACT16());
			stmt.setDouble(71, rm.getIFACT17());
			stmt.setDouble(72, rm.getIFACT18());
			stmt.setDouble(73, rm.getIFACT19());
			stmt.setDouble(74, rm.getIFACT20());
			stmt.setString(75, rm.getNANTI01());
			stmt.setString(76, rm.getNANTI02());
			stmt.setString(77, rm.getNANTI03());
			stmt.setString(78, rm.getNANTI04());
			stmt.setString(79, rm.getNANTI05());
			stmt.setString(80, rm.getNANTI06());
			stmt.setString(81, rm.getNANTI07());
			stmt.setString(82, rm.getNANTI08());
			stmt.setString(83, rm.getNANTI09());
			stmt.setString(84, rm.getNANTI10());
			stmt.setDouble(85, rm.getIANTI01());
			stmt.setDouble(86, rm.getIANTI02());
			stmt.setDouble(87, rm.getIANTI03());
			stmt.setDouble(88, rm.getIANTI04());
			stmt.setDouble(89, rm.getIANTI05());
			stmt.setDouble(90, rm.getIANTI06());
			stmt.setDouble(91, rm.getIANTI07());
			stmt.setDouble(92, rm.getIANTI08());
			stmt.setDouble(93, rm.getIANTI09());
			stmt.setDouble(94, rm.getIANTI10());
			stmt.setString(95, rm.getNCRED01());
			stmt.setString(96, rm.getNCRED02());
			stmt.setString(97, rm.getNCRED03());
			stmt.setString(98, rm.getNCRED04());
			stmt.setString(99, rm.getNCRED05());
			stmt.setString(100, rm.getNCRED06());
			stmt.setString(101, rm.getNCRED07());
			stmt.setString(102, rm.getNCRED08());
			stmt.setString(103, rm.getNCRED09());
			stmt.setString(104, rm.getNCRED10());
			stmt.setDouble(105, rm.getICRED01());
			stmt.setDouble(106, rm.getICRED02());
			stmt.setDouble(107, rm.getICRED03());
			stmt.setDouble(108, rm.getICRED04());
			stmt.setDouble(109, rm.getICRED05());
			stmt.setDouble(110, rm.getICRED06());
			stmt.setDouble(111, rm.getICRED07());
			stmt.setDouble(112, rm.getICRED08());
			stmt.setDouble(113, rm.getICRED09());
			stmt.setDouble(114, rm.getICRED10());
			stmt.setDouble(115, rm.getDESCUENTOS());
			stmt.setDouble(116, rm.getRETENCION());
			stmt.setDouble(117, rm.getUSADO());
			stmt.setDouble(118, rm.getNROGENDEUDA());
			stmt.setDouble(119, rm.getDIFDSPAGO());


			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	//baja
	public boolean bajaReciboPorNroRecibo(String nro) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM recibosm WHERE nrecibo = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nro);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	//modifica
	public boolean modificaActa(double acta, String comp, String tcomp, String codcli) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE recibosm SET a_cta = ? WHERE ncomp = ? AND tcomp = ? AND codcli = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, acta);
			stmt.setString(2, comp);
			stmt.setString(3, tcomp);
			stmt.setString(4, codcli);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace(); 
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	// consulta
	public ReciboM consultaRecibo(String nroRecibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		String sql = "SELECT * FROM recibosm WHERE nrecibo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nroRecibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rm;
	}
	
	public ReciboM consultaReciboPorTipo(String nroRecibo, String tRecibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		String sql = "SELECT * FROM recibosm WHERE nrecibo = ? AND trecibo = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nroRecibo);
			stmt.setString(2, tRecibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rm;
	}
	
	public ReciboM consultaReciboPorNroyPrefijo(String prefijo, String nroRecibo) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		String sql = "SELECT * FROM recibosm WHERE prefijo = ? AND nrecibo = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, prefijo);
			stmt.setString(2, nroRecibo);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return rm;
	}
	
	//listar
	public ArrayList<ReciboM> listarRecibosM() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		ArrayList<ReciboM> lista = new ArrayList<>();
		String sql = "SELECT * FROM RECIBOSM ORDER BY NRECIBO";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
					lista.add(rm);
					rm= null;
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { 
			cerrar(stmt, rs);
			System.gc();}
		return lista;
	}
	
	public ArrayList<ReciboM> listarRecibosMSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		ArrayList<ReciboM> lista = new ArrayList<>();
		String sql = "SELECT * FROM RECIBOSM WHERE CODCLI = ? ORDER BY NRECIBO";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
					lista.add(rm);
					rm= null;
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { 
			cerrar(stmt, rs);
			System.gc();}
		return lista;
	}
	
	public ArrayList<ReciboM> listarRecibosMPendientes(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		ArrayList<ReciboM> lista = new ArrayList<>();
		String sql = "SELECT * FROM RECIBOSM WHERE CODCLI = ? AND PREFIJO = '0006' AND CERRADA = 'N' AND LIQUIDA = 'N' ORDER BY NRECIBO";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					rm = new ReciboM();
					rm.setNRECIBO(rs.getString("NRECIBO"));
					rm.setCIA(rs.getString("CIA"));
					rm.setPREFIJO(rs.getString("PREFIJO"));
					rm.setFRECIBO(rs.getDate("FRECIBO"));
					rm.setTRECIBO(rs.getString("TRECIBO"));
					rm.setCODCLI(rs.getString("CODCLI"));
					rm.setNVIAJ(rs.getString("NVIAJ"));
					rm.setLIQUIDA(rs.getString("LIQUIDA"));
					rm.setEFECTIVO(rs.getDouble("EFECTIVO"));
					rm.setCHEQUE(rs.getDouble("CHEQUES"));
					rm.setA_CTA(rs.getDouble("A_CTA"));
					rm.setCERRADA(rs.getString("CERRADA"));
					rm.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					rm.setREFERENCIA(rs.getString("REFERENCIA"));
					rm.setTCOMP01(rs.getString("TCOMP01"));
					rm.setNFACT01(rs.getString("NFACT01"));
					rm.setTCOMP02(rs.getString("TCOMP02"));
					rm.setNFACT02(rs.getString("NFACT02"));
					rm.setTCOMP03(rs.getString("TCOMP03"));
					rm.setNFACT03(rs.getString("NFACT03"));
					rm.setTCOMP04(rs.getString("TCOMP04"));
					rm.setNFACT04(rs.getString("NFACT04"));
					rm.setTCOMP05(rs.getString("TCOMP05"));
					rm.setNFACT05(rs.getString("NFACT05"));
					rm.setTCOMP06(rs.getString("TCOMP06"));
					rm.setNFACT06(rs.getString("NFACT06"));
					rm.setTCOMP07(rs.getString("TCOMP07"));
					rm.setNFACT07(rs.getString("NFACT07"));
					rm.setTCOMP08(rs.getString("TCOMP08"));
					rm.setNFACT08(rs.getString("NFACT08"));
					rm.setTCOMP09(rs.getString("TCOMP09"));
					rm.setNFACT09(rs.getString("NFACT09"));
					rm.setTCOMP10(rs.getString("TCOMP10"));
					rm.setNFACT10(rs.getString("NFACT10"));
					rm.setTCOMP11(rs.getString("TCOMP11"));
					rm.setNFACT11(rs.getString("NFACT11"));
					rm.setTCOMP12(rs.getString("TCOMP12"));
					rm.setNFACT12(rs.getString("NFACT12"));
					rm.setTCOMP13(rs.getString("TCOMP13"));
					rm.setNFACT13(rs.getString("NFACT13"));
					rm.setTCOMP14(rs.getString("TCOMP14"));
					rm.setNFACT14(rs.getString("NFACT14"));
					rm.setTCOMP15(rs.getString("TCOMP15"));
					rm.setNFACT15(rs.getString("NFACT15"));
					rm.setTCOMP16(rs.getString("TCOMP16"));
					rm.setNFACT16(rs.getString("NFACT16"));
					rm.setTCOMP17(rs.getString("TCOMP17"));
					rm.setNFACT17(rs.getString("NFACT17"));
					rm.setTCOMP18(rs.getString("TCOMP18"));
					rm.setNFACT18(rs.getString("NFACT18"));
					rm.setTCOMP19(rs.getString("TCOMP19"));
					rm.setNFACT19(rs.getString("NFACT19"));
					rm.setTCOMP20(rs.getString("TCOMP20"));
					rm.setNFACT20(rs.getString("NFACT20"));
					rm.setIFACT01(rs.getDouble("IFACT01"));
					rm.setIFACT02(rs.getDouble("IFACT02"));
					rm.setIFACT03(rs.getDouble("IFACT03"));
					rm.setIFACT04(rs.getDouble("IFACT04"));
					rm.setIFACT05(rs.getDouble("IFACT05"));
					rm.setIFACT06(rs.getDouble("IFACT06"));
					rm.setIFACT07(rs.getDouble("IFACT07"));
					rm.setIFACT08(rs.getDouble("IFACT08"));
					rm.setIFACT09(rs.getDouble("IFACT09"));
					rm.setIFACT10(rs.getDouble("IFACT10"));
					rm.setIFACT11(rs.getDouble("IFACT11"));
					rm.setIFACT12(rs.getDouble("IFACT12"));
					rm.setIFACT13(rs.getDouble("IFACT13"));
					rm.setIFACT14(rs.getDouble("IFACT14"));
					rm.setIFACT15(rs.getDouble("IFACT15"));
					rm.setIFACT16(rs.getDouble("IFACT16"));
					rm.setIFACT17(rs.getDouble("IFACT17"));
					rm.setIFACT18(rs.getDouble("IFACT18"));
					rm.setIFACT19(rs.getDouble("IFACT19"));
					rm.setIFACT20(rs.getDouble("IFACT20"));
					rm.setNANTI01(rs.getString("NANTI01"));
					rm.setNANTI02(rs.getString("NANTI02"));
					rm.setNANTI03(rs.getString("NANTI03"));
					rm.setNANTI04(rs.getString("NANTI04"));
					rm.setNANTI05(rs.getString("NANTI05"));
					rm.setNANTI06(rs.getString("NANTI06"));
					rm.setNANTI07(rs.getString("NANTI07"));
					rm.setNANTI08(rs.getString("NANTI08"));
					rm.setNANTI09(rs.getString("NANTI09"));
					rm.setNANTI10(rs.getString("NANTI10"));
					rm.setIANTI01(rs.getDouble("IANTI01"));
					rm.setIANTI02(rs.getDouble("IANTI02"));
					rm.setIANTI03(rs.getDouble("IANTI03"));
					rm.setIANTI04(rs.getDouble("IANTI04"));
					rm.setIANTI05(rs.getDouble("IANTI05"));
					rm.setIANTI06(rs.getDouble("IANTI06"));
					rm.setIANTI07(rs.getDouble("IANTI07"));
					rm.setIANTI08(rs.getDouble("IANTI08"));
					rm.setIANTI09(rs.getDouble("IANTI09"));
					rm.setIANTI10(rs.getDouble("IANTI10"));
					rm.setNCRED01(rs.getString("NCRED01"));
					rm.setNCRED02(rs.getString("NCRED02"));
					rm.setNCRED03(rs.getString("NCRED03"));
					rm.setNCRED04(rs.getString("NCRED04"));
					rm.setNCRED05(rs.getString("NCRED05"));
					rm.setNCRED06(rs.getString("NCRED06"));
					rm.setNCRED07(rs.getString("NCRED07"));
					rm.setNCRED08(rs.getString("NCRED08"));
					rm.setNCRED09(rs.getString("NCRED09"));
					rm.setNCRED10(rs.getString("NCRED10"));
					rm.setICRED01(rs.getDouble("ICRED01"));
					rm.setICRED02(rs.getDouble("ICRED02"));
					rm.setICRED03(rs.getDouble("ICRED03"));
					rm.setICRED04(rs.getDouble("ICRED04"));
					rm.setICRED05(rs.getDouble("ICRED05"));
					rm.setICRED06(rs.getDouble("ICRED06"));
					rm.setICRED07(rs.getDouble("ICRED07"));
					rm.setICRED08(rs.getDouble("ICRED08"));
					rm.setICRED09(rs.getDouble("ICRED09"));
					rm.setICRED10(rs.getDouble("ICRED10"));
					rm.setDESCUENTOS(rs.getDouble("DESCUENTOS"));
					rm.setRETENCION(rs.getDouble("RETENCION"));
					rm.setUSADO(rs.getDouble("USADO"));
					rm.setNROGENDEUDA(rs.getDouble("NROGENDEUDA"));
					rm.setDIFDSPAGO(rs.getDouble("DIFDSPAGO"));
					lista.add(rm);
					rm= null;
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { 
			cerrar(stmt, rs);
			System.gc();}
		return lista;
	}
	
	public String ultimoID() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String r = "";
		String sql = "SELECT nrecibo FROM recibosm ORDER BY nrecibo DESC LIMIT 1";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					r = rs.getString("nrecibo");
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return r;
	}
	
}
