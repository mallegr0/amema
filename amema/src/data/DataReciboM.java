package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ReciboM;
import util.ApplicationException;

public class DataReciboM {
	
	/* VARIABLES */
	//Conector conn = new Conector();
		ConectorMySQL conn = new ConectorMySQL();
	
	/* CONSTRUCTOR */
	public DataReciboM() {}
	
	/* METODOS */
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	//alta
	
	//baja
	
	//modifica
	
	// consulta
	
	//listar
	public ArrayList<ReciboM> listarRecibosM() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ReciboM rm = null; 
		ArrayList<ReciboM> lista = new ArrayList<>();
		String sql = "SELECT * FROM RECIBOSM ORDER BY NRECIBO";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
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
			stmt = conn.abrirConn().prepareStatement(sql);
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
	
}
