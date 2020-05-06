package data;

import java.sql.*;
import util.ApplicationException;



public class Conector {
	
	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	private static String db = "C:/AMEMA/prueba2.mdb";
	private static String user = "sa";
	private static String pass = "meriadoc";
	private Connection conexion;
	private int cantConn = 0; //cantidad de conexiones a la BBDD
	
	
	public Conector(){
		try{
			Class.forName(driver);
			conexion = DriverManager.getConnection("jdbc:ucanaccess://"+db+";jackessOpener=util.CryptCodecOpener",user,pass);
		}
		catch(ClassNotFoundException | SQLException e){
			System.out.println("--------");
			System.out.println("FALLO LA CONEXION !!");
			System.out.println("--------");
			e.printStackTrace();
		}
		
	}
	
	public Connection abrirConn(){
		try {
			if( conexion == null || conexion.isClosed()){
				cantConn++;
			}
		} catch (SQLException e) {
			new ApplicationException("Error al conectar a la BBDD",e);
		}
		return conexion;
	}
	
	public void cerrarConn() throws ApplicationException {
		try {
			cantConn--;
			if(cantConn == 0) conexion.close();
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar la conexión",e);
		}
		
	}
	
}
