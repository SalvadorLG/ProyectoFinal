package pack.puntoVenta.model;

public class Usuario {
	private int id_usuario;
	private String nombreUsuario;
	private String password;
	private String apellidoUsuario;
	private String correo;
	private String tipoUsuario;
	
	public Usuario(int id_usuario,String nombreUsuario,String password, String apellidoUsuario,String correo,String tipoUsuario) {
		this.id_usuario = id_usuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.apellidoUsuario = apellidoUsuario;
		this.correo = correo;
		this.tipoUsuario = tipoUsuario;
		
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}
	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
	
}
