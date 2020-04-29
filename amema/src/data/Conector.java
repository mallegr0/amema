package data;

import java.sql.*;
import util.ApplicationException;
import util.CryptCodecOpener;


public class Conector {
	
	/*private static String server = "jdbc:mysql://localhost/amemaweb?serverTimezone=UTC"; //Nombre del servidor
	private static String user = "root"; //Usuario de BBDD en el servidor
	private static String pass = "root"; //Contraseña de BBDD en el servidor
	private static String driver = "com.mysql.cj.jdbc.Driver"; //Driver de conexion JDBC*/
	
	
	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	private static String server = "jdbc:ucanaccess://C:/AMEMA/AMEMA.mdb;jackessOpener=CryptCodecOpener";
	private static String user = "";
	private static String pass = "Meriadoc";
	
	private Connection conexion;
	private int cantConn = 0; //cantidad de conexiones a la BBDD
	
	
	public Conector(){
		try{
			Class.forName(driver);
			conexion = DriverManager.getConnection(server,"sa","meriadoc");
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
