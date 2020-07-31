package pack.puntoVenta;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexion {

	private static final String URL = "jdbc:sqlserver://localhost;databaseName=PuntoVenta;";
	private static final String USER = "salva";
	private static final String PASSWORD = "123";
	
	public static Connection getConection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No se pudo establecer la conexion "+e);
		}
		return con;
	}

	
	public static void main(String[] args) {
		/*
		try {
			Connection con = null;
			con = getConection();
			
			PreparedStatement ps;
			ResultSet res;
			
			ps = con.prepareStatement("SELECT * FROM Productos WHERE id =" + id);
			res = ps.executeQuery();
			while (res.next()) {
				System.out.println(res.getString("marca_producto"));
			Producto producto = new Producto(res.getString("nombre"));
			recibo.addProducto(producto);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
	}
}
