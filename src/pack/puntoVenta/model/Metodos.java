package pack.puntoVenta.model;

import java.sql.Connection;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Metodos extends Conection{
	public boolean registrar(Usuario usuario) {
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "INSERT INTO Users (id_Users,Nombre,Apellido,[E-mail],Password,Type_User) VALUES(?,?,?,?,?,?)";
		try {
			ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getApellidoUsuario());
			ps.setString(3,usuario.getCorreo());
			ps.setString(4, usuario.getPassword());
			ps.setString(5, usuario.getTipoUsuario());
			ps.setInt(6,usuario.getId_usuario());
			
			
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return false;
	}
	public boolean eliminarUsuarios(Usuario usuario) {
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "DELETE FROM Users WHERE id_usuario=?";
		try {
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, usuario.getId_usuario());
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Usuario eliminado");
			return true;
		}catch(Exception e) {
			System.out.println("Error: "+e);
			
		}
		return false;
	}
	public boolean modificarUsuario(Usuario usuario) {
		PreparedStatement ps = null;
		Connection conexion = getCon();
		String sql = "UPDATE Users SET Nombre=?,Apellido=?,[E-mail]=?,Password=?,Typer_User=? WHERE id_Users=?";
		try {
			ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getApellidoUsuario());
			ps.setString(3,usuario.getCorreo());
			ps.setString(4, usuario.getPassword());
			ps.setString(5, usuario.getTipoUsuario());
			ps.setInt(6, usuario.getId_usuario());
			
			
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return false;
	}
	public boolean registrarProductos(Producto producto) {
	
		return false;		
	}
}
