package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.VentasM;
import util.ApplicationException;

public class DataVentasM {
	
	/* VARIABLES */
	Conector conn = new Conector();
	
	/* CONSTRUCTOR */
	public DataVentasM() {}
	
	
	/* METODOS */
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	
	//alta
	//baja
	//modifica
	//consulta
	public VentasM consultaVentaM(String comp) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		VentasM v = null;
		String sql = "SELECT * FROM VENTASM WHERE NCOMP = ?";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setString(1, comp);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
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
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return v;
	}
	
	//lista
	
	public ArrayList<VentasM> listarVentasMPorNroMov(int mov) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		VentasM v = null;
		ArrayList<VentasM> lista = new ArrayList<>();
		String sql = "SELECT * FROM VENTASM WHERE NROMOVPLANIF = ? ORDER BY NCOMP";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			stmt.setInt(1, mov);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
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
		}
		catch(SQLException e) { e.printStackTrace();}
		finally { cerrar(stmt, rs); }
		return lista;
	}

}
