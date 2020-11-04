package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorMySQL {
	
	private static String server = "jdbc:mysql://localhost/"; //Nombre del servidor
	private static String user = "root"; //Usuario de BBDD en el servidor
	private static String pass = "root"; //Contraseña de BBDD en el servidor
	private static String driver = "com.mysql.jdbc.Driver"; //Driver de conexion JDBC
	private static String db = "amema"; //Base de datos que quiero crear
	private Connection conexion;
	private int cantConn = 0; //cantidad de conexiones a la BBDD
	
	
	public ConectorMySQL(){
		try{
			Class.forName(driver);
			conexion = DriverManager.getConnection(server+db, user, pass);
		}
		catch(ClassNotFoundException | SQLException e){
			System.out.println("--------");
			System.out.println("FALLO LA CONEXION !!");
			System.out.println("--------");
			e.printStackTrace();
		}
		
	}
	
	public Connection abrirConn(){
		System.out.println("abre la conexcion");
		try {
			if( conexion == null || conexion.isClosed()){
				cantConn++;
			}
		} catch (SQLException e) {
			new Exception("fallo la creacion de la conexion", e);
		}
		return conexion;
	}
	
	public void cerrarConn(){
		try {
			cantConn--;
			if(cantConn == 0) conexion.close();
		} catch (SQLException e) {
			new Exception("fallo el cierre de la conexion", e);
		}
		
	}

}
