package data;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class PoolConection {
	
	//Declaro las variables que uso para conectarme a la BBDD de MySQL. En este caso AMEMA
		private final String DRIVER = "com.mysql.cj.jdbc.Driver";
		private final String DB = "amema";
		private final String URL = "jdbc:mysql://localhost:";
		private final int PORT = 3306;
		private final String USR = "root";
		private final String PSW = "root";
		
		private static PoolConection dataSource; //Creo la instacia de esta clase para ser invocada despues. Es la unica que voy a invocar
		private BasicDataSource basicDataSource = null; //Creo la variable con la que me voy a conectar
		
		//Declaro el metodo que crea el pool de conexiones
		
		private PoolConection() {
			//Declaro las variables que uso para el pool
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(DRIVER);
			basicDataSource.setUsername(USR);
			basicDataSource.setPassword(PSW);
			basicDataSource.setUrl(URL+PORT+"/"+DB+"?serverTimezone=UTC");
			
			//Declaro las variables que quiero modificar para el pool
			basicDataSource.setMinIdle(5); // Conexion minimas inactivas.
			basicDataSource.setMaxIdle(20); // Conexiones maximas inactivas.
			basicDataSource.setMaxTotal(50); // total de conexiones 
			basicDataSource.setMaxWaitMillis(10000); //Tiempo de espera para resolver la solicitud. En este caso se espera 10 segundos.
					
		}
		
		public static PoolConection getInstance() {
			if(dataSource == null) {
				dataSource = new PoolConection();
				return dataSource;
			}
			else { return dataSource; }
		}
		
		public Connection abrirConexion() {
			Connection conn = null;
			try { conn = this.basicDataSource.getConnection(); }
			catch (SQLException e) { System.out.println(e.getMessage());}
			return conn;
		}
		
		public void cerrarConexion(Connection conn) throws SQLException {
			conn.close();
		}

}
