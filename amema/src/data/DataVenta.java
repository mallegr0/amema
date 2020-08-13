package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import entidades.Venta;
import util.ApplicationException;

public class DataVenta {
	
	/* VARIABLES */
	Conector conn = new Conector();
	
	/* CONSTRUCTOR */
	public DataVenta() {}
	
	private void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			conn.cerrarConn();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
	// metodo alta
	
	// metodo baja
	
	//metodo modificacion
	
	//metodo consultar
	
	//metodo listar 
	
	public ArrayList<Venta> listarVentaPorSocio(String cod) throws ApplicationException {
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		ArrayList<Venta> lista = new ArrayList<>();
		Venta v = null;
		String sql = "SELECT * FROM VENTAS WHERE CODCLI = ? ORDER BY NROMOV";
		
		try {
			stmt = conn.abrirConn().prepareStatement(sql);
			
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


}
