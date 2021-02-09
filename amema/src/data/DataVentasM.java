package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import entidades.VentasM;
import util.ApplicationException;

public class DataVentasM {

	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();

	/* CONSTRUCTOR */
	public DataVentasM() {
	}

	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private java.sql.Date cambiaFecha(java.util.Date fecha) throws ApplicationException {
		java.sql.Date fec = new java.sql.Date(fecha.getTime());
		return fec; 
	}

	// alta
	public boolean altaVentasM(VentasM v) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "INSERT INTO VENTASM (PREFIJO, NCOMP, TCOMP, LETRA, CIA, FMOV, NFACC, CODCLI, CVTO, TASA, PAGADO,"
				+ "ANULADO, PORAJUSTE, RECHAZADO, PORDESCUEN, CERRADA, REFERENCIA, DIRECTA, COMI_DIFE, FECVTO, PORDESCTO, "
				+ "PORBONIF, CCOND_1, CCOND_2, CCOND_3, CCOND_4, NVIAJ, LIQUIDA, LIQ_VIA, LIQ_TOT, NROPEDIDO, NROREMITO, "
				+ "NROPRESUP, OBSERVAC, DESPACHADO, FLETE, SUBTOTAL, A_CUENTA, A_CUENTAD, IMPPAG, IMPPAGD, IMPDESCTO, "
				+ "IMPBONIF, IMPIVA_1, IMPIVA_2, IMPIVA_3, TDOLAR, DOLAR, TEXTO, TEXLIB, VA_DTO, CUENTA, CPERS1, CPERS2,"
				+ "CPERS3, ANALISIS, NROMOVPLANIF, OBSERV, NROACTUALIZ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, v.getPREFIJO());
			stmt.setString(2, v.getNCOMP());
			stmt.setString(3, v.getTCOMP());
			stmt.setString(4, v.getLETRA());
			stmt.setString(5, v.getCIA());
			stmt.setDate(6, cambiaFecha(v.getFMOV()));
			stmt.setString(7, v.getNFACC());
			stmt.setString(8, v.getCODCLI());
			stmt.setString(9, v.getCVTO());
			stmt.setDouble(10, v.getTASA());
			stmt.setString(11, v.getPAGADO());
			stmt.setString(12, v.getANULADO());
			stmt.setString(13, v.getPORAJUSTE());
			stmt.setString(14, v.getRECHAZADO());
			stmt.setString(15, v.getPORDESCUEN());
			stmt.setString(16, v.getCERRADA());
			stmt.setString(17, v.getREFERENCIA());
			stmt.setString(18, v.getDIRECTA());
			stmt.setString(19, v.getCOMI_DIFE());
			stmt.setDate(20, cambiaFecha(v.getFECVTO()));
			stmt.setDouble(21, v.getPORDESCTO());
			stmt.setDouble(22, v.getPORBONIF());
			stmt.setString(23, v.getCCOND_1());
			stmt.setString(24, v.getCCOND_2());
			stmt.setString(25, v.getCCOND_3());
			stmt.setString(26, v.getCCOND_4());
			stmt.setString(27, v.getNVIAJ());
			stmt.setString(28, v.getLIQUIDA());
			stmt.setDouble(29, v.getLIQ_VIA());
			stmt.setDouble(30, v.getLIQ_TOT());
			stmt.setString(31, v.getNROPEDIDO());
			stmt.setString(32, v.getNROREMITO());
			stmt.setString(33, v.getNROPRESUP());
			stmt.setString(34, v.getOBSERVAC());
			stmt.setString(35, v.getDESPACHADO());
			stmt.setDouble(36, v.getFLETE());
			stmt.setDouble(37, v.getSUBTOTAL());
			stmt.setDouble(38, v.getA_CUENTA());
			stmt.setDouble(39, v.getA_CUENTAD());
			stmt.setDouble(40, v.getIMPPAG());
			stmt.setDouble(41, v.getIMPPAGD());
			stmt.setDouble(42, v.getIMPDESCTO());
			stmt.setDouble(43, v.getIMPBONIF());
			stmt.setDouble(44, v.getIMPIVA_1());
			stmt.setDouble(45, v.getIMPIVA_2());
			stmt.setDouble(46, v.getIMPIVA_3());
			stmt.setDouble(47, v.getTDOLAR());
			stmt.setDouble(48, v.getDOLAR());
			stmt.setDouble(49, v.getTEXTO());
			stmt.setString(50, v.getTEXTLIB());
			stmt.setString(51, v.getVA_DTO());
			stmt.setString(52, v.getCUENTA());
			stmt.setString(53, v.getCPERS1());
			stmt.setString(54, v.getCPERS2());
			stmt.setString(55, v.getCPERS3());
			stmt.setString(56, v.getANALISIS());
			stmt.setInt(57, v.getNROMOVPLANIF());
			stmt.setString(58, v.getOBSERV());
			stmt.setInt(59, v.getNROACTUALIZ());
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
		
		
		
	}

	// baja
	public boolean bajaVentasMPorMovimiento(int nro) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM VENTASM WHERE NROMOVPLANIF = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nro);
			
			if(stmt.executeUpdate() > 0) { return true; }
			else { return false; }
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false; 
		}
		finally { cerrar(stmt, null); }
	}
	
	
	// modifica
	public boolean modificaVentasMImporte(VentasM v) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE ventasm SET a_cuenta = ?, pagado = ? WHERE prefijo = ? AND ncomp = ? AND tcomp = ? AND "
				+ "letra = ? AND codcli = ?";		
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, v.getA_CUENTA());
			stmt.setString(2, v.getPAGADO());
			stmt.setString(3, v.getPREFIJO());
			stmt.setString(4, v.getNCOMP());
			stmt.setString(5, v.getTCOMP());
			stmt.setString(6, v.getLETRA());
			stmt.setString(7, v.getCODCLI());
			
			
			int i = stmt.executeUpdate();
			
			if(i > 0) { return true; }
			else { return false; }
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally { cerrar(stmt, null); }
	}
	
	public boolean modificoLetraPagado(String comp) throws ApplicationException {
		PreparedStatement stmt = null; 
		String sql = "UPDATE ventasm SET pagado = 'S' WHERE ncomp = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comp);
			
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
	public VentasM consultaVentaM(String comp) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		String sql = "SELECT * FROM VENTASM WHERE NCOMP = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comp);

			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return v;
	}
	
	public VentasM consultaVentaPorTipoComp(String comprobante, String tcomprobante) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		String sql = "SELECT * FROM VENTASM WHERE NCOMP = ? AND TCOMP = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprobante);
			stmt.setString(2, tcomprobante);

			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return v;
	}
	
	public double obtengoImporte(String comp) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String sql = "SELECT subtotal FROM ventasm WHERE ncomp = ?"; 
		double imp = 0.0;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comp);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					imp = rs.getDouble("subtotal");
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return imp;
	}
	
	public String obtengoCliente(String comp) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String codcli = "";
		String sql = "SELECT codcli FROM ventasm WHERE ncomp = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comp);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					codcli = rs.getString("codcli");
				}
			}
			
		}
		catch( SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return codcli; 
	}

	// lista

	public ArrayList<VentasM> listarVentasMPorNroMov(int mov) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT * FROM VENTASM WHERE NROMOVPLANIF = ? ORDER BY NCOMP";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mov);

			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
					lista.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return lista;
	}

	public ArrayList<VentasM> listarVentasM() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT * FROM VENTASM";

		try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
					lista.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return lista;
	}

	public ArrayList<VentasM> listarVentasMSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT * FROM VENTASM WHERE CODCLI = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
					lista.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return lista;
	}
	
	public ArrayList<VentasM> listarVentasMPendientes(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT * FROM VENTASM WHERE CODCLI = ? AND (TCOMP = '1' OR TCOMP ='4') AND PAGADO ='N' AND ANULADO = 'N'";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
					lista.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return lista;
	}
	
	public ArrayList<VentasM> listarVentasMporFecha(Date fecha) throws ApplicationException {
		PreparedStatement stmt =  null ;
		ResultSet rs =  null;
		VentasM v =  null;
		ArrayList < VentasM > lista =  new  ArrayList <> ();
		String sql =  "SELECT * FROM VENTASM WHERE FMOV =? AND PAGADO = 'N' AND ANULADO = 'N' ";

		try {
			stmt = conn.prepareStatement (sql);
			stmt.setDate ( 1 , cambiaFecha(fecha));
			rs = stmt.executeQuery();

			if (rs !=  null ) {
				while (rs.next ()) {
					v =  new  VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setCIA(rs.getString("CIA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setNFACC(rs.getString("NFACC"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCVTO(rs.getString("CVTO"));
					v.setTASA(rs.getDouble("TASA"));
					v.setPAGADO(rs.getString("PAGADO"));
					v.setANULADO(rs.getString("ANULADO"));
					v.setPORAJUSTE(rs.getString("PORAJUSTE"));
					v.setRECHAZADO(rs.getString("RECHAZADO"));
					v.setPORDESCUEN(rs.getString("PORDESCUEN"));
					v.setCERRADA(rs.getString("CERRADA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setDIRECTA(rs.getString("DIRECTA"));
					v.setCOMI_DIFE(rs.getString("COMI_DIFE"));
					v.setFECVTO(rs.getDate("FECVTO"));
					v.setPORDESCTO(rs.getDouble("PORDESCTO"));
					v.setPORBONIF(rs.getDouble("PORBONIF"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setCCOND_2(rs.getString("CCOND_2"));
					v.setCCOND_3(rs.getString("CCOND_3"));
					v.setCCOND_4(rs.getString("CCOND_4"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setLIQUIDA(rs.getString("LIQUIDA"));
					v.setLIQ_VIA(rs.getDouble("LIQ_VIA"));
					v.setLIQ_TOT(rs.getDouble("LIQ_TOT"));
					v.setNROPEDIDO(rs.getString("NROPEDIDO"));
					v.setNROREMITO(rs.getString("NROREMITO"));
					v.setNROPRESUP(rs.getString("NROPRESUP"));
					v.setOBSERVAC(rs.getString("OBSERVAC"));
					v.setDESPACHADO(rs.getString("DESPACHADO"));
					v.setFLETE(rs.getDouble("FLETE"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setA_CUENTAD(rs.getDouble("A_CUENTAD"));
					v.setIMPPAG(rs.getDouble("IMPPAG"));
					v.setIMPPAGD(rs.getDouble("IMPPAGD"));
					v.setIMPDESCTO(rs.getDouble("IMPDESCTO"));
					v.setIMPBONIF(rs.getDouble("IMPBONIF"));
					v.setIMPIVA_1(rs.getDouble("IMPIVA_1"));
					v.setIMPIVA_2(rs.getDouble("IMPIVA_2"));
					v.setIMPIVA_3(rs.getDouble("IMPIVA_3"));
					v.setTDOLAR(rs.getDouble("TDOLAR"));
					v.setDOLAR(rs.getDouble("DOLAR"));
					v.setTEXTO(rs.getDouble("TEXTO"));
					v.setTEXTLIB(rs.getString("TEXLIB"));
					v.setVA_DTO(rs.getString("VA_DTO"));
					v.setCUENTA(rs.getString("CUENTA"));
					v.setCPERS1(rs.getString("CPERS1"));
					v.setCPERS2(rs.getString("CPERS2"));
					v.setCPERS3(rs.getString("CPERS3"));
					v.setANALISIS(rs.getString("ANALISIS"));
					v.setNROMOVPLANIF(rs.getInt("NROMOVPLANIF"));
					v.setOBSERV(rs.getString("OBSERV"));
					v.setNROACTUALIZ(rs.getInt("NROACTUALIZ"));
					lista.add(v);
				}
			}
		}catch ( SQLException e) { e.printStackTrace (); } 
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<VentasM> listarVentasParatablaAux(String convenio, Date fecha) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT codcli, ncomp, fmov, subtotal, a_cuenta, tcomp, letra, prefijo, nviaj, referencia, ccond_1, analisis  FROM ventasm"
				+ " WHERE (tcomp = '1' OR tcomp = '4') AND pagado <> 'S' AND fmov <= ? AND ccond_1 = ? AND anulado <> 'S' AND "
				+ "subtotal > 0 AND (subtotal > a_cuenta OR a_cuenta IS NULL) ORDER BY codcli, analisis desc"; 

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, cambiaFecha(fecha));
			stmt.setString(2, convenio);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					v = new VentasM();
					v.setPREFIJO(rs.getString("PREFIJO"));
					v.setNCOMP(rs.getString("NCOMP"));
					v.setTCOMP(rs.getString("TCOMP"));
					v.setLETRA(rs.getString("LETRA"));
					v.setFMOV(rs.getDate("FMOV"));
					v.setCODCLI(rs.getString("CODCLI"));
					v.setCCOND_1(rs.getString("CCOND_1"));
					v.setNVIAJ(rs.getString("NVIAJ"));
					v.setSUBTOTAL(rs.getDouble("SUBTOTAL"));
					v.setA_CUENTA(rs.getDouble("A_CUENTA"));
					v.setREFERENCIA(rs.getString("REFERENCIA"));
					v.setANALISIS(rs.getString("ANALISIS"));
					lista.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return lista;
	}
	
	public ArrayList<VentasM> listarDeudasPorSocio(String codcli) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<VentasM> lista = new ArrayList<VentasM>();
		VentasM v = null; 
		String sql = "SELECT ncomp, observ, fmov, subtotal, a_cuenta FROM ventasm WHERE (tcomp = '1' OR tcomp = '4') AND pagado = 'N'"
				+ " AND anulado = 'N' AND codcli = ? AND subtotal > 0 AND (subtotal > a_cuenta OR a_cuenta IS NULL) ORDER BY fmov"; 
		
		try {
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, codcli);
			
			rs = stmt.executeQuery(); 
			if(rs != null) {
				while(rs.next()) {
					v = new VentasM(); 
					v.setNCOMP(rs.getString("ncomp"));
					v.setOBSERV(rs.getString("observ"));
					v.setFMOV(rs.getDate("fmov"));
					v.setSUBTOTAL(rs.getDouble("subtotal"));
					v.setA_CUENTA(rs.getDouble("a_cuenta"));
					lista.add(v);
				}
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista; 
	}
	
	public ArrayList<VentasM> listarAnalisisDeuda() throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<VentasM> lista = new ArrayList<>();
		VentasM v = null; 
		String sql = "SELECT codcli, ncomp, fmov, subtotal, a_cuenta, tcomp, letra, prefijo, referencia, texlib FROM ventasm "
				+ "WHERE (tcomp = '1' OR tcomp = '4') AND pagado = 'N' AND anulado = 'N' AND subtotal > 0 "
				+ "AND (subtotal > a_cuenta OR a_cuenta IS NULL)";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					v = new VentasM();
					v.setCODCLI(rs.getString("codcli"));
					v.setNCOMP(rs.getString("ncomp"));
					v.setFMOV(rs.getDate("fmov"));
					v.setSUBTOTAL(rs.getDouble("subtotal"));
					v.setA_CUENTA(rs.getDouble("a_cuenta"));
					v.setTCOMP(rs.getString("tcomp"));
					v.setLETRA(rs.getString("letra"));
					v.setPREFIJO(rs.getString("prefijo"));
					v.setREFERENCIA(rs.getString("referencia"));
					v.setTEXTLIB(rs.getString("texlib"));
					lista.add(v);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public ArrayList<VentasM> listarAnalisisDeudaConvenio(String convenio) throws ApplicationException {
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		ArrayList<VentasM> lista = new ArrayList<>();
		VentasM v = null; 
		String sql = "SELECT codcli, ncomp, fmov, subtotal, a_cuenta, tcomp, letra, prefijo, referencia, texlib FROM ventasm "
				+ "WHERE (tcomp = '1' OR tcomp = '4') AND pagado = 'N' AND anulado = 'N' AND subtotal > 0 "
				+ "AND (subtotal > a_cuenta OR a_cuenta IS NULL) AND ccond_1 = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setNString(1, convenio);
			
			rs = stmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					v = new VentasM();
					v.setCODCLI(rs.getString("codcli"));
					v.setNCOMP(rs.getString("ncomp"));
					v.setFMOV(rs.getDate("fmov"));
					v.setSUBTOTAL(rs.getDouble("subtotal"));
					v.setA_CUENTA(rs.getDouble("a_cuenta"));
					v.setTCOMP(rs.getString("tcomp"));
					v.setLETRA(rs.getString("letra"));
					v.setPREFIJO(rs.getString("prefijo"));
					v.setREFERENCIA(rs.getString("referencia"));
					v.setTEXTLIB(rs.getString("texlib"));
					lista.add(v);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally { cerrar(stmt, rs); }
		return lista;
	}
	
	public String ultimoID() throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT NCOMP FROM VENTASM ORDER BY NCOMP DESC LIMIT 1";
		String r = "";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					r = rs.getString("NCOMP");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar(stmt, rs);
		}
		return r;
	}
	
}
