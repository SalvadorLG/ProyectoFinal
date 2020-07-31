package pack.puntoVenta.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
	private static final String URL = "jdbc:sqlserver://localhost;databaseName=PuntoVenta";
	private static final String USER = "salva";
	private static final String PASSWORD = "123";
	private Connection con;
	
	public  Conection (){
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Base de datos conectada");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Connection getCon() {
		return con;
	}
	

}
