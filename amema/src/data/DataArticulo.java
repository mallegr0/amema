package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

import entidades.Articulo;
import util.ApplicationException;

public class DataArticulo {
	
	/* VARIABLES */
	Connection conn = PoolConection.getInstance().abrirConexion();
	
	
	/* CONSTRUCTOR */
	public DataArticulo() {}
	
	
	/* METODOS */
	public void cerrar(PreparedStatement stmt, ResultSet rs) throws ApplicationException {
		try {
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			PoolConection.getInstance().cerrarConexion(conn);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	
	// metodos alta
	// metodos baja
	// metodos modificacion
	// metodos consultar
	public Articulo ConsultaArticulo(String cod, String scod, String art) throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Articulo a = null; 
		String sql ="SELECT * FROM ARTICULO WHERE CGRUPO = ? AND CSUBF = ? AND NROART = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1 , cod);
			stmt.setString(2 , scod);
			stmt.setString(3 , art);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new Articulo();
					a.setCGRUPO(rs.getString("CGRUPO"));
					a.setCSUBF(rs.getString("CSUBF"));
					a.setNROART(rs.getString("NROART"));
					a.setDESART(rs.getString("DESART"));
					a.setUNIDAD(rs.getDouble("UNIDAD"));
					a.setENVASE(rs.getString("ENVASE"));
					a.setCPROV(rs.getString("CPROV"));
					a.setANALISIS(rs.getString("ANALISIS"));
					a.setTIVA(rs.getString("TIVA"));
					a.setTASA(rs.getDouble("TASA"));
					a.setPCOMPRA(rs.getDouble("PCOMPRA"));
					a.setPVENTA_1(rs.getDouble("PVENTA_1"));
					a.setPVENTA_2(rs.getDouble("PVENTA_2"));
					a.setPVENTA_3(rs.getDouble("PVENTA_3"));
					a.setPVENTA_4(rs.getDouble("PVENTA_4"));
					a.setFECAUM((Date) rs.getDate("FECAUM"));
					a.setSTOCK_1(rs.getDouble("STOCK_1"));
					a.setSTOCK_2(rs.getDouble("STOCK_2"));
					a.setMINIMO_1(rs.getDouble("MINIMO_1"));
					a.setMINIMO_2(rs.getDouble("MINIMO_2"));
					a.setVTAPROM_1(rs.getDouble("VTAPROM_1"));
					a.setHORI_1(rs.getDouble("HORI1"));
					a.setHORI_2(rs.getDouble("HORI2"));
					a.setPE(rs.getDouble("PE"));
					a.setVTAPROM_2(rs.getDouble("VTAPROM_2"));
					a.setUNIVTA(rs.getString("UNIVTA"));
					a.setMARCA(rs.getString("MARCA"));
					a.setPBONIF(rs.getDouble("PBONIF"));
					a.setCAJA(rs.getString("CAJA"));
					a.setPPPC(rs.getDouble("PPPC"));
					a.setDESDE_PPPC((Date) rs.getDate("DESDE_PPPC"));
					a.setHASTA_PPPC((Date) rs.getDate("HASTA_PPPC"));
					a.setPPPV(rs.getDouble("PPPV"));
					a.setDESDE_PPPV((Date) rs.getDate("DESDE_PPPV"));
					a.setHASTA_PPPV((Date) rs.getDate("HASTA_PPPV"));
					a.setUBICACION(rs.getString("UBICACION"));
					a.setADUANA(rs.getString("ADUANA"));
					a.setDESPACHO1(rs.getString("DESPACHO1"));
					a.setDESPACHO2(rs.getString("DESPACHO2"));
					a.setDESPACHO3(rs.getString("DESPACHO3"));
					a.setFDESPACHO((Date) rs.getDate("FDESPACHO"));
					a.setBAK(rs.getDouble("BAK"));
					a.setPFISCAL(rs.getDouble("PFISCAL"));
					a.setULT_CPRA((Date) rs.getDate("ULT_CPRA"));
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {cerrar(stmt, rs); }
		return a;
	}
	
	// metodos listar 
	public ArrayList<Articulo> listarArticulos() throws ApplicationException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Articulo a = null; 
		ArrayList<Articulo> lista = new ArrayList<>();
		String sql ="SELECT * FROM ARTICULO ORDER BY DESART";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					a = new Articulo();
					a.setCGRUPO(rs.getString("CGRUPO"));
					a.setCSUBF(rs.getString("CSUBF"));
					a.setNROART(rs.getString("NROART"));
					a.setDESART(rs.getString("DESART"));
					a.setUNIDAD(rs.getDouble("UNIDAD"));
					a.setENVASE(rs.getString("ENVASE"));
					a.setCPROV(rs.getString("CPROV"));
					a.setANALISIS(rs.getString("ANALISIS"));
					a.setTIVA(rs.getString("TIVA"));
					a.setTASA(rs.getDouble("TASA"));
					a.setPCOMPRA(rs.getDouble("PCOMPRA"));
					a.setPVENTA_1(rs.getDouble("PVENTA_1"));
					a.setPVENTA_2(rs.getDouble("PVENTA_2"));
					a.setPVENTA_3(rs.getDouble("PVENTA_3"));
					a.setPVENTA_4(rs.getDouble("PVENTA_4"));
					a.setFECAUM((Date) rs.getDate("FECAUM"));
					a.setSTOCK_1(rs.getDouble("STOCK_1"));
					a.setSTOCK_2(rs.getDouble("STOCK_2"));
					a.setMINIMO_1(rs.getDouble("MINIMO_1"));
					a.setMINIMO_2(rs.getDouble("MINIMO_2"));
					a.setVTAPROM_1(rs.getDouble("VTAPROM_1"));
					a.setHORI_1(rs.getDouble("HORI1"));
					a.setHORI_2(rs.getDouble("HORI2"));
					a.setPE(rs.getDouble("PE"));
					a.setVTAPROM_2(rs.getDouble("VTAPROM_2"));
					a.setUNIVTA(rs.getString("UNIVTA"));
					a.setMARCA(rs.getString("MARCA"));
					a.setPBONIF(rs.getDouble("PBONIF"));
					a.setCAJA(rs.getString("CAJA"));
					a.setPPPC(rs.getDouble("PPPC"));
					a.setDESDE_PPPC((Date) rs.getDate("DESDE_PPPC"));
					a.setHASTA_PPPC((Date) rs.getDate("HASTA_PPPC"));
					a.setPPPV(rs.getDouble("PPPV"));
					a.setDESDE_PPPV((Date) rs.getDate("DESDE_PPPV"));
					a.setHASTA_PPPV((Date) rs.getDate("HASTA_PPPV"));
					a.setUBICACION(rs.getString("UBICACION"));
					a.setADUANA(rs.getString("ADUANA"));
					a.setDESPACHO1(rs.getString("DESPACHO1"));
					a.setDESPACHO2(rs.getString("DESPACHO2"));
					a.setDESPACHO3(rs.getString("DESPACHO3"));
					a.setFDESPACHO((Date) rs.getDate("FDESPACHO"));
					a.setBAK(rs.getDouble("BAK"));
					a.setPFISCAL(rs.getDouble("PFISCAL"));
					a.setULT_CPRA((Date) rs.getDate("ULT_CPRA"));
					lista.add(a);
				}
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		finally {cerrar(stmt, rs); }
		return lista;
	}
	
	
}
